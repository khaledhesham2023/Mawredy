package com.udacity.mawardy.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.udacity.mawardy.R

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        setContentView(R.layout.item_loading)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

    override fun dismiss() {
        if (isShowing)
        super.dismiss()
    }

    override fun show() {
        if (!isShowing)
        super.show()
    }
}