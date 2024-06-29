package com.d121211017.gamedealsnew

import android.content.Context
import android.widget.Toast

fun makeToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}