package com.hadilo.hdlfit.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.hadilo.hdlfit.R
import com.hadilo.hdlfit.inputData.InputDataActivity
import com.hadilo.hdlfit.model.DataModel

import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    val TAG = "Main2Activity"

    val INSERT_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent(this, InputDataActivity::class.java)
            startActivityForResult(intent, INSERT_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (requestCode == INSERT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                val model = intent?.getParcelableExtra<DataModel>("DATA_MODEL")
                Log.d(TAG, "onActivityResult: $model")

            }
        }
    }
}
