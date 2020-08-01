/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/31/20 9:42 PM
 * Last modified 7/31/20 9:40 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abanabsalan.aban.magazine.AbanMagazinePhoneApplication
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsLiveData
import com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.Extensions.favoritesPostsNetworkOperations
import com.abanabsalan.aban.magazine.Utils.Ads.AdsConfiguration
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListener
import com.abanabsalan.aban.magazine.Utils.Network.NetworkConnectionListenerInterface
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

    val adsConfiguration: AdsConfiguration by lazy {
        AdsConfiguration(this@FavoritesPostsView)
    }

    @Inject
    lateinit var networkConnectionListener: NetworkConnectionListener

    lateinit var favoritePostsBinding: FavoritePostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritePostsBinding = FavoritePostsBinding.inflate(layoutInflater)

        (application as AbanMagazinePhoneApplication)
            .dependencyGraph
            .subDependencyGraph()
            .create(this@FavoritesPostsView, favoritePostsBinding.rootView)
            .inject(this@FavoritesPostsView)

        networkConnectionListener.networkConnectionListenerInterface = this@FavoritesPostsView

        adsConfiguration.initialize()

        favoritesPostsLiveData.allFavoritedPosts.observe(this@FavoritesPostsView, Observer {



        })

    }

    override fun onResume() {
        super.onResume()

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