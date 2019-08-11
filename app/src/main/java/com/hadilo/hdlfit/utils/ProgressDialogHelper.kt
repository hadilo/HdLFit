package com.hadilo.hdlfit.utils

import android.app.ProgressDialog

/**
 * Created by Hadilo on 6/11/18.
 */

object ProgressDialogHelper {

    fun showProgress(progressDialog: ProgressDialog) {
        if (!progressDialog.isShowing) {
            progressDialog.isIndeterminate = false
            progressDialog.setCancelable(false)
            progressDialog.setMessage("Mohon Menunggu")
            progressDialog.show()
        }
    }

    fun dismissProgress(progressDialog: ProgressDialog) {
        if (progressDialog.isShowing)
            progressDialog.dismiss()
    }

}
