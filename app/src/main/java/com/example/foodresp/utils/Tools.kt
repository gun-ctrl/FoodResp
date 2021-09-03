package com.example.foodresp.utils

import android.content.Context
import android.widget.Toast

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
fun showToast(context: Context,message:String){
    Toast.makeText(context,"Get Recipes Failed:$message",Toast.LENGTH_LONG)
        .show()
}
