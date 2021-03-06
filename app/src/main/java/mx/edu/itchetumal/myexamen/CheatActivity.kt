package mx.edu.itchetumal.myexamen
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itchetumal.myexamen.IntentExtraKeyConstants.EXTRA_ANSWER_IS_TRUE
import mx.edu.itchetumal.myexamen.IntentExtraKeyConstants.EXTRA_ANSWER_SHOWN
class CheatActivity: AppCompatActivity() {

    private var mAnswerIsTrue: Boolean = false

    private lateinit var mAnswerTextView: TextView
    private lateinit var mShowAnswerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        mAnswerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        mAnswerTextView = findViewById(R.id.answer_text_view)

        mShowAnswerButton = findViewById(R.id.show_answer_button)
        mShowAnswerButton.setOnClickListener({
            mAnswerTextView.setText(
                if (mAnswerIsTrue) R.string.true_button
                else R.string.false_button
            )
            setAnswerShownResult(true)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val cx: Int = mShowAnswerButton.width / 2
                val cy: Int = mShowAnswerButton.height / 2
                val radius: Float = mShowAnswerButton.width.toFloat()
                var anim: Animator = ViewAnimationUtils.createCircularReveal(mShowAnswerButton, cx, cy, radius, 0.toFloat())
                anim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        mShowAnswerButton.visibility = View.INVISIBLE
                    }
                })
                anim.start()
            } else {
                mShowAnswerButton.visibility = View.INVISIBLE
            }
        })
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            val intent = Intent(packageContext, CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            return intent
        }

        fun wasAnswerShown(result: Intent): Boolean {
            return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
        }
    }
}