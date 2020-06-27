/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/27/20 9:22 AM
 * Last modified 6/27/20 9:03 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder


class PostsDataParameters {

    object JsonDataStructure {
        const val FeauturedImage: String = "jetpack_featured_media_url"
    }

    object PostParameters {
        const val PostLinkAddress: String = "PostLinkAddress"
        const val PostTitle: String = "PostTitle"
        const val PostExcerpt: String = "PostExcerpt"
        const val PostFeaturedImage: String = "PostFeaturedImage"
        const val PostPublishDate: String = "PostPublishDate"
    }

    object PostItemsParameters {
        const val PostParagraph: Int = 0
        const val PostTextLink: Int = 1
        const val PostImage: Int = 2
        const val PostIFrame: Int = 3
    }
}

data class PostItemParagraph(var paragraphText: String)
data class PostItemImage(var imageLink: String )
data class PostItemTextLink(var linkText: String)
data class PostItemIFrame(var iFrameContent: String)

data class PostItemData (var dataType: Int,
                         var postItemParagraph: PostItemParagraph?,
                         var postItemImage: PostItemImage?,
                         var postItemTextLink: PostItemTextLink?,
                         var postItemIFrame: PostItemIFrame?)