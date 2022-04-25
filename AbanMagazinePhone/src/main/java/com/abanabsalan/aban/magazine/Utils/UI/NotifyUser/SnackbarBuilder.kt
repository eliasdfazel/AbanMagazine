/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
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
    fun onActionButtonClicked(snackbar: Snackbar) {}
    fun onSnackbarShows(snackbar: Snackbar) {}
    fun onSnackbarDismissed(snackbar: Snackbar) {}
}

class SnackbarBuilder(private val context: Context) {

    fun show(rootView: ViewGroup,
             messageText: String,
             messageDuration: Int = Snackbar.LENGTH_LONG,
             actionButtonText: Int = android.R.string.ok,
             messageTextColor: Int = context.getColor(R.color.light),
             actionButtonTextColor: Int = context.getColor(R.color.pink),
             backgroundColor: Int = context.getColor(R.color.default_color_dark),
             snackbarActionHandlerInterface: SnackbarActionHandlerInterface) : Snackbar {

        val snackbar: Snackbar = Snackbar.make(
            rootView,
            messageText,
            messageDuration
        )
        snackbar.setTextColor(messageTextColor)
        snackbar.setActionTextColor(actionButtonTextColor)
        snackbar.setBackgroundTint(backgroundColor)
        snackbar.setAction(actionButtonText) {

            snackbarActionHandlerInterface.onActionButtonClicked(snackbar)

        }
        snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {

            override fun onShown(transientBottomBar: Snackbar?) {
                super.onShown(transientBottomBar)

                snackbarActionHandlerInterface.onSnackbarShows(snackbar)

            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)

                snackbarActionHandlerInterface.onSnackbarDismissed(snackbar)

            }

        })
        snackbar.show()

        return snackbar
    }
}