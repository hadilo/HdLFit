package com.hadilo.hdlfit.detail

import com.hadilo.hdlfit.base.BasePresenter

/**
 * Created by Hadilo Muhammad on 2019-07-21.
 */

class DetailPresenter : BasePresenter<DetailContract.View>, DetailContract.Presenter {

    var view: DetailContract.View? = null

    override fun takeView(view: DetailContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

}