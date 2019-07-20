package com.hadilo.hdlfit.inputData

import com.hadilo.hdlfit.base.BasePresenter

/**
 * Created by Hadilo Muhammad on 2019-07-20.
 */

class InputDataPresenter : BasePresenter<InputDataContract.View>, InputDataContract.Presenter {

    var view: InputDataContract.View? = null

    override fun takeView(view: InputDataContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }


}