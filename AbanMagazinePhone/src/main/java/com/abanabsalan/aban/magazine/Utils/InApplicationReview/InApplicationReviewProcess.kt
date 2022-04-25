/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.InApplicationReview

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.BuildConfig
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.Data.LastUpdateInformation
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.testing.FakeReviewManager

class InApplicationReviewProcess (private val context: AppCompatActivity) {

    private val reviewManager = ReviewManagerFactory.create(context)

    private val fakeReviewManager = FakeReviewManager(context)

    fun start(forceReviewFlow: Boolean) {

        val lastUpdateInformation = LastUpdateInformation(context)

        if (lastUpdateInformation.isApplicationUpdated() || forceReviewFlow) {

            val requestReviewFlow = if (BuildConfig.DEBUG) {
                fakeReviewManager.requestReviewFlow()
            } else {
                reviewManager.requestReviewFlow()
            }

            requestReviewFlow.addOnSuccessListener { reviewInfo ->

                val reviewFlow = if (BuildConfig.DEBUG) {
                    fakeReviewManager.launchReviewFlow(context, reviewInfo)
                } else {
                    reviewManager.launchReviewFlow(context, reviewInfo)
                }

                reviewFlow.addOnSuccessListener {

                }.addOnFailureListener {

                    Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse(context.getString(R.string.playStoreLink))
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(this@apply)
                    }

                }

            }.addOnFailureListener {
                Log.d(this@InApplicationReviewProcess.javaClass.simpleName, "In Application Review Process Error")

                Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse(context.getString(R.string.playStoreLink))
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(this@apply)
                }

            }

        } else {

        }

    }

}