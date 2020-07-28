/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/27/20 11:11 PM
 * Last modified 7/27/20 11:10 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Ads

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.BuildConfig
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
import com.abanabsalan.aban.magazine.R
import com.google.android.gms.ads.*

class AdsConfiguration (private val context: AppCompatActivity) {

    val interstitialAd: InterstitialAd = InterstitialAd(context)

    fun initialize() {

        if (!BuildConfig.DEBUG) {

            MobileAds.initialize(context) { initializationStatus ->


                interstitialAdsLoadShow()

                bannerAdsLoadShow()


            }

            val testDeviceIds =
                listOf("3E192B3766F6EDE8127A5ADFAA0E7B67", "A06676F37C8588BFF7D434B66274567A")
            val configuration = RequestConfiguration.Builder()
                .setTestDeviceIds(testDeviceIds)
                .build()
            MobileAds.setRequestConfiguration(configuration)

        }

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

        when(context) {
            is HomePage -> {

                (context).homePageViewBinding.bannerAdView.loadAd(adRequest)

                (context).homePageViewBinding.bannerAdView.adListener = object: AdListener() {
                    override fun onAdLoaded() {

                        (context).homePageViewBinding.bannerAdView.visibility = View.VISIBLE
                    }

                    override fun onAdFailedToLoad(errorCode : Int) {

                        (context).homePageViewBinding.bannerAdView.loadAd(adRequest)

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
            is PostView -> {

            }
        }

    }

}