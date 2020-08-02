/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/2/20 4:02 AM
 * Last modified 8/2/20 4:00 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Ads

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.R
import com.google.android.gms.ads.*

class AdsConfiguration (private val appCompatActivity: AppCompatActivity) {

    val interstitialAd: InterstitialAd = InterstitialAd(appCompatActivity)

    fun initialize() {

        MobileAds.initialize(appCompatActivity) { initializationStatus ->

            interstitialAdsLoadShow()

            bannerAdsLoadShow()

        }

        val testDeviceIds = listOf("3E192B3766F6EDE8127A5ADFAA0E7B67", "A06676F37C8588BFF7D434B66274567A")

        val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(testDeviceIds)
            .build()
        MobileAds.setRequestConfiguration(configuration)

    }

    private fun interstitialAdsLoadShow() {

        val adRequest = AdRequest.Builder().build()

        interstitialAd.setImmersiveMode(true)

        when(appCompatActivity) {
            is HomePage -> {
                Log.d(this@AdsConfiguration.javaClass.simpleName, "Home Page Requesting Ads")

                interstitialAd.adUnitId = appCompatActivity.getString(R.string.homePageInterstitial)
            }
            is SinglePostView -> {
                Log.d(this@AdsConfiguration.javaClass.simpleName, "Post View Requesting Ads")

                interstitialAd.adUnitId = appCompatActivity.getString(R.string.postViewInterstitial)
            }
            is FavoritesPostsView -> {
                Log.d(this@AdsConfiguration.javaClass.simpleName, "Favorites Posts View Requesting Ads")

                interstitialAd.adUnitId = appCompatActivity.getString(R.string.favoritesPostsViewInterstitial)
            }
        }

        interstitialAd.loadAd(adRequest)
        interstitialAd.adListener = object : AdListener() {

            override fun onAdLoaded() {
                super.onAdLoaded()

                if (!appCompatActivity.isFinishing) {
                    interstitialAd.show()
                }

            }

            override fun onAdFailedToLoad(errorCode: LoadAdError) {
                super.onAdFailedToLoad(errorCode)

                interstitialAd.loadAd(adRequest)

            }

        }

        interstitialAd.setOnPaidEventListener {

        }

    }

    private fun bannerAdsLoadShow() {

        val adRequest = AdRequest.Builder().build()

        when(appCompatActivity) {
            is HomePage -> {

                (appCompatActivity).homePageViewBinding.bannerAdView.loadAd(adRequest)

                (appCompatActivity).homePageViewBinding.bannerAdView.adListener = object: AdListener() {
                    override fun onAdLoaded() {

                        (appCompatActivity).homePageViewBinding.bannerAdView.visibility = View.VISIBLE
                    }

                    override fun onAdFailedToLoad(errorCode : Int) {

                        (appCompatActivity).homePageViewBinding.bannerAdView.loadAd(adRequest)

                    }

                    override fun onAdOpened() {

                    }

                    override fun onAdClicked() {

                    }

                    override fun onAdLeftApplication() {

                    }

                    override fun onAdClosed() {

                    }
                }

            }
            is SinglePostView -> {

            }
        }

    }

}