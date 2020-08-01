/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/31/20 9:42 PM
 * Last modified 7/31/20 9:37 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.DependencyInjections.SubComponents

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.Modules.Network.NetworkConnectionModule
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.Scopes.ActivityScope
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [NetworkConnectionModule::class])
interface NetworkSubDependencyGraph {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance appCompatActivity: AppCompatActivity, @BindsInstance constraintLayout: ConstraintLayout): NetworkSubDependencyGraph
    }

    fun inject(homePage: HomePage)
    fun inject(allCategoryPosts: AllCategoryPosts)
    fun inject(favoritesPostsView: FavoritesPostsView)
}