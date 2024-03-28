package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.screen

import android.provider.CalendarContract.Colors
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.QuestionViewModel
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.model.QuestionItem
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.ui.theme.JetpackComposeComprehensiveBootcampTheme
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.ui.theme.mDarkPurple
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.ui.theme.mLightBlue
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.ui.theme.mLightGray
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.ui.theme.mLightPurple
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.ui.theme.mOffDarkPurple
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.ui.theme.mOffWhite

@Composable
fun JetTriviaApp(modifier: Modifier = Modifier, viewModel: QuestionViewModel = hiltViewModel()) {
    JetpackComposeComprehensiveBootcampTheme {
        Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                QuestionScreen(viewModel)
            }
        }
    }
}

@Composable
fun QuestionScreen(viewModel: QuestionViewModel) {
    val questions = viewModel.questionData.value.data?.toMutableList()
    val questionIndex = remember { mutableIntStateOf(0) }

    if (viewModel.questionData.value.loading == true) {
        CircularProgressIndicator()
    } else {
        val question = try {
            questions?.get(questionIndex.intValue)
        } catch (exception: Exception) {
            null
        }
        if (questions != null && question != null) {
            QuestionDisplay(question, questionIndex, viewModel) {
                questionIndex.intValue += 1
            }
        }
    }

    Log.d("JetTriviaAppActivity", "Questions: ${questions?.size}")
}

//@Preview
@Composable
private fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionViewModel,
    onNextClicked: (Int) -> Unit = {}
) {
    val choicesState = remember(question) { question.questionChoices.toMutableList() }
    val answerState = remember(question) { mutableStateOf<Int?>(null) }

    val correctAnswerState = remember(question) { mutableStateOf<Boolean?>(null) }
    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState[it] == question.questionAnswer
        }
    }

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = mDarkPurple
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            if (questionIndex.value >= 3) ShowProgressBar(questionIndex.value)

            QuestionTracker(counter = questionIndex.value, outOf = viewModel.getTotalQuestionCount() ?: 0)
            DrawDottedLine(pathEffect = pathEffect)

            Column {
                Text(
                    text = question.questionText,
                    modifier = Modifier
                        .padding(6.dp)
                        .align(alignment = Alignment.Start)
                        .fillMaxHeight(0.3f),
                    fontSize = 17.sp,
                    color = mOffWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp
                )

                // Choices
                choicesState.forEachIndexed { index, s ->
                    Row(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                width = 4.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        mOffDarkPurple,
                                        mOffDarkPurple
                                    )
                                ),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .clip(RoundedCornerShape(50))
                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(
                            selected = (answerState.value == index),
                            onClick = { updateAnswer(index) },
                            modifier = Modifier.padding(start = 16.dp),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = if (correctAnswerState.value == true && index == answerState.value) {
                                    Color.Green.copy(0.2f)
                                } else {
                                    Color.Red.copy(alpha = 0.2f)
                                }
                            )
                        )

                        val annotatedString = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Light,
                                    color = if (correctAnswerState.value == true && index == answerState.value) {
                                        Color.Green
                                    } else if (correctAnswerState.value == false && index == answerState.value) {
                                        Color.Red
                                    } else {
                                        mOffWhite
                                    },
                                    fontSize = 17.sp
                                )
                            ) {
                                append(s)
                            }
                        }
                        Text(text = annotatedString, modifier = Modifier.padding(6.dp))
                    }
                }

                // Next Button
                Button(
                    onClick = { onNextClicked(questionIndex.value) },
                    modifier = Modifier
                        .padding(3.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = mLightBlue
                    )
                ) {
                    Text(
                        text = "Next", modifier = Modifier.padding(4.dp),
                        color = mOffWhite, fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun QuestionTracker(modifier: Modifier = Modifier, counter: Int = 10, outOf: Int = 100) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
                withStyle(
                    style = SpanStyle(
                        color = mLightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp
                    )
                ) {
                    append("Question ${counter + 1}/")
                    withStyle(
                        style = SpanStyle(
                            color = mLightGray,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        )
                    ) {
                        append("$outOf")
                    }
                }
            }
        },
        modifier = modifier.padding()
    )
}

@Composable
fun DrawDottedLine(modifier: Modifier = Modifier, pathEffect: PathEffect) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = mLightGray, start = Offset(0f, 0f),
            end = Offset(size.width, y = 0f),
            pathEffect = pathEffect
        )
    }
}

@Preview
@Composable
private fun ShowProgressBar(score: Int = 12) {
    val gradientBrush = Brush.linearGradient(listOf(Color(0xFFF95975), Color(0xFFBE6BE5)))
    val progressFactor by remember(score) { mutableFloatStateOf(score * 0.005f) }

    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(colors = listOf(mLightPurple, mLightPurple)),
                shape = RoundedCornerShape(34.dp)
            )
            .clip(RoundedCornerShape(50))
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(progressFactor)
                .background(brush = gradientBrush),
            enabled = false,
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(1.dp)
        ) {
            Text(text = (score * 10).toString(),
                modifier = Modifier.clip(RoundedCornerShape(25.dp)).fillMaxHeight(0.90f)
                    .fillMaxWidth()
                    .padding(6.dp),
                color = mOffWhite,
                textAlign = TextAlign.Center
            )
        }
    }
}