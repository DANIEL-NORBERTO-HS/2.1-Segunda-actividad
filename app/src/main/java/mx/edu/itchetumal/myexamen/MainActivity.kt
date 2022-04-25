package mx.edu.itchetumal.myexamen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import mx.edu.itchetumal.myexamen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity","Creada nuevamente")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btTrue.setOnClickListener {
            checkQuestion(true)
        }
        binding.btFalse.setOnClickListener {
            checkQuestion(false)
        }

        binding.button.setOnClickListener {
            quizViewModel.moveToNext()
            updatePregunta()

        }
        updatePregunta()
    }
        private fun updatePregunta(){
            val preguntaResId = quizViewModel.currentQuestionText
            binding.textView.setText(preguntaResId)
        }
        private fun checkQuestion(answer: Boolean){
            val resp = if (answer == quizViewModel.currentQuestionAnswer){
                R.string.correct_toast
            }else{
                R.string.incorrect_toast
            }

            Toast.makeText(this, resp, Toast.LENGTH_SHORT).show()
        }
    }
