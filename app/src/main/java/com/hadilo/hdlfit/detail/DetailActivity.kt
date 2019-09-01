package com.hadilo.hdlfit.detail

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.hadilo.hdlfit.R
import com.hadilo.hdlfit.model.Movement
import com.hadilo.hdlfit.model.Property
import com.hadilo.hdlfit.utils.ProgressDialogHelper
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var presenter: DetailContract.Presenter
    private lateinit var progressDialog: ProgressDialog
    private lateinit var adapter: DetailAdapter

    private var movement: Movement? = null

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
        presenter.takeView(this)
    }

    private fun setRecyclerView(){
        adapter = DetailAdapter(baseContext) {

        }
        //recyclerview dengan layout listview
        val linearLayoutManager = LinearLayoutManager(baseContext)
        rv_data.layoutManager = linearLayoutManager

        //mengaktifkan smooth scroll
        linearLayoutManager.isSmoothScrollbarEnabled = true
        //agar tidak auto scroll ke recyclerview bila diatas recyclerview terdapat layout lain
        rv_data.isFocusable = false

        rv_data.adapter = adapter

        presenter.countData(movement?.property)
    }

    override fun sortDescByDate(items: MutableList<Property>?) {
        adapter.setItems(items)
    }

    override fun showEmptyState(isShow: Boolean) {
        if (!isShow) { presenter.sortBescByDate(movement?.property) }

        rv_data.visibility = if(isShow) View.GONE else View.VISIBLE
        lay_empty_state.visibility = if(isShow) View.VISIBLE else View.GONE
    }

    override fun showDialog(message: String?) {

    }

    override fun showProgress() {
        ProgressDialogHelper.showProgress(progressDialog)
    }

    override fun hideProgress() {
        ProgressDialogHelper.dismissProgress(progressDialog)
    }

    override fun goToMaintenance() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
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