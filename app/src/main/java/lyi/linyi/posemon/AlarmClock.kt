package lyi.linyi.posemon


import android.app.TimePickerDialog
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class AlarmClock : AppCompatActivity() {
    private lateinit var textViewAlarmTime: TextView
    private lateinit var buttonSetAlarm: Button
    private lateinit var buttonTurnOffAlarm: Button
    lateinit var alarmRingtone: Ringtone
    private var alarmTime: Date? = null
    private var isAlarmActive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarmclock)


        textViewAlarmTime = findViewById(R.id.textViewAlarmTime)
        buttonSetAlarm = findViewById(R.id.buttonSetAlarm)
        buttonTurnOffAlarm = findViewById(R.id.buttonTurnOffAlarm)

        buttonSetAlarm.setOnClickListener {
            showTimePickerDialog()
        }

        buttonTurnOffAlarm.setOnClickListener {
            turnOffAlarm()
        }


    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()

        TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                calendar.apply {
                    set(Calendar.HOUR_OF_DAY, hourOfDay)
                    set(Calendar.MINUTE, minute)
                    set(Calendar.SECOND, 0)
                }

                alarmTime = calendar.time

                val alarmTimeFormatted =
                    SimpleDateFormat("hh:mm a", Locale.getDefault()).format(alarmTime!!)
                textViewAlarmTime.text = alarmTimeFormatted

                buttonSetAlarm.visibility = View.GONE
                buttonTurnOffAlarm.visibility = View.VISIBLE
                startAlarm()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        ).show()
    }

    private fun startAlarm() {
        if (!isAlarmActive) {
            isAlarmActive = true
            val currentTime = Calendar.getInstance().time
            val timeDifference = alarmTime?.time?.minus(currentTime.time) ?: 0

            object : CountDownTimer(timeDifference, 1000) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    playAlarmSound()
                }
            }.start()
        }
    }

    fun playAlarmSound() {
        try {
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            alarmRingtone = RingtoneManager.getRingtone(applicationContext, notification)
            alarmRingtone.play()
            main()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    fun turnOffAlarm() {
        if (isAlarmActive) {
            isAlarmActive = false
            alarmRingtone.stop()
            textViewAlarmTime.text = ""
            buttonSetAlarm.visibility = View.VISIBLE
            buttonTurnOffAlarm.visibility = View.GONE
        }
    }

    private fun main() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun off(){

            alarmRingtone.stop()


    }
}
