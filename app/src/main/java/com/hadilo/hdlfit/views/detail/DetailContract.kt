package com.hadilo.hdlfit.views.detail

import com.hadilo.hdlfit.views.base.BaseView
import com.hadilo.hdlfit.views.base.BasePresenter
import com.hadilo.hdlfit.model.Property

/**
 * Created by Hadilo Muhammad on 2019-07-21.
 */

class DetailContract {

    interface View : BaseView<Presenter> {

        fun sortDescByDate(items: MutableList<Property>?)

        fun showEmptyState(isShow: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun sortBescByDate(items: MutableList<Property>?)

        fun countData(items: MutableList<Property>?)
    }
}