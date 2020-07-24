/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/23/20 9:56 PM
 * Last modified 7/23/20 9:50 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.AbanMagazinePhoneApplication
import com.abanabsalan.aban.magazine.HomePageConfigurations.DataHolder.HomePageLiveData
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.*
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.NewestPosts.NewestPostsAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.PrimaryCategory.PrimaryCategoryAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SecondaryCategory.SecondaryCategoryAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SpecificCategory.SpecificCategoryAdapter
import com.abanabsalan.aban.magazine.Preferences.PopupPreferencesController
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Utils.PageCounter
import com.abanabsalan.aban.magazine.Utils.Network.NetworkCheckpoint
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListener
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListenerInterface
import com.abanabsalan.aban.magazine.Utils.UI.Display.columnCount
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.Utils.UI.Theme.toggleLightDarkThemeHomePage
import com.abanabsalan.aban.magazine.databinding.HomePageViewBinding
import javax.inject.Inject

class HomePage : AppCompatActivity(), NetworkConnectionListenerInterface {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    val networkCheckpoint: NetworkCheckpoint by lazy {
        NetworkCheckpoint(applicationContext)
    }

    val homePageLiveData: HomePageLiveData by lazy {
        ViewModelProvider(this@HomePage).get(HomePageLiveData::class.java)
    }

    @Inject
    lateinit var networkConnectionListener: NetworkConnectionListener

    lateinit var homePageViewBinding: HomePageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePageViewBinding = HomePageViewBinding.inflate(layoutInflater)
        setContentView(homePageViewBinding.root)

        PopupPreferencesController(this@HomePage, homePageViewBinding.preferencePopupInclude)

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

        val primaryCategoryAdapter: PrimaryCategoryAdapter = PrimaryCategoryAdapter(this@HomePage, overallTheme)

        val secondaryCategoryAdapter: SecondaryCategoryAdapter = SecondaryCategoryAdapter(this@HomePage, overallTheme)

        val specificCategoryAdapter: SpecificCategoryAdapter = SpecificCategoryAdapter(this@HomePage, overallTheme)

        val newestPostsAdapter: NewestPostsAdapter = NewestPostsAdapter(this@HomePage, overallTheme)

        homePageViewBinding.root.post {

            setupUserInterface()

            /*Load Featured Posts*/
            homePageLiveData.specificCategoryLiveItemData.observe(this@HomePage, Observer {

                if (!it.isNullOrEmpty()) {

                    homePageViewBinding.featuredPostsTextView.visibility = View.VISIBLE

                    if (specificCategoryAdapter.specificCategoryPostsItemData.isEmpty()) {

                        specificCategoryAdapter.specificCategoryPostsItemData.addAll(it)

                        homePageViewBinding.featuredPostsRecyclerView.adapter = specificCategoryAdapter

                        Handler().postDelayed({
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

                    homePageViewBinding.featuredPostsLoadingView.visibility = View.INVISIBLE

                    Toast.makeText(applicationContext, getString(R.string.noMoreContent), Toast.LENGTH_LONG).show()

                }

                homePageLiveData.controlLoadingView.postValue(false)

            })

            /*Load Newest Posts*/
            homePageLiveData.newestPostsLiveItemData.observe(this@HomePage, Observer {

                if (it.isNotEmpty()) {

                    homePageViewBinding.newestPostsTextView.visibility = View.VISIBLE

                    newestPostsAdapter.newestPostsItemData.clear()
                    newestPostsAdapter.newestPostsItemData.addAll(it)

                    homePageViewBinding.newestPostsRecyclerView.adapter = newestPostsAdapter

                }

                homePageLiveData.controlLoadingView.postValue(false)

            })

            /*Load Categories*/
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

                }

                homePageLiveData.controlLoadingView.postValue(false)

            })

            /*Progress Loading View*/
            homePageLiveData.controlLoadingView.observe(this@HomePage, Observer {

                if (it) {

                    homePageViewBinding.loadingView.visibility = View.VISIBLE

                } else {

                    homePageViewBinding.loadingView.visibility = View.INVISIBLE

                }

            })

            /*Change Theme*/
            homePageLiveData.toggleTheme.observe(this@HomePage, Observer {

                var delayTheme: Long = 3333

                when(it) {
                    ThemeType.ThemeLight -> {
                        delayTheme = 3000
                    }
                    ThemeType.ThemeDark -> {
                        delayTheme = 1133
                    }
                }

                Handler().postDelayed({

                    specificCategoryAdapter.notifyItemRangeChanged(0, specificCategoryAdapter.itemCount, null)

                    newestPostsAdapter.notifyItemRangeChanged(0, newestPostsAdapter.itemCount, null)

                    toggleLightDarkThemeHomePage(this@HomePage)

                }, delayTheme)

            })

            startFeaturedPostsLoadMoreListener(homePageLiveData, specificCategoryAdapter)

        }

    }

    override fun onResume() {
        super.onResume()

        setupTheme()

        internetCheckpoint()

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

    override fun networkAvailable() {

        startNetworkOperations()

    }

    override fun networkLost() {

    }

}