/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/12/20 6:10 AM
 * Last modified 11/12/20 6:10 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI

import android.app.ActivityOptions
import android.app.PictureInPictureParams
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.AbanMagazinePhoneApplication
import com.abanabsalan.aban.magazine.AccountManager.UserInformation
import com.abanabsalan.aban.magazine.AccountManager.UserInformationIO
import com.abanabsalan.aban.magazine.AccountManager.UserSignIn
import com.abanabsalan.aban.magazine.Ads.AdsConfiguration
import com.abanabsalan.aban.magazine.BuildConfig
import com.abanabsalan.aban.magazine.HomePageConfigurations.DataHolder.HomePageLiveData
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.*
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.InstagramStoryHighlights.InstagramStoryHighlightsAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.NewestPosts.NewestPostsAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.PrimaryCategory.PrimaryCategoryAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.ProductShowcase.ProductShowcaseAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SecondaryCategory.SecondaryCategoryAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SpecificCategory.SpecificCategoryAdapter
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Utils.FavoriteIt
import com.abanabsalan.aban.magazine.PostsConfigurations.OfflineDatabase.Firestore.FirestoreConfiguration
import com.abanabsalan.aban.magazine.Preferences.PopupPreferencesController
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Utils.PageCounter
import com.abanabsalan.aban.magazine.TagsConfigurations.Utils.TagsIO
import com.abanabsalan.aban.magazine.Utils.IndexingConfiguration.ApplicationDataIndexing
import com.abanabsalan.aban.magazine.Utils.Network.NetworkCheckpoint
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListener
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListenerInterface
import com.abanabsalan.aban.magazine.Utils.PopupShortcuts.PopupShortcutsCreator
import com.abanabsalan.aban.magazine.Utils.UI.Display.columnCount
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.Utils.UI.Theme.toggleLightDarkThemeHomePage
import com.abanabsalan.aban.magazine.databinding.HomePageViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.geekstools.supershortcuts.PRO.Utils.UI.Gesture.GestureConstants
import net.geekstools.supershortcuts.PRO.Utils.UI.Gesture.GestureListenerConstants
import net.geekstools.supershortcuts.PRO.Utils.UI.Gesture.GestureListenerInterface
import net.geekstools.supershortcuts.PRO.Utils.UI.Gesture.SwipeGestureListener
import javax.inject.Inject

class HomePage : AppCompatActivity(), GestureListenerInterface, NetworkConnectionListenerInterface {

    val firestoreConfiguration: FirestoreConfiguration by lazy {
        FirestoreConfiguration(applicationContext)
    }

    val firestoreDatabase: FirebaseFirestore by lazy {
        firestoreConfiguration.initialize()
    }

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    val networkCheckpoint: NetworkCheckpoint by lazy {
        NetworkCheckpoint(applicationContext)
    }

    val homePageLiveData: HomePageLiveData by lazy {
        ViewModelProvider(this@HomePage).get(HomePageLiveData::class.java)
    }

    val favoriteIt: FavoriteIt by lazy {
        FavoriteIt(applicationContext)
    }

    val tagsIO: TagsIO by lazy {
        TagsIO(applicationContext)
    }

    val adsConfiguration: AdsConfiguration by lazy {
        AdsConfiguration(this@HomePage)
    }

    private val swipeGestureListener: SwipeGestureListener by lazy {
        SwipeGestureListener(applicationContext, this@HomePage)
    }

    var scrollViewAtTop: Boolean = false
    var updateDelay: Boolean = true

    lateinit var userSignIn: UserSignIn

    val userInformationIO: UserInformationIO by lazy {
        UserInformationIO(applicationContext)
    }

    val firebaseAuth = Firebase.auth

    val applicationDataIndexing: ApplicationDataIndexing = ApplicationDataIndexing()

    @Inject
    lateinit var networkConnectionListener: NetworkConnectionListener

    lateinit var homePageViewBinding: HomePageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePageViewBinding = HomePageViewBinding.inflate(layoutInflater)
        setContentView(homePageViewBinding.root)

        setupTheme()

        if (BuildConfig.VERSION_NAME.contains("BETA")) {
            FirebaseMessaging.getInstance()
                .subscribeToTopic("BETA")
                .addOnSuccessListener {

                }
        }

        FirebaseMessaging.getInstance()
            .subscribeToTopic("NewestPosts")
            .addOnSuccessListener {

            }

        adsConfiguration.initialize()

        PopupPreferencesController(this@HomePage, homePageViewBinding.preferencePopupInclude)
            .initializeForHomePage()

        (application as AbanMagazinePhoneApplication)
            .dependencyGraph
            .subDependencyGraph()
            .create(this@HomePage, homePageViewBinding.rootView)
            .inject(this@HomePage)

        networkConnectionListener.networkConnectionListenerInterface = this@HomePage

        homePageViewBinding.primaryCategoriesRecyclerView.layoutManager = GridLayoutManager(applicationContext, columnCount(applicationContext, 115), RecyclerView.VERTICAL, false)

        homePageViewBinding.secondaryCategoriesRecyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)

        val specificCategoryLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
        homePageViewBinding.featuredPostsRecyclerView.layoutManager = specificCategoryLinearLayoutManager

        homePageViewBinding.newestPostsRecyclerView.layoutManager = GridLayoutManager(applicationContext, columnCount(applicationContext, 193), RecyclerView.VERTICAL, false)

        homePageViewBinding.productShowcaseRecyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)

        homePageViewBinding.instagramStoryHighlightsRecyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)

        val primaryCategoryAdapter: PrimaryCategoryAdapter = PrimaryCategoryAdapter(this@HomePage, overallTheme)

        val secondaryCategoryAdapter: SecondaryCategoryAdapter = SecondaryCategoryAdapter(this@HomePage, overallTheme)

        val specificCategoryAdapter: SpecificCategoryAdapter = SpecificCategoryAdapter(this@HomePage, overallTheme)

        val newestPostsAdapter: NewestPostsAdapter = NewestPostsAdapter(this@HomePage, overallTheme)

        val recommendedPostsAdapter: NewestPostsAdapter = NewestPostsAdapter(this@HomePage, overallTheme)

        val productShowcaseAdapter: ProductShowcaseAdapter = ProductShowcaseAdapter(this@HomePage, overallTheme)

        val instagramStoryHighlightsAdapter: InstagramStoryHighlightsAdapter = InstagramStoryHighlightsAdapter(this@HomePage, overallTheme)

        homePageViewBinding.root.post {

            setupUserInterface()

            homePageViewBinding.officialLogo.setOnClickListener {

                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.websiteLink))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

            }

            /* Load Featured Posts */
            homePageLiveData.specificCategoryLiveItemData.observe(this@HomePage, Observer {

                if (!it.isNullOrEmpty()) {

                    homePageViewBinding.featuredPostsTextView.visibility = View.VISIBLE

                    if (specificCategoryAdapter.specificCategoryPostsItemData.isEmpty()) {

                        specificCategoryAdapter.specificCategoryPostsItemData.addAll(it)

                        homePageViewBinding.featuredPostsRecyclerView.adapter = specificCategoryAdapter

                        Handler(Looper.getMainLooper()).postDelayed({
                            PageCounter.PageNumberToLoad = PageCounter.PageNumberToLoad.plus(1)

                            startFeaturedPostCategoryRetrieval(applicationContext, homePageViewBinding, homePageLiveData, PageCounter.PageNumberToLoad)
                        }, 777)

                        homePageViewBinding.featuredPostsLoadingView.visibility = View.INVISIBLE

                    } else {

                        val previousDataCount: Int = specificCategoryAdapter.specificCategoryPostsItemData.size

                        specificCategoryAdapter.specificCategoryPostsItemData.addAll(it)

                        specificCategoryAdapter.notifyItemRangeInserted(previousDataCount, (specificCategoryAdapter.specificCategoryPostsItemData.size - 1))

                        homePageViewBinding.featuredPostsLoadingView.visibility = View.INVISIBLE

                    }

                } else {

                    if (specificCategoryAdapter.specificCategoryPostsItemData.isNullOrEmpty()) {

                        homePageViewBinding.featuredPostsTextView.visibility = View.GONE
                        homePageViewBinding.featuredPostsRecyclerView.visibility = View.GONE

                    }

                    homePageViewBinding.featuredPostsLoadingView.visibility = View.GONE

                    Toast.makeText(applicationContext, getString(R.string.noMoreContent), Toast.LENGTH_LONG).show()

                }

                homePageLiveData.controlLoadingView.postValue(false)

            })

            /* Load Newest Posts */
            homePageLiveData.newestPostsLiveItemData.observe(this@HomePage, Observer {

                if (it.isNotEmpty()) {

                    homePageViewBinding.newestPostsTextView.visibility = View.VISIBLE

                    newestPostsAdapter.newestPostsItemData.clear()
                    newestPostsAdapter.newestPostsItemData.addAll(it)

                    homePageViewBinding.newestPostsRecyclerView.adapter = newestPostsAdapter

                } else {

                    homePageViewBinding.newestPostsTextView.visibility = View.GONE
                    homePageViewBinding.newestPostsRecyclerView.visibility = View.GONE

                }

                homePageLiveData.controlLoadingView.postValue(false)

            })

            /* Load Categories */
            homePageLiveData.categoriesLiveItemData.observe(this@HomePage, Observer {

                if (it.isNotEmpty()) {

                    if ((columnCount(applicationContext, 115) > it.size)) {

                        primaryCategoryAdapter.categoriesItemData.clear()
                        primaryCategoryAdapter.categoriesItemData.addAll(it)

                        homePageViewBinding.primaryCategoriesRecyclerView.adapter = primaryCategoryAdapter

                        homePageViewBinding.secondaryCategoriesRecyclerView.visibility = View.GONE

                    } else {

                        val primaryCategoriesData = it.slice(IntRange(0, (columnCount(applicationContext, 115) - 1)))

                        primaryCategoryAdapter.categoriesItemData.clear()
                        primaryCategoryAdapter.categoriesItemData.addAll(primaryCategoriesData)

                        homePageViewBinding.primaryCategoriesRecyclerView.adapter = primaryCategoryAdapter

                        val secondaryCategoriesData = it.slice(IntRange(columnCount(applicationContext, 115), (it.size - 1)))

                        secondaryCategoryAdapter.categoriesItemData.clear()
                        secondaryCategoryAdapter.categoriesItemData.addAll(secondaryCategoriesData)

                        homePageViewBinding.secondaryCategoriesRecyclerView.adapter = secondaryCategoryAdapter

                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {

                        PopupShortcutsCreator(this@HomePage)
                            .buildAppShortcut(
                                it as ArrayList<Any>,
                                PopupShortcutsCreator.TYPE.Category
                            )

                    }

                } else {

                    homePageViewBinding.primaryCategoriesRecyclerView.visibility = View.GONE
                    homePageViewBinding.secondaryCategoriesRecyclerView.visibility = View.GONE

                }

                homePageLiveData.controlLoadingView.postValue(false)

            })

            /* Product Showcase */
            homePageLiveData.productShowcaseLiveItemData.observe(this@HomePage, Observer {

                if (it.isNotEmpty()) {

                    homePageViewBinding.productShowcaseTextView.visibility = View.VISIBLE

                    productShowcaseAdapter.productShowcaseItemData.clear()
                    productShowcaseAdapter.productShowcaseItemData.addAll(it)

                    homePageViewBinding.productShowcaseRecyclerView.adapter = productShowcaseAdapter

                } else {

                    homePageViewBinding.productShowcaseRecyclerView.visibility = View.GONE
                    homePageViewBinding.productShowcaseTextView.visibility = View.GONE

                }

            })

            /* Recommended Posts */
            homePageLiveData.recommendedPostsLiveItemData.observe(this@HomePage, Observer {

                if (it.isNotEmpty()) {

                    homePageViewBinding.forYouPostsTextView.visibility = View.VISIBLE

                    recommendedPostsAdapter.newestPostsItemData.clear()
                    recommendedPostsAdapter.newestPostsItemData.addAll(it)

                    homePageViewBinding.recommendedPostsRecyclerView.adapter = newestPostsAdapter

                } else {

                    homePageViewBinding.forYouPostsTextView.visibility = View.GONE
                    homePageViewBinding.recommendedPostsRecyclerView.visibility = View.GONE

                }

                homePageLiveData.controlLoadingView.postValue(false)

            })

            /* Instagram Story Highlights */
            homePageLiveData.instagramStoryHighlightsLiveItemData.observe(this@HomePage, Observer {

                if (it.isNotEmpty()) {

                    homePageViewBinding.instagramStoryHighlightsRecyclerView.visibility = View.VISIBLE

                    instagramStoryHighlightsAdapter.storyHighlightsItemData.clear()
                    instagramStoryHighlightsAdapter.storyHighlightsItemData.addAll(it)

                    homePageViewBinding.instagramStoryHighlightsRecyclerView.adapter = instagramStoryHighlightsAdapter

                } else {

                    homePageViewBinding.instagramStoryHighlightsRecyclerView.visibility = View.GONE

                }

            })

            /* Progress Loading View */
            homePageLiveData.controlLoadingView.observe(this@HomePage, Observer {

                if (it) {

                    homePageViewBinding.loadingView.visibility = View.VISIBLE

                } else {

                    homePageViewBinding.loadingView.visibility = View.INVISIBLE

                }

            })

            /* Change Theme */
            homePageLiveData.toggleTheme.observe(this@HomePage, Observer {

                var delayTheme: Long = 3333

                when(overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight -> {
                        delayTheme = 3000
                    }
                    ThemeType.ThemeDark -> {
                        delayTheme = 1133
                    }
                }

                if (it) {

                    Handler(Looper.getMainLooper()).postDelayed({

                        specificCategoryAdapter.notifyItemRangeChanged(0, specificCategoryAdapter.itemCount, null)

                        newestPostsAdapter.notifyItemRangeChanged(0, newestPostsAdapter.itemCount, null)

                        productShowcaseAdapter.notifyItemRangeChanged(0, productShowcaseAdapter.itemCount, null)

                        instagramStoryHighlightsAdapter.notifyItemRangeChanged(0, instagramStoryHighlightsAdapter.itemCount, null)

                        toggleLightDarkThemeHomePage(this@HomePage)

                    }, delayTheme)

                } else {

                    toggleLightDarkThemeHomePage(this@HomePage)

                }


            })

            startFeaturedPostsLoadMoreListener(homePageLiveData, specificCategoryAdapter)

            if (!favoriteIt.getAllFavoritedPosts().isNullOrEmpty()) {

                homePageViewBinding.favoritedPostsView.visibility = View.VISIBLE

            } else {

                homePageViewBinding.favoritedPostsView.visibility = View.INVISIBLE

            }

            homePageViewBinding.favoritedPostsView.setOnClickListener {

                val activityOptions = ActivityOptions.makeScaleUpAnimation(
                    it,
                    it.x.toInt(),
                    it.y.toInt(),
                    it.width,
                    it.height
                )

                Intent(applicationContext, FavoritesPostsView::class.java).apply {
                    startActivity(this@apply, activityOptions.toBundle())
                }

            }

        }

        homePageRemoteConfiguration()

    }

    override fun onStart() {
        super.onStart()

        homePageViewBinding.nestedScrollView.setOnScrollChangeListener { nestedScrollView: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->

            if (scrollY == 0) {
                scrollViewAtTop = true
            } else if (scrollY > 0) {
                scrollViewAtTop = false
            }

        }


    }

    override fun onResume() {
        super.onResume()

        adsConfiguration.getInterstitialAd?.let {
            if (it.isLoaded) {
                it.show()
            }
        }

        if (OverallTheme.LastActivity != null) {
            if (OverallTheme.LastActivity != this@HomePage.javaClass.simpleName) {
                homePageLiveData.toggleTheme.postValue(true)
            } else {
                homePageLiveData.toggleTheme.postValue(false)
            }
        } else {
            homePageLiveData.toggleTheme.postValue(false)
        }

        internetCheckpoint()

        if (this@HomePage.isInMultiWindowMode) {
            Log.d(this@HomePage.javaClass.simpleName, "Multi Window Mode Entered")

            homePageViewBinding.blurViewBottomBar.visibility = View.GONE

            homePageViewBinding.preferencePopupInclude.instagramView.visibility = View.GONE
            homePageViewBinding.preferencePopupInclude.youtubeView.visibility = View.GONE
            homePageViewBinding.preferencePopupInclude.twitterView.visibility = View.GONE
            homePageViewBinding.preferencePopupInclude.pinterestView.visibility = View.GONE

        } else {
            Log.d(this@HomePage.javaClass.simpleName, "Multi Window Mode Exited")

            homePageViewBinding.blurViewBottomBar.visibility = View.VISIBLE

            homePageViewBinding.preferencePopupInclude.instagramView.visibility = View.VISIBLE
            homePageViewBinding.preferencePopupInclude.youtubeView.visibility = View.VISIBLE
            homePageViewBinding.preferencePopupInclude.twitterView.visibility = View.VISIBLE
            homePageViewBinding.preferencePopupInclude.pinterestView.visibility = View.VISIBLE

        }

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()

        networkConnectionListener.unregisterDefaultNetworkCallback()

    }

    override fun onBackPressed() {

        if (homePageViewBinding.preferencePopupInclude.root.isShown) {

            hidePopupPreferences()

        } else {

            startActivity(Intent(Intent.ACTION_MAIN).apply {
                this.addCategory(Intent.CATEGORY_HOME)
                this.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }, ActivityOptions.makeCustomAnimation(applicationContext, android.R.anim.fade_in, android.R.anim.fade_out).toBundle())

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.let {

            when (requestCode) {
                UserInformation.GoogleSignInRequestCode -> {

                    val googleSignInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
                    val googleSignInAccount = googleSignInAccountTask.getResult(ApiException::class.java)

                    val authCredential = GoogleAuthProvider.getCredential(googleSignInAccount?.idToken, null)
                    firebaseAuth.signInWithCredential(authCredential).addOnSuccessListener {

                        val firebaseUser = firebaseAuth.currentUser

                        if (firebaseUser != null) {

                            val accountName: String = firebaseUser.email.toString()

                            userInformationIO.saveUserInformation(accountName)

                            userSignIn.signInSuccessful(accountName)

                            Glide.with(applicationContext)
                                .asDrawable()
                                .load(firebaseAuth.currentUser?.photoUrl)
                                .transform(CenterCrop(), CircleCrop())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .listener(object : RequestListener<Drawable> {
                                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                                        return false
                                    }

                                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                                        runOnUiThread {

                                            homePageViewBinding.preferencePopupInclude.signupView.icon = resource

                                        }

                                        return false
                                    }

                                })
                                .submit()

                            /* Retrieve Favorited Data */
                            val favoriteIt: FavoriteIt = FavoriteIt(applicationContext)

                            val databasePath = firestoreConfiguration.favoritedPostsCollectionPath(accountName)

                            firestoreDatabase
                                .collection(databasePath)
                                .get()
                                .addOnSuccessListener {

                                    val favoritedPostsDocuments = it.documents

                                    repeat(favoritedPostsDocuments.size) { index ->

                                        favoriteIt.saveAsFavorite(favoritedPostsDocuments[index][PostsDataParameters.PostParameters.PostId].toString())

                                    }

                                    homePageViewBinding.favoritedPostsView.visibility = View.VISIBLE

                                }.addOnFailureListener {



                                }

                        }

                    }.addOnFailureListener {

                        userSignIn.signInDismissed()

                    }

                }
                else -> {

                }
            }

        }

    }

    override fun enterPictureInPictureMode(pictureInPictureParams: PictureInPictureParams): Boolean {

        if (this@HomePage.isInPictureInPictureMode) {
            Log.d(this@HomePage.javaClass.simpleName, "Picture In Picture Mode Entered")

            homePageViewBinding.blurViewBottomBar.visibility = View.GONE

            homePageViewBinding.preferencePopupInclude.instagramView.visibility = View.GONE
            homePageViewBinding.preferencePopupInclude.youtubeView.visibility = View.GONE
            homePageViewBinding.preferencePopupInclude.twitterView.visibility = View.GONE
            homePageViewBinding.preferencePopupInclude.pinterestView.visibility = View.GONE

        } else {
            Log.d(this@HomePage.javaClass.simpleName, "Picture In Picture Mode Exited")

            homePageViewBinding.blurViewBottomBar.visibility = View.VISIBLE

            homePageViewBinding.preferencePopupInclude.instagramView.visibility = View.VISIBLE
            homePageViewBinding.preferencePopupInclude.youtubeView.visibility = View.VISIBLE
            homePageViewBinding.preferencePopupInclude.twitterView.visibility = View.VISIBLE
            homePageViewBinding.preferencePopupInclude.pinterestView.visibility = View.VISIBLE

        }

        return super.enterPictureInPictureMode(pictureInPictureParams)
    }

    override fun onSwipeGesture(gestureConstants: GestureConstants, downMotionEvent: MotionEvent, moveMotionEvent: MotionEvent, initVelocityX: Float, initVelocityY: Float) {
        super.onSwipeGesture(gestureConstants, downMotionEvent, moveMotionEvent, initVelocityX, initVelocityY)

        when (gestureConstants) {
            is GestureConstants.SwipeVertical -> {
                when (gestureConstants.verticallDirection) {
                    GestureListenerConstants.SWIPE_DOWN -> {

                        if (scrollViewAtTop && updateDelay) {
                            Log.d(this@HomePage.javaClass.simpleName, "Updating Content")

                            updateDelay = false

                            cacheDir.deleteRecursively()

                            CoroutineScope(Dispatchers.IO).launch {

                                try {

                                    firestoreDatabase.clearPersistence()

                                    Glide.get(this@HomePage).clearDiskCache()
                                    Glide.get(this@HomePage).clearMemory()

                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }

                            }

                            setupRefreshView()

                            startNetworkOperations()

                        } else {

                            Toast.makeText(applicationContext, getString(R.string.noMoreContent), Toast.LENGTH_LONG).show()

                        }

                    }
                }
            }
        }

    }

    override fun dispatchTouchEvent(motionEvent: MotionEvent?): Boolean {
        motionEvent?.let {
            swipeGestureListener.onTouchEvent(it)
        }

        return super.dispatchTouchEvent(motionEvent)
    }

    override fun networkAvailable() {

        startNetworkOperations()

    }

    override fun networkLost() {

    }

}