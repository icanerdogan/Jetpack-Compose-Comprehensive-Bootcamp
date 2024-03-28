package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.data.Resource
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.model.QuestionItem
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository
) : ViewModel() {

    val questionData: MutableState<Resource<ArrayList<QuestionItem>, Boolean, Exception>> = mutableStateOf(
        Resource(null, true, Exception(""))
    )

    init {
        getAllQuestions()
    }
    private fun getAllQuestions() {
        viewModelScope.launch {
            questionData.value.loading = true
            questionData.value = repository.getAllQuestions()

            if (questionData.value.data.toString().isNotEmpty()) {
                questionData.value.loading = false
            }
        }
    }

    fun getTotalQuestionCount() : Int? {
        return questionData.value.data?.toMutableList()?.size
    }
}