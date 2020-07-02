/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/2/20 3:53 PM
 * Last modified 7/2/20 3:53 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.NotifyUser

import android.content.Context
import android.view.ViewGroup
import com.abanabsalan.aban.magazine.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

interface SnackbarActionHandlerInterface {
    fun onActionButtonClicked() {}
    fun onSnackbarShows() {}
    fun onSnackbarDismissed() {}
}

class SnackbarBuilder(private val context: Context) {

    fun show(rootView: ViewGroup,
             messageText: String,
             messageDuration: Int = Snackbar.LENGTH_LONG,
             actionButtonText: Int = android.R.string.ok,
             messageTextColor: Int = context.getColor(R.color.light),
             actionButtonTextColor: Int = context.getColor(R.color.pink),
             backgroundColor: Int = context.getColor(R.color.default_color_darker),
             snackbarActionHandlerInterface: SnackbarActionHandlerInterface) {

        val snackbar: Snackbar = Snackbar.make(
            rootView,
            messageText,
            messageDuration
        )
        snackbar.setTextColor(messageTextColor)
        snackbar.setActionTextColor(actionButtonTextColor)
        snackbar.setBackgroundTint(backgroundColor)
        snackbar.setAction(actionButtonText) {

            snackbarActionHandlerInterface.onActionButtonClicked()

        }
        snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {

            override fun onShown(transientBottomBar: Snackbar?) {
                super.onShown(transientBottomBar)

                snackbarActionHandlerInterface.onSnackbarShows()

            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)

                snackbarActionHandlerInterface.onSnackbarDismissed()

            }

        })
        snackbar.show()
    }
}