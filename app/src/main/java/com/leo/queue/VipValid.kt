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
class VipValid(context: Context) : Valid {

    var context: Context? = context

    override fun check(): Boolean {
        return VipActivity.vip;
    }

    override fun doValid() {
        var intent = Intent(context, VipActivity::class.java)
        context?.let { startActivity(it, intent, null) }
    }

    override fun validTag(): String {
        return VipActivity::class.java.name;
    }
}