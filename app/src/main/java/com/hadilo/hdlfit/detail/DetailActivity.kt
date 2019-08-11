package com.hadilo.hdlfit.detail

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.hadilo.hdlfit.R
import com.hadilo.hdlfit.model.Movement
import com.hadilo.hdlfit.utils.ProgressDialogHelper
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContract.View {

    var presenter: DetailContract.Presenter? = null

    private var movement: Movement? = null
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        movement = intent.getParcelableExtra("MODEL")

        title = movement?.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressDialog = ProgressDialog(this)

        setPresenter()
        setRecyclerView()
    }

    fun setPresenter() {
        presenter = DetailPresenter()
    }

    private fun setRecyclerView(){
        val adapter = DetailAdapter(baseContext) {

        }
        //recyclerview dengan layout listview
        val linearLayoutManager = LinearLayoutManager(baseContext)
        rv_data.layoutManager = linearLayoutManager

        //mengaktifkan smooth scroll
        linearLayoutManager.isSmoothScrollbarEnabled = true
        //agar tidak auto scroll ke recyclerview bila diatas recyclerview terdapat layout lain
        rv_data.isFocusable = false

        rv_data.adapter = adapter

        adapter.setItems(movement?.property)

    }

    override fun showDialog(message: String?) {

    }

    override fun showProgress() {
        ProgressDialogHelper.showProgress(progressDialog!!)
    }

    override fun hideProgress() {
        ProgressDialogHelper.dismissProgress(progressDialog!!)
    }

    override fun goToMaintenance() {

    }

    override fun onResume() {
        super.onResume()
        presenter?.takeView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter?.dropView()
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