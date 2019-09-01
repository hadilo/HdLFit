package com.hadilo.hdlfit.inputData

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.hadilo.hdlfit.R
import com.hadilo.hdlfit.model.Movement
import kotlinx.android.synthetic.main.activity_input_data.*
import com.hadilo.hdlfit.utils.widget.spinner.SpinnerTextInputLayout
import android.widget.ArrayAdapter
import com.hadilo.hdlfit.utils.ProgressDialogHelper

class InputDataActivity : AppCompatActivity(), InputDataContract.View {

    val TAG = "InputDataActivity"

    private lateinit var presenter: InputDataContract.Presenter
    private lateinit var progressDialog: ProgressDialog

    private var movement = mutableListOf<Movement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data)
        title = "Data"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movement = intent.getParcelableArrayListExtra("MODEL")

        progressDialog = ProgressDialog(this)

        setPresenter()

        setLayout()
    }

    fun setPresenter() {
        presenter = InputDataPresenter()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    fun setLayout() {
        setSpinner()

        btn_save.setOnClickListener {
            if (validate(cmb_movement_name.editText?.text.toString(), til_set.editText?.text.toString(), til_repetition.editText?.text.toString(), til_load.editText?.text.toString())) {

                val m = movement.find {
                    it.name == cmb_movement_name.editText?.text.toString()
                }

                showProgress()
                presenter.addProperty(
                    m,
                    til_set.editText?.text.toString().toInt(),
                    til_repetition.editText?.text.toString().toInt(),
                    til_load.editText?.text.toString().toInt()
                )

            }
        }
    }

    fun setSpinner() {

        //remove duplicate strings from array https://stackoverflow.com/a/40430451
        val m = movement.distinctBy { it.name }

        cmb_movement_name.setMode(SpinnerTextInputLayout.MODE_POPUP)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, m)
        cmb_movement_name.setItems(adapter)
        cmb_movement_name.setOnItemSelectedListener { item, selectedIndex ->
            cmb_movement_name.editText?.setText((item as Movement).name)
            cmb_movement_name.isErrorEnabled = false
            cmb_movement_name.error = ""
        }
    }

    fun validate(movementName: String?, set: String?, repetition: String?, load: String?): Boolean {
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
            showDialog("Repetisi harus diisi")
            return false
        }
        if (repetition == "0") {
            showDialog("Repetisi tidak boleh 0")
            return false
        }

        if (load.isNullOrEmpty()) {
            showDialog("Beban harus diisi")
            return false
        }
        if (load == "0") {
            showDialog("Beban tidak boleh 0")
            return false
        }
        return true
    }

    override fun onSuccessAddProperty(movement: Movement?) {
        val intent = Intent()
        intent.putExtra("MODEL", movement)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onFailedAddProperty(message: String?) {
        hideProgress()
    }

    override fun showDialog(message: String?) {
        Log.d(TAG, "showDialog: $message")
    }

    override fun showProgress() {
        ProgressDialogHelper.showProgress(progressDialog)
    }

    override fun hideProgress() {
        ProgressDialogHelper.dismissProgress(progressDialog)
    }

    override fun goToMaintenance() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
