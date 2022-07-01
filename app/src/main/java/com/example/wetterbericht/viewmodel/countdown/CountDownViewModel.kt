package com.example.wetterbericht.viewmodel.countdown

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class CountDownViewModel: ViewModel() {

    private lateinit var countDownTimer: CountDownTimer
    private val initialTime = MutableLiveData<Long>()
    private val durationTime = MutableLiveData<Long>()

    val progressBar = MutableLiveData<Int>()
    val eventFinish = MutableLiveData<Boolean>()

    private var number = 0


    val currentTimeString = Transformations.map(durationTime){ time ->
        DateUtils.formatElapsedTime(time / 1000)
    }




    fun setInitialCountDown(time : Long){
        val convertTime = time * 60 * 1000
        initialTime.value = convertTime
        durationTime.value = convertTime

        countDownTimer = object : CountDownTimer(convertTime,1000){
            override fun onTick(timeRemind: Long) {
                durationTime.value = timeRemind
                number++
                progressBar.value = number * 100/(convertTime.toInt()/1000)
            }

            override fun onFinish() {
                resetTimer()
            }
        }
    }

    fun start(){
        countDownTimer.start()
    }

    fun resetTimer(): Boolean{
        countDownTimer.cancel()
        durationTime.value = initialTime.value
        eventFinish.value = true
        return true
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer.cancel()
    }






}