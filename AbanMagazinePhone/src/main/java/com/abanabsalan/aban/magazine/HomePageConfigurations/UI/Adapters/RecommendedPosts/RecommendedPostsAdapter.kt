/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/12/20 9:16 AM
 * Last modified 11/12/20 9:16 AM
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
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class RecommendedPostsAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<RecommendedPostsViewHolder>() {

    val recommendedPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecommendedPostsViewHolder {

        return RecommendedPostsViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_recommended_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return recommendedPostsItemData.size
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
            .load(recommendedPostsItemData[position].postFeaturedImage)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                    context.runOnUiThread {
                        recommendedPostsViewHolder.postFeaturedImage.setImageDrawable(resource)
                    }

                    return false
                }

            })
            .submit()

        recommendedPostsViewHolder.postTitleView.text = Html.fromHtml(recommendedPostsItemData[position].postTitle, Html.FROM_HTML_MODE_LEGACY)

        recommendedPostsViewHolder.readMoreView.setOnClickListener {

            SinglePostView.show(
                context = context,
                featuredImageSharedElement = recommendedPostsViewHolder.postFeaturedImage as AppCompatImageView,
                postId = recommendedPostsItemData[position].postId,
                postFeaturedImage = recommendedPostsItemData[position].postFeaturedImage,
                postTitle = recommendedPostsItemData[position].postTitle,
                postContent = recommendedPostsItemData[position].postContent,
                postTags = recommendedPostsItemData[position].postTags,
                postExcerpt = recommendedPostsItemData[position].postExcerpt,
                postLink = recommendedPostsItemData[position].postLink,
                relatedPostStringJson = recommendedPostsItemData[position].relatedPostsContent
            )

        }

        recommendedPostsViewHolder.rootViewItem.setOnTouchListener { view, motionEvent ->

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {

                    val animation = ValueAnimator.ofFloat(13.3f, 0f)
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

                        val animation = ValueAnimator.ofFloat(0f, 13f)
                        animation.duration = 1111
                        animation.addUpdateListener {
                            val value = it.animatedValue as Float

                            recommendedPostsViewHolder.postFeaturedBlurryImage.setBlurRadius(value)

                            if (value == 13f) {

                            }

                        }
                        animation.start()

                        recommendedPostsViewHolder.readMoreView.visibility = View.INVISIBLE

                    }, 1975)

                }
                MotionEvent.ACTION_CANCEL -> {

                    Handler(Looper.getMainLooper()).postDelayed({

                        val animation = ValueAnimator.ofFloat(0f, 13f)
                        animation.duration = 1111
                        animation.addUpdateListener {
                            val value = it.animatedValue as Float

                            recommendedPostsViewHolder.postFeaturedBlurryImage.setBlurRadius(value)

                            if (value == 13f) {

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