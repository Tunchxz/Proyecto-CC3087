package com.example.seafriend.ui.elements

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.example.seafriend.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.launch

@Composable
fun AuthOption(
    modifier: Modifier = Modifier,
//    context: Context,
    @DrawableRes image: Int,
    contentDescription: String? = null
//    onGetCredentialResponse: (Credential) -> Unit = {}
) {
//    val coroutineScope = rememberCoroutineScope()
//    val credentialManager = CredentialManager.create(context)

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.3f),
                shape = RoundedCornerShape(14.dp)
            )
            .clip(RoundedCornerShape(14.dp))
            .clickable {
                /*
                val googleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.default_web_client_id))
                    .build()

                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                coroutineScope.launch {
                    try {
                        val result = credentialManager.getCredential(
                            request = request,
                            context = context
                        )

                        onGetCredentialResponse(result.credential)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        if(e is GetCredentialException) throw e
                    }
                }
                */
            }
            .padding(horizontal = 35.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = contentDescription,
            modifier = Modifier.size(30.dp)
        )
    }
}