package com.example.timer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_timer.*
import java.lang.Integer.parseInt
import java.lang.Long.parseLong

class TimerActivity : AppCompatActivity() {

    var start_seconds = 0L
    lateinit var countdown_timer: CountDownTimer
    var isPaused: Boolean = false
    var isReset:Boolean = false
    var isStopped:Boolean = false
    var current_time_in_second = 0L
    lateinit var mediaPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        var hr = parseLong(intent.getStringExtra("hr"))
        var min = parseLong(intent.getStringExtra("min"))
        var sec = parseLong(intent.getStringExtra("sec"))

        start_seconds = ((hr*3600) + (min*60) + sec) + 1

        startTimer(start_seconds)

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm)

        btnPause.setOnClickListener {
            if(!isPaused){
                isPaused = true
                btnPause.text = "CONT"
                btnReset.visibility = View.INVISIBLE

            }
            else {
                isPaused = false
                btnPause.text = "PAUSE"
                startTimer(current_time_in_second)
                btnReset.visibility = View.VISIBLE
            }
        }

        btnReset.setOnClickListener {
            if(!isReset){
                btnReset.text = "START"
                isReset = true
                countdown_timer.cancel()
                displayText((start_seconds * 1000) - 1 )
                btnPause.visibility = View.INVISIBLE
            }
            else{
                btnReset.text = "RESET"
                isReset = false
                startTimer(start_seconds)
                btnPause.visibility = View.VISIBLE
            }
        }

        btnStop.setOnClickListener {
            if(!isStopped){
                isStopped = true
                btnStop.text = "RESTART"
                mediaPlayer?.pause()
                mediaPlayer?.seekTo(0)
                displayText((start_seconds * 1000) - 1 )
            }
            else{
                isStopped = false
                btnStop.text = "STOP"
                btnStop.visibility = View.INVISIBLE
                btnReset.visibility = View.VISIBLE
                btnPause.visibility = View.VISIBLE
                startTimer(start_seconds)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countdown_timer.cancel()
    }

    private fun startTimer(total: Long ){

        countdown_timer = object : CountDownTimer(total*1000.toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
               displayText(millisUntilFinished)
                if(isPaused){
                    this.cancel()
                    current_time_in_second = millisUntilFinished/1000;
                }
            }

            override fun onFinish() {
                Toast.makeText(getApplicationContext(), "Time's up!", Toast.LENGTH_SHORT).show()
                mediaPlayer?.start()
                btnStop.visibility = View.VISIBLE
                btnReset.visibility = View.INVISIBLE
                btnPause.visibility = View.INVISIBLE

                tvSec.setText("00")
                tvMin.setText("00")
                tvHr.setText("00")
            }
        }.start()
    }

    private fun displayText(millisUntilFinished: Long){
        var seconds = (millisUntilFinished/1000) % 60
        var minutes = (millisUntilFinished/1000) / 60
        var hours = (millisUntilFinished/3600000)

        tvSec.setText(formatTextUI(seconds))
        tvMin.setText(formatTextUI(minutes))
        tvHr.setText(formatTextUI(hours))
    }

    private fun formatTextUI(num: Long): String {
        if(num<10){
            return "0" + num
        }
            return num.toString()
    }



}


