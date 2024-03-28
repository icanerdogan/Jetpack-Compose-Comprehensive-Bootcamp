package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.model

import com.google.gson.annotations.SerializedName

data class QuestionItem(
    @SerializedName("question")
    val questionText: String,
    @SerializedName("answer")
    val questionAnswer: String,
    @SerializedName("category")
    val questionCategory: String,
    @SerializedName("choices")
    val questionChoices: List<String>
)