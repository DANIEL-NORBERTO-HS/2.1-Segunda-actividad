package mx.edu.itchetumal.myexamen

import android.util.Log
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {
    private val bancoPreguntas = listOf<Preguntas>(
        Preguntas(R.string.question_1, false),
        Preguntas(R.string.question_2, true),
        Preguntas(R.string.question_3, false),
        Preguntas(R.string.question_4, true),
        Preguntas(R.string.question_5, true)
    )
    var index = 0
    val  currentQuestionAnswer: Boolean
        get() = bancoPreguntas[index].respuesta
    val currentQuestionText: Int
        get() = bancoPreguntas[index].textResId
    fun moveToNext(){
        index= (index+1) % bancoPreguntas.size
    }

}