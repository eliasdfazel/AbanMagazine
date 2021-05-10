/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 5/10/21, 4:30 AM
 * Last modified 5/10/21, 4:26 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI

import android.accounts.AccountManager
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.AccountManager.UserInformation
import com.abanabsalan.aban.magazine.AccountManager.UserInformationIO
import com.abanabsalan.aban.magazine.AccountManager.UserSignIn
import com.abanabsalan.aban.magazine.Advertising.AdvertisingConfiguration
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsLiveData
import com.abanabsalan.aban.magazine.PostsConfigurations.RelatedPosts.UI.RelatedPostsAdapter
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.Extensions.hidePopupPreferences
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.Extensions.setupUserInterface
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.SinglePostViewAdapter
import com.abanabsalan.aban.magazine.PostsConfigurations.Utils.GesturePinchImageView
import com.abanabsalan.aban.magazine.Preferences.PopupPreferencesController
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.TagsConfigurations.Utils.TagsIO
import com.abanabsalan.aban.magazine.Utils.Network.NetworkCheckpoint
import com.abanabsalan.aban.magazine.Utils.UI.Display.columnCount
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.Utils.UI.Theme.toggleLightDarkThemePostView
import com.abanabsalan.aban.magazine.databinding.PostsViewUiBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import net.geekstools.supershortcuts.PRO.Utils.UI.Gesture.GestureConstants
import net.geekstools.supershortcuts.PRO.Utils.UI.Gesture.GestureListenerInterface
import org.json.JSONArray

open class SinglePostView : AppCompatActivity(), GestureListenerInterface, AppBarLayout.OnOffsetChangedListener {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    val postsLiveData: PostsLiveData by lazy {
        ViewModelProvider(this@SinglePostView).get(PostsLiveData::class.java)
    }

    val advertisingConfiguration: AdvertisingConfiguration by lazy {
        AdvertisingConfiguration(this@SinglePostView)
    }

    val tagsIO: TagsIO by lazy {
        TagsIO(applicationContext)
    }

    private var postTopBarIsExpanded = true

    var postId: String? = null
    var featureImageLink: String? = null
    var postTitle: String? = null
    var postExcerpt: String? = null
    var postLink: String? = null

    var rawPostContent: String? = null

    var relatedPostContent: String? = null

    var dominantColor: Int? = null
    var vibrantColor: Int? = null

    lateinit var userSignIn: UserSignIn

    private val userInformationIO: UserInformationIO by lazy {
        UserInformationIO(applicationContext)
    }

    val firebaseAuth = Firebase.auth

    val firebaseAnalytics: FirebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(applicationContext)
    }

    val networkCheckpoint: NetworkCheckpoint by lazy {
        NetworkCheckpoint(applicationContext)
    }

    val favoritedPostData: HashMap<String, Any?> = HashMap<String, Any?>()

    val gesturePinchImageView = GesturePinchImageView()

    lateinit var postsViewUiBinding: PostsViewUiBinding

    companion object {

        fun show(context: AppCompatActivity,
                 featuredImageSharedElement: AppCompatImageView,
                 postId: String,
                 postFeaturedImage: String, postTitle: String, postContent: String,
                 postTags: String,
                 postExcerpt: String, postLink: String,
                 relatedPostStringJson: String?) {

            Intent(context, SinglePostView::class.java).apply {

                putExtra(PostsDataParameters.PostParameters.PostId, postId)

                putExtra(PostsDataParameters.PostParameters.PostFeaturedImage, postFeaturedImage)

                putExtra(PostsDataParameters.PostParameters.PostTitle, postTitle)
                putExtra(PostsDataParameters.PostParameters.PostContent, postContent)

                putExtra(PostsDataParameters.PostParameters.PostTags, postTags)

                putExtra(PostsDataParameters.PostParameters.PostExcerpt, postExcerpt)
                putExtra(PostsDataParameters.PostParameters.PostLink, postLink)

                putExtra(PostsDataParameters.PostParameters.RelatedPosts, relatedPostStringJson)

                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                context.startActivity(this@apply,
                    ActivityOptions.makeSceneTransitionAnimation(context,
                        featuredImageSharedElement,
                        context.getString(R.string.featuredImageTransitionName))
                        .toBundle())

            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsViewUiBinding = PostsViewUiBinding.inflate(layoutInflater)
        setContentView(postsViewUiBinding.root)

        advertisingConfiguration.initialize()

        postId = intent.getStringExtra(PostsDataParameters.PostParameters.PostId)
        favoritedPostData[PostsDataParameters.PostParameters.PostId] = postId

        featureImageLink = intent.getStringExtra(PostsDataParameters.PostParameters.PostFeaturedImage)
        favoritedPostData[PostsDataParameters.PostParameters.PostFeaturedImage] = featureImageLink

        postTitle = intent.getStringExtra(PostsDataParameters.PostParameters.PostTitle)
        favoritedPostData[PostsDataParameters.PostParameters.PostTitle] = postTitle

        postExcerpt = intent.getStringExtra(PostsDataParameters.PostParameters.PostExcerpt)
        favoritedPostData[PostsDataParameters.PostParameters.PostExcerpt] = postExcerpt

        postLink = intent.getStringExtra(PostsDataParameters.PostParameters.PostLink)
        favoritedPostData[PostsDataParameters.PostParameters.PostLink] = postLink

        rawPostContent = intent.getStringExtra(PostsDataParameters.PostParameters.PostContent)
        favoritedPostData[PostsDataParameters.PostParameters.PostContent] = rawPostContent

        relatedPostContent = intent.getStringExtra(PostsDataParameters.PostParameters.RelatedPosts)
        favoritedPostData[PostsDataParameters.PostParameters.RelatedPosts] = relatedPostContent

        PopupPreferencesController(this@SinglePostView, postsViewUiBinding.preferencePopupInclude)
            .initializeForPostView(postId)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        postsViewUiBinding.postRecyclerView.layoutManager = linearLayoutManager

        postsViewUiBinding.relatedPostsRecyclerView.layoutManager = GridLayoutManager(applicationContext, columnCount(applicationContext, 193), RecyclerView.VERTICAL, false)

        val singlePostViewAdapter: SinglePostViewAdapter = SinglePostViewAdapter(this@SinglePostView)

        val relatedPostsAdapter = RelatedPostsAdapter(this@SinglePostView, overallTheme)

        if (!postTitle.isNullOrEmpty() && !featureImageLink.isNullOrEmpty()) {
            setupUserInterface(postTitle!!, featureImageLink!!)
        } else {
            this@SinglePostView.finish()
        }

        /*Post Content*/
        postsLiveData.singleSinglePostLiveItemData.observe(this@SinglePostView, Observer {

            if (it.isNotEmpty()) {

                singlePostViewAdapter.singlePostItemsData.clear()

                singlePostViewAdapter.singlePostItemsData.addAll(it)
                postsViewUiBinding.postRecyclerView.adapter = singlePostViewAdapter

            }

        })

        /*Related Posts*/
        postsLiveData.relatedPostsLiveItemData.observe(this@SinglePostView, Observer {

            if (it.isNotEmpty()) {

                postsViewUiBinding.relatedPostsTextView.visibility = View.VISIBLE

                relatedPostsAdapter.relatedPostsItemData.clear()

                relatedPostsAdapter.relatedPostsItemData.addAll(it)
                postsViewUiBinding.relatedPostsRecyclerView.adapter = relatedPostsAdapter

            }

        })

        /*Change Theme*/
        postsLiveData.toggleTheme.observe(this@SinglePostView, Observer {

            var delayTheme: Long = 3333

            when(it) {
                ThemeType.ThemeLight -> {
                    delayTheme = 3000
                }
                ThemeType.ThemeDark -> {
                    delayTheme = 1133
                }
            }

            Handler(Looper.getMainLooper()).postDelayed({

                singlePostViewAdapter.notifyItemRangeChanged(0, singlePostViewAdapter.itemCount, null)


                toggleLightDarkThemePostView(this@SinglePostView)

            }, delayTheme)

        })

        rawPostContent?.let { rawPostContent ->
            postsLiveData.prepareRawDataToRenderForSinglePost(rawPostContent)
        }

        relatedPostContent?.let { relatedPostContent ->
            postsLiveData.extractRelatedPostIds(JSONArray(relatedPostContent))
        }

        /*Post Tags*/
        intent.getStringExtra(PostsDataParameters.PostParameters.PostTags)?.let {
            tagsIO.saveTagsDatabase(it)
        }

        firebaseAnalytics.logEvent(this@SinglePostView.javaClass.simpleName, Bundle().apply {
            putString("PostId", postId)
            putString("PostTitle", postTitle)
        })

    }

    override fun onResume() {
        super.onResume()

        advertisingConfiguration.getInterstitialAd?.let {
            it.show(this@SinglePostView)
        }

        postsViewUiBinding.postTopBar.addOnOffsetChangedListener(this@SinglePostView)

    }

    override fun onPause() {
        super.onPause()

        postsViewUiBinding.postTopBar.removeOnOffsetChangedListener(this@SinglePostView)

    }

    override fun onBackPressed() {

        if (postsViewUiBinding.preferencePopupInclude.root.isShown) {

            hidePopupPreferences()

        } else if (postsViewUiBinding.gesturePinchImageViewContainer.isShown) {

            postsViewUiBinding.postMenuButton.visibility = View.VISIBLE
            postsViewUiBinding.postMenuButton.startAnimation(AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in))

            postsViewUiBinding.postMenuIcon.visibility = View.VISIBLE
            postsViewUiBinding.postMenuIcon.startAnimation(AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in))

            postsViewUiBinding.gesturePinchImageViewContainer.visibility = View.INVISIBLE
            postsViewUiBinding.gesturePinchImageViewContainer.startAnimation(AnimationUtils.loadAnimation(applicationContext, android.R.anim.slide_out_right))

            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_out_right, 0)
                .remove(gesturePinchImageView)
                .commit()

        } else {

            this@SinglePostView.finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.let {

            when (requestCode) {
                UserInformation.AccountPickerRequestCode -> {

                    val accountName: String? = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)

                    if (accountName != null) {

                        userInformationIO.saveUserInformation(accountName)

                        userSignIn.signInSuccessful(accountName)

                    } else {

                        userSignIn.signInDismissed()

                    }

                }
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

    override fun onSwipeGesture(gestureConstants: GestureConstants, downMotionEvent: MotionEvent, moveMotionEvent: MotionEvent, initVelocityX: Float, initVelocityY: Float) {
        super.onSwipeGesture(gestureConstants, downMotionEvent, moveMotionEvent, initVelocityX, initVelocityY)


    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        postTopBarIsExpanded = (verticalOffset == 0)

        if (postTopBarIsExpanded) {
            if (postsViewUiBinding.postMenuButton.isShown) {
                postsViewUiBinding.postMenuButton.visibility = View.INVISIBLE
            }
            if (postsViewUiBinding.postMenuIcon.isShown) {
                postsViewUiBinding.postMenuIcon.visibility = View.INVISIBLE
            }
        } else {
            if (!postsViewUiBinding.postMenuButton.isShown && !postsViewUiBinding.gesturePinchImageViewContainer.isShown) {
                postsViewUiBinding.postMenuButton.visibility = View.VISIBLE
            }
            if (!postsViewUiBinding.postMenuIcon.isShown && !postsViewUiBinding.gesturePinchImageViewContainer.isShown) {
                postsViewUiBinding.postMenuIcon.visibility = View.VISIBLE
            }
        }

    }

}