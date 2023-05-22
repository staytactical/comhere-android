package com.example.ComHere

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.google.firebase.messaging.FirebaseMessaging

class AlarmActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        val aFront_alarm: Switch = findViewById(R.id.aFront)
        val bFront_alarm: Switch = findViewById(R.id.bFront)
        val bBack_alarm: Switch = findViewById(R.id.bBack)

        sharedPreferences = getSharedPreferences("switch_prefs", Context.MODE_PRIVATE)


        aFront_alarm.isChecked = sharedPreferences.getBoolean("aFront", false)
        bFront_alarm.isChecked = sharedPreferences.getBoolean("bFront", false)
        bBack_alarm.isChecked = sharedPreferences.getBoolean("bBack", false)


        aFront_alarm.setOnCheckedChangeListener { _, isChecked ->
            setSwitchState("aFront", isChecked)
            if (isChecked) {
                // 스위치가 켜지면, 해당 토픽을 구독
                FirebaseMessaging.getInstance().subscribeToTopic("aFront")
                    .addOnCompleteListener { task ->
                        var msg = "A동 정문 빈자리 알림이 설정되었습니다."
                        if (!task.isSuccessful) {
                            msg = "Failed to subscribe to aFront topic"
                        }
                        Log.d(TAG, msg)
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    }
            } else {
                // 스위치가 꺼지면, 해당 토픽 구독 해제
                FirebaseMessaging.getInstance().unsubscribeFromTopic("aFront")
                    .addOnCompleteListener { task ->
                        var msg = "A동 정문 빈자리 알림이 해제되었습니다."
                        if (!task.isSuccessful) {
                            msg = "Failed to unsubscribe from aFront topic"
                        }
                        Log.d(TAG, msg)
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    }
            }
        }

        bFront_alarm.setOnCheckedChangeListener { _, isChecked ->
            setSwitchState("bFront", isChecked)
            if (isChecked) {
                // 스위치가 켜지면, 해당 토픽을 구독
                FirebaseMessaging.getInstance().subscribeToTopic("bFront")
                    .addOnCompleteListener { task ->
                        var msg = "B동 정문 빈자리 알림이 설정되었습니다."
                        if (!task.isSuccessful) {
                            msg = "Failed to subscribe to bFront topic"
                        }
                        Log.d(TAG, msg)
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    }
            } else {
                // 스위치가 꺼지면, 해당 토픽 구독 해제
                FirebaseMessaging.getInstance().unsubscribeFromTopic("bFront")
                    .addOnCompleteListener { task ->
                        var msg = "B동 정문 빈자리 알림이 해제되었습니다."
                        if (!task.isSuccessful) {
                            msg = "Failed to unsubscribe from bFront topic"
                        }
                        Log.d(TAG, msg)
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    }
            }
        }

        bBack_alarm.setOnCheckedChangeListener { _, isChecked ->
            setSwitchState("bBack", isChecked)
            if (isChecked) {
                // 스위치가 켜지면, 해당 토픽을 구독
                FirebaseMessaging.getInstance().subscribeToTopic("bBack")
                    .addOnCompleteListener { task ->
                        var msg = "B동 후문 빈자리 알림이 설정되었습니다."
                        if (!task.isSuccessful) {
                            msg = "Failed to subscribe to bBack topic"
                        }
                        Log.d(TAG, msg)
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    }
            } else {
                // 스위치가 꺼지면, 해당 토픽 구독 해제
                FirebaseMessaging.getInstance().unsubscribeFromTopic("bBack")
                    .addOnCompleteListener { task ->
                        var msg = "B동 후문 빈자리 알림이 해제되었습니다."
                        if (!task.isSuccessful) {
                            msg = "Failed to unsubscribe from bBack topic"
                        }
                        Log.d(TAG, msg)
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun setSwitchState(key: String, state: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, state)
        editor.apply()
    }
}