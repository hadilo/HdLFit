package com.hadilo.hdlfit.main

import com.hadilo.hdlfit.base.BasePresenter
import com.hadilo.hdlfit.model.DataModel

/**
 * Created by Hadilo Muhammad on 2019-07-21.
 */

class Main2Presenter : BasePresenter<Main2Contract.View>, Main2Contract.Presenter {

    var view: Main2Contract.View? = null

    override fun takeView(view: Main2Contract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun getDatas() {
        view?.showProgress()

        view?.onSuccessGetDatas(initData())
    }

    fun initData(): MutableList<DataModel> {
        var dataModel = mutableListOf<DataModel>()
        dataModel.add(DataModel("Bench Press", 4, 8))
        dataModel.add(DataModel("Push up", 4, 8))
        dataModel.add(DataModel("Butterfly", 4, 8))
        return dataModel
    }
}