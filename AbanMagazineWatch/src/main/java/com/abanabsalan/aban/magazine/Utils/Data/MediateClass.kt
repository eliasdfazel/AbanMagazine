/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Data

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView

class MediateClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val linkToLoad = intent.getStringExtra(Intent.EXTRA_TEXT)?:intent.dataString

        linkToLoad?.let {

            BuiltInWebView.show(
                context = applicationContext,
                linkToLoad = it,
                gradientColorOne = null,
                gradientColorTwo = null
            )

        }

        this@MediateClass.finish()

    }

}