package com.hadilo.hdlfit.views.inputData

import com.hadilo.hdlfit.views.base.BasePresenter
import com.hadilo.hdlfit.views.base.BaseView
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