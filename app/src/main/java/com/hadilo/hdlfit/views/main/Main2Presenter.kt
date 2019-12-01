package com.hadilo.hdlfit.views.main

import com.hadilo.hdlfit.helper.network.Client
import com.hadilo.hdlfit.helper.network.ErrorUtils
import com.hadilo.hdlfit.helper.network.Service
import com.hadilo.hdlfit.views.base.BasePresenter
import com.hadilo.hdlfit.model.Movement
import com.hadilo.hdlfit.model.Property
import com.hadilo.hdlfit.model.pojo.error.APIError
import com.hadilo.hdlfit.model.pojo.login.Login
import com.hadilo.hdlfit.model.pojo.login.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hadilo Muhammad on 2019-07-21.
 */

class Main2Presenter : BasePresenter<Main2Contract.View>, Main2Contract.Presenter {

    var view: Main2Contract.View? = null
    lateinit var error: APIError

    override fun takeView(view: Main2Contract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun getDatas() {

        val client = Client()
        val apiService = client.getData().create(Service::class.java)

        val call: Call<MutableList<Movement>> = apiService.getMovement()
        call.enqueue(object : Callback<MutableList<Movement>> {

            override fun onResponse(call: Call<MutableList<Movement>>, response: Response<MutableList<Movement>>) {
                if(response.isSuccessful) {
                    view?.onSuccessGetDatas(response.body() as MutableList<Movement>)
                }else{
                    error = ErrorUtils.parseError(response)
                    view?.onFailedGetDatas(error.message as String)
                }
            }

            override fun onFailure(call: Call<MutableList<Movement>>, t: Throwable) {
                view?.onFailedGetDatas(t.message as String)
            }

        })
    }

    override fun login(username: String, password: String){

        val client = Client()
        val apiService = client.getData().create(Service::class.java)
        val param = LoginRequest(username, password)
        val call: Call<Login> = apiService.login(param)
        call.enqueue(object : Callback<Login> {

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if(response.isSuccessful) {
                    view?.onSuccessLogin(response.body() as Login)
                }else{
                    error = ErrorUtils.parseError(response)
                    view?.onFailedLogin(error.message as String)
                }
            }

            override fun onFailure(call: Call<Login>, t: Throwable) {
                view?.onFailedLogin(t.message as String)
            }

        })
    }

    override fun insertDataMovementName(name: String) {
        val movement = Movement(
            name = name
        )

        val client = Client()
        val apiService = client.getData().create(Service::class.java)

        val call = apiService.postMovement(movement)
        call.enqueue(object : Callback<Movement> {

            override fun onResponse(call: Call<Movement>, response: Response<Movement>) {
                if(response.isSuccessful) {
                    view?.onSuccessDataMovementName(response.body()!!)
                }else{
                    error = ErrorUtils.parseError(response)
                    view?.onFailedDataMovementName(error.message as String)
                }
            }

            override fun onFailure(call: Call<Movement>, t: Throwable) {
                view?.onFailedDataMovementName(t.message as String)
            }

        })
    }

}