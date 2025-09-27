package com.example.assignmentthreeq5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignmentthreeq5.ui.theme.AssignmentThreeQ5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentThreeQ5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginForm(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    // 'remember' and 'mutableStateOf' are used to create and hold the state for the UI.
    // When the state changes (e.g., user types in a field), Compose automatically recomposes the UI.
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // These states hold whether an error should be shown for each field.
    var usernameError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    // 'LocalContext.current' is used to get the application context, which is needed to show Toasts.
    val context = LocalContext.current

    // Define the custom androidGreen color.
    val androidGreen = Color(0xFF3DDC84)

    // Column arranges its children vertically.
    // 'fillMaxSize' makes it take up the whole screen.
    // 'verticalArrangement' and 'horizontalAlignment' center the content on the screen.
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // A simple title for the form, using typography from the Material 3 theme.
        Text(text = "Login Form", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // This is the input field for the username.
        OutlinedTextField(
            value = username,
            // This lambda is called every time the user types a character. It updates the 'username' state.
            onValueChange = {
                username = it
                // If there was an error, clear it as soon as the user starts typing again.
                usernameError = false
            },
            label = { Text("Username") },
            // isError visually indicates that the field has a validation error (e.g., turns red).
            isError = usernameError,
            // 'singleLine' ensures the input field does not wrap to a new line.
            singleLine = true
        )
        // If 'usernameError' is true, this Text composable will be displayed below the field.
        if (usernameError) {
            Text(
                text = "Username field is empty",
                color = MaterialTheme.colorScheme.error, // Uses the error color from the theme.
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // This is the input field for the password.
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                // Clear the error as soon as the user starts typing.
                passwordError = false
            },
            label = { Text("Password") },
            // This hides the typed characters, showing dots instead, which is standard for password fields.
            visualTransformation = PasswordVisualTransformation(),
            // This sets the keyboard to one specifically designed for passwords.
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = passwordError,
            singleLine = true
        )
        // If 'passwordError' is true, show the error message.
        if (passwordError) {
            Text(
                text = "Password field is empty",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // A standard button for submitting the form.
        Button(
            onClick = {
                // This is the validation logic. We check if the fields are blank when the button is pressed.
                usernameError = username.isBlank()
                passwordError = password.isBlank()

                // If both fields are filled out, show a success message.
                if (!usernameError && !passwordError) {
                    Toast.makeText(context, "Login was successful!", Toast.LENGTH_SHORT).show()
                }
            },
            // Use ButtonDefaults to set the color of the button.
            colors = ButtonDefaults.buttonColors(containerColor = androidGreen)
        ) {
            Text("submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginFormPreview() {
    AssignmentThreeQ5Theme {
        LoginForm()
    }
}
