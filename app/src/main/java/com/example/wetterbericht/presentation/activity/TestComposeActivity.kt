package com.example.wetterbericht.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.wetterbericht.R
import com.example.wetterbericht.presentation.activity.onboarding.OnBoardingViewModel
import com.example.wetterbericht.presentation.activity.ui.theme.WetterberichtTheme
import com.example.wetterbericht.viewmodel.ViewModelFactory

class TestComposeActivity : ComponentActivity() {
    private val localViewModel : OnBoardingViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localViewModel.getOnBoardingStatus().observe(this){ status->
            if (status){
//                Handler(Looper.getMainLooper()).postDelayed({
//                    startActivity(Intent(this, MainActivity::class.java))
//                },100)
            }else{
//                Handler(Looper.getMainLooper()).postDelayed({
//                    startActivity(Intent(this, OnBoardingActivity::class.java))
//                },100)
            }
        }
        setContent {
            WetterberichtTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondary
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xFF3C84AB))
            .wrapContentSize(Alignment.Center),
    ) {
        Text(
            text = "Plan",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(
                Font(
                    resId = R.font.righteous
                )
            ),
            color = Color.White,
            fontSize = 60.sp
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    Greeting("Android")
}