package com.syousa1982.todoapp.util.extention

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText

/**
 * OnChangedTextListenerをインライン関数で設定
 *
 * @param onChangedTextListener Unit
 */
fun EditText.setOnChangedTextListener(onChangedTextListener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // do nothing
        }

        override fun afterTextChanged(s: Editable?) {
            // do nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onChangedTextListener.invoke(s.toString())
        }
    })
}


fun EditText.setOnEnterKeyListener(onEnterTextListener: (String) -> Unit) {
    setOnKeyListener { v, keyCode, event ->
        if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
            onEnterTextListener.invoke((v as EditText).text.toString())
            return@setOnKeyListener true
        }
        return@setOnKeyListener false
    }
}