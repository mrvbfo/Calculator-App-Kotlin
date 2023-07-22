package com.mervebfo.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mervebfo.calculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        supportActionBar?.hide()

        binding.apply {
            btn0.appendClick("0")
            btn1.appendClick("1")
            btn2.appendClick("2")
            btn3.appendClick("3")
            btn4.appendClick("4")
            btn5.appendClick("5")
            btn6.appendClick("6")
            btn7.appendClick("7")
            btn8.appendClick("8")
            btn9.appendClick("9")
            btnminus.appendClick("-")
            btnmult.appendClick("*")
            btnplus.appendClick("+")
            btndivide.appendClick("/")
            btndot.appendClick(".")
            btnbracket1.appendClick("(")
            btnbracket2.appendClick(")")
            btnAC.setOnClickListener {
                binding.processtext.text = null
                binding.resulttext.text = ""
            }
            btndel.setOnClickListener {
                val expression = processtext.text.toString()
                if (expression.isNotEmpty()) {
                    processtext.text = expression.substring(0, expression.length - 1)
                }
            }

            btnequal.setOnClickListener {
                try {
                    val expression = ExpressionBuilder(binding.processtext.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()

                    if (result == longResult.toDouble()) {
                        binding.resulttext.text = longResult.toString()
                    } else {
                        binding.resulttext.text = result.toString()
                    }
                }catch (e:Exception){
                    Log.d("Exception", "Message: ${e.message}")
                }

            }
        }
    }
    private fun View.appendClick(string: String) {
        setOnClickListener {
            binding.processtext.append(string)
        }
    }
}