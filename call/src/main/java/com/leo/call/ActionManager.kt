package com.leo.call

import java.util.*

/**
 * @author leo
 * @date 2020/12/24
 * @desc
 * @email hqb1992522@qq.com
 */
class ActionManager {

    companion object {
        val INSTANCE = Instance.instance;

        private object Instance {
            val instance = ActionManager()
        }
    }

    var actions: Stack<CallUnit> = Stack()
    fun postCallUnit(callUnit: CallUnit) {
        actions.clear()
        callUnit.check()
        if (callUnit.validQueue.size == 0) {
            callUnit.action?.doCall(true, "")
        } else {
            actions.push(callUnit)
            val valid = callUnit.validQueue.peek()
            callUnit.lastValid = valid
            valid?.doValid()
        }
    }
}