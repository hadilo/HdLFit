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
//        if (isLogin()) {
            Backendless.UserService.login(username, password,  object: AsyncCallback<BackendlessUser> {
                override fun handleResponse(user: BackendlessUser?) {
                    Log.d("TAG", "handleResponse: ${user?.email}")

//                Platform.runLater(object : Runnable {
//                    override fun run() {
                    view?.onSuccessLogin(user)
//                    }
//                })
                }

                override fun handleFault(fault: BackendlessFault) {
                    Log.d("TAG", "handleFault: ${fault.message}")

                    // login failed, to get the error code call fault.getCode()
//                Platform.runLater(object : Runnable {
//                    override fun run() {
                    view?.onFailedLogin(fault.message)
//                    }
//                })
                }
            }, false)
//        }

    }

    fun isLogin(): Boolean {
        val userToken = UserTokenStorageFactory.instance().storage.get()
        if(userToken.isNotEmpty()){
            return true
        }
        return false
    }


    fun getAllMedicalRecord(dataQueryBuilder: DataQueryBuilder) { //return 10 datas

        view?.showProgress()

        Backendless.Data.of(Movement::class.java).find(object : AsyncCallback<MutableList<Movement>> {
            override fun handleResponse(response: MutableList<Movement>?) {

                if (response?.size == 0) {
                    view?.hideProgress()
                    view?.showDialog("Data Tidak ditemukan")
                } else {
                    view?.hideProgress()

                    var filterMovement = mutableListOf<Movement>()

                    response?.forEach {movement ->

                        movement.property?.forEach {property ->

                            val m = Movement()
                            m.created = movement.created
                            m.name = movement.name
                            m.___class = movement.___class
                            m.property = mutableListOf(property)
                            m.ownerId = movement.ownerId
                            m.updated = movement.updated
                            m.objectId = movement.objectId

                            filterMovement.add(m)
                        }
                    }

                    view?.onSuccessGetDatas(filterMovement)
                }
            }

            override fun handleFault(fault: BackendlessFault?) {
                view?.hideProgress()
                Log.d("TAG", "handleFault: $fault")

                view?.showDialog(fault?.message)
            }
        })
    }



    fun getQuery(objectId: String?): DataQueryBuilder {
        val queryBuilder = DataQueryBuilder.create()

        //invert relation https://backendless.com/docs/android/data_inverted_relation_retrieval.html
//        queryBuilder.whereClause = "PatientBpjs[medicalRecord].objectId = '${objectId}'"
        //paging https://backendless.com/docs/android/data_data_paging.html
        queryBuilder.setPageSize(100).setOffset(0)

        //start sort asc
//        queryBuilder.sortBy = mutableListOf("dateCheckup DESC")
        //end sort asc

        return queryBuilder
    }

    fun initData(): MutableList<DataModel> {
        var dataModel = mutableListOf<DataModel>()
        dataModel.add(DataModel("Bench Press", 4, 8))
        dataModel.add(DataModel("Push up", 4, 8))
        dataModel.add(DataModel("Butterfly", 4, 8))
        return dataModel
    }
}