package com.ales.fittrackmobile.helpers

import android.content.Context
import android.widget.ArrayAdapter
import com.google.android.material.R

class Autocomplete {

    companion object {
        fun getAutocompleteAdapter(list: List<String>, context: Context): ArrayAdapter<String> {

            return ArrayAdapter(
                context,
                R.layout.support_simple_spinner_dropdown_item,
                list)
        }
    }

}