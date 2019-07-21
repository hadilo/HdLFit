package com.hadilo.hdlfit.main

import com.hadilo.hdlfit.base.BaseView
import com.hadilo.hdlfit.base.BasePresenter
import com.hadilo.hdlfit.model.DataModel

/**
 * Created by Hadilo Muhammad on 2019-07-21.
 */

class Main2Contract {

    interface View : BaseView<Presenter> {
        fun onSuccessGetDatas(dataModels: MutableList<DataModel>)
        fun onFailedGetDatas(message: String)
    }

    interface Presenter : BasePresenter<View> {
        fun getDatas()
    }
}