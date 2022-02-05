package com.example.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tvHr
import kotlinx.android.synthetic.main.activity_main.tvMin
import kotlinx.android.synthetic.main.activity_main.tvSec
import kotlinx.android.synthetic.main.activity_timer.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var typeNumber:Int = 0;
        val numbers  = ArrayList<Int>()
        val listener = View.OnClickListener { v ->

            v as Button

            val char = v.text
            numbers.add(Integer.parseInt(v.text.toString()))

            typeNumber += 1

            if(typeNumber == 1){
                tvSec.setText("0"+numbers[0].toString())
            }
            else if(typeNumber == 2){
                tvSec.setText("")
                tvSec.append(numbers[0].toString())
                tvSec.append(char.toString())
            }
            else if(typeNumber == 3){
                tvMin.setText("0"+numbers[0].toString())
                tvSec.setText("")
                tvSec.append(numbers[1].toString())
                tvSec.append(char.toString())
            }
            else if(typeNumber == 4){
                tvMin.setText("")
                tvMin.append(numbers[0].toString())
                tvMin.append(numbers[1].toString())
                tvSec.setText("")
                tvSec.append(numbers[2].toString())
                tvSec.append(char.toString())
            }
            else if(typeNumber == 5){
                tvHr.setText("0"+numbers[0].toString())
                tvMin.setText("")
                tvMin.append(numbers[1].toString())
                tvMin.append(numbers[2].toString())
                tvSec.setText("")
                tvSec.append(numbers[3].toString())
                tvSec.append(char.toString())
            }
            else if(typeNumber == 6){
                tvHr.setText("")
                tvHr.append(numbers[0].toString())
                tvHr.append(numbers[1].toString())
                tvMin.setText("")
                tvMin.append(numbers[2].toString())
                tvMin.append(numbers[3].toString())
                tvSec.setText("")
                tvSec.append(numbers[4].toString())
                tvSec.append(char.toString())
            }
            else {
                typeNumber = 6
            }
        }

        btn1.setOnClickListener(listener)
        btn2.setOnClickListener(listener)
        btn3.setOnClickListener(listener)
        btn4.setOnClickListener(listener)
        btn5.setOnClickListener(listener)
        btn6.setOnClickListener(listener)
        btn7.setOnClickListener(listener)
        btn8.setOnClickListener(listener)
        btn9.setOnClickListener(listener)
        btn0.setOnClickListener(listener)

        btnDelete.setOnClickListener {
            tvHr.setText("00")
            tvMin.setText("00")
            tvSec.setText("00")
            typeNumber = 0
            numbers.clear()
        }

        btnEnter.setOnClickListener {
            val intent = Intent(this, TimerActivity::class.java)
            intent.putExtra("hr", tvHr.text.toString())
            intent.putExtra("min", tvMin.text.toString())
            intent.putExtra("sec", tvSec.text.toString())

            startActivity(intent)
        }
    }

    private fun oldCode(){


    }

}