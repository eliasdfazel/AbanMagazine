/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/12/20 9:16 AM
 * Last modified 11/12/20 8:35 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.DataHolder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesDataParameters
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesItemData
import com.abanabsalan.aban.magazine.InstagramConfigurations.StoryHighlights.Network.DataHolder.StoryHighlightsItemData
import com.abanabsalan.aban.magazine.InstagramConfigurations.StoryHighlights.Network.Endpoints.StoryHighlightsEndpoint
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder.ProductShowcase
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder.ProductShowcaseItemData
import com.abanabsalan.aban.magazine.Utils.BlogContent.LanguageUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import org.json.JSONArray
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class HomePageLiveData : ViewModel() {

    val newestPostsLiveItemData: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    val categoriesLiveItemData: MutableLiveData<ArrayList<CategoriesItemData>> by lazy {
        MutableLiveData<ArrayList<CategoriesItemData>>()
    }

    val specificCategoryLiveItemData: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    val productShowcaseLiveItemData: MutableLiveData<ArrayList<ProductShowcaseItemData>> by lazy {
        MutableLiveData<ArrayList<ProductShowcaseItemData>>()
    }

    val recommendedPostsLiveItemData: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    val instagramStoryHighlightsLiveItemData: MutableLiveData<ArrayList<StoryHighlightsItemData>> by lazy {
        MutableLiveData<ArrayList<StoryHighlightsItemData>>()
    }

    val controlLoadingView: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    /**
     * True To Force Reset Theme
     **/
    val toggleTheme: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun prepareRawDataToRenderForNewestPosts(postsJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        controlLoadingView.postValue(true)

        val newestPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

        for (i in 0 until postsJsonArray.length()) {
            val postJsonObject = postsJsonArray.getJSONObject(i)
            Log.d("${this@HomePageLiveData.javaClass.simpleName} PrepareRawDataToRenderForNewestPosts", postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId))

            newestPostsItemData.add(PostsItemData(
                postLink = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostLink),
                postId = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId),
                postFeaturedImage = postJsonObject.getString(PostsDataParameters.JsonDataStructure.FeauturedImage),
                postTitle = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostTitle).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postContent = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostContent).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postExcerpt = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostExcerpt).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postPublishDate = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostDate),
                postCategories = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostCategories).join(","),
                postTags = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostTags).join(","),
                relatedPostsContent = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.RelatedPosts).toString()
            ))

        }

        newestPostsLiveItemData.postValue(newestPostsItemData)

    }

    fun prepareRawDataToRenderForCategories(categoriesJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        controlLoadingView.postValue(true)

        val languageUtils: LanguageUtils = LanguageUtils()

        val categoriesItemData: ArrayList<CategoriesItemData> = ArrayList<CategoriesItemData>()

        for (i in 0 until categoriesJsonArray.length()) {
            val categoryJsonObject = categoriesJsonArray.getJSONObject(i)
            Log.d("${this@HomePageLiveData.javaClass.simpleName} PrepareRawDataToRenderForCategories", categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryName))

            if (categoryJsonObject.getInt(CategoriesDataParameters.JsonDataStructure.CategoryParentId) == 0) {

                when (LanguageUtils.SelectedLanguage) {
                    PostsDataParameters.Language.Persian -> {
                        Log.d(this@HomePageLiveData.javaClass.simpleName, "Language.Persian | ${categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryName)}")

                        if (languageUtils.checkRtl(categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryName))) {

                            categoriesItemData.add(CategoriesItemData(
                                categoryLink = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryLink),
                                categoryId = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryId),
                                categoryName = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryName),
                                categoryDescription = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryDescription)
                            ))

                        } else {



                        }

                    }
                    PostsDataParameters.Language.English -> {
                        Log.d(this@HomePageLiveData.javaClass.simpleName, "Language.English | ${categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryName)}")

                        if (languageUtils.checkRtl(categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryName))) {



                        } else {

                            categoriesItemData.add(CategoriesItemData(
                                categoryLink = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryLink),
                                categoryId = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryId),
                                categoryName = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryName),
                                categoryDescription = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryDescription)
                            ))

                        }

                    }
                }

            }

        }

        categoriesLiveItemData.postValue(categoriesItemData)

    }

    fun prepareRawDataToRenderForSpecificPosts(featuredPostsJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        controlLoadingView.postValue(true)

        val specificCategoryPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

        for (i in 0 until featuredPostsJsonArray.length()) {
            val postJsonObject = featuredPostsJsonArray.getJSONObject(i)
            Log.d("${this@HomePageLiveData.javaClass.simpleName} PrepareRawDataToRenderForSpecificPosts", postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId))

            specificCategoryPostsItemData.add(PostsItemData(
                postLink = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostLink),
                postId = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId),
                postFeaturedImage = postJsonObject.getString(PostsDataParameters.JsonDataStructure.FeauturedImage),
                postTitle = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostTitle).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postContent = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostContent).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postExcerpt = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostExcerpt).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postPublishDate = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostDate),
                postCategories = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostCategories).join(","),
                postTags = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostTags).join(","),
                relatedPostsContent = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.RelatedPosts).toString()
            ))

        }

        specificCategoryLiveItemData.postValue(specificCategoryPostsItemData)

    }

    fun prepareRawDataToRenderForProductShowcase(rawProductShowcase: String) {

        val productShowcaseItemData: ArrayList<ProductShowcaseItemData> = ArrayList<ProductShowcaseItemData>()

        val productShowcaseContent: Document = Jsoup.parse(rawProductShowcase)

        val allHtmlElement = productShowcaseContent.allElements

        allHtmlElement.forEachIndexed { index, element ->

            if (element.`is`("p")) {
                Log.d(this@HomePageLiveData.javaClass.simpleName, "Link ${element}")

                val productName = element.getElementById(ProductShowcase.ProductLink).text()
                val linkToProduct = element.getElementById(ProductShowcase.ProductLink).select("a").first().attr("abs:href")
                val linkToProductImage = element.getElementById(ProductShowcase.ProductImage).select("a").first().attr("abs:href")

                productShowcaseItemData.add(
                    ProductShowcaseItemData(
                        titleOfProduct = productName,
                        linkToProduct = linkToProduct,
                        linkToImageProduct = linkToProductImage,
                        descriptionOfProduct = null,
                        brandOfProduct = null
                    ))

            }

        }

        productShowcaseLiveItemData.postValue(productShowcaseItemData)

    }

    fun prepareRawDataToRenderForRecommendedPosts(postsJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        controlLoadingView.postValue(true)

        val recommendedPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

        for (i in 0 until postsJsonArray.length()) {
            val postJsonObject = postsJsonArray.getJSONObject(i)
            Log.d("${this@HomePageLiveData.javaClass.simpleName} PrepareRawDataToRenderForRecommendedPosts", postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId))

            recommendedPostsItemData.add(PostsItemData(
                postLink = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostLink),
                postId = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId),
                postFeaturedImage = postJsonObject.getString(PostsDataParameters.JsonDataStructure.FeauturedImage),
                postTitle = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostTitle).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postContent = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostContent).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postExcerpt = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostExcerpt).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postPublishDate = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostDate),
                postCategories = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostCategories).join(","),
                postTags = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostTags).join(","),
                relatedPostsContent = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.RelatedPosts).toString()
            ))

        }

        recommendedPostsLiveItemData.postValue(recommendedPostsItemData)

    }

    fun prepareRawDataToRenderForInstagramStoryHighlights(rawInstagramStoryHighlights: String) {

        val storyHighlightsItemData: ArrayList<StoryHighlightsItemData> = ArrayList<StoryHighlightsItemData>()

        val storyHighlightsContent: Document = Jsoup.parse(rawInstagramStoryHighlights)

        val allHtmlElement = storyHighlightsContent.allElements

        allHtmlElement.forEachIndexed { index, element ->
            if (element.`is`("a")) {
                Log.d(this@HomePageLiveData.javaClass.simpleName, "Link ${element}")

                val linkContent: Document = Jsoup.parse(element.toString())
                val linkToStoryHighlights = linkContent.select("a").first().attr("abs:href")

                val linkToStoryHighlightsCoverImage = StoryHighlightsEndpoint.InstagramStoryHighlightsCoverImageBaseLink + linkToStoryHighlights.replace("https://www.instagram.com/stories/highlights/", "").replace("/", "")

                storyHighlightsItemData.add(
                    StoryHighlightsItemData(
                        linkToStoryHighlight = linkToStoryHighlights,
                        storyHighlightsName = element.text(),
                        storyHighlightsCoverImage = linkToStoryHighlightsCoverImage
                    ))

            }
        }

        instagramStoryHighlightsLiveItemData.postValue(storyHighlightsItemData)

    }

}