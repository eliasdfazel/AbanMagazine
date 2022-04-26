/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/26/22, 7:31 AM
 * Last modified 4/26/22, 7:29 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.RecommendedPosts

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Colors.extractDominantColor
import com.abanabsalan.aban.magazine.Utils.UI.Colors.extractVibrantColor
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import com.abanabsalan.aban.magazine.databinding.HomePageRecommendedItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class RecommendedPostsAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<RecommendedPostsViewHolder>() {

    val postsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecommendedPostsViewHolder {

        return RecommendedPostsViewHolder(HomePageRecommendedItemBinding.inflate(context.layoutInflater, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return postsItemData.size
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(recommendedPostsViewHolder: RecommendedPostsViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {



            }
            ThemeType.ThemeDark -> {



            }
        }

        val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
        drawableError?.setTint(context.getColor(R.color.red))

        val requestOptions = RequestOptions()
            .error(drawableError)

        Glide.with(context)
            .asDrawable()
            .load(postsItemData[position].postFeaturedImage)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(CenterCrop(), RoundedCorners(13))
            .into(recommendedPostsViewHolder.postFeaturedImage)

        recommendedPostsViewHolder.postTitleView.text = Html.fromHtml(postsItemData[position].postTitle, Html.FROM_HTML_MODE_LEGACY)

        recommendedPostsViewHolder.readMoreView.setOnClickListener {

            Glide.with(context)
                .asDrawable()
                .load(postsItemData[position].postFeaturedImage)
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                        context.runOnUiThread {

                            BuiltInWebView.showPost(
                                context = context,
                                postId = postsItemData[position].postId,
                                postFeaturedImage = postsItemData[position].postFeaturedImage,
                                postTitle = postsItemData[position].postTitle,
                                postContent = postsItemData[position].postContent,
                                postTags = postsItemData[position].postTags,
                                postExcerpt = postsItemData[position].postExcerpt,
                                postLink = postsItemData[position].postLink,
                                relatedPostStringJson = postsItemData[position].relatedPostsContent,
                                gradientColorOne = extractDominantColor(context, resource?:context.getDrawable(R.drawable.official_business_logo)!!),
                                gradientColorTwo = extractVibrantColor(context, resource?:context.getDrawable(R.drawable.official_business_logo)!!),
                                linkToLoad = postsItemData[position].postLink,
                            )

                        }

                        return false
                    }

                })
                .submit()

        }

        recommendedPostsViewHolder.rootViewItem.setOnTouchListener { view, motionEvent ->

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {

                    val animation = ValueAnimator.ofFloat(11.3f, 0f)
                    animation.duration = 1111
                    animation.addUpdateListener {
                        val value = it.animatedValue as Float

                        recommendedPostsViewHolder.postFeaturedBlurryImage.setBlurRadius(value)

                        if (value == 0f) {

                        }

                    }
                    animation.start()

                    recommendedPostsViewHolder.readMoreView.visibility = View.VISIBLE

                }
                MotionEvent.ACTION_UP -> {

                    Handler(Looper.getMainLooper()).postDelayed({

                        val animation = ValueAnimator.ofFloat(0f, 11f)
                        animation.duration = 1111
                        animation.addUpdateListener {
                            val value = it.animatedValue as Float

                            recommendedPostsViewHolder.postFeaturedBlurryImage.setBlurRadius(value)

                            if (value == 11f) {

                            }

                        }
                        animation.start()

                        recommendedPostsViewHolder.readMoreView.visibility = View.INVISIBLE

                    }, 1975)

                }
                MotionEvent.ACTION_CANCEL -> {

                    Handler(Looper.getMainLooper()).postDelayed({

                        val animation = ValueAnimator.ofFloat(0f, 11f)
                        animation.duration = 1111
                        animation.addUpdateListener {
                            val value = it.animatedValue as Float

                            recommendedPostsViewHolder.postFeaturedBlurryImage.setBlurRadius(value)

                            if (value == 11f) {

                            }

                        }
                        animation.start()

                        recommendedPostsViewHolder.readMoreView.visibility = View.INVISIBLE

                    }, 1975)

                }
            }

            true
        }

    }

}