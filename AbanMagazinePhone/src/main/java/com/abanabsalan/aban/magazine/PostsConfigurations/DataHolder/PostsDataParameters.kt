/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/25/20 2:00 PM
 * Last modified 6/25/20 1:31 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder

import android.graphics.drawable.Drawable


class PostsDataParameters {

    object PostParameters {
        const val PostLinkAddress: Int = 0
        const val PostTitle: Int = 1
        const val PostExcerpt: Int = 2
        const val PostFeaturedImage: Int = 3
        const val postPublishDate: Int = 4
    }

    object PostItemsParameters {
        const val PostParagraph: Int = 0
        const val PostTextLink: Int = 1
        const val PostImage: Int = 2
        const val PostIFrame: Int = 3
    }
}

data class PostItemParagraph(var paragraphText: String)
data class PostItemImage(var paragraphText: Drawable)
data class PostItemTextLink(var linkText: String)
data class PostItemIFrame(var iFrameContent: String)

data class PostItemData (var dataType: Int,
                         var postItemParagraph: PostItemParagraph?,
                         var postItemImage: PostItemImage?,
                         var postItemTextLink: PostItemTextLink?,
                         var postItemIFrame: PostItemIFrame?)