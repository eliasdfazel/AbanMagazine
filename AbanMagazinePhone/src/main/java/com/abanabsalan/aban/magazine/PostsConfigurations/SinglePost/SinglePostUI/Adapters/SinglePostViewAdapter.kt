/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 12/31/20 10:34 AM
 * Last modified 12/31/20 10:34 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.SinglePostItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.Extensions.paragraphsTextSelectionProcess
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders.*
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.BlogContent.LanguageUtils
import com.abanabsalan.aban.magazine.Utils.Data.convertDrawableToByteArray
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToInteger
import com.abanabsalan.aban.magazine.Utils.UI.Display.displayX
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class SinglePostViewAdapter (private val context: SinglePostView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val languageUtils: LanguageUtils = LanguageUtils()

    val singlePostItemsData: ArrayList<SinglePostItemData> = ArrayList<SinglePostItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {

            PostsDataParameters.PostItemsViewParameters.PostParagraph -> {

                PostViewParagraphAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_paragraph, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostSubTitle -> {

                PostViewSubTitleAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_sub_title, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostImage -> {

                PostViewImageAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_image, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostTextLink -> {

                PostViewTextLinkAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_text_link, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostButton -> {

                PostViewButtonAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_button, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostIFrame -> {

                PostViewIFrameAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_i_frame, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.PostBlockQuoteInstagram -> {

                PostViewBlockQuoteInstagramAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_block_quote_instagram, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsViewParameters.ProductShowcase -> {

                if (displayX(context) <= DpToInteger(context, 700)) {

                    PostViewProductShowcaseAdapterViewHolder(
                        LayoutInflater.from(context)
                            .inflate(R.layout.post_view_content_item_showcase_vertical, viewGroup, false)
                    )

                } else {

                    PostViewProductShowcaseAdapterViewHolder(
                        LayoutInflater.from(context)
                            .inflate(R.layout.post_view_content_item_showcase_horizontal, viewGroup, false)
                    )

                }

            }
            else -> {

                PostViewParagraphAdapterViewHolder(
                    LayoutInflater.from(context)
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

                viewHolder.postParagraph.setTextColor(when (context.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight -> {
                        context.getColor(R.color.dark)
                    }
                    ThemeType.ThemeDark -> {
                        context.getColor(R.color.light)
                    }
                    else -> {
                        context.getColor(R.color.dark)
                    }
                })

            }
            PostsDataParameters.PostItemsViewParameters.PostSubTitle -> {

                (viewHolder as PostViewSubTitleAdapterViewHolder)

                viewHolder.postSubTitle.setTextColor(when (context.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight -> {
                        context.getColor(R.color.darker)
                    }
                    ThemeType.ThemeDark -> {
                        context.getColor(R.color.lighter)
                    }
                    else -> {
                        context.getColor(R.color.darker)
                    }
                })

            }
            PostsDataParameters.PostItemsViewParameters.PostImage -> {

                (viewHolder as PostViewImageAdapterViewHolder)

            }
            PostsDataParameters.PostItemsViewParameters.PostTextLink -> {

                (viewHolder as PostViewTextLinkAdapterViewHolder)

            }
            PostsDataParameters.PostItemsViewParameters.PostButton -> {



            }
            PostsDataParameters.PostItemsViewParameters.PostIFrame -> {

                (viewHolder as PostViewIFrameAdapterViewHolder)

            }
            PostsDataParameters.PostItemsViewParameters.PostBlockQuoteInstagram -> {

                (viewHolder as PostViewBlockQuoteInstagramAdapterViewHolder)

            }
            PostsDataParameters.PostItemsViewParameters.ProductShowcase -> {

                (viewHolder as PostViewProductShowcaseAdapterViewHolder)

                val productImageBackground =  if (displayX(context) <= DpToInteger(context, 700)) {
                    context.getDrawable(R.drawable.product_image_showcase_item_background_vertical) as LayerDrawable
                } else {
                    context.getDrawable(R.drawable.product_image_showcase_item_background_horizontal) as LayerDrawable
                }

                val productTextBackground = if (displayX(context) <= DpToInteger(context, 700)) {
                    context.getDrawable(R.drawable.product_text_showcase_item_background_vertical) as LayerDrawable
                } else {
                    context.getDrawable(R.drawable.product_text_showcase_item_background_horizontal) as LayerDrawable
                }

                when (context.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight ->{

                        productImageBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.dark))
                        productImageBackground.findDrawableByLayerId(R.id.temporaryForeground).setTint(context.getColor(R.color.light))

                        productTextBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.dark))
                        productTextBackground.findDrawableByLayerId(R.id.temporaryForeground).setTint(context.getColor(R.color.lighter))

                        (viewHolder).productTitle.setTextColor(context.getColor(R.color.darker))
                        (viewHolder).productDescription.setTextColor(context.getColor(R.color.dark))

                    }
                    ThemeType.ThemeDark -> {

                        productImageBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.light))
                        productImageBackground.findDrawableByLayerId(R.id.temporaryForeground).setTint(context.getColor(R.color.dark))

                        productTextBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.light))
                        productTextBackground.findDrawableByLayerId(R.id.temporaryForeground).setTint(context.getColor(R.color.darker))

                        (viewHolder).productTitle.setTextColor(context.getColor(R.color.lighter))
                        (viewHolder).productDescription.setTextColor(context.getColor(R.color.light))

                    }
                }

                (viewHolder).productImage.background = productImageBackground
                (viewHolder).productInformation.background = productTextBackground

            }
            else -> {

            }

        }

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        when (singlePostItemsData[position].dataType) {

            PostsDataParameters.PostItemsViewParameters.PostParagraph -> {

                (viewHolder as PostViewParagraphAdapterViewHolder).postParagraph.setTextColor(when (context.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight -> {
                        context.getColor(R.color.dark)
                    }
                    ThemeType.ThemeDark -> {
                        context.getColor(R.color.light)
                    }
                    else -> {
                        context.getColor(R.color.dark)
                    }
                })

                singlePostItemsData[position].postItemParagraph?.let {

                    (viewHolder as PostViewParagraphAdapterViewHolder).postParagraph.text = Html.fromHtml(it.paragraphText, Html.FROM_HTML_MODE_LEGACY)

                    (viewHolder as PostViewParagraphAdapterViewHolder).postParagraph.setOnClickListener {



                    }

                    (viewHolder as PostViewParagraphAdapterViewHolder).postParagraph.paragraphsTextSelectionProcess(context)

                }

            }
            PostsDataParameters.PostItemsViewParameters.PostSubTitle -> {

                (viewHolder as PostViewSubTitleAdapterViewHolder).postSubTitle.setTextColor(when (context.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight -> {
                        context.getColor(R.color.darker)
                    }
                    ThemeType.ThemeDark -> {
                        context.getColor(R.color.lighter)
                    }
                    else -> {
                        context.getColor(R.color.darker)
                    }
                })

                singlePostItemsData[position].postItemSubTitle?.let {

                    (viewHolder as PostViewSubTitleAdapterViewHolder).postSubTitle.text = Html.fromHtml(it.subTitleText, Html.FROM_HTML_MODE_LEGACY)

                    (viewHolder as PostViewSubTitleAdapterViewHolder).postSubTitle.setOnClickListener {



                    }

                }

            }
            PostsDataParameters.PostItemsViewParameters.PostImage -> {

                (viewHolder as PostViewImageAdapterViewHolder).postImageDescription.setTextColor(when (context.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight ->{

                        context.getColor(R.color.dark)

                    }
                    ThemeType.ThemeDark -> {

                        context.getColor(R.color.light)

                    }
                    else -> {
                        context.getColor(R.color.dark)
                    }
                })

                singlePostItemsData[position].postItemImage?.let {

                    (viewHolder as PostViewImageAdapterViewHolder).postImageDescription.text = it.imageDescription

                    val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
                    drawableError?.setTint(context.getColor(R.color.red))

                    val requestOptions = RequestOptions()
                        .error(drawableError)

                    if (it.imageLink.contains(".gif")) {

                        Glide.with(context)
                            .asGif()
                            .load(it.imageLink)
                            .apply(requestOptions)
                            .transform(CenterInside(), RoundedCorners(11))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into((viewHolder as PostViewImageAdapterViewHolder).postImage)

                    } else {

                        Glide.with(context)
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

                                    context.runOnUiThread {
                                        (viewHolder as PostViewImageAdapterViewHolder).postImage.setImageDrawable(resource)
                                        (viewHolder as PostViewImageAdapterViewHolder).postImageLoading.visibility = View.GONE
                                    }

                                    return false
                                }

                            })
                            .submit()

                    }

                    if (it.targetLink.isNullOrBlank()) {

                        (viewHolder as PostViewImageAdapterViewHolder).postImage.setOnClickListener {

                            context.postsViewUiBinding.postMenuIcon.visibility = View.GONE
                            context.postsViewUiBinding.postMenuIcon.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))

                            context.postsViewUiBinding.postMenuButton.visibility = View.GONE
                            context.postsViewUiBinding.postMenuButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))

                            context.postsViewUiBinding.gesturePinchImageViewContainer.visibility = View.VISIBLE

                            context.gesturePinchImageView.arguments = Bundle().apply {
                                putByteArray("ImageByteArray", (viewHolder as PostViewImageAdapterViewHolder).postImage.drawable.convertDrawableToByteArray())
                            }

                            context.supportFragmentManager
                                .beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_right, 0)
                                .replace(R.id.gesturePinchImageViewContainer, context.gesturePinchImageView, "Gesture Pinch Image View")
                                .commit()

                        }

                    } else {

                        it.targetLink?.also { targetLink ->

                            (viewHolder as PostViewImageAdapterViewHolder).postImage.setOnClickListener { view ->

                                BuiltInWebView.show(
                                    context = context,
                                    linkToLoad = targetLink,
                                    gradientColorOne = context.dominantColor,
                                    gradientColorTwo = context.vibrantColor
                                )

                            }

                        }

                    }

                }

            }
            PostsDataParameters.PostItemsViewParameters.PostTextLink -> {

                singlePostItemsData[position].postItemTextLink?.let {

                    (viewHolder as PostViewTextLinkAdapterViewHolder).postTextLink.text = Html.fromHtml(
                        "<small>${context.getString(R.string.clickHere)}</small>" +
                                "<br/>" +
                                "<big>" + it.linkText + "</big>", Html.FROM_HTML_MODE_LEGACY
                    )

                    val linkContent: Document = Jsoup.parse(it.linkText)

                    (viewHolder as PostViewTextLinkAdapterViewHolder).postTextLink.setOnClickListener {

                        linkContent.select("a").first().attr("abs:href")?.let { aLink ->

                            BuiltInWebView.show(
                                context = context,
                                linkToLoad = aLink,
                                gradientColorOne = context.dominantColor,
                                gradientColorTwo = context.vibrantColor
                            )

                        }

                    }

                }

            }
            PostsDataParameters.PostItemsViewParameters.PostButton -> {

                singlePostItemsData[position].postItemButton?.let {

                    (viewHolder as PostViewButtonAdapterViewHolder).postButton.text = it.textButton

                    (viewHolder as PostViewButtonAdapterViewHolder).postButton.setOnClickListener { view ->

                        val buttonIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.linkButton))
                        buttonIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(buttonIntent)

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

                    val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
                    drawableError?.setTint(context.getColor(R.color.red))

                    val requestOptions = RequestOptions()
                        .error(drawableError)

                    Glide.with(context)
                        .asDrawable()
                        .load(it.instagramPostImage)
                        .apply(requestOptions)
                        .transform(CenterInside(), RoundedCorners(11))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((viewHolder as PostViewBlockQuoteInstagramAdapterViewHolder).instagramPostImage)

                    (viewHolder as PostViewBlockQuoteInstagramAdapterViewHolder).instagramPostUsername.text = "@" + Html.fromHtml(it.instagramUsername, Html.FROM_HTML_MODE_LEGACY)
                    (viewHolder as PostViewBlockQuoteInstagramAdapterViewHolder).instagramPostTitle.text = Html.fromHtml(it.instagramPostTitle, Html.FROM_HTML_MODE_LEGACY)

                    (viewHolder as PostViewBlockQuoteInstagramAdapterViewHolder).rootViewItem.setOnClickListener { view ->

                        Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse(it.instagramPostAddress)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(this@apply)
                        }

                    }

                }

            }
            PostsDataParameters.PostItemsViewParameters.ProductShowcase -> {

                (viewHolder as PostViewProductShowcaseAdapterViewHolder)

                val productImageBackground =  if (displayX(context) <= DpToInteger(context, 700)) {
                    context.getDrawable(R.drawable.product_image_showcase_item_background_vertical) as LayerDrawable
                } else {
                    context.getDrawable(R.drawable.product_image_showcase_item_background_horizontal) as LayerDrawable
                }

                val productTextBackground = if (displayX(context) <= DpToInteger(context, 700)) {
                    context.getDrawable(R.drawable.product_text_showcase_item_background_vertical) as LayerDrawable
                } else {
                    context.getDrawable(R.drawable.product_text_showcase_item_background_horizontal) as LayerDrawable
                }

                when (context.overallTheme.checkThemeLightDark()) {
                    ThemeType.ThemeLight ->{

                        productImageBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.dark))
                        productImageBackground.findDrawableByLayerId(R.id.temporaryForeground).setTint(context.getColor(R.color.light))

                        productTextBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.dark))
                        productTextBackground.findDrawableByLayerId(R.id.temporaryForeground).setTint(context.getColor(R.color.light))

                        (viewHolder).productTitle.setTextColor(context.getColor(R.color.darker))
                        (viewHolder).productDescription.setTextColor(context.getColor(R.color.dark))

                    }
                    ThemeType.ThemeDark -> {

                        productImageBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.light))
                        productImageBackground.findDrawableByLayerId(R.id.temporaryForeground).setTint(context.getColor(R.color.dark))

                        productTextBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.light))
                        productTextBackground.findDrawableByLayerId(R.id.temporaryForeground).setTint(context.getColor(R.color.dark))

                        (viewHolder).productTitle.setTextColor(context.getColor(R.color.lighter))
                        (viewHolder).productDescription.setTextColor(context.getColor(R.color.light))

                    }
                }

                (viewHolder).productImage.background = productImageBackground
                (viewHolder).productInformation.background = productTextBackground

                singlePostItemsData[position].productShowcaseItemData?.let { productShowcaseItemData ->

                    (viewHolder).productTitle.text = Html.fromHtml(productShowcaseItemData.titleOfProduct, Html.FROM_HTML_MODE_LEGACY)
                    (viewHolder).productBrand.text = Html.fromHtml(productShowcaseItemData.brandOfProduct, Html.FROM_HTML_MODE_LEGACY)
                    (viewHolder).productDescription.text = Html.fromHtml(productShowcaseItemData.descriptionOfProduct, Html.FROM_HTML_MODE_LEGACY)

                    val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
                    drawableError?.setTint(context.getColor(R.color.red))

                    val requestOptions = RequestOptions()
                        .error(drawableError)

                    Glide.with(context)
                        .asDrawable()
                        .load(productShowcaseItemData.linkToImageProduct)
                        .apply(requestOptions)
                        .transform(CenterCrop(), RoundedCorners(DpToInteger(context, 13)))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((viewHolder).productImage)

                    (viewHolder).productImage.setOnClickListener {

                        val buttonIntent = Intent(Intent.ACTION_VIEW, Uri.parse(productShowcaseItemData.linkToProduct))
                        buttonIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(buttonIntent)

                    }

                    (viewHolder).purchaseButton.setOnClickListener {

                        val buttonIntent = Intent(Intent.ACTION_VIEW, Uri.parse(productShowcaseItemData.linkToProduct))
                        buttonIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(buttonIntent)

                    }

                }

            }
            else -> {

            }

        }

    }

}