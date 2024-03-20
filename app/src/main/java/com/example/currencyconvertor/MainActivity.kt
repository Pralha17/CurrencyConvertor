package com.example.currencyconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconvertor.ui.theme.CurrencyConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CurrencyConverter()
                }
            }
        }
    }
}

@Composable
fun CurrencyConverter(){
    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Rupees") }
    var outputUnit by remember{ mutableStateOf("Rupees") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val iconversionFactor = remember { mutableStateOf(1.00) }
    val oconversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits(){
        val inputDoubleValue = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputDoubleValue*iconversionFactor.value*100.0/oconversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Currency Converter",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits() },
            label = {Text("Enter Value")})
        Row(modifier = Modifier.padding(16.dp)) {
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = "Input")
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {
                    iExpanded = false
                }) {
                    DropdownMenuItem(text = { Text("USD") }, onClick = {
                        iExpanded = false
                        inputUnit = "USD"
                        iconversionFactor.value = 82.85
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Euro") }, onClick = {
                        iExpanded = false
                        inputUnit = "Euro"
                        iconversionFactor.value = 89.861
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Rupee") }, onClick = {
                        iExpanded = false
                        inputUnit = "Rupee"
                        iconversionFactor.value = 0.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("GBP") }, onClick = {
                        iExpanded = false
                        inputUnit = "GBP"
                        iconversionFactor.value = 105.093
                        convertUnits()
                    })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = "Output")
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {
                    oExpanded = false
                }) {
                    DropdownMenuItem(text = { Text("USD") }, onClick = {
                        oExpanded = false
                        outputUnit = "USD"
                        oconversionFactor.value = 82.85
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Euro") }, onClick = {
                        oExpanded = false
                        outputUnit = "USD"
                        oconversionFactor.value = 89.861
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Rupee") }, onClick = {
                        oExpanded = false
                        outputUnit = "USD"
                        oconversionFactor.value = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("GBP") }, onClick = {
                        oExpanded = false
                        outputUnit = "USD"
                        oconversionFactor.value = 105.093
                        convertUnits()
                    })
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Result:$outputValue $outputUnit",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyConverterPreview(){
    CurrencyConverter()
}

