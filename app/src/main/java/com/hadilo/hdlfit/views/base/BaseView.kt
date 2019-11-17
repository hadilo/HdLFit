package com.hadilo.hdlfit.views.base

interface BaseView<T> {

    fun showDialog(message: String?)

    fun showProgress()

    fun hideProgress()

    fun goToMaintenance()

}