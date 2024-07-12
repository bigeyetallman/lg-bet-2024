package com.bigeyetallman.welcometopranking.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
import com.bigeyetallman.welcometopranking.R
import kotlin.math.roundToInt

class Utils {
    companion object {
        fun getCustomProgressDialog(
            context: Context?,
            progressText: String?
        ): CustomProgressDialog? {
            val customProgressDialog = CustomProgressDialog(
                context!!,
                progressText!!
            )
            customProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            return customProgressDialog
        }
    }
}