package com.example.ComHere

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 파이어 베이스 토픽 구독
//        FirebaseMessaging.getInstance().subscribeToTopic("test")
//            .addOnCompleteListener { task ->
//                var msg = "Subscribed to the topic"
//                if (!task.isSuccessful) {
//                    msg = "Failed to subscribe to the topic"
//                }
//                Log.d(TAG, msg)
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
//            }

        // 일정 시간 지연 이후 실행하기 위한 코드
        Handler(Looper.getMainLooper()).postDelayed({
            // 일정 시간이 지나면 MainActivity로 이동
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // 이전 키를 눌렀을 때 스플래시 화면으로의 이동을 방지하기 위해
            // 이동한 다음 사용안함으로 finish 처리
            finish()
        }, 2000)
    }
}