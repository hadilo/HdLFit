package com.hadilo.hdlfit.base

interface BaseView<T> {

    fun showDialog(message: String?)

    fun showProgress()

    fun hideProgress()

    fun goToMaintenance()

}