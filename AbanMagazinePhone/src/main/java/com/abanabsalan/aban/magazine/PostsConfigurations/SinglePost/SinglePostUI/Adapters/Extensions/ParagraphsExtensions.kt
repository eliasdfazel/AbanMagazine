/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.Extensions

import android.content.Context
import android.content.Intent
import android.text.Spannable
import android.view.ActionMode
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatTextView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.SearchConfigurations.RemoteQuery.UI.SearchRemoteQuery


fun AppCompatTextView.paragraphsTextSelectionProcess(context: Context) {

    customSelectionActionModeCallback = object : ActionMode.Callback {

        var wordToSpan: Spannable = this@paragraphsTextSelectionProcess.text as Spannable

        override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {

            val inflater: MenuInflater = actionMode!!.menuInflater
            inflater.inflate(R.menu.text_selection_menu, menu)

            return true
        }

        override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {

            return false
        }

        override fun onActionItemClicked(actionMode: ActionMode?, menuItem: MenuItem?): Boolean {

            return when (menuItem?.itemId) {
                R.id.searchSelectedMenu -> {

                    val selectedText = this@paragraphsTextSelectionProcess.text.substring(
                        this@paragraphsTextSelectionProcess.selectionStart,
                        this@paragraphsTextSelectionProcess.selectionEnd
                    )

                    context.startActivity(Intent(context, SearchRemoteQuery::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        putExtra(Intent.EXTRA_PROCESS_TEXT, selectedText)
                    })

                    actionMode?.finish()

                    true
                }
                else -> false
            }

        }

        override fun onDestroyActionMode(actionMode: ActionMode?) {


        }

    }

}