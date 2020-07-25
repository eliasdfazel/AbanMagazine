/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/25/20 12:40 AM
 * Last modified 7/25/20 12:35 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Ads

import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
import com.abanabsalan.aban.magazine.R
import com.google.android.gms.ads.*

class AdsConfiguration (private val context: AppCompatActivity) {

    val interstitialAd: InterstitialAd = InterstitialAd(context)

    fun initialize() {

        MobileAds.initialize(context) { initializationStatus ->

            interstitialAdsLoadShow()

            bannerAdsLoadShow()

        }

        val testDeviceIds = listOf("3E192B3766F6EDE8127A5ADFAA0E7B67")
        val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(testDeviceIds)
            .build()
        MobileAds.setRequestConfiguration(configuration)

    }

    private fun interstitialAdsLoadShow() {

        val adRequest = AdRequest.Builder().build()

        interstitialAd.setImmersiveMode(true)

        when(context) {
            is HomePage -> {
                interstitialAd.adUnitId = context.getString(R.string.homePageInterstitial)
            }
            is PostView -> {
                interstitialAd.adUnitId = context.getString(R.string.postViewInterstitial)
            }
        }

        interstitialAd.loadAd(adRequest)
        interstitialAd.adListener = object : AdListener() {

            override fun onAdLoaded() {
                super.onAdLoaded()

                if (!context.isFinishing) {
                    interstitialAd.show()
                }

            }

            override fun onAdFailedToLoad(error: Int) {
                super.onAdFailedToLoad(error)

                interstitialAd.loadAd(adRequest)

            }

        }

        interstitialAd.setOnPaidEventListener {

        }

    }

    private fun bannerAdsLoadShow() {

        when(context) {
            is HomePage -> {
                val adRequest = AdRequest.Builder().build()

                (context).homePageViewBinding.bannerAdView.loadAd(adRequest)

            }
            is PostView -> {
                interstitialAd.adUnitId = context.getString(R.string.postViewInterstitial)
            }
        }

    }

}