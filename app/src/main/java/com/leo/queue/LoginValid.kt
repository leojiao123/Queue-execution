package com.leo.queue

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.leo.call.Valid

/**
 * @author leo
 * @date 2020/12/24
 * @desc
 * @email hqb1992522@qq.com
 */
class LoginValid(context: Context) : Valid {

    var context: Context? = context

    override fun check(): Boolean {
        return LoginActivity.login;
    }

    override fun doValid() {
        var intent = Intent(context, LoginActivity::class.java)
        context?.let { startActivity(it, intent, null) }
    }

    override fun validTag(): String {
        return LoginActivity::class.java.name;
    }
}