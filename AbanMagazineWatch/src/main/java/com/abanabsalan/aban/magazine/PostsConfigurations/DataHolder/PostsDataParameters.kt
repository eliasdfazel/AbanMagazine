 /*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 2:15 AM
 * Last modified 8/6/20 3:33 AM
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

        const val PostCategories: String = "categories"
        const val PostTags: String = "tags"

        const val PostDate: String = "date_gmt"
        const val Rendered: String = "rendered"
        const val ExtraLinks: String = "_links"
        const val HrefLinks: String = "href"

        const val RelatedPosts: String = "jetpack-related-posts"
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

        const val RelatedPosts: String = "RelatedPosts"
    }

    object RelatedPostsJsonStructure {
        const val RelatedPostLink: String = "url"
        const val RelatedPostTitle: String = "title"
        const val RelatedPostExcerpt: String = "excerpt"

        const val RelatedPostFeaturedImage: String = "img"
        const val RelatedPostFeaturedImageLink: String = "src"
    }

    object PostItemsViewParameters {
        const val PostParagraph: Int = 0
        const val PostSubTitle: Int = 1
        const val PostTextLink: Int = 2
        const val PostImage: Int = 3
        const val PostIFrame: Int = 4
        const val PostBlockQuoteInstagram: Int = 5
        const val PostBlockQuoteTwitter: Int = 6
        const val PostBlockQuoteFacebook: Int = 7
    }

    object PostItemsBlockQuoteType {
        const val BlockQuoteInstagram: String = "instagram"
        const val BlockQuoteTwitter: String = "twitter"
        const val BlockQuoteFacebook: String = "facebook"
    }

}

data class PostsItemData (var postLink: String,
                          var postId: String,
                          var postFeaturedImage: String,
                          var postTitle: String,
                          var postContent: String,
                          var postExcerpt: String,
                          var postPublishDate: String,
                          var postCategories: String,
                          var postTags: String,
                          var relatedPostsContent: String?)

data class PostItemParagraph(var paragraphText: String)
data class PostItemSubTitle(var subTitleText: String)
data class PostItemImage(var imageLink: String, var targetLink: String?)
data class PostItemTextLink(var linkText: String)
data class PostItemIFrame(var iFrameContent: String)
data class PostItemBlockQuoteInstagram(var instagramUsername: String, var instagramUserAddress: String, var instagramPostAddress: String, var instagramPostImage: String, var instagramPostTitle: String)

data class SinglePostItemData (var dataType: Int,
                               var postItemParagraph: PostItemParagraph?,
                               val postItemSubTitle: PostItemSubTitle?,
                               var postItemImage: PostItemImage?,
                               var postItemTextLink: PostItemTextLink?,
                               var postItemIFrame: PostItemIFrame?,
                               var postItemBlockQuoteInstagram: PostItemBlockQuoteInstagram?)