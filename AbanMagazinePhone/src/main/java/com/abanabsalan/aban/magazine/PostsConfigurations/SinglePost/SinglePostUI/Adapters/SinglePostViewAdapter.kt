/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/2/20 10:38 PM
 * Last modified 8/2/20 10:25 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.SinglePostItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders.*
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.PostsConfigurations.Utils.ImageResizingProcess
import com.abanabsalan.aban.magazine.PostsConfigurations.Utils.ImageResizingProcessAction
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class SinglePostViewAdapter (private val singlePostViewContext: SinglePostView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val singlePostItemsData: ArrayList<SinglePostItemData> = ArrayList<SinglePostItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {

            PostsDataParameters.PostItemsViewParameters.PostParagraph -> {

                PostViewParagraphAdapterViewHolder(
                    LayoutInflater.from(singlePostViewContext)
                        .inflate(R.layout.post_view_content_item_paragraph, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostSubTitle -> {

                PostViewSubTitleAdapterViewHolder(
                    LayoutInflater.from(singlePostViewContext)
                        .inflate(R.layout.post_view_content_item_sub_title, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostImage -> {

                PostViewImageAdapterViewHolder(
                    LayoutInflater.from(singlePostViewContext)
                        .inflate(R.layout.post_view_content_item_image, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostTextLink -> {

                PostViewTextLinkAdapterViewHolder(
                    LayoutInflater.from(singlePostViewContext)
                        .inflate(R.layout.post_view_content_item_text_link, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostIFrame -> {

                PostViewIFrameAdapterViewHolder(
                    LayoutInflater.from(singlePostViewContext)
                        .inflate(R.layout.post_view_content_item_i_frame, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostBlockQuoteInstagram -> {

                PostViewBlockQuoteInstagramAdapterViewHolder(
                    LayoutInflater.from(singlePostViewContext)
                        .inflate(R.layout.post_view_content_item_block_quote_instagram, viewGroup, false)
                )

            }
            else -> {

                PostViewParagraphAdapterViewHolder(
                    LayoutInflater.from(singlePostViewContext)
                        .inflate(R.layout.post_view_content_item_paragraph, viewGroup, false)
                )

            }

        }

    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)

        return singlePostItemsData[position].dataType
    }

    override fun getItemCount(): Int {

        return singlePostItemsData.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int, dataPayloads: MutableList<Any>) {
        super.onBindViewHolder(viewHolder, position, dataPayloads)

        when (singlePostItemsData[position].dataType) {

            PostsDataParameters.PostItemsViewParameters.PostParagraph -> {

                (viewHolder as PostViewParagraphAdapterViewHolder)

                viewHolder.postParagraph.setTextColor(when (singlePostViewContext.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight -> {
                        singlePostViewContext.getColor(R.color.dark)
                    }
                    ThemeType.ThemeDark -> {
                        singlePostViewContext.getColor(R.color.light)
                    }
                    else -> {
                        singlePostViewContext.getColor(R.color.dark)
                    }
                })

            }
            PostsDataParameters.PostItemsViewParameters.PostSubTitle -> {

                (viewHolder as PostViewSubTitleAdapterViewHolder)

                viewHolder.postSubTitle.setTextColor(when (singlePostViewContext.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight -> {
                        singlePostViewContext.getColor(R.color.darker)
                    }
                    ThemeType.ThemeDark -> {
                        singlePostViewContext.getColor(R.color.lighter)
                    }
                    else -> {
                        singlePostViewContext.getColor(R.color.darker)
                    }
                })

            }
            PostsDataParameters.PostItemsViewParameters.PostImage -> {

                (viewHolder as PostViewImageAdapterViewHolder)

            }
            PostsDataParameters.PostItemsViewParameters.PostTextLink -> {

                (viewHolder as PostViewTextLinkAdapterViewHolder)

            }
            PostsDataParameters.PostItemsViewParameters.PostIFrame -> {

                (viewHolder as PostViewIFrameAdapterViewHolder)

            }
            PostsDataParameters.PostItemsViewParameters.PostBlockQuoteInstagram -> {

                (viewHolder as PostViewBlockQuoteInstagramAdapterViewHolder)

            }
            else -> {

            }

        }

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        when (singlePostItemsData[position].dataType) {

            PostsDataParameters.PostItemsViewParameters.PostParagraph -> {

                (viewHolder as PostViewParagraphAdapterViewHolder).postParagraph.setTextColor(when (singlePostViewContext.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight -> {
                        singlePostViewContext.getColor(R.color.dark)
                    }
                    ThemeType.ThemeDark -> {
                        singlePostViewContext.getColor(R.color.light)
                    }
                    else -> {
                        singlePostViewContext.getColor(R.color.dark)
                    }
                })

                singlePostItemsData[position].postItemParagraph?.let {

                    (viewHolder as PostViewParagraphAdapterViewHolder).postParagraph.text = Html.fromHtml(it.paragraphText)

                    (viewHolder as PostViewParagraphAdapterViewHolder).postParagraph.setOnClickListener {



                    }

                }

            }
            PostsDataParameters.PostItemsViewParameters.PostSubTitle -> {

                (viewHolder as PostViewSubTitleAdapterViewHolder).postSubTitle.setTextColor(when (singlePostViewContext.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight -> {
                        singlePostViewContext.getColor(R.color.darker)
                    }
                    ThemeType.ThemeDark -> {
                        singlePostViewContext.getColor(R.color.lighter)
                    }
                    else -> {
                        singlePostViewContext.getColor(R.color.darker)
                    }
                })

                singlePostItemsData[position].postItemSubTitle?.let {

                    (viewHolder as PostViewSubTitleAdapterViewHolder).postSubTitle.text = Html.fromHtml(it.subTitleText)

                    (viewHolder as PostViewSubTitleAdapterViewHolder).postSubTitle.setOnClickListener {



                    }

                }

            }
            PostsDataParameters.PostItemsViewParameters.PostImage -> {

                (viewHolder as PostViewImageAdapterViewHolder).showFullScreenInformation.setTextColor(when (singlePostViewContext.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight ->{

                        singlePostViewContext.getColor(R.color.dark)

                    }
                    ThemeType.ThemeDark -> {

                        singlePostViewContext.getColor(R.color.light)

                    }
                    else -> {
                        singlePostViewContext.getColor(R.color.dark)
                    }
                })

                singlePostItemsData[position].postItemImage?.let {

                    val drawableError: Drawable? = singlePostViewContext.getDrawable(android.R.drawable.ic_menu_report_image)
                    drawableError?.setTint(singlePostViewContext.getColor(R.color.red))

                    val requestOptions = RequestOptions()
                        .error(drawableError)

                    if (it.imageLink.contains(".gif")) {

                        Glide.with(singlePostViewContext)
                            .asGif()
                            .load(it.imageLink)
                            .apply(requestOptions)
                            .transform(CenterInside(), RoundedCorners(11))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into((viewHolder as PostViewImageAdapterViewHolder).postImage)

                    } else {

                        Glide.with(singlePostViewContext)
                            .asDrawable()
                            .load(it.imageLink)
                            .apply(requestOptions)
                            .transform(RoundedCorners(11))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .listener(object : RequestListener<Drawable> {

                                override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                                    return false
                                }

                                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                                    singlePostViewContext.runOnUiThread {
                                        (viewHolder as PostViewImageAdapterViewHolder).postImage.setImageDrawable(resource)
                                        (viewHolder as PostViewImageAdapterViewHolder).postImageLoading.visibility = View.GONE
                                    }

                                    return false
                                }

                            })
                            .submit()

                    }

                    if (it.targetLink.isNullOrBlank()) {

                        ImageResizingProcess((viewHolder as PostViewImageAdapterViewHolder).postImage,
                            (viewHolder as PostViewImageAdapterViewHolder).showFullScreen,
                            (viewHolder as PostViewImageAdapterViewHolder).showFullScreenInformation)
                            .start(object : ImageResizingProcessAction {

                                override fun onImageViewReverted() {
                                    super.onImageViewReverted()
                                }

                            })

                        (viewHolder as PostViewImageAdapterViewHolder).showFullScreen.setOnClickListener { view ->

                            BuiltInWebView.show(context = singlePostViewContext, linkToLoad = it.imageLink)

                        }

                        (viewHolder as PostViewImageAdapterViewHolder).showFullScreenInformation.setOnClickListener { view ->

                            BuiltInWebView.show(context = singlePostViewContext, linkToLoad = it.imageLink)

                        }

                    } else {

                        it.targetLink?.also { targetLink ->

                            BuiltInWebView.show(context = singlePostViewContext, linkToLoad = targetLink)

                        }

                    }

                }

            }
            PostsDataParameters.PostItemsViewParameters.PostTextLink -> {

                singlePostItemsData[position].postItemTextLink?.let {

                    (viewHolder as PostViewTextLinkAdapterViewHolder).postTextLink.text = Html.fromHtml(it.linkText)

                    val linkContent: Document = Jsoup.parse(it.linkText)

                    (viewHolder as PostViewTextLinkAdapterViewHolder).postTextLink.setOnClickListener {

                        linkContent.select("a").first().attr("abs:href")?.let { aLink ->

                            BuiltInWebView.show(context = singlePostViewContext, linkToLoad = aLink)

                        }

                    }

                }

            }
            PostsDataParameters.PostItemsViewParameters.PostIFrame -> {

                singlePostItemsData[position].postItemIFrame?.let {

                    (viewHolder as PostViewIFrameAdapterViewHolder).postIFrame.settings.javaScriptEnabled = true
                    (viewHolder as PostViewIFrameAdapterViewHolder).postIFrame.settings.domStorageEnabled = true

                    (viewHolder as PostViewIFrameAdapterViewHolder).postIFrame.loadData(it.iFrameContent, "text/html", "UTF-8")

                }

            }
            PostsDataParameters.PostItemsViewParameters.PostBlockQuoteInstagram -> {

                singlePostItemsData[position].postItemBlockQuoteInstagram?.let {

                    val drawableError: Drawable? = singlePostViewContext.getDrawable(android.R.drawable.ic_menu_report_image)
                    drawableError?.setTint(singlePostViewContext.getColor(R.color.red))

                    val requestOptions = RequestOptions()
                        .error(drawableError)

                    Glide.with(singlePostViewContext)
                        .asDrawable()
                        .load(it.instagramPostImage)
                        .apply(requestOptions)
                        .transform(CenterInside(), RoundedCorners(11))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((viewHolder as PostViewBlockQuoteInstagramAdapterViewHolder).instagramPostImage)

                    (viewHolder as PostViewBlockQuoteInstagramAdapterViewHolder).instagramPostUsername.text = "@" + Html.fromHtml(it.instagramUsername)
                    (viewHolder as PostViewBlockQuoteInstagramAdapterViewHolder).instagramPostTitle.text = Html.fromHtml(it.instagramPostTitle)

                    (viewHolder as PostViewBlockQuoteInstagramAdapterViewHolder).rootViewItem.setOnClickListener { view ->

                        Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse(it.instagramPostAddress)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            singlePostViewContext.startActivity(this@apply)
                        }

                    }

                }

            }
            else -> {

            }

        }

    }

}