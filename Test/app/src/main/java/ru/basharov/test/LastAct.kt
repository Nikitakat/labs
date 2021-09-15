package ru.basharov.test

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_last.*

class LastAct : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)

        val im = intent.getDoubleExtra("Resultat", 0.0)
        val im1: String
        val longRes = im.toLong()
        if(im == longRes.toDouble())
            im1 = longRes.toString()
        else
            im1 = im.toString()

        textView4.text = "Поздравляю, вы прошли тест.\n Ваш балл - $im1\n Если хотите, можете начать заново, нажав соответствующую кнопку"

        buttonRestart.setOnClickListener{
            val i2 = Intent(this, MainActivity::class.java)
            startActivity(i2)
        }
    }
}