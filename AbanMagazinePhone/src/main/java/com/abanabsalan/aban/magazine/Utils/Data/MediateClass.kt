/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/26/22, 7:31 AM
 * Last modified 4/26/22, 7:30 AM
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

            BuiltInWebView.showLink(
                context = applicationContext,
                linkToLoad = it,
                gradientColorOne = null,
                gradientColorTwo = null
            )

        }

        this@MediateClass.finish()

    }

}