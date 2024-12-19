package com.example.lyfecycle

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var height_imtET: EditText
    private lateinit var weight_imtET: EditText

    private lateinit var calculateBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        height_imtET = findViewById(R.id.height_imtET)
        weight_imtET = findViewById(R.id.weight_imtET)

        calculateBTN = findViewById(R.id.calculateBTN)

        calculateBTN.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    override fun onClick(v: View) {
        if (height_imtET.text.isEmpty() || weight_imtET.text.isEmpty()) return

        val height = height_imtET.text.toString().toDouble() / 100
        val weight = weight_imtET.text.toString().toInt()

        val resultImt = weight / (height * height)

        calculateBTN.setOnClickListener {
            val intent = Intent(this, CalculateIMTActivity::class.java)
            intent.putExtra("key", resultImt.toString())
            startActivity(intent)
        }
    }
}