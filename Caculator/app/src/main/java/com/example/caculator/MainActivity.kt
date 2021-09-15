package com.example.caculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var calc: Calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
    }

    private fun setNum(btn: Button) {
        tv_main.append(btn.text)
    }

    private fun setOper(btn: Button) {
        if (tv_main.text != "") {
            if (calc.lastoper != "" && calc.lastoper != "+/-") {
                tv_main.text = tv_result.text
                tv_result.text = ""
                tv_main.append(" ${btn.text} ")
            } else tv_main.append(" ${btn.text} ")
        } else tv_main.append("${btn.text}")
    }

    fun onClick(v: View) {
        when (v) {
            num0 -> setNum(num0)
            num1 -> setNum(num1)
            num2 -> setNum(num2)
            num3 -> setNum(num3)
            num4 -> setNum(num4)
            num5 -> setNum(num5)
            num6 -> setNum(num6)
            num7 -> setNum(num7)
            num8 -> setNum(num8)
            num9 -> setNum(num9)
            btnCom -> setNum(btnCom)
            btnDel -> setOper(btnDel)
            btnPlus -> setOper(btnPlus)
            btnMin -> setOper(btnMin)
            btnPr -> setOper(btnPr)
        }
    }

    fun onClear(v: View) {
        tv_main.text = ""
        tv_result.text = ""
        calc.clear()
    }

    fun onBack(v: View) {
        val str = tv_main.text
        if (str.isNotEmpty()) {
            if (str[str.lastIndex].toString() != " ")
                tv_main.text = str.substring(0, str.length - 1)
            else tv_main.text = str.substring(0, str.length - 3)
            tv_result.text = ""
        }
    }

    fun onOperationClick(v: View) {
        when (v) {
            btnInv -> {
                if (tv_main.text != "" || tv_result.text != "") {
                    if (calc.lastoper != "") {
                        tv_main.text = tv_result.text
                        tv_result.text = ""
                        calc.oper = btnInv.text.toString()
                        calc.lastoper = ""
                        calc.num1 = tv_main.text.toString().toDouble()
                        tv_main.text = calc.result().toString()
                    } else {
                        calc.oper = btnInv.text.toString()
                        calc.num1 = tv_main.text.toString().toDouble()
                        tv_main.text = calc.result().toString()
                    }
                } else Toast.makeText(this, "Введите числа", Toast.LENGTH_SHORT).show()
            }
            btnSqrt -> {
                if (tv_main.text != "" || tv_result.text != "") {
                    if (calc.lastoper != "") {
                        tv_main.text = tv_result.text
                        tv_result.text = ""
                        calc.oper = btnSqrt.text.toString()
                        calc.lastoper = ""
                        calc.num1 = tv_main.text.toString().toDouble()
                        if (calc.num1 < 0) {
                            Toast.makeText(
                                    this,
                                    "Нельзя брать корень из отрицательного числа",
                                    Toast.LENGTH_SHORT
                            ).show()
                            onClear(btnClear)
                        } else
                            tv_main.text = calc.result().toString()
                    } else {
                        calc.oper = btnSqrt.text.toString()
                        calc.num1 = tv_main.text.toString().toDouble()
                        if (calc.num1 < 0) {
                            Toast.makeText(
                                    this,
                                    "Нельзя брать корень из отрицательного числа",
                                    Toast.LENGTH_SHORT
                            ).show()
                            onClear(btnClear)
                        } else
                            tv_main.text = calc.result().toString()
                    }
                } else Toast.makeText(this, "Введите числа", Toast.LENGTH_SHORT).show()
            }
            btnResult -> {
                if (tv_main.text != "" || tv_result.text != "") {
                    val separator = tv_main.text.toString().split(" ")
                    calc.num1 = separator[0].toDouble()
                    calc.oper = separator[1]
                    calc.lastoper = calc.oper
                    calc.num2 = separator[2].toDouble()
                    if (calc.result().toString() == "NaN" || calc.result()
                                    .toString() == "Infinity"
                    ) {
                        Toast.makeText(this, "Так делать нельзя!!!", Toast.LENGTH_LONG).show()
                        onClear(btnClear)
                    } else tv_result.text = calc.result().toString()
                } else Toast.makeText(this, "Введите числа", Toast.LENGTH_SHORT).show()
            }
        }
    }
}