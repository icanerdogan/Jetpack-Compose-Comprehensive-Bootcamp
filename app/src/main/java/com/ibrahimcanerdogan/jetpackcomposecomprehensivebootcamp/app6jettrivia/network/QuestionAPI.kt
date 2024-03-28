package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.network

import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionAPI {

    @GET("world.json")
    suspend fun getAllQuestionsNetwork() : Question


}