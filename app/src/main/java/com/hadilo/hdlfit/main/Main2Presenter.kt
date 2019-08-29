package com.hadilo.hdlfit.main

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

class Main2Presenter : BasePresenter<Main2Contract.View>, Main2Contract.Presenter {

    var view: Main2Contract.View? = null

    override fun takeView(view: Main2Contract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun getDatas() {
        UserTokenStorageFactory.instance().storage.get()
        val dataQueryBuilder = getQuery("objectId")

        getAllMedicalRecord(dataQueryBuilder)

    }

    override fun login(username: String, password: String){
            Backendless.UserService.login(username, password,  object: AsyncCallback<BackendlessUser> {
                override fun handleResponse(user: BackendlessUser?) {
                    view?.onSuccessLogin(user)
                }

                override fun handleFault(fault: BackendlessFault) {
                    view?.onFailedLogin(fault.message)
                }
            }, false)

    }

    fun isLogin(): Boolean {
        val userToken = UserTokenStorageFactory.instance().storage.get()
        if(userToken.isNotEmpty()){
            return true
        }
        return false
    }

    fun getAllMedicalRecord(dataQueryBuilder: DataQueryBuilder) { //return 10 datas

        Backendless.Data.of(Movement::class.java).find(object : AsyncCallback<MutableList<Movement>> {
            override fun handleResponse(response: MutableList<Movement>?) {
                if (response?.size == 0) {
                    view?.showDialog("Data Tidak ditemukan")
                } else {
                    view?.onSuccessGetDatas(response)
                }
            }

            override fun handleFault(fault: BackendlessFault?) {
                view?.showDialog(fault?.message)
            }
        })
    }



    fun getQuery(objectId: String?): DataQueryBuilder {
        val queryBuilder = DataQueryBuilder.create()
        queryBuilder.setPageSize(100).setOffset(0)
        return queryBuilder
    }

    override fun insertDataMovementName(name: String) {
        val movement = Movement(
            name = name
        )

        Backendless.Data.of(Movement::class.java).save(movement, object : AsyncCallback<Movement> {

            override fun handleResponse(response: Movement?) {
                view?.onSuccessDataMovementName(response)
            }

            override fun handleFault(fault: BackendlessFault?) {
                view?.onFailedDataMovementName(fault?.message)
            }
        })
    }

}