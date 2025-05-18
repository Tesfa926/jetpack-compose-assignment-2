package com.example.taskNest.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytodo.R

@Composable
fun BackgroundWithCircles(content: @Composable BoxScope.() -> Unit ={}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0ECC3))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0x804CAF50),
                radius = size.width * 0.2f,
                center = androidx.compose.ui.geometry.Offset(x = size.width * 0.1f, y = size.height * 0.1f)
            )
            drawCircle(
                color = Color(0x804CAF50),
                radius = size.width * 0.2f,
                center = androidx.compose.ui.geometry.Offset(x = size.width * 0.2f, y = size.height * 0.0f)
            )
        }
        content()
    }
}

@Composable
fun WelcomeScreen(onGetStarted: () -> Unit = {}) {
    BackgroundWithCircles {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Illustration
            Image(
                painter = painterResource(id = R.drawable.illustration),
                contentDescription = null,
                modifier = Modifier
                    .height(220.dp)
                    .padding(bottom = 24.dp)
            )

            // Title
            Text(
                text = "Get things done with TaskNest",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color(0xFF222222),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle
            Text(
                text = "Welcome to TaskNest!\nStay organized, stay focused, and make every task count. Start your productive journey—one checklist at a time.",
                fontSize = 18.sp,
                color = Color(0xFF222222),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Emoji line
            Text(
                text = "\u270D\uFE0F Let's get things done!",
                fontSize = 18.sp,
                color = Color(0xFF222222),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Get Started Button
            Button(
                onClick = onGetStarted,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text(
                    text = "Get Started",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}
