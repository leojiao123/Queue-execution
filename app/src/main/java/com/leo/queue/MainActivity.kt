package com.leo.queue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.leo.call.Action
import com.leo.call.SingleCall
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_test.setOnClickListener {
            SingleCall.INSTANCE.tag("LOGIN").addAction(object : Action {
                override fun doCall() {
                    Handler().postDelayed({
                        Toast.makeText(this@MainActivity, "登录成功后的操作", Toast.LENGTH_SHORT).show()
                    }, 2000)
                }

                override fun doFailCall() {
                }
            }).addValid(LoginValid(this)).doCall("LOGIN")
        }
        btn_reset.setOnClickListener {
            LoginActivity.login = false
            VipActivity.vip = false
        }
    }

}