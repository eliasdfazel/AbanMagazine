/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/27/20 12:45 PM
 * Last modified 6/27/20 12:45 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostItemImage
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostItemParagraph
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.PostViewAdapter
import com.abanabsalan.aban.magazine.Utils.UI.Colors.extractDominantColor
import com.abanabsalan.aban.magazine.Utils.UI.Colors.extractVibrantColor
import com.abanabsalan.aban.magazine.Utils.UI.Colors.isDrawableLightDark
import com.abanabsalan.aban.magazine.databinding.PostsViewUiBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class PostView : AppCompatActivity() {

    lateinit var postsViewUiBinding: PostsViewUiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsViewUiBinding = PostsViewUiBinding.inflate(layoutInflater)
        setContentView(postsViewUiBinding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        postsViewUiBinding.postRecyclerView.layoutManager = linearLayoutManager

        val postViewAdapter: PostViewAdapter = PostViewAdapter(this@PostView)


        val featureImageLink = intent.getStringExtra("PostFeatureImageLink")
        val postTitle = intent.getStringExtra("PostTitle")

        val rawPostContent = intent.getStringExtra("RawPostContent")

        postsViewUiBinding.postTitle.text = Html.fromHtml(postTitle)

        postsViewUiBinding.postTitle.post {
            val postTopBarMarginLayoutParams = postsViewUiBinding.postTopBarMargin.layoutParams
            postTopBarMarginLayoutParams.height = postsViewUiBinding.postTitle.height
            postsViewUiBinding.postTopBarMargin.layoutParams = postTopBarMarginLayoutParams
        }


        CoroutineScope(Dispatchers.Default).async {

            Glide.with(this@PostView)
                .load(featureImageLink)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                        return true
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                        runOnUiThread {

                            postsViewUiBinding.postFeatureImage.setImageDrawable(resource)

                            resource?.let {

                                val dominantColor = extractDominantColor(applicationContext, it)
                                val vibrantColor = extractVibrantColor(applicationContext, it)

                                window.setBackgroundDrawable(GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, arrayOf(vibrantColor, dominantColor).toIntArray()))

                                postsViewUiBinding.collapsingPostTopBar.contentScrim = GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, arrayOf(vibrantColor, dominantColor).toIntArray())

                                postsViewUiBinding.postFavoriteItButton.backgroundTintList = ColorStateList.valueOf(vibrantColor)
                                postsViewUiBinding.postFavoriteItButton.rippleColor = ColorStateList.valueOf(dominantColor)

                                if (!isDrawableLightDark(applicationContext, resource)) {
                                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                                } else {
                                    window.decorView.systemUiVisibility = 0
                                }

                            }

                        }

                        return true
                    }

                })
                .submit()

            val postContent: Document = Jsoup.parse(rawPostContent)

            postContent.allElements.forEachIndexed { index, element ->

                if (element.`is`("p")) {
                    Log.d(this@PostView.javaClass.simpleName, "Paragraph ${element}")

                    postViewAdapter.postItemsData.add(
                        PostItemData(PostsDataParameters.PostItemsParameters.PostParagraph,
                            PostItemParagraph(element.text()),
                            null,
                            null,
                            null
                        )
                    )

                } else if (element.`is`("a")) {
                    Log.d(this@PostView.javaClass.simpleName, "Link ${element}")



                } else if (element.`is`("img")) {
                    Log.d(this@PostView.javaClass.simpleName, "Image ${element.attr("src")}")

                    postViewAdapter.postItemsData.add(
                        PostItemData(PostsDataParameters.PostItemsParameters.PostImage,
                            null,
                            PostItemImage(element.attr("src")),
                            null,
                            null
                        )
                    )

                } else if (element.`is`("iframe")) {
                    Log.d(this@PostView.javaClass.simpleName, "iFrame ${element}")


                }

            }

            withContext(Dispatchers.Main) {

                postsViewUiBinding.postRecyclerView.adapter = postViewAdapter
            }
        }

        postsViewUiBinding.postTopBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

        })

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}