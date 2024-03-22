package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app3tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app3tipcalculator.components.InputField
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app3tipcalculator.ui.theme.JetpackComposeComprehensiveBootcampTheme
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app3tipcalculator.util.calculateTotalPerPerson
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app3tipcalculator.util.calculateTotalTip
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app3tipcalculator.widgets.RoundIconButton

class TipCalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                MainContent()
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    JetpackComposeComprehensiveBootcampTheme {
        Scaffold(
            modifier = modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun TopHeader(totalPerPerson: Double = 0.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(15.dp)
            .clip(RoundedCornerShape(CornerSize(12.dp))),
        color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val formatTotalPerPerson = "%.2f".format(totalPerPerson)

            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "$$formatTotalPerPerson",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}


@Preview
@Composable
fun MainContent(modifier: Modifier = Modifier) {
    BillForm {
        Log.d("MainContent", "Bill: $it")
    }
}

@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = {}
) {
    val totalBillState = remember { mutableStateOf("") }
    val validState = remember(totalBillState.value) { totalBillState.value.trim().isNotEmpty() }
    val sliderPositionState = remember { mutableFloatStateOf(0f) }

    val splitByState = remember { mutableIntStateOf(1) }
    val range = IntRange(start = 1, endInclusive = 100)
    val tipPercentage = (sliderPositionState.floatValue * 100).toInt()
    val tipAmountState = remember { mutableDoubleStateOf(0.0) }

    val totalPerPersonState = remember {
        mutableDoubleStateOf(0.0)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    TopHeader(totalPerPerson = totalPerPersonState.doubleValue)

    Surface(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(
            modifier = modifier
                .padding(6.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValChange(totalBillState.value.trim())
                    keyboardController?.hide()
                }
            )

            SplitRow(splitByState = splitByState,validState = validState, range = range, totalPerPersonState = totalPerPersonState, totalBillState = totalBillState, tipPercentage = tipPercentage)
            TipContent(sliderPositionState, validState = validState, totalBillState = totalBillState, tipPercentage = tipPercentage, tipAmountState = tipAmountState, totalPerPersonState = totalPerPersonState, splitByState = splitByState)
        }
    }
}

@Composable
private fun SplitRow(modifier: Modifier = Modifier, validState: Boolean,splitByState: MutableIntState, range: IntRange, totalPerPersonState: MutableDoubleState, totalBillState: MutableState<String>, tipPercentage: Int) {
    if (validState) {
    Row(
        modifier = modifier.padding(3.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Split",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(120.dp))
        Row(
            modifier = modifier.padding(horizontal = 3.dp),
            horizontalArrangement = Arrangement.End
        ) {
            RoundIconButton(
                imageVector = Icons.Default.Remove,
                onClick = {
                    splitByState.intValue = if(splitByState.intValue > 1) splitByState.intValue - 1 else 1
                    totalPerPersonState.doubleValue = calculateTotalPerPerson(totalBillState.value.toDouble(), splitByState.intValue, tipPercentage)
                }
            )
            Text(
                text = "${splitByState.intValue}",
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp, end = 10.dp)
            )
            RoundIconButton(
                imageVector = Icons.Default.Add,
                onClick = {
                    if (splitByState.intValue < range.last) {
                        splitByState.intValue += 1
                        totalPerPersonState.doubleValue = calculateTotalPerPerson(totalBillState.value.toDouble(), splitByState.intValue, tipPercentage)
                    }
                }
            )
        }
    }
    }
}

@Composable
private fun TipContent(sliderPositionState: MutableFloatState,validState: Boolean, totalBillState: MutableState<String>, tipPercentage: Int, tipAmountState: MutableDoubleState, totalPerPersonState: MutableDoubleState, splitByState: MutableIntState) {
    if (validState) {
        TipRow(tipAmountState = tipAmountState)
        TipBar(
            sliderPositionState = sliderPositionState,
            totalBillState = totalBillState,
            tipPercentage = tipPercentage,
            tipAmountState = tipAmountState,
            totalPerPersonState = totalPerPersonState,
            splitByState = splitByState
        )
    }
}

@Composable
fun TipRow(modifier: Modifier = Modifier, tipAmountState: MutableDoubleState) {
    Row(
        modifier = modifier.padding(horizontal = 3.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Tip",
            modifier = modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = modifier.width(200.dp))
        Text(
            text = "$${tipAmountState.doubleValue}",
            modifier = modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun TipBar(modifier: Modifier = Modifier, totalBillState:  MutableState<String>, sliderPositionState: MutableFloatState, tipPercentage: Int, tipAmountState: MutableDoubleState, totalPerPersonState: MutableDoubleState, splitByState: MutableIntState) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$tipPercentage%")
        Spacer(modifier = modifier.height(14.dp))

        Slider(
            modifier = modifier.padding(horizontal = 16.dp),
            value = sliderPositionState.floatValue,
            onValueChange = {
                sliderPositionState.floatValue = it
                tipAmountState.doubleValue = calculateTotalTip(totalBillState.value.toDouble(), tipPercentage)
                totalPerPersonState.doubleValue = calculateTotalPerPerson(totalBillState.value.toDouble(), splitByState.intValue, tipPercentage)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApp {
        MainContent()
    }
}