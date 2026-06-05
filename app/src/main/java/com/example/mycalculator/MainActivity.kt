package com.example.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private  lateinit var resultTextView: TextView
    private lateinit  var previousCalculationTextView: TextView
    private var isNewOperation = true
    private var firstNumber=0.0
    private var operation=""


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        resultTextView=findViewById(R.id.resultTextView)
        previousCalculationTextView=findViewById(R.id.previousCalculationTextView)

        val btn0 =findViewById<Button>(R.id.btn0)
        val btn1 =findViewById<Button>(R.id.btn1)
        val btn2 =findViewById<Button>(R.id.btn2)
        val btn3 =findViewById<Button>(R.id.btn3)
        val btn4 =findViewById<Button>(R.id.btn4)
        val btn5 =findViewById<Button>(R.id.btn5)
        val btn6 =findViewById<Button>(R.id.btn6)
        val btn7 =findViewById<Button>(R.id.btn7)
        val btn8 =findViewById<Button>(R.id.btn8)
        val btn9 =findViewById<Button>(R.id.btn9)

        val div =findViewById<Button>(R.id.btnDivide)
        val mul =findViewById<Button>(R.id.btnMultiply)
        val sub =findViewById<Button>(R.id.btnMinus)
        val add =findViewById<Button>(R.id.btnAdd)

        val clear =findViewById<Button>(R.id.btnClear)
        val backspace =findViewById<Button>(R.id.btnBackspace)
        val percent =findViewById<Button>(R.id.btnPercent)
        val equal =findViewById<Button>(R.id.btnEquals)
        val dot =findViewById<Button>(R.id.btnDot)

        btn0.setOnClickListener {appendNumber("0")}
        btn1.setOnClickListener {appendNumber("1")}
        btn2.setOnClickListener {appendNumber("2")}
        btn3.setOnClickListener {appendNumber("3")}
        btn4.setOnClickListener {appendNumber("4")}
        btn5.setOnClickListener {appendNumber("5")}
        btn6.setOnClickListener {appendNumber("6")}
        btn7.setOnClickListener {appendNumber("7")}
        btn8.setOnClickListener {appendNumber("8")}
        btn9.setOnClickListener {appendNumber("9")}
        dot.setOnClickListener {appendNumber(".")}

        add.setOnClickListener {setOperation("+")  }
        sub.setOnClickListener {setOperation("-")  }
        mul.setOnClickListener {setOperation("*") }
        div.setOnClickListener {setOperation("÷")  }
        percent.setOnClickListener { setOperation("%") }

        equal.setOnClickListener {calculateResult()  }
        clear.setOnClickListener {clearCalculator()  }
        backspace.setOnClickListener { deleteNum() }

    }

    fun deleteNum() {
        if (resultTextView.text.isNotEmpty() && resultTextView.text.toString() != "0.0" && resultTextView.text.toString() != "Error"){
            resultTextView.text=resultTextView.text.dropLast(1)
        }
        else{
            Toast.makeText(this, "Invalid operation", Toast.LENGTH_LONG).show()
        }
    }


    fun clearCalculator() {
        resultTextView.text="0.0"
        previousCalculationTextView.text=""
        firstNumber=0.0
        operation=""
        isNewOperation=true
    }

    fun calculateResult() {
        try {
            val secondNumber: Double = resultTextView.text.toString().toDouble()
            if (operation == "") {
                resultTextView.text = "0.0"
            } else {
                val result: Double = when (operation) {
                    "+" -> firstNumber + secondNumber
                    "-" -> firstNumber - secondNumber
                    "*" -> firstNumber * secondNumber
                    "÷" -> {
                        if (secondNumber == 0.0) {
                            throw ArithmeticException("Divition by zero")
                        } else {
                            firstNumber / secondNumber
                        }
                    }

                    "%" -> firstNumber * secondNumber / 100
                    else -> secondNumber
                }
                previousCalculationTextView.text = "${firstNumber} $operation $secondNumber ="
                resultTextView.text = result.toString()
                isNewOperation = true
            }
        }
        catch (e: Exception){
            resultTextView.text="Error"
        }
    }
    fun setOperation(op: String) {

        firstNumber=resultTextView.text.toString().toDouble()
        operation=op
        isNewOperation=true
        previousCalculationTextView.text="$firstNumber $operation"
        resultTextView.text=firstNumber.toString();
    }

    private fun appendNumber(number: String){
        if (number == "." && resultTextView.text.contains(".")){
            return
        }
        if (isNewOperation){
            if (number == "."){
                resultTextView.text="0."
            }
            else {
                resultTextView.text = number
            }
            isNewOperation=false
        }
        else{
//            resultTextView.append(number)
              resultTextView.text="${resultTextView.text}$number"
        }
    }
}