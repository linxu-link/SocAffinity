package com.wj.socaffinity

object ThreadAffinity {

    private external fun getCores(): Int

    private external fun bindThreadToCore(core: Int): Int

    private external fun bindPidToCore(pid: Int, core: Int): Int

    init {
        System.loadLibrary("socaffinity")
    }

    fun getCoresCount(): Int {
        return getCores()
    }

    fun threadToCore(core: Int, block: () -> Unit) {
        bindThreadToCore(core)
        block()
    }

    fun pidToCore(pid: Int, core: Int){
        bindPidToCore(pid, core)
    }

}