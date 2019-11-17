package com.hadilo.hdlfit.views.base

/**
 * Created by Hadilo Muhammad on 2019-07-20.
 */

interface BasePresenter<T> {

    fun takeView(view: T)

    fun dropView()
}