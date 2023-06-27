package com.teacomputers.moviesapp.common.connectionlivedata

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.teacomputers.moviesapp.R
import com.teacomputers.moviesapp.common.getStringText

object CustomDialogConnection {
    var dialog2: Dialog? = null

    @SuppressLint("InflateParams")
    fun showHintDialogWifi(context: Activity) {
        dialog2 = Dialog(context)
        dialog2!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog2!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val view: View = LayoutInflater.from(context).inflate(R.layout.dialog_show_connection, null)

        val continueTextView = view.findViewById<TextView>(R.id.continueTextView)

        continueTextView.setOnClickListener {
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            context.startActivity(intent)
        }
        val closeTextView = view.findViewById<TextView>(R.id.closeTextView)

        closeTextView.getStringText(R.string.cancel)

        closeTextView.setOnClickListener {
            dialog2!!.dismiss()
        }

        dialog2!!.setContentView(view)
        dialog2!!.setCancelable(false)
        dialog2!!.setCanceledOnTouchOutside(false)
        try {
            if (!context.isFinishing) {
                if (!dialog2!!.isShowing) {
                    dialog2!!.show()
                }
            }
        } catch (e: WindowManager.BadTokenException) {
            e.printStackTrace()
            // use a log message
        }
    }
}
