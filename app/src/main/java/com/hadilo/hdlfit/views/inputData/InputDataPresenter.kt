package com.hadilo.hdlfit.views.inputData

import android.util.Log
import com.hadilo.hdlfit.helper.network.Client
import com.hadilo.hdlfit.helper.network.ErrorUtils
import com.hadilo.hdlfit.helper.network.Service
import com.hadilo.hdlfit.views.base.BasePresenter
import com.hadilo.hdlfit.model.Movement
import com.hadilo.hdlfit.model.Property
import com.hadilo.hdlfit.model.pojo.error.APIError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.JsonObject

/**
 * Created by Hadilo Muhammad on 2019-07-20.
 */

class InputDataPresenter : BasePresenter<InputDataContract.View>, InputDataContract.Presenter {

    var view: InputDataContract.View? = null
    lateinit var error: APIError

    override fun takeView(view: InputDataContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun addProperty(movement: Movement?, set: Int?, repetition: Int?, load: Int?) {

        val property = Property(
            set = set,
            load = load,
            repetition = repetition
        )

        val client = Client()
        val apiService = client.getData().create(Service::class.java)

        val call = apiService.postProperty(property)
        call.enqueue(object : Callback<Property> {

            override fun onResponse(call: Call<Property>, response: Response<Property>) {
                if(response.isSuccessful) {
                    addRelation(response.body(), movement)
                }else{
                    error = ErrorUtils.parseError(response)
                    view?.onFailedAddProperty(error.message as String)
                }
            }

            override fun onFailure(call: Call<Property>, t: Throwable) {
                view?.onFailedAddProperty(t.message as String)
            }

        })

    }

    fun addRelation(property: Property?, movement: Movement?){

        val client = Client()
        val apiService = client.getData().create(Service::class.java)

        val jsonObject = JsonObject()
        jsonObject.addProperty("objectId", property?.objectId)
        Log.d("TAG", "addRelation: ")

        val call = apiService.postMovementRelation(jsonObject.asJsonObject, movement?.objectId!!, "property:Property:n")
        call.enqueue(object : Callback<Int> {

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if(response.isSuccessful) {
                    val m = Movement()
                    m.created = movement.created
                    m.name = movement.name
                    m.___class = movement.___class
                    m.property = mutableListOf(property!!)
                    m.ownerId = movement.ownerId
                    m.updated = movement.updated
                    m.objectId = movement.objectId
                    view?.onSuccessAddProperty(m)
                }else{
                    error = ErrorUtils.parseError(response)
                    view?.onFailedAddProperty(error.message as String)
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                view?.onFailedAddProperty(t.message as String)
            }

        })
    }

}