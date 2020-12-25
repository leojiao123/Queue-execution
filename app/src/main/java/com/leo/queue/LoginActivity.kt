package com.leo.queue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.leo.call.Action
import com.leo.call.SingleCall
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.btn_test
import java.util.logging.Logger
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    companion object {
        var login = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_test.setOnClickListener {
            SingleCall.INSTANCE.tag("VIP").addAction(object : Action {
                override fun doCall() {
                    println("vip测试")
                    Toast.makeText(this@LoginActivity, "vip测试", Toast.LENGTH_LONG).show()
                    finish()
                    login = true
                    SingleCall.INSTANCE.doCall("LOGIN")
                }

                override fun doFailCall() {
                }

            }).addValid(VipValid(this)).doCall("VIP")
        }

        btn_test_vip.visibility = View.GONE
        btn_test_vip.setOnClickListener {
            SingleCall.INSTANCE.tag("VIP").addAction(object : Action {
                override fun doCall() {
                    Toast.makeText(this@LoginActivity, "vip测试", Toast.LENGTH_SHORT).show()
                    SingleCall.INSTANCE.doCall("LOGIN")
                }

                override fun doFailCall() {
                }

            }).addValid(VipValid(this)).doCall("VIP")

        }
    }
}