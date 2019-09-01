package com.hadilo.hdlfit.main

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import com.backendless.BackendlessUser
import com.hadilo.hdlfit.R
import com.hadilo.hdlfit.detail.DetailActivity
import com.hadilo.hdlfit.inputData.InputDataActivity
import com.hadilo.hdlfit.model.Movement
import com.hadilo.hdlfit.utils.ProgressDialogHelper

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*
import java.util.ArrayList

class Main2Activity : AppCompatActivity(), Main2Contract.View {

    private lateinit var presenter: Main2Contract.Presenter
    private lateinit var progressDialog: ProgressDialog
    private lateinit var adapter: Main2Adapter

    val TAG = "Main2Activity"

    val INSERT_REQUEST = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        progressDialog = ProgressDialog(this)

        setPresenter()
        setLayout()

        showProgress()
        presenter.login("devhadi@gmail.com","123456")
    }

    fun setPresenter() {
        presenter = Main2Presenter()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    fun setLayout() {
        setRecyclerView()

        fab_movement_name.setOnClickListener {
            popup()
        }

        fab_progress.setOnClickListener {
            val intent = Intent(this, InputDataActivity::class.java)
            intent.putParcelableArrayListExtra("MODEL", adapter.getItems() as ArrayList)
            startActivityForResult(intent, INSERT_REQUEST)
        }
    }

    private fun setRecyclerView(){
        adapter = Main2Adapter(baseContext) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("MODEL", it)
            startActivity(intent)
        }
        //recyclerview dengan layout listview
        val linearLayoutManager = LinearLayoutManager(baseContext)
        rv_data.layoutManager = linearLayoutManager

        //mengaktifkan smooth scroll
        linearLayoutManager.isSmoothScrollbarEnabled = true
        //agar tidak auto scroll ke recyclerview bila diatas recyclerview terdapat layout lain
        rv_data.isFocusable = false

        rv_data.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (requestCode == INSERT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                val model = intent?.getParcelableExtra<Movement>("MODEL")
                Log.d(TAG, "onActivityResult: $model")
//
                adapter.setItem(model!!)
            }
        }
    }

    fun popup() {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.dialog_input_movement_name, null)

        val builder = AlertDialog.Builder(this)
            .setView(view)
            .show()

        val tilMovementName = view.findViewById<TextInputLayout>(R.id.til_movement_name)
        val btnSave = view.findViewById<Button>(R.id.btn_save)

        btnSave.setOnClickListener {
            if (validasiPopup(tilMovementName.editText?.text.toString())) {
                showProgress()
                presenter.insertDataMovementName(tilMovementName.editText?.text.toString())
                builder.dismiss()
            }
        }
    }

    fun validasiPopup(movementName: String?): Boolean{
        if(movementName.isNullOrEmpty()) {
//            showDialogError("Bandara Keberangkatan harus diisi");
            return false
        }
        return true
    }



    override fun showDialog(message: String?) {

    }

    override fun onSuccessLogin(user: BackendlessUser?) {
        hideProgress()
        showProgress()
        presenter.getDatas()
    }

    override fun onFailedLogin(message: String?) {
        hideProgress()
    }

    override fun onSuccessGetDatas(movements: MutableList<Movement>?) {
        hideProgress()
        adapter.setItems(movements!!)
    }

    override fun onFailedGetDatas(message: String?) {
        hideProgress()
    }

    override fun onSuccessDataMovementName(movements: Movement?) {
        hideProgress()
    }

    override fun onFailedDataMovementName(message: String?) {
        hideProgress()
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
}
