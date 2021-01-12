package com.leo.call

import java.util.*

/**
 * @author leo
 * @date 2020/12/24
 * @desc 一个执行单元,判断所有的前置条件，所有的前置条件都满足的情况下,可以执行
 * @email hqb1992522@qq.com
 */
class CallUnit(action: Action?) {
    var action: Action? = action
    val validQueue: Queue<Valid> = ArrayDeque()
    var lastValid: Valid? = null

    fun check() {
        for (valid in validQueue) {
            if (valid.check()) {
                validQueue.remove(valid)
            }
        }
    }

    fun addValid(valid: Valid): CallUnit {
        validQueue.add(valid)
        return this
    }

    fun doCall() {
        ActionManager.INSTANCE.postCallUnit(this)
    }

}