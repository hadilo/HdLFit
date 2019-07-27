package com.hadilo.hdlfit.inputData

import com.backendless.BackendlessUser
import com.hadilo.hdlfit.base.BasePresenter
import com.hadilo.hdlfit.base.BaseView
import com.hadilo.hdlfit.model.Movement

/**
 * Created by Hadilo Muhammad on 2019-07-20.
 */

class InputDataContract {

    interface View : BaseView<Presenter> {
        fun onSuccessAddProperty(movement: Movement?)
        fun onFailedAddProperty(message: String?)
    }

    interface Presenter : BasePresenter<View> {
        fun addProperty(movement: Movement?, set: Int?, repetition: Int?, load: Int?)
    }

}