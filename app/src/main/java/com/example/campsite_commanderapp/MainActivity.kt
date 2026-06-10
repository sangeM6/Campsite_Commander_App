package com.example.campsite_commanderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.campsite_commanderapp.ui.theme.Campsite_CommanderAppTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import kotlinx.coroutines.delay



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Campsite_CommanderAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Campsite_CommanderAppTheme {
        Greeting("Android")
    }
}
@Composable
fun CampingApp(){
    var showSplash by remember {mutableStateOf(true)}
    var showDetails by remember {mutableStateOf(false)}

    // Parallel Arrays and Sample Data
    val items = remember {
        mutableStateListOf("Tent", "Stove", "First Aid Kit")
    }
    val categories = remember {
        mutableStateListOf("Shelter", "Cooking", "First Aid")
    }
    val quantities = remember {
        mutableStateListOf(1,1,1)
    }
    val notes = remember {
        mutableStateListOf("2-person tent", "Gas stove", "Bandages and antiseptic")
    }
    androidx.compose.material3.MaterialTheme(colorScheme = androidx.compose.material3.darkColorScheme())
    {
        when {
            showSplash-> {
                SplashScreen{
                    showSplash = false
                }
            }
            showDetails ->{
                DetailsScreen(items, categories, quantities, notes){showDetails = true}
            }
        }
    }
}
@Composable
fun SplashScreen(onFinish: () -> Unit){
    LaunchedEffect(Unit) {
        delay(3000)
        onFinish()
    }

    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id=R.drawable.logo),
            contentDescription = "Loading",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Campsite Commander",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}