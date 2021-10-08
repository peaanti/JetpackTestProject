package com.example.jetpacktestproject

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktestproject.ui.theme.JetpackTestProjectTheme
import kotlinx.coroutines.NonDisposableHandle.parent
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import android.net.Uri.parse
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URI
import java.util.logging.Level.parse


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromApi()
        setContent {
            JetpackTestProjectTheme {
                myCard()
            }
        }
    }

    var repos : List<Repo>? = null

    private fun getDataFromApi() {

        ApiService.endpoint.getRepos()
            .enqueue(object : Callback<List<Repo>>{
                override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                    if (response.isSuccessful){
                        repos = response.body()!!
                    }
                }

                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                    Log.e("error", t.localizedMessage)
                }

            })

    }

}

@Composable
fun WebViewer(){
    val context = LocalContext.current
    Text(text = "https://github.com/peaanti", modifier = Modifier
        .padding(top = 1.dp)
        .clickable(onClick = {
            startActivity(
                context,
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/peaanti")),
                null
            )
        }),
        fontSize = 18.sp)
}

@Composable
fun myCard() {
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
        myCard()
    }
}