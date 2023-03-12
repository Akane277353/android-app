package com.example.jetpack_launcher

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_launcher.`class`.appli
import com.example.jetpack_launcher.ui.theme.JetpacklauncherTheme
import com.example.jetpack_launcher.utility.getApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpacklauncherTheme {
                // A surface container using the 'background' color from the theme
                androidx.compose.material3.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    View()
                    println(getApp(LocalContext.current))
                }
            }
        }
    }
}

@Composable
fun View() {
    var txt by remember { mutableStateOf("oui") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        androidx.compose.material3.Button(onClick = { txt = "non" }) {
            androidx.compose.material3.Text(txt)
        }
        androidx.compose.material3.Text(text = "ok")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpacklauncherTheme {
        View()
    }
}

