/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder.OnlineStoreLiveData
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Extensions.setupOnlineStoreActionListener
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Extensions.setupOnlineStoreUserInterface
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Extensions.startOnlineStoreNetworkOperations
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Operations.ProductShowcaseRetrieval
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI.Adapter.AllProductsAdapter
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.columnCount
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.databinding.OnlineStoreLayoutBinding

class OnlineStore : AppCompatActivity() {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    val productShowcaseRetrieval: ProductShowcaseRetrieval by lazy {
        ProductShowcaseRetrieval(applicationContext)
    }

    val onlineStoreLiveData: OnlineStoreLiveData by lazy {
        ViewModelProvider(this@OnlineStore).get(OnlineStoreLiveData::class.java)
    }

    lateinit var onlineStoreLayoutBinding: OnlineStoreLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onlineStoreLayoutBinding = OnlineStoreLayoutBinding.inflate(layoutInflater)
        setContentView(onlineStoreLayoutBinding.root)

        setupOnlineStoreUserInterface()

        setupOnlineStoreActionListener()

        onlineStoreLayoutBinding.featuredProductsRecyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, true)

        onlineStoreLayoutBinding.allProductsRecyclerView.layoutManager = StaggeredGridLayoutManager(columnCount(applicationContext, 193), RecyclerView.VERTICAL)

        val allProductsAdapter = AllProductsAdapter(this@OnlineStore, overallTheme)

        onlineStoreLayoutBinding.rootView.post {

            onlineStoreLayoutBinding.allProductsRecyclerView.adapter = allProductsAdapter

            onlineStoreLiveData.allProductsShowcaseLiveItemData.observe(this@OnlineStore, Observer {

                if (it.isNotEmpty()) {

                    if (allProductsAdapter.productJsonDataStructureItem.isNotEmpty()) {
                        allProductsAdapter.productJsonDataStructureItem.clear()
                        allProductsAdapter.notifyDataSetChanged()
                    }

                    allProductsAdapter.productJsonDataStructureItem.addAll(it)

                    onlineStoreLayoutBinding.waitingView.pauseAnimation()
                    onlineStoreLayoutBinding.waitingView.visibility = View.INVISIBLE

                    onlineStoreLayoutBinding.allProductsRecyclerView.visibility = View.VISIBLE

                    allProductsAdapter.notifyDataSetChanged()

                }

            })

        }

        startOnlineStoreNetworkOperations()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()

        overridePendingTransition(R.anim.fade_in, 0)

    }

    override fun onBackPressed() {

        overridePendingTransition(R.anim.fade_in, 0)
        this@OnlineStore.finish()

    }

}