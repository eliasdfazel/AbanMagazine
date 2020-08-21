/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/21/20 8:37 AM
 * Last modified 8/21/20 8:37 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.AccountManager

import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.System.SystemInformation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.AccountPicker
import java.util.*

interface UserSignIn {
    fun signInSuccessful(accountName: String)
    fun signInDismissed()
}

class UserInformation(private val context: SinglePostView, private val userSignIn: UserSignIn) {

    companion object {
        const val AccountPickerRequestCode = 123
        const val GoogleSignInRequestCode = 321
    }

    fun startSignInProcess() {

        val systemInformation = SystemInformation(context)

        if (systemInformation.getCountryIso().toUpperCase(Locale.getDefault()) == "IR"
            || systemInformation.getCountryIso() == "Undefined") {

            context.userSignIn = userSignIn

            val accountPickerIntent = AccountPicker.newChooseAccountIntent(
                AccountPicker.AccountChooserOptions.Builder()
                    .setAllowableAccountsTypes(listOf("com.google"))
                    .build()
            )
            context.startActivityForResult(accountPickerIntent, UserInformation.AccountPickerRequestCode)

        } else {

            if (context.firebaseAuth.currentUser == null) {

                val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.webClientId))
                    .requestEmail()
                    .build()

                val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
                googleSignInClient.signInIntent.run {
                    context.startActivityForResult(this@run, UserInformation.GoogleSignInRequestCode)
                }

            }

        }

    }

}