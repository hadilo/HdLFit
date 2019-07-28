package com.hadilo.hdlfit.main

import com.backendless.BackendlessUser
import com.hadilo.hdlfit.base.BaseView
import com.hadilo.hdlfit.base.BasePresenter
import com.hadilo.hdlfit.model.Movement

/**
 * Created by Hadilo Muhammad on 2019-07-21.
 */

class Main2Contract {

    interface View : BaseView<Presenter> {

        fun onSuccessLogin(user: BackendlessUser?)
        fun onFailedLogin(message: String?)

        fun onSuccessGetDatas(movements: MutableList<Movement>?)
        fun onFailedGetDatas(message: String?)

        fun onSuccessDataMovementName(movements: Movement?)
        fun onFailedDataMovementName(message: String?)
    }

    interface Presenter : BasePresenter<View> {
        fun login(username: String, password: String)
        fun getDatas()
        fun insertDataMovementName(name: String)
    }
}