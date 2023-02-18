package com.udacity.mawardy.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class DisplayManager {
    companion object {
        fun showConfirmationDialog(
            context: Context,
            message: Int,
            positiveWord: Int,
            negativeWord: Int,
            callback: DialogInterface.OnClickListener
        ) {
            val alertDialog = AlertDialog.Builder(context).setMessage(message).setCancelable(false)
                .setPositiveButton(positiveWord, callback)
                .setNegativeButton(negativeWord, null)
                .create()
            alertDialog.show()
        }
    }
}