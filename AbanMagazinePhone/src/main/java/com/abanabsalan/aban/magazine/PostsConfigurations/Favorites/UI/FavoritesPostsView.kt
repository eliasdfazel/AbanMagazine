/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/31/20 11:57 PM
 * Last modified 7/31/20 11:56 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.AbanMagazinePhoneApplication
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsLiveData
import com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.Extensions.favoritesPostsNetworkOperations
import com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.Extensions.setupUserInterface
import com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.UI.Adapter.FavoritesPostsViewAdapter
import com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.Utils.FavoriteIt
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.Ads.AdsConfiguration
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListener
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListenerInterface
import com.abanabsalan.aban.magazine.Utils.UI.Display.columnCount
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.databinding.FavoritePostsBinding
import javax.inject.Inject

class FavoritesPostsView : AppCompatActivity(), NetworkConnectionListenerInterface {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    val favoritesPostsLiveData: PostsLiveData by lazy {
        ViewModelProvider(this@FavoritesPostsView).get(PostsLiveData::class.java)
    }

    val favoritesPostsViewAdapter: FavoritesPostsViewAdapter by lazy {
        FavoritesPostsViewAdapter(this@FavoritesPostsView, overallTheme)
    }

    val favoriteIt: FavoriteIt by lazy {
        FavoriteIt(applicationContext)
    }

    val adsConfiguration: AdsConfiguration by lazy {
        AdsConfiguration(this@FavoritesPostsView)
    }

    @Inject
    lateinit var networkConnectionListener: NetworkConnectionListener

    lateinit var favoritePostsBinding: FavoritePostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritePostsBinding = FavoritePostsBinding.inflate(layoutInflater)
        setContentView(favoritePostsBinding.root)

        (application as AbanMagazinePhoneApplication)
            .dependencyGraph
            .subDependencyGraph()
            .create(this@FavoritesPostsView, favoritePostsBinding.rootView)
            .inject(this@FavoritesPostsView)

        networkConnectionListener.networkConnectionListenerInterface = this@FavoritesPostsView

        setupUserInterface()

        favoritePostsBinding.favoritePostsRecyclerView.layoutManager = GridLayoutManager(applicationContext, columnCount(applicationContext, 379), RecyclerView.VERTICAL, false)

        adsConfiguration.initialize()

        favoritesPostsLiveData.allFavoritedPosts.observe(this@FavoritesPostsView, Observer {

            if (it.isNullOrEmpty()) {

                Toast.makeText(applicationContext, getString(R.string.noMoreContent), Toast.LENGTH_LONG).show()

            } else {

                favoritePostsBinding.loadingView.visibility = View.GONE

                if (favoritesPostsViewAdapter.postsItemData.isEmpty()) {

                    favoritesPostsViewAdapter.postsItemData.clear()
                    favoritesPostsViewAdapter.postsItemData.addAll(it)

                    favoritePostsBinding.favoritePostsRecyclerView.adapter = favoritesPostsViewAdapter

                } else {

                    favoritesPostsViewAdapter.postsItemData.clear()
                    favoritesPostsViewAdapter.postsItemData.addAll(it)

                    favoritesPostsViewAdapter.notifyDataSetChanged()

                }
            }

        })

    }

    override fun onResume() {
        super.onResume()

        if (FavoriteIt.FavoriteDataChanged) {

            favoriteIt.getAllFavoritedPosts()?.let {

                if (it.isEmpty()) {

                    favoritePostsBinding.favoritePostsRecyclerView.removeAllViews()

                    favoritesPostsViewAdapter.postsItemData.clear()

                    Toast.makeText(applicationContext, getString(R.string.noMoreContent), Toast.LENGTH_LONG).show()

                    this@FavoritesPostsView.finish()

                } else {

                    favoritesPostsNetworkOperations()

                }

            }

        }

        if (adsConfiguration.interstitialAd.isLoaded) {
            adsConfiguration.interstitialAd.show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        networkConnectionListener.unregisterDefaultNetworkCallback()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun networkAvailable() {

        favoritesPostsNetworkOperations()

    }

    override fun networkLost() {

    }

}