package ru.basharov.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_podskazka.*

class Podskazka : Activity() {

    var qq = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podskazka)
        buttonNo.setOnClickListener{
            peredacha()
        }

        buttonClose.setOnClickListener{
            peredacha()
        }


        buttonYes.setOnClickListener{
            qq = true
            buttonNo.isClickable = false
            val arguments = intent.extras
            val name = arguments!!["MyStr"].toString()
            val name2 = name[0]
            if (name2 == '-') {
                val name1 = name.substring(0, 2)
                textView5.text = "Первая цифра вашего ответа $name1.."
            }
            else
                textView5.text = "Первая цифра вашего ответа $name2.."
        }
    }

    private fun peredacha(){
        val i1 = Intent(this, MainActivity::class.java)
        i1.putExtra("MyOtv", qq)
        setResult(RESULT_OK, i1)
        finish()
    }
}