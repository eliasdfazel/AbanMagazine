/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/19/20 5:40 PM
 * Last modified 7/19/20 4:15 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.WebView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.Utils.UI.Display.statusBarHeight
import com.abanabsalan.aban.magazine.databinding.BrowserViewBinding

class BuiltInWebView : AppCompatActivity() {

    lateinit var browserViewBinding: BrowserViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        browserViewBinding = BrowserViewBinding.inflate(layoutInflater)
        setContentView(browserViewBinding.root)

        browserViewBinding.root.setPadding(0, statusBarHeight(applicationContext) , 0, 0)

        val linkToLoad = intent.getStringExtra("Link")

        if (linkToLoad != null) {

            browserViewBinding.webView.settings.javaScriptEnabled = true
            browserViewBinding.webView.addJavascriptInterface(WebInterface(applicationContext), "WebInterface")
            browserViewBinding.webView.loadUrl(linkToLoad)

        } else {

            this@BuiltInWebView.finish()

        }

    }

    override fun onPause() {
        super.onPause()

        this@BuiltInWebView.finish()
    }
}