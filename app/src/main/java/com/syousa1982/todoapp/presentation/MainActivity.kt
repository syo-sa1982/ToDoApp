package com.syousa1982.todoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syousa1982.todoapp.R

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)

        when {
            savedInstanceState == null -> {
                val fragment = TodoCollectionFragment()
                supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
            }
        }
    }
}
