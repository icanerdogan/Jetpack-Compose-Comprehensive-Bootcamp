package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.repository

import android.util.Log
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.data.Resource
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.model.QuestionItem
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionAPI: QuestionAPI
) {

    private val listOfQuestion = Resource<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions() : Resource<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            listOfQuestion.loading = true
            listOfQuestion.data = questionAPI.getAllQuestionsNetwork()

            if (listOfQuestion.data.toString().isNotEmpty()) listOfQuestion.loading = false

        } catch (e: Exception) {
            listOfQuestion.exception = e
            Log.e("QuestionRepository", "getAllQuestions: ${listOfQuestion.exception!!.localizedMessage}")
        }
        return listOfQuestion
    }
}