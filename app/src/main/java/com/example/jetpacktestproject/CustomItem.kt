package com.example.jetpacktestproject

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktestproject.ui.theme.JetpackTestProjectTheme
import com.example.jetpacktestproject.ui.theme.Shapes
import com.example.jetpacktestproject.ui.theme.Typography


@Composable
fun CustomItem(repo: Repo) {
    Card(
        shape = Shapes.small,
        modifier = Modifier
            .clickable {}
            .wrapContentSize()
            .padding(horizontal = 8.dp,vertical = 3.dp)
            .fillMaxWidth(),
        elevation = 15.dp,
    ){
        Column() {
            Row() {
                Text(
                    text = repo.name,
                    color = Color.Blue,
                    fontSize = Typography.h6.fontSize,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = repo.visibility,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(50)

                        )
                )
            }
            Row() {
                androidx.compose.foundation.Canvas(modifier = Modifier.size(24.dp)) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height
                    val color: Color = when (repo.language) {
                        "Java" -> Color.Yellow
                        "Kotlin" -> Color.Magenta
                        else -> {
                            Color.Black
                        }
                    }

                    drawCircle(
                        color = color,
                        center = Offset(
                            x = canvasWidth - (canvasWidth / 3),
                            y = canvasHeight / 4
                        ),
                        radius = size.minDimension / 6
                    )
                }
                Text(text = repo.language)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MyPreview() {
    JetpackTestProjectTheme {
        CustomItem(repo = Repo("G-Market", "public", "Java"))
    }
}