package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app2moneycounter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app2moneycounter.ui.theme.JetpackComposeComprehensiveBootcampTheme

class ComposeFundamentalsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeComprehensiveBootcampTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    /*IntroComposePage(
                        name = "Android",
                        modifier = Modifier.padding()
                    )*/
                    MyClickApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyClickAppPreview() {
    JetpackComposeComprehensiveBootcampTheme {
        MyClickApp()
    }
}

@Composable
fun MyClickApp() {
    var moneyCounter by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF546E7A)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$$moneyCounter", style = TextStyle(
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
            ))
            Spacer(modifier = Modifier.height(50.dp))
            CreateCircle(CircleShape, moneyCounter) {
                moneyCounter += 1
            }
            if (moneyCounter > 25) {
                Text(text = "Lots of money!")
            }
        }
    }
}


@Composable
fun CreateCircle(shape: Shape, moneyCount: Int, updatedMoneyCounter: (Int) -> Unit) {

    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)
        .clip(CircleShape)
        .clickable {
            // moneyCounter += 1
            updatedMoneyCounter(moneyCount)
            Log.d("ComposeFundamentalsActivity", "CreateCircle: Tap!")
        },
        elevation = CardDefaults.elevatedCardElevation(50.dp)) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(shape)
                .background(Color.White)
                .border(
                    shape = CircleShape,
                    color = Color.Green,
                    width = 2.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Tap!", color = Color.Black)
        }
    }
}

@Composable
fun IntroComposePage(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        ShowAge(age = 24)
    }
}

@Composable
fun ShowAge(age: Int) {
    Text(text = "$age")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeComprehensiveBootcampTheme {
        IntroComposePage("Android")
    }
}