/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/8/20 1:39 AM
 * Last modified 8/8/20 1:38 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.InApplicationReview

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.BuildConfig
import com.abanabsalan.aban.magazine.Utils.Data.LastUpdateInformation
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.testing.FakeReviewManager

class InApplicationReviewProcess (private val context: AppCompatActivity) {

    private val reviewManager = ReviewManagerFactory.create(context)

    private val fakeReviewManager = FakeReviewManager(context)

    fun start() {

        val lastUpdateInformation = LastUpdateInformation(context)

        if (lastUpdateInformation.isApplicationUpdated()) {

            val requestReviewFlow = if (BuildConfig.DEBUG) {
                fakeReviewManager.requestReviewFlow()
            } else {
                reviewManager.requestReviewFlow()
            }

            requestReviewFlow.addOnCompleteListener { request ->

                if (request.isSuccessful) {

                    val reviewInfo = request.result

                    val reviewFlow = if (BuildConfig.DEBUG) {
                        fakeReviewManager.launchReviewFlow(context, reviewInfo)
                    } else {
                        reviewManager.launchReviewFlow(context, reviewInfo)
                    }

                    reviewFlow.addOnCompleteListener {

                        if (it.isSuccessful) {

                        } else {

                        }

                    }

                } else {
                    Log.d(this@InApplicationReviewProcess.javaClass.simpleName, "In Application Review Process Error")



                }

            }

        } else {

        }

    }

}