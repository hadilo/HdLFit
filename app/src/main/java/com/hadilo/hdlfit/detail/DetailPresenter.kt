package com.hadilo.hdlfit.detail

import com.hadilo.hdlfit.base.BasePresenter
import com.hadilo.hdlfit.model.Property

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

    override fun sortBescByDate(items: MutableList<Property>?) {
        items?.sortByDescending { it.created }
        view?.sortDescByDate(items)
    }

    override fun countData(items: MutableList<Property>?) {
        if (items.isNullOrEmpty()) view?.showEmptyState(true)
        else view?.showEmptyState(false)
    }
}