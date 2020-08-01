/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/1/20 5:52 AM
 * Last modified 8/1/20 5:31 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.WebView

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.statusBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.databinding.BrowserViewBinding

class BuiltInWebView : AppCompatActivity() {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    lateinit var browserViewBinding: BrowserViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        browserViewBinding = BrowserViewBinding.inflate(layoutInflater)
        setContentView(browserViewBinding.root)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeDark -> {

                window.navigationBarColor = getColor(R.color.dark)

                browserViewBinding.webView.setBackgroundColor(getColor(R.color.dark))

            }
            ThemeType.ThemeLight -> {

                window.navigationBarColor = getColor(R.color.light)

                browserViewBinding.webView.setBackgroundColor(getColor(R.color.light))

            }
        }

        browserViewBinding.root.setPadding(0, statusBarHeight(applicationContext) , 0, 0)

        val linkToLoad = intent.getStringExtra("Link")

        if (linkToLoad != null) {



            browserViewBinding.webView.settings.javaScriptEnabled = true
            browserViewBinding.webView.settings.builtInZoomControls = true
            browserViewBinding.webView.settings.displayZoomControls = false
            browserViewBinding.webView.settings.setSupportZoom(true)
            browserViewBinding.webView.settings.useWideViewPort = true
            browserViewBinding.webView.settings.loadWithOverviewMode = true
            browserViewBinding.webView.setInitialScale(0)
            browserViewBinding.webView.webViewClient = BuiltInWebViewClient()
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

    inner class BuiltInWebViewClient() : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url?.toString())
            return true
        }

    }
}