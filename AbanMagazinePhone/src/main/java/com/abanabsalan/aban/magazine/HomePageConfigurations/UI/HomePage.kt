/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/10/20 12:53 PM
 * Last modified 7/10/20 12:53 PM
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
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.AbanMagazinePhoneApplication
import com.abanabsalan.aban.magazine.HomePageConfigurations.DataHolder.HomePageLiveData
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.setupUserInterface
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.startFeaturedPostsLoadMoreListener
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.startNetworkOperations
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.startSpecificCategoryRetrieval
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.NewestPosts.NewestPostsAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.PrimaryCategory.PrimaryCategoryAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SecondaryCategory.SecondaryCategoryAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SpecificCategory.SpecificCategoryAdapter
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Utils.PageCounter
import com.abanabsalan.aban.magazine.Utils.Network.NetworkCheckpoint
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListener
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListenerInterface
import com.abanabsalan.aban.magazine.Utils.UI.Display.columnCount
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
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

        val primaryCategoryAdapter: PrimaryCategoryAdapter = PrimaryCategoryAdapter(this@HomePage, overallTheme.checkThemeLightDark())

        val secondaryCategoryAdapter: SecondaryCategoryAdapter = SecondaryCategoryAdapter(this@HomePage, overallTheme.checkThemeLightDark())

        val specificCategoryAdapter: SpecificCategoryAdapter = SpecificCategoryAdapter(this@HomePage, overallTheme.checkThemeLightDark())

        val newestPostsAdapter: NewestPostsAdapter = NewestPostsAdapter(this@HomePage, overallTheme.checkThemeLightDark())

        homePageViewBinding.root.post {

            setupUserInterface()

            homePageLiveData.specificCategoryLiveItemData.observe(this@HomePage, Observer {

                if (!it.isNullOrEmpty()) {

                    homePageViewBinding.featuredPostsTextView.visibility = View.VISIBLE

                    if (specificCategoryAdapter.specificCategoryPostsItemData.isEmpty()) {

                        specificCategoryAdapter.specificCategoryPostsItemData.addAll(it)

                        homePageViewBinding.featuredPostsRecyclerView.adapter = specificCategoryAdapter

                        Handler().postDelayed({
                            PageCounter.PageNumberToLoad = PageCounter.PageNumberToLoad.plus(1)

                            startSpecificCategoryRetrieval(applicationContext, homePageLiveData, PageCounter.PageNumberToLoad)
                        }, 777)

                    } else {

                        specificCategoryAdapter.specificCategoryPostsItemData.addAll(it)

                        specificCategoryAdapter.notifyDataSetChanged()

                    }

                } else {



                }

                homePageLiveData.controlLoadingView.postValue(false)

            })

            homePageLiveData.newestPostsLiveItemData.observe(this@HomePage, Observer {

                if (it.isNotEmpty()) {

                    homePageViewBinding.newestPostsTextView.visibility = View.VISIBLE

                    newestPostsAdapter.newestPostsItemData.clear()
                    newestPostsAdapter.newestPostsItemData.addAll(it)

                    homePageViewBinding.newestPostsRecyclerView.adapter = newestPostsAdapter

                }

                homePageLiveData.controlLoadingView.postValue(false)

            })

            homePageLiveData.categoriesLiveItemData.observe(this@HomePage, Observer {

                if (it.isNotEmpty()) {

                    val primaryCategoriesData = it.slice(IntRange(0, (columnCount(applicationContext, 115) - 1)))

                    primaryCategoryAdapter.categoriesItemData.clear()
                    primaryCategoryAdapter.categoriesItemData.addAll(primaryCategoriesData)

                    homePageViewBinding.primaryCategoriesRecyclerView.adapter = primaryCategoryAdapter

                    val secondaryCategoriesData = it.slice(IntRange(columnCount(applicationContext, 115), (it.size - 1)))

                    secondaryCategoryAdapter.categoriesItemData.clear()
                    secondaryCategoryAdapter.categoriesItemData.addAll(secondaryCategoriesData)

                    homePageViewBinding.secondaryCategoriesRecyclerView.adapter = secondaryCategoryAdapter

                }

                homePageLiveData.controlLoadingView.postValue(false)

            })

            homePageLiveData.controlLoadingView.observe(this@HomePage, Observer {

                if (it) {

                    homePageViewBinding.loadingView.visibility = View.VISIBLE

                } else {

                    homePageViewBinding.loadingView.visibility = View.INVISIBLE

                }

            })

            startFeaturedPostsLoadMoreListener(homePageLiveData, specificCategoryAdapter)



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

        startActivity(Intent(Intent.ACTION_MAIN).apply {
            this.addCategory(Intent.CATEGORY_HOME)
            this.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }, ActivityOptions.makeCustomAnimation(applicationContext, android.R.anim.fade_in, android.R.anim.fade_out).toBundle())

    }

    override fun networkAvailable() {

        startNetworkOperations()

    }

    override fun networkLost() {

    }

}