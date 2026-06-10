package com.example.campsite_commanderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import kotlinx.coroutines.delay
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampingApp()
        }
    }
}


//@Preview(showBackground = true)

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
        mutableStateListOf("2-person tent", "Gas stove", "Antiseptics")
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Color(0xFF0F1412),
            surface= Color(0xFF1B2A22),
            primary = Color(0xFF4CAF50),
            onBackground = Color.White,
            onSurface = Color.White,
            onPrimary = Color.Black
        )

    )
    {
        when {
            showSplash-> {
                SplashScreen{
                    showSplash = false
                }
            }
            showDetails ->{
                DetailsScreen(items, categories, quantities, notes){showDetails = false}
            }
            else -> {
                MainScreen(items, categories,quantities,notes){showDetails = true}
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
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    items: MutableList<String>,
    categories : MutableList<String>,
    quantities : MutableList<Int>,
    notes : MutableList<String>,
    onViewDetails : () -> Unit
){
    var item by remember {mutableStateOf("")}
    var category by remember {mutableStateOf("")}
    var quantity by remember {mutableStateOf("")}
    var note by remember {mutableStateOf("")}
    var errorMessage by remember {mutableStateOf("")}

    // Calculating the total items using a loop
    var totalItems = 0
    for (i in quantities) totalItems += i

    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Campsite Commander",
            color = Color.White)



        Text("Total Items Packed: $totalItems",
            color = Color.White)

        //User input fields
        OutlinedTextField(
            value = item,
            onValueChange = { item = it },
            label = { Text("Enter Item Name") },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedLabelColor= Color.White,
                unfocusedLabelColor = Color.White
            )
        )
        OutlinedTextField(
            value = category,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { category = it },
            label = { Text("Category Name (Shelter/Cooking/First Aid") },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedLabelColor= Color.White,
                unfocusedLabelColor = Color.White
            )
        )
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Enter quantity") },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedLabelColor= Color.White,
                unfocusedLabelColor = Color.White
            )
        )
        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Enter a note") },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedLabelColor= Color.White,
                unfocusedLabelColor = Color.White
            )
        )

            if(errorMessage.isNotBlank()){
                Text(
                    text = errorMessage,
                    color = Color(0xFFFFA276)
                )
            }

        Row (
            modifier= Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Button(onClick = {
                if (item.isNotBlank() && category.isNotBlank() && quantity.isNotBlank()) {
                    items.add(item)
                    categories.add(category)
                    quantities.add(quantity.toInt())
                    notes.add(note)

                    item = ""
                    category = ""
                    quantity = ""
                    note = ""
                    errorMessage = ""
                }else{
                    errorMessage = "⚠ Please item, category, quantity and a note."
                }
            }) {
                Text("Add Gear")
            }
            Button(onClick = onViewDetails) {
                Text("View Full List")
            }
        }
    }
}
@Composable
fun DetailsScreen(
    items: List<String>,
    categories:List<String>,
    quantities: List<Int>,
    notes:List<String>,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding().padding(16.dp)
    ) {
        Text("Campsite Commander List", color = Color.White)
        Spacer(modifier = Modifier.height(10.dp))

        TableRow(
            listOf("Item", "Category", "Qty", "Notes"),
                    isHeader =true
        )

        HorizontalLine()

        for (q in items.indices) {
            TableRow(
                listOf(
                    items[q],
                    categories[q],
                    quantities[q].toString(),
                    notes[q]


                )
            )

            HorizontalDivider()
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onBack) {
            Text("Back To Base")
        }

    }
}
@Composable
fun HorizontalLine() {
    HorizontalDivider(thickness = 1.dp)
}
@Composable
fun TableRow(
    cells: List<String>,
    isHeader: Boolean = false
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical =6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        cells.forEachIndexed{ index, cell ->
            Box(
                modifier= Modifier.weight(1f).padding(4.dp)
            ){
                Text(
                    text = cell,
                    color =Color.White,
                    style = if (isHeader)
                        MaterialTheme.typography.titleMedium
                    else
                        MaterialTheme.typography.bodyMedium


                )
            }
            if (index != cells.lastIndex){
                HorizontalDivider(
                    modifier = Modifier.width(1.dp).height(30.dp)
                )
            }
        }
    }
}


