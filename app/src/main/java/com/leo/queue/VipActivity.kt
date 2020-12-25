package com.leo.queue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.leo.call.Action
import com.leo.call.SingleCall
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_test

class VipActivity : AppCompatActivity() {
    companion object {
        var vip = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_test.text = resources.getString(R.string.app_vip)
        btn_test.setOnClickListener {
            vip = true
            finish()
            SingleCall.INSTANCE.doCall("VIP")
        }
        btn_test_vip.visibility = View.GONE
    }
}