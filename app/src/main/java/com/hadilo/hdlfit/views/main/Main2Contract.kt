package com.hadilo.hdlfit.views.main

import com.hadilo.hdlfit.views.base.BaseView
import com.hadilo.hdlfit.views.base.BasePresenter
import com.hadilo.hdlfit.model.Movement
import com.hadilo.hdlfit.model.pojo.login.Login

/**
 * Created by Hadilo Muhammad on 2019-07-21.
 */

class Main2Contract {

    interface View : BaseView<Presenter> {

        fun onSuccessLogin(user: Login)
        fun onFailedLogin(message: String?)

        fun onSuccessGetDatas(movements: MutableList<Movement>)
        fun onFailedGetDatas(message: String?)

        fun onSuccessDataMovementName(movements: Movement)
        fun onFailedDataMovementName(message: String?)
    }

    interface Presenter : BasePresenter<View> {
        fun login(username: String, password: String)
        fun getDatas()
        fun insertDataMovementName(name: String)
    }
}