 /*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 5/10/21, 8:05 AM
 * Last modified 5/10/21, 6:34 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder

 import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder.InPostProductShowcaseItemData


 class PostsDataParameters {

    object Language {
        const val English: String = "2418"
        const val Persian: String = "2052"
    }

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
        const val PostContent: String = "PostContent"
        const val PostTags: String = "PostTags"
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
        const val PostButton: Int = 3
        const val PostImage: Int = 4
        const val PostIFrame: Int = 5
        const val PostBlockQuoteInstagram: Int = 6
        const val PostBlockQuoteTwitter: Int = 7
        const val PostBlockQuoteFacebook: Int = 8
        const val ProductShowcase: Int = 9
        const val AuthorBlock: Int = 10
    }

    object PostItemsBlockQuoteType {
        const val BlockQuoteInstagram: String = "instagram"
        const val BlockQuoteTwitter: String = "twitter"
        const val BlockQuoteFacebook: String = "facebook"
    }

     object PostAuthorBlock {
         const val AuthorBlock: String = "authorBlock"
         const val AuthorBlockName: String = "authorBlockName"
         const val AuthorBlockBiography: String = "authorBlockBiography"
         const val AuthorBlockImage: String = "authorBlockImage"
         const val AuthorBlockSocialMedia: String = "authorBlockSocialMedia"
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
data class PostItemImage(var imageLink: String, var targetLink: String?, var imageDescription: String?)
data class PostItemTextLink(var linkText: String)
data class PostItemButton(var linkButton: String, var textButton: String)
data class PostItemIFrame(var iFrameContent: String)
data class PostItemBlockQuoteInstagram(var instagramUsername: String, var instagramUserAddress: String, var instagramPostAddress: String, var instagramPostImage: String, var instagramPostTitle: String)
data class PostAuthorBlock(var authorBlockName: String, var authorBlockImage: String?, var authorBlockBiography: String?)

data class SinglePostItemData (var dataType: Int,
                               var postItemParagraph: PostItemParagraph?,
                               val postItemSubTitle: PostItemSubTitle?,
                               var postItemImage: PostItemImage?,
                               var postItemTextLink: PostItemTextLink?,
                               var postItemButton: PostItemButton?,
                               var postItemIFrame: PostItemIFrame?,
                               var postItemBlockQuoteInstagram: PostItemBlockQuoteInstagram?,
                               var inPostProductShowcaseItemData: InPostProductShowcaseItemData?,
                               var postAuthorBlock: PostAuthorBlock?)