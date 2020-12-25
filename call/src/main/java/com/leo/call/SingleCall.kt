package com.leo.call

/**
 * @author leo
 * @date 2020/12/24
 * @desc
 * @email hqb1992522@qq.com
 */
class SingleCall {

    companion object {
        val INSTANCE = Instance.instance;

        private object Instance {
            val instance = SingleCall()
        }
    }

    var tag = "DEFAULT"

    fun tag(tag: String): SingleCall {
        this.tag = tag
        return this
    }

    private val callUnitMap = HashMap<String, CallUnit>()

    private fun clear(tag: String) {
        val callUnit = callUnitMap[tag]
        callUnit?.validQueue?.clear()
        callUnit?.action = null
        callUnit?.lastValid = null
        callUnitMap.remove(tag)
    }

    fun addAction(action: Action): SingleCall {
        clear(tag)
        val callUnit = CallUnit(action)
        callUnitMap[tag] = callUnit
        return this
    }

    fun addValid(valid: Valid): SingleCall {
        if (valid.check()) {
            return this
        }
        callUnitMap[tag]?.addValid(valid)
        return this
    }

    fun doCall(tag: String) {
        val callUnit = callUnitMap[tag] ?: return

        if (callUnit.lastValid != null && !callUnit.lastValid!!.check()) {
            return
        }
        if (callUnit.validQueue.size == 0) {
            callUnit.action?.doCall()
            clear(tag)
            return
        }
        val valid = callUnit.validQueue.poll()
        callUnit.lastValid = valid
        valid?.doValid()
    }
}