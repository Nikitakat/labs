package com.example.caculator

import kotlin.math.sqrt

class Calculator {
    var num1: Double = 0.0
    var num2: Double = 0.0
    var lastoper: String = ""
    var oper: String = ""

    fun result(): Double {
        when (oper) {
            "+" -> return this.num1 + this.num2
            "-" -> return this.num1 - this.num2
            "X" -> return this.num1 * this.num2
            "/" -> return this.num1 / this.num2
            "√" -> return sqrt(num1)
            "+/-" -> return num1 * (-1.0)
            "=" -> return this.num1

        }
        return 0.0
    }

    fun clear() {
        num1 = 0.0
        num2 = 0.0
        oper = ""
        lastoper = ""
    }
}