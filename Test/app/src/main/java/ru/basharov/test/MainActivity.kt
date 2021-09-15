package ru.basharov.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {

    var resultat: Double = 0.0
    var eq: Int = 0
    var im: Boolean = false

    var a: Int = 0
    var b: Int = 0
    var c: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonPods.isClickable = false

        button1.setOnClickListener {
            ab()
            c = a + b

            txtPrimer.text = "$a + $b"
            pods()
            buttonOtv.setOnClickListener {
                button1.text = "X"
                otv()
                button1.isClickable = false
            }
        }

        button2.setOnClickListener {
            ab()
            c = a - b

            txtPrimer.text = "$a - $b"

            pods()

            buttonOtv.setOnClickListener {
                button2.text = "X"
                otv()
                button2.isClickable = false
            }
        }

        button3.setOnClickListener {
            a = (1..100).random()
            b = (2..10).random()
            c = a * b

            txtPrimer.text = "$a * $b"

            pods()

            buttonOtv.setOnClickListener {
                button3.text = "X"
                otv()
                button3.isClickable = false
            }
        }

        button4.setOnClickListener {
            a = 3
            b = 2

            while (a % b != 0) {
                ab()
            }

            c = a / b

            txtPrimer.text = "$a / $b"

            pods()

            buttonOtv.setOnClickListener {
                button4.text = "X"
                otv()
                button4.isClickable = false
            }
        }

        button5.setOnClickListener {
            a = (10..20).random()
            b = a * a
            c = a

            txtPrimer.text = "Корень из $b"

            pods()

            buttonOtv.setOnClickListener {
                button5.text = "X"
                otv()
                button5.isClickable = false
            }
        }
    }

    private fun pods(){
        im = false
        buttonPods.isClickable = true
        Otvet.setText("")
        textView3.text = ""
        buttonPods.setOnClickListener {
            val i = Intent(this, Podskazka::class.java)
            i.putExtra("MyStr", c.toString())
            startActivityForResult(i, 1)
        }
    }

    private fun ab(){
        a = (5..500).random()
        b = (5..500).random()
    }

    private fun otv(){
        if (Otvet.getText().toString() == c.toString()) {
            if (im)
                resultat += 0.5
            else
                resultat += 1
        }
        eq += 1
        if (eq != 5)
            textView3.text = "Ответ принят! Переходите к следующему примеру"
        else{
            val i = Intent(this, LastAct::class.java)
            i.putExtra("Resultat", resultat)
            startActivity(i)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1)
            if (resultCode == RESULT_OK)
                if (data != null) {
                    im = data.getBooleanExtra("MyOtv",false)
                }
    }
}