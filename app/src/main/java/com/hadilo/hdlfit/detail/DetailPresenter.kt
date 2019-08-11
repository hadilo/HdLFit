package com.hadilo.hdlfit.detail

import android.util.Log
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.backendless.persistence.local.UserTokenStorageFactory
import com.hadilo.hdlfit.base.BasePresenter
import com.hadilo.hdlfit.model.DataModel
import com.hadilo.hdlfit.model.Movement
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

}