package com.example.passwordvalidation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // show password rules in text view

        findViewById<EditText>(R.id.editText)

        findViewById<TextView>(R.id.textView).text = "Password minimum length 8"
        findViewById<TextView>(R.id.textView).append("\n1 uppercase")
        findViewById<TextView>(R.id.textView).append("\n1 number")
        findViewById<TextView>(R.id.textView).append("\n1 special character")


        // add text changed listener for edit text
        findViewById<EditText>(R.id.editText).addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?,
                                           start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?,
                                       start: Int, before: Int, count: Int) {
                s?.apply {
                    // check user input a valid formatted password
                    if (isValidPassword() && toString().length>=8) {
                        findViewById<EditText>(R.id.editText).error == null
                    }else{
                        // show error on input invalid password
                        findViewById<EditText>(R.id.editText).error = "invalid password."
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })

    }


    // extension function to validate password rules/patterns
    fun CharSequence.isValidPassword(): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        val pattern = Pattern.compile(passwordPattern)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }
}