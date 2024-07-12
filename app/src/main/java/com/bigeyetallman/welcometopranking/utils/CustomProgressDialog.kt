package com.bigeyetallman.welcometopranking.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import com.bigeyetallman.welcometopranking.R

class CustomProgressDialog(context: Context) : Dialog(context) {
    private var progressBarSpinner: ProgressBar? = null
    private var txtProgress: TextView? = null
    private var progressText = "";

    constructor(context: Context, progressText:String) : this(context) {
        this.progressText = progressText
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        layoutParams.dimAmount = 0.5f
        window!!.attributes = layoutParams

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.dialog_custom_progress)

        progressBarSpinner = findViewById(R.id.progress_bar_spinner)
        txtProgress = findViewById(R.id.txt_progress)
        txtProgress?.text = progressText;
    }
}