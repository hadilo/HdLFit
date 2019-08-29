package com.hadilo.hdlfit.inputData

import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.hadilo.hdlfit.base.BasePresenter
import com.hadilo.hdlfit.model.Movement
import com.hadilo.hdlfit.model.Property

/**
 * Created by Hadilo Muhammad on 2019-07-20.
 */

class InputDataPresenter : BasePresenter<InputDataContract.View>, InputDataContract.Presenter {

    var view: InputDataContract.View? = null

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

        Backendless.Data.of(Property::class.java).save(property, object : AsyncCallback<Property> {

            override fun handleResponse(response: Property?) {
                addRelation(property, movement)
            }

            override fun handleFault(fault: BackendlessFault?) {
                view?.onFailedAddProperty(fault?.message)
            }
        })
    }

    fun addRelation(property: Property?, movement: Movement?){

        val array = ArrayList<Property?>()
        array.add(property)

        Backendless.Data.of(Movement::class.java).addRelation(movement, "property:Property:n", array,
            object : AsyncCallback<Int> {
                override fun handleResponse(response: Int?) {
                    val m = Movement()
                    m.created = movement?.created
                    m.name = movement?.name
                    m.___class = movement?.___class
                    m.property = mutableListOf(property!!)
                    m.ownerId = movement?.ownerId
                    m.updated = movement?.updated
                    m.objectId = movement?.objectId

                    view?.onSuccessAddProperty(m)
                }

                override fun handleFault(fault: BackendlessFault?) {
                    view?.onFailedAddProperty(fault?.message)
                }
            })
    }

}