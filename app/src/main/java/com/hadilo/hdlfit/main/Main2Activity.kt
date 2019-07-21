package com.hadilo.hdlfit.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import com.hadilo.hdlfit.R
import com.hadilo.hdlfit.inputData.InputDataActivity
import com.hadilo.hdlfit.model.DataModel

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class Main2Activity : AppCompatActivity(), Main2Contract.View {

    var presenter: Main2Contract.Presenter? = null

    val TAG = "Main2Activity"

    val INSERT_REQUEST = 1

    lateinit var adapter: Main2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        setPresenter()
        setLayout()

        presenter?.getDatas()
    }

    fun setPresenter() {
        presenter = Main2Presenter()
        presenter?.takeView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter?.dropView()
    }

    fun setLayout() {
        setRecyclerView()

        fab_movement_name.setOnClickListener {
            popup()
        }

        fab_progress.setOnClickListener {
            val intent = Intent(this, InputDataActivity::class.java)
            startActivityForResult(intent, INSERT_REQUEST)
        }
    }

    private fun setRecyclerView(){
        adapter = Main2Adapter(baseContext) {

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
                val model = intent?.getParcelableExtra<DataModel>("DATA_MODEL")
                Log.d(TAG, "onActivityResult: $model")

                adapter.setItems(mutableListOf(model!!))
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



    override fun showDialog(message: String) {

    }

    override fun onSuccessGetDatas(dataModels: MutableList<DataModel>) {
        adapter.setItems(dataModels)
    }

    override fun onFailedGetDatas(message: String) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun goToMaintenance() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
