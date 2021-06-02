package com.clap.android.common.data.state

class StateData<T> {
    var status: DataStatus
        private set
    var data: T?
        private set
    var error: String?
        private set

    fun loading(data: T?): StateData<T> {
        status = DataStatus.LOADING
        this.data = data
        error = null
        return this
    }

    fun success(data: T?): StateData<T> {
        status = DataStatus.SUCCESS
        this.data = data
        error = null
        return this
    }

    fun error(error: String): StateData<T> {
        status = DataStatus.ERROR
        data = null
        this.error = error
        return this
    }

    enum class DataStatus {
        SUCCESS, ERROR, LOADING
    }

    init {
        status = DataStatus.LOADING
        data = null
        error = null
    }
}