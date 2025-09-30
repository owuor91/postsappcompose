package dev.owuor91.postscompose.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.owuor91.postscompose.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.core.content.edit

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, viewmodel: AuthViewModel = koinViewModel()) {
  var username by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var pwVisibility by remember { mutableStateOf(false) }
  val context = LocalContext.current
  
  val loginResponse by viewmodel.loginResponse.observeAsState()
  
  fun valid(): Boolean {
    return username.isNotBlank() && password.isNotBlank()
  }
  
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    OutlinedTextField(
      value = username,
      onValueChange = { username = it },
      placeholder = { Text(text = "Enter username") }
    )
    
    Spacer(Modifier.height(16.dp))
    
    OutlinedTextField(
      value = password,
      onValueChange = { password = it },
      placeholder = { Text(text = "Enter password") },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      visualTransformation = if (pwVisibility) VisualTransformation.None else PasswordVisualTransformation()
    )
    
    Button(onClick = {
      if (valid()) {
        viewmodel.login(username, password)
      }
    }) {
      Text(text = "Login")
    }
    
    
    if(loginResponse!=null){
      val prefs = context.getSharedPreferences("POSTSAPP_PREFS", Context.MODE_PRIVATE)
      prefs.edit {
        putString("ACCESS_TOKEN", loginResponse?.accessToken)
        putString("EMAIL", loginResponse?.email)
        putString("FIRST_NAME", loginResponse?.firstName)
      }
      onLoginSuccess()
    }
    
  }
}