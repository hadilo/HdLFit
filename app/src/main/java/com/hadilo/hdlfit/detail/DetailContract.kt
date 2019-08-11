package com.hadilo.hdlfit.detail

import com.backendless.BackendlessUser
import com.hadilo.hdlfit.base.BaseView
import com.hadilo.hdlfit.base.BasePresenter
import com.hadilo.hdlfit.model.Movement

/**
 * Created by Hadilo Muhammad on 2019-07-21.
 */

class DetailContract {

    interface View : BaseView<Presenter> {

//        fun onSuccessGetDatas(movements: MutableList<Movement>?)
//        fun onFailedGetDatas(message: String?)

    }

    interface Presenter : BasePresenter<View> {
//        fun getDatas()
    }
}