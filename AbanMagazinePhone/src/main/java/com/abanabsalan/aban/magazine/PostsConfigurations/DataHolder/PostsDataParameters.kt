/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/18/20 11:03 AM
 * Last modified 7/18/20 10:43 AM
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
        const val PostCommentsLink: String = "replies"


        const val PostDate: String = "date_gmt"
        const val Rendered: String = "rendered"
        const val ExtraLinks: String = "_links"
        const val HrefLinks: String = "href"
    }

    object PostParameters {
        const val PostLink: String = "PostLinkAddress"
        const val PostId: String = "PostId"

        const val PostFeaturedImage: String = "PostFeaturedImage"
        const val PostTitle: String = "PostTitle"
        const val PostContent: String = "content"
        const val PostExcerpt: String = "PostExcerpt"
        const val PostCommentsLink: String = "PostCommentsLink"

        const val PostPublishDate: String = "PostPublishDate"
    }

    object PostItemsViewParameters {
        const val PostParagraph: Int = 0
        const val PostSubTitle: Int = 1
        const val PostTextLink: Int = 2
        const val PostImage: Int = 3
        const val PostIFrame: Int = 4
    }
}

data class PostsItemData (var postLink: String,
                          var postId: String,
                          var postFeaturedImage: String,
                          var postTitle: String,
                          var postContent: String,
                          var postExcerpt: String,
                          var postPublishDate: String)

data class PostItemParagraph(var paragraphText: String)
data class PostItemSubTitle(var subTitleText: String)
data class PostItemImage(var imageLink: String)
data class PostItemTextLink(var linkText: String)
data class PostItemIFrame(var iFrameContent: String)

data class SinglePostItemData (var dataType: Int,
                               var postItemParagraph: PostItemParagraph?,
                               val postItemSubTitle: PostItemSubTitle?,
                               var postItemImage: PostItemImage?,
                               var postItemTextLink: PostItemTextLink?,
                               var postItemIFrame: PostItemIFrame?)