package com.example.jetpacktestproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktestproject.ui.theme.JetpackTestProjectTheme
import kotlinx.coroutines.NonDisposableHandle.parent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTestProjectTheme {
                myCard()
            }
        }
    }
}

@Composable
fun myCard(){
    Row(modifier = Modifier
        .clickable(onClick = { /* Ignoring onClick */ })
        .padding(7.dp)
        .fillMaxWidth()
    ) {
       Surface(modifier = Modifier.size(50.dp),
           shape = CircleShape,
           color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
       ){
        //image here
       }
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Text("Matvey Mironov", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("Junior Android Developer", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackTestProjectTheme {
        myCard()
    }
}