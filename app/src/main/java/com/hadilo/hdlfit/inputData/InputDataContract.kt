package com.hadilo.hdlfit.inputData

import com.hadilo.hdlfit.base.BasePresenter
import com.hadilo.hdlfit.base.BaseView

/**
 * Created by Hadilo Muhammad on 2019-07-20.
 */

class InputDataContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter<View> {

    }
}