/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.AbanMagazinePhoneApplication
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesDataParameters
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoryPostsLiveData
import com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Operations.AllCategoryPostsRetrieval
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.Adapter.AllCategoryPostsAdapter
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.Extensions.setupUserInterface
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListener
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListenerInterface
import com.abanabsalan.aban.magazine.Utils.UI.Display.columnCount
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.databinding.AllCategoryPostsBinding
import javax.inject.Inject

class AllCategoryPosts : AppCompatActivity(), NetworkConnectionListenerInterface {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    val categoryPostsLiveData: CategoryPostsLiveData by lazy {
        ViewModelProvider(this@AllCategoryPosts).get(CategoryPostsLiveData::class.java)
    }

    val allCategoryPostsRetrieval: AllCategoryPostsRetrieval by lazy {
        AllCategoryPostsRetrieval(applicationContext)
    }

    var categoryLink: String? = null
    var categoryId: String? = null

    @Inject
    lateinit var networkConnectionListener: NetworkConnectionListener

    lateinit var allCategoryPostsBinding: AllCategoryPostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allCategoryPostsBinding = AllCategoryPostsBinding.inflate(layoutInflater)
        setContentView(allCategoryPostsBinding.root)

        (application as AbanMagazinePhoneApplication)
            .dependencyGraph
            .subDependencyGraph()
            .create(this@AllCategoryPosts, allCategoryPostsBinding.rootView)
            .inject(this@AllCategoryPosts)

        networkConnectionListener.networkConnectionListenerInterface = this@AllCategoryPosts

        categoryLink = intent.getStringExtra(CategoriesDataParameters.CategoryParameters.CategoryLink)
        categoryId = intent.getStringExtra(CategoriesDataParameters.CategoryParameters.CategoryId)

        val categoryName: String = intent.getStringExtra(CategoriesDataParameters.CategoryParameters.CategoryName)

        val categoryDescription: String = intent.getStringExtra(CategoriesDataParameters.CategoryParameters.CategoryDescription)

        setupUserInterface(categoryName, categoryDescription)

        allCategoryPostsBinding.allCategoryPostsRecyclerView.layoutManager = GridLayoutManager(applicationContext, columnCount(applicationContext, 379), RecyclerView.VERTICAL, false)

        val allCategoryPostsAdapter: AllCategoryPostsAdapter = AllCategoryPostsAdapter(this@AllCategoryPosts, overallTheme)

        categoryPostsLiveData.allCategoryPosts.observe(this@AllCategoryPosts, Observer {

            if (it.isNullOrEmpty()) {

                Toast.makeText(applicationContext, getString(R.string.noMoreContent), Toast.LENGTH_LONG).show()

            } else {

                allCategoryPostsBinding.loadingView.visibility = View.GONE

                if (allCategoryPostsAdapter.postsItemData.isEmpty()) {

                    allCategoryPostsAdapter.postsItemData.addAll(it)

                    allCategoryPostsBinding.allCategoryPostsRecyclerView.adapter = allCategoryPostsAdapter

                } else {

                    allCategoryPostsAdapter.postsItemData.addAll(it)

                    allCategoryPostsAdapter.notifyDataSetChanged()

                }
            }

        })



    }

    override fun onDestroy() {
        super.onDestroy()

        networkConnectionListener.unregisterDefaultNetworkCallback()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun networkAvailable() {

        categoryId?.let {
            allCategoryPostsRetrieval.start(
                categoryPostsLiveData,
                it.toInt()
            )
        }

    }

    override fun networkLost() {

    }

}