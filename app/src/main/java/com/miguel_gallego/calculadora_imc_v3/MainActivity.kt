package com.miguel_gallego.calculadora_imc_v3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    // Weight
    lateinit var btnMinus: Button
    lateinit var tvWeight: TextView
    lateinit var btnPlus: Button
    // Height
    lateinit var tvHeight: TextView
    lateinit var sliHeight: Slider
    // Result
    lateinit var tvResult: TextView
    lateinit var tvResultDescr: TextView
    // Calc Button
    lateinit var btnCalc: Button

    var m = Model()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
    }

    fun initViews() {
        btnMinus = findViewById(R.id.btnMinus)
        tvWeight = findViewById(R.id.tvWeight)
        btnPlus = findViewById(R.id.btnPlus)

        tvHeight = findViewById(R.id.tvHeight)
        sliHeight = findViewById(R.id.sliHeight)

        tvResult = findViewById(R.id.tvResult)
        tvResultDescr = findViewById(R.id.tvResultDescr)

        btnCalc = findViewById(R.id.btnCalc)

        btnMinus.setOnClickListener {
            m.weight -= 1
            tvWeight.text = "${m.weight.toInt()} kg"
            m.calcBMI()
            updateBMIView()
        }

        btnPlus.setOnClickListener {
            m.weight += 1
            tvWeight.text = "${m.weight.toInt()} kg"
            m.calcBMI()
            updateBMIView()
        }

        sliHeight.addOnChangeListener { _, value, _ ->
            m.height = value
            tvHeight.text = "${m.height.toInt()} cm"
            m.calcBMI()
            updateBMIView()
        }

        btnCalc.setOnClickListener {
            m.calcBMI()
            updateBMIView()
        }
    }

    fun updateBMIView() {
        val bmiColor = getColor(m.idColor)
        tvResult.setTextColor(bmiColor)
        tvResult.text = m.bmiFormatted()
        tvResultDescr.setTextColor(bmiColor)
        tvResultDescr.text = m.strBMICategory()
    }
}