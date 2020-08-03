/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/3/20 12:27 AM
 * Last modified 8/2/20 11:50 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.WebView

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.*
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

    companion object {

        fun show(context: Context,
                 linkToLoad: String) {

            Intent(context, BuiltInWebView::class.java).apply {
                putExtra(Intent.EXTRA_TEXT, linkToLoad)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(this@apply, ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_right, R.anim.fade_out).toBundle())
            }

        }

    }

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

        val linkToLoad = intent.getStringExtra(Intent.EXTRA_TEXT)

        if (linkToLoad != null) {

            browserViewBinding.webView.settings.javaScriptEnabled = true
            browserViewBinding.webView.settings.builtInZoomControls = true
            browserViewBinding.webView.settings.displayZoomControls = false
            browserViewBinding.webView.settings.setSupportZoom(true)
            browserViewBinding.webView.settings.useWideViewPort = true
            browserViewBinding.webView.settings.loadWithOverviewMode = true
            browserViewBinding.webView.settings.setAppCacheEnabled(true)
            browserViewBinding.webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            browserViewBinding.webView.setInitialScale(0)
            browserViewBinding.webView.webViewClient = BuiltInWebViewClient()
            browserViewBinding.webView.webChromeClient = BuiltInChromeWebViewClient()
            browserViewBinding.webView.addJavascriptInterface(WebInterface(this@BuiltInWebView), "Android")
            browserViewBinding.webView.loadUrl(linkToLoad)

        } else {

            this@BuiltInWebView.finish()

        }

    }

    override fun onPause() {
        super.onPause()

        this@BuiltInWebView.finish()
        overridePendingTransition(R.anim.fade_in, android.R.anim.slide_out_right)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    inner class BuiltInWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url?.toString())

            return false
        }

    }

    inner class BuiltInChromeWebViewClient : WebChromeClient() {

    }

}