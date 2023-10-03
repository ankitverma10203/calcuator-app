package com.ankit.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var lastOperator : String = ""
    var res : Double = 0.0;
    var textView : TextView? = null
    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.textView)
    }

    fun onClick(view:View) {
        lastNumeric = true
        val value = (view as Button).text;
        textView?.text = "${textView?.text as String}$value"
    }

    fun onDecimal(view:View) {
        if (lastNumeric && !lastDot) {
            val value = (view as Button).text;
            textView?.text = "${textView?.text as String}$value"
            lastDot = true
        }
    }

    fun onClear(view:View) {
        textView?.text = ""
        res = 0.0
        lastOperator = ""
        lastNumeric = false
        lastDot = false
    }

    fun onOperator(view:View) {
        lastNumeric = false
        lastDot = false
        val value : String = (view as Button).text as String;
        if(lastOperator != "") onResult(null)
        lastOperator = value
        textView?.text = "${textView?.text as String}$value"
    }

    fun onResult(view:View?) {
        val lstIdx = (textView?.text as String).lastIndexOf(lastOperator);
        if (lstIdx >= 0) {
            val value1 = (textView?.text as String).substring(0, lstIdx)
            val value2 = (textView?.text as String).substring(lstIdx + 1)
            res = if (value1 == "") 0.0
            else if (value2 == "")
                value1.toDouble()
            else {
                val val1 = value1.toDouble()
                val val2 = value2.toDouble()
                when(lastOperator) {
                    getString(R.string.btn_txt_div) -> val1 / val2
                    getString(R.string.btn_txt_multi) -> val1 * val2
                    getString(R.string.btn_txt_sum) -> val1 + val2
                    getString(R.string.btn_txt_sub) -> val1 - val2
                    else -> res
                }
            }
        }

        textView?.text = res.toString()
        lastOperator = ""
    }
}