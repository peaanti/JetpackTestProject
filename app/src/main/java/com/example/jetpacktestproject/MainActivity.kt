package com.example.jetpacktestproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.jetpacktestproject.ui.theme.JetpackTestProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTestProjectTheme {
                ShowEverything()
            }
        }
    }
}

@Composable
fun ShowEverything(){
    val repos = Repos()
    val getAllData = repos.getAllData()
    Column() {
        MyCard()
        LazyColumn {

            items(items = getAllData) { repo ->
                CustomItem(repo = repo)
            }
        }
    }
}

@Composable
fun WebViewer() {
    val context = LocalContext.current
    Text(
        text = "https://github.com/peaanti", modifier = Modifier
            .padding(top = 1.dp)
            .clickable(onClick = {
                startActivity(
                    context,
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/peaanti")),
                    null
                )
            }),
        fontSize = 18.sp
    )
}

@Composable
fun MyCard() {
    Row(
        modifier = Modifier
            .padding(7.dp)
            .fillMaxWidth()
            .background(color = Color(150, 241, 255), shape = RoundedCornerShape(10))
    ) {
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .size(130.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),

            ) {
            Image(painterResource(id = R.drawable.my_photo), contentDescription = "my photo")
        }
        Column(
            modifier = Modifier
                .padding(start = 2.dp)
        ) {
            Text(
                "Matvey Mironov",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    "Junior Android Developer",
                    style = MaterialTheme.typography.body2,
                    fontSize = 16.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 2.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_location_city_24),
                    contentDescription = "location city",
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(text = "Russia, Moscow", fontSize = 18.sp)
            }
            Text(text = "16 years old", modifier = Modifier.padding(top = 1.dp), fontSize = 18.sp)
            WebViewer()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackTestProjectTheme {
        MyCard()
    }
}