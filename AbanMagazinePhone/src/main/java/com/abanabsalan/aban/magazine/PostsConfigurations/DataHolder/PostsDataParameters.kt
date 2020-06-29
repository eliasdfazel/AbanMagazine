/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/28/20 5:47 PM
 * Last modified 6/28/20 4:49 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder


class PostsDataParameters {

    object JsonDataStructure {
        const val PostLink: String = "link"
        const val PostId: String = "id"

        const val FeauturedImage: String = "jetpack_featured_media_url"
        const val PostTitle: String = "title"
        const val PostContent: String = "content"
        const val PostExcerpt: String = "excerpt"
        const val PostComments: String = "replies"


        const val PostDate: String = "date_gmt"
        const val Rendered: String = "rendered"
        const val ExtraLinks: String = "_links"
    }

    object PostParameters {
        const val PostLink: String = "PostLinkAddress"
        const val PostId: String = "PostId"

        const val PostFeaturedImage: String = "PostFeaturedImage"
        const val PostTitle: String = "PostTitle"
        const val PostContent: String = "content"
        const val PostExcerpt: String = "PostExcerpt"
        const val PostComments: String = "PostComments"

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