package com.hadilo.hdlfit.inputData

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hadilo.hdlfit.R
import com.hadilo.hdlfit.model.DataModel
import com.hadilo.hdlfit.model.Movement
import kotlinx.android.synthetic.main.activity_input_data.*
import com.hadilo.hdlfit.utils.widget.spinner.SpinnerTextInputLayout



class InputDataActivity : AppCompatActivity(), InputDataContract.View {

    val TAG = "InputDataActivity"

    var presenter: InputDataContract.Presenter? = null

    private var movement: MutableList<Movement>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data)
        title = "Data"

        movement = intent.getParcelableArrayListExtra("MODEL")

        setPresenter()

        setLayout()
    }

    fun setPresenter() {
        presenter = InputDataPresenter()
        presenter?.takeView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter?.dropView()
    }

    fun setLayout() {
        setSpinner()

        btn_save.setOnClickListener {
            if (validate(cmb_movement_name.editText?.text.toString(), til_set.editText?.text.toString(), til_repetition.editText?.text.toString())) {

                val dataModel = DataModel(
                    cmb_movement_name.editText?.text.toString(),
                    til_set.editText?.text.toString().toInt(),
                    til_repetition.editText?.text.toString().toInt()
                )

                val intent = Intent()
                intent.putExtra("DATA_MODEL", dataModel)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    fun setSpinner() {

        //remove duplicate strings from array https://stackoverflow.com/a/40430451
        val m = movement?.distinctBy { it.name }

        cmb_movement_name.setMode(SpinnerTextInputLayout.MODE_POPUP)
        cmb_movement_name.setItems(m)
        cmb_movement_name.setOnItemSelectedListener { item, selectedIndex ->
            cmb_movement_name.editText?.setText(item.label)
            cmb_movement_name.isErrorEnabled = false
            cmb_movement_name.error = ""
        }
    }

    fun validate(movementName: String?, set: String?, repetition: String?): Boolean {
        if (movementName.isNullOrEmpty()) {
            showDialog("Nama Gerakan harus diisi")
            return false
        }
        if (set.isNullOrEmpty()) {
            showDialog("Set harus diisi")
            return false
        }
        if (set == "0") {
            showDialog("Set tidak boleh 0")
            return false
        }

        if (repetition.isNullOrEmpty()) {
            showDialog("Repetisi tidak boleh 0")
            return false
        }
        return true
    }

    override fun showDialog(message: String?) {
        Log.d(TAG, "showDialog: $message")
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToMaintenance() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
