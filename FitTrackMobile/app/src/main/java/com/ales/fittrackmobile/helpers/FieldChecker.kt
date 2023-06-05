package com.ales.fittrackmobile.helpers

import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText

class FieldChecker {

    companion object {
        fun checkField(field: TextInputEditText): Boolean {
            if (field.text.isNullOrEmpty()) {
                field.error = "You must enter this field"
                return false
            }
            field.error = null
            return true
        }

        fun checkField(field: AutoCompleteTextView): Boolean {
            if (field.text.isNullOrEmpty()) {
                field.error = "You must enter this field"
                return false
            }
            field.error = null
            return true
        }
    }
}