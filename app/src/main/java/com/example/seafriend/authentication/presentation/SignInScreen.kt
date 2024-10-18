@file:Suppress("UNCHECKED_CAST")

package com.example.seafriend.authentication.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.seafriend.R
import com.example.seafriend.authentication.data.AccountServiceImpl
import com.example.seafriend.ui.elements.AuthOption

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = viewModel<SignInViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SignInViewModel(
                    accountService = AccountServiceImpl()
                ) as T
            }
        }
    ),
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth(0.25f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Iniciar sesión",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )
        }

        OutlinedTextField(
            value = viewModel.signInState.email, // Use the email from signInState
            onValueChange = { viewModel.updateEmail(it) }, // Update the email
            label = { Text("Email") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.Gray,
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = viewModel.signInState.password, // Use the password from signInState
            onValueChange = { viewModel.updatePassword(it) }, // Update the password
            label = { Text("Contraseña") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.Gray,
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(), // Mask password input
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(
            onClick = {
                viewModel.onSignInClick() // Trigger sign-in
                if (viewModel.isSignInDone()) {
                    onSignInClick() // Call onSignInClick if successful
                } else {
                    Toast.makeText(context, "${viewModel.signInState.signInError}", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Ingresar",
                fontSize = 17.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Text(
            text = "También puedes iniciar sesión con...",
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .alpha(0.5f)
        )

        AuthOption(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            image = R.drawable.google
        )

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = "¿No tienes una cuenta? ",
                fontSize = 16.sp
            )
            Text(
                text = "Registrate",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }
    }
}