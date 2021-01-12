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

    fun doCall() {
        doCall(tag)
    }

    /**
     * 对指定tag 的callUnit 进行执行，没有执行完拦截器的继续执行拦截器的操作
     */
    fun doCall(tag: String) {
        val callUnit = callUnitMap[tag] ?: return
        // 最后一个拦截器没有通过的话直接返回
        if (callUnit.lastValid != null && !callUnit.lastValid!!.check()) {
            return
        }
        if (callUnit.validQueue.size == 0) {
            callUnit.action?.doCall(true, "")
            clear(tag)
            return
        }
        // 正常来对拦截器进行校验,
        val valid = callUnit.validQueue.poll()
        callUnit.lastValid = valid
        valid?.doValid()
    }
}