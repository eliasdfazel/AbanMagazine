/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/29/22, 7:49 AM
 * Last modified 4/29/22, 7:48 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.WebView

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.SearchConfigurations.UI.Utils.SetupSearchViewUI
import com.abanabsalan.aban.magazine.Utils.UI.Colors.setColorAlpha
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToPixel
import com.abanabsalan.aban.magazine.Utils.UI.Display.statusBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.Extensions.hidePopupSearches
import com.abanabsalan.aban.magazine.WebView.Extensions.showPopupSearches
import com.abanabsalan.aban.magazine.databinding.BrowserViewBinding

class BuiltInWebView : AppCompatActivity() {

    val overallTheme: OverallTheme by lazy {
        OverallTheme(applicationContext)
    }

    val builtInWebViewClient = BuiltInWebViewClient()

    var postId: String? = null
    var featureImageLink: String? = null
    var postTitle: String? = null
    var postExcerpt: String? = null
    var postLink: String? = null
    var rawPostContent: String? = null
    var relatedPostContent: String? = null

    val favoritedPostData: HashMap<String, Any?> = HashMap<String, Any?>()

    lateinit var browserViewBinding: BrowserViewBinding

    companion object {

        fun showLink(context: Context,
                 linkToLoad: String,
                 gradientColorOne: Int?,
                 gradientColorTwo: Int?) {

            Intent(context, BuiltInWebView::class.java).apply {

                putExtra(Intent.EXTRA_TEXT, linkToLoad)

                putExtra("GradientColorOne", gradientColorOne)
                putExtra("GradientColorTwo", gradientColorTwo)

                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(this@apply, ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_right, R.anim.fade_out).toBundle())
            }

        }

        fun showPost(context: Context,
                 linkToLoad: String,
                 gradientColorOne: Int?,
                 gradientColorTwo: Int?,
                 postId: String,
                 postFeaturedImage: String,
                 postTitle: String,
                 postContent: String,
                 postTags: String,
                 postExcerpt: String,
                 postLink: String,
                 relatedPostStringJson: String?) {

            Intent(context, BuiltInWebView::class.java).apply {

                putExtra(Intent.EXTRA_TEXT, linkToLoad)

                putExtra("GradientColorOne", gradientColorOne)
                putExtra("GradientColorTwo", gradientColorTwo)

                putExtra(PostsDataParameters.PostParameters.PostId, postId)

                putExtra(PostsDataParameters.PostParameters.PostFeaturedImage, postFeaturedImage)

                putExtra(PostsDataParameters.PostParameters.PostTitle, postTitle)
                putExtra(PostsDataParameters.PostParameters.PostContent, postContent)

                putExtra(PostsDataParameters.PostParameters.PostTags, postTags)

                putExtra(PostsDataParameters.PostParameters.PostExcerpt, postExcerpt)
                putExtra(PostsDataParameters.PostParameters.PostLink, postLink)

                putExtra(PostsDataParameters.PostParameters.RelatedPosts, relatedPostStringJson)

                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(this@apply, ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_right, R.anim.fade_out).toBundle())
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        browserViewBinding = BrowserViewBinding.inflate(layoutInflater)
        setContentView(browserViewBinding.root)

        val dominantColor = intent.getIntExtra("GradientColorOne", getColor(R.color.default_color))
        val vibrantColor = intent.getIntExtra("GradientColorTwo", getColor(R.color.default_color_game))

        if (intent.hasExtra(PostsDataParameters.PostParameters.PostId) &&
            intent.hasExtra(PostsDataParameters.PostParameters.PostFeaturedImage) &&
            intent.hasExtra(PostsDataParameters.PostParameters.PostTitle) &&
            intent.hasExtra(PostsDataParameters.PostParameters.PostExcerpt) &&
            intent.hasExtra(PostsDataParameters.PostParameters.PostLink) &&
            intent.hasExtra(PostsDataParameters.PostParameters.PostContent) &&
            intent.hasExtra(PostsDataParameters.PostParameters.RelatedPosts)) {

            postId = intent.getStringExtra(PostsDataParameters.PostParameters.PostId)
            favoritedPostData[PostsDataParameters.PostParameters.PostId] = postId

            featureImageLink = intent.getStringExtra(PostsDataParameters.PostParameters.PostFeaturedImage)
            favoritedPostData[PostsDataParameters.PostParameters.PostFeaturedImage] = featureImageLink

            postTitle = intent.getStringExtra(PostsDataParameters.PostParameters.PostTitle)
            favoritedPostData[PostsDataParameters.PostParameters.PostTitle] = postTitle

            postExcerpt = intent.getStringExtra(PostsDataParameters.PostParameters.PostExcerpt)
            favoritedPostData[PostsDataParameters.PostParameters.PostExcerpt] = postExcerpt

            postLink = intent.getStringExtra(PostsDataParameters.PostParameters.PostLink)
            favoritedPostData[PostsDataParameters.PostParameters.PostLink] = postLink

            rawPostContent = intent.getStringExtra(PostsDataParameters.PostParameters.PostContent)
            favoritedPostData[PostsDataParameters.PostParameters.PostContent] = rawPostContent

            relatedPostContent = intent.getStringExtra(PostsDataParameters.PostParameters.RelatedPosts)
            favoritedPostData[PostsDataParameters.PostParameters.RelatedPosts] = relatedPostContent

            browserViewBinding.blurViewTopBar.setOverlayColor(setColorAlpha(vibrantColor, 0.7f))
            browserViewBinding.blurViewTopBar.setSecondOverlayColor(setColorAlpha(dominantColor, 0.7f))

            browserViewBinding.homepageTopBar.visibility = View.VISIBLE

            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            browserViewBinding.root.post {

                val topPadding = browserViewBinding.homepageTopBar.height + statusBarHeight(applicationContext) + DpToPixel(applicationContext, 11f)

                val topBarLayoutParams = browserViewBinding.homepageTopBar.layoutParams as ConstraintLayout.LayoutParams
                topBarLayoutParams.height = topPadding.toInt()
                browserViewBinding.homepageTopBar.layoutParams = topBarLayoutParams

                builtInWebViewClient.topPadding = browserViewBinding.homepageTopBar.height + statusBarHeight(applicationContext).toFloat()

                browserViewBinding.searchView.setOnClickListener {

                    if (browserViewBinding.searchPopupInclude.root.isShown) {

                        hidePopupSearches()

                    } else {


                        showPopupSearches()

                    }

                }

                SetupSearchViewUI(
                    this@BuiltInWebView,
                    browserViewBinding.searchPopupInclude
                ).initialize()

            }

        }

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeDark -> {

                browserViewBinding.webView.setBackgroundColor(getColor(R.color.dark))

            }
            ThemeType.ThemeLight -> {

                browserViewBinding.webView.setBackgroundColor(getColor(R.color.light))

            }
        }

        window.setBackgroundDrawable(GradientDrawable(GradientDrawable.Orientation.TL_BR,
            arrayOf(
                vibrantColor,
                dominantColor,
                dominantColor,
                vibrantColor,
                vibrantColor,
                dominantColor
            ).toIntArray()))

        val linkToLoad = intent.getStringExtra(Intent.EXTRA_TEXT)

        if (linkToLoad != null) {

            val progressBarLayerList = getDrawable(R.drawable.web_view_progress_bar_drawable) as LayerDrawable
            val progressBarClipDrawable = progressBarLayerList.findDrawableByLayerId(android.R.id.progress) as ClipDrawable
            progressBarClipDrawable.drawable = GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, arrayOf(vibrantColor, dominantColor).toIntArray())

            browserViewBinding.webViewProgressBar.indeterminateDrawable = progressBarLayerList

            browserViewBinding.webView.settings.javaScriptEnabled = true

            browserViewBinding.webView.settings.builtInZoomControls = true
            browserViewBinding.webView.settings.displayZoomControls = false
            browserViewBinding.webView.settings.setSupportZoom(true)

            browserViewBinding.webView.settings.useWideViewPort = true
            browserViewBinding.webView.settings.loadWithOverviewMode = true
            browserViewBinding.webView.setInitialScale(0)

            browserViewBinding.webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

            browserViewBinding.webView.webViewClient = builtInWebViewClient
            browserViewBinding.webView.webChromeClient = BuiltInChromeWebViewClient()
            browserViewBinding.webView.addJavascriptInterface(WebInterface(this@BuiltInWebView), "Android")

            browserViewBinding.root.post {

                browserViewBinding.webView.loadUrl(linkToLoad)

            }

        } else {

            this@BuiltInWebView.finish()

        }

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onBackPressed() {

        this@BuiltInWebView.finish()
        overridePendingTransition(R.anim.fade_in, android.R.anim.slide_out_right)

    }

    inner class BuiltInWebViewClient : WebViewClient() {

        var topPadding: Float = 0f

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            if (request != null) {
                view?.loadUrl(request.url.toString())
            }

            return false
        }

        override fun onPageStarted(webView: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(webView, url, favicon)

            browserViewBinding.webViewProgressBar.visibility = View.VISIBLE

        }

        override fun onPageFinished(webView: WebView?, url: String?) {
            super.onPageFinished(webView, url)

            browserViewBinding.webViewProgressBar.visibility = View.INVISIBLE

            webView?.loadUrl("javascript:(function(){ document.body.style.paddingTop = '${topPadding}px'})();")

        }

    }

    inner class BuiltInChromeWebViewClient : WebChromeClient() {

    }

}