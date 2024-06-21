package com.example.simplecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var text: TextView
    lateinit var equal: Button
    lateinit var btn_plus: Button
    lateinit var btn_minus: Button
    lateinit var btn_div: Button
    lateinit var btn_mul: Button
    lateinit var btn0: Button
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btn5: Button
    lateinit var btn6: Button
    lateinit var btn7: Button
    lateinit var btn8: Button
    lateinit var btn9: Button
    lateinit var dot: Button
    lateinit var clear: Button
    lateinit var back: Button

    private var canAddOperator = false
    private var canAddDot = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        text = findViewById(R.id.text_cal)
        equal = findViewById(R.id.btn_equal)
        btn_div = findViewById(R.id.btn_div)
        btn_mul = findViewById(R.id.btn_mul)
        btn_plus = findViewById(R.id.btn_plus)
        btn_minus = findViewById(R.id.btn_minus)
        dot = findViewById(R.id.btn_dot)
        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        clear = findViewById(R.id.all_clear)
        back = findViewById(R.id.backspace)


//        Listners for each numerical button

        btn0.setOnClickListener() {
            text.append("0")
            canAddOperator = true
        }
        btn1.setOnClickListener() {
            text.append("1")
            canAddOperator = true
        }
        btn2.setOnClickListener() {
            text.append("2")
            canAddOperator = true
        }
        btn3.setOnClickListener() {
            text.append("3")
            canAddOperator = true
        }
        btn4.setOnClickListener() {
            text.append("4")
            canAddOperator = true
        }
        btn5.setOnClickListener() {
            text.append("5")
            canAddOperator = true
        }
        btn6.setOnClickListener() {
            text.append("6")
            canAddOperator = true
        }
        btn7.setOnClickListener() {
            text.append("7")
            canAddOperator = true
        }
        btn8.setOnClickListener() {
            text.append("8")
            canAddOperator = true
        }
        btn9.setOnClickListener() {
            text.append("9")
            canAddOperator = true
        }
        dot.setOnClickListener() {
            if(canAddDot) {
                text.append(".")
                canAddOperator = false
                canAddDot = false
            }
        }



        //        creating result function
        fun result(): Double {
            val exp = text.text
            var stack_num = ArrayDeque<Double>()
            var stack_op = ArrayDeque<Char>()
            var num:String = ""
            var ope:Char
            var dig1:Double ; var dig2:Double

            for (i in exp.indices){
                if(exp[i]=='/' || exp[i]=='*' || exp[i]=='-' || exp[i]=='+'){
                    if(num!=""){
                        stack_num.addLast(num.toDouble())
                        num=""
                    }
                    stack_op.addLast(exp[i])
                }
                else{
                    num+=exp[i]
                }
            }
            if(num!=""){
                stack_num.addLast(num.toDouble())
                num=""
            }


            while(stack_op.size > 0){
                ope = stack_op.removeLast()
                dig1 = stack_num.removeLast()
                dig2 = stack_num.removeLast()

                if(dig1.toInt()==0 && ope=='/'){
                    print("error")
                }else {
                    var ans: Double = when (ope) {
                        '/' -> dig2 / dig1
                        '+' -> dig2 + dig1
                        '-' -> dig2 - dig1
                        '*' -> dig2 * dig1
                        else -> {
                            0.0
                        }
                    }
                    stack_num.addLast(ans)

                }

            }

            return stack_num.last()
        }

        try {


            btn_minus.setOnClickListener() {
                if (canAddOperator) {
                    text.append("-")
                    canAddDot = true
                    canAddOperator = false
                }
            }

            btn_plus.setOnClickListener() {
                if (canAddOperator) {
                    text.append("+")
                    canAddDot = true
                    canAddOperator = false
                }
            }

            btn_mul.setOnClickListener() {
                if (canAddOperator) {
                    text.append("*")
                    canAddDot = true
                    canAddOperator = false
                }
            }

            btn_div.setOnClickListener() {
                if (canAddOperator) {
                    text.append("/")
                    canAddDot = true
                    canAddOperator = false
                }
            }
        }catch (e:Exception){

        }

        // adding back button function
        back.setOnClickListener(){
            val str : String = text.text.toString()
            if(str.length>0){
                if(str.last() == '/' || str.last() == '*' || str.last() == '-' || str.last() == '+'){
                    canAddOperator = true
                    canAddDot = false
                }
                text.text = str.substring(0, str.length - 1)

            }
        }

        // Adding all clear button function
        clear.setOnClickListener(){
            text.text = ""
        }

        // Equal button

        equal.setOnClickListener() {
            text.text = result().toString()
        }
    }
}