package com.brewble.pocketcpmplus.view.fragments

import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.brewble.pocketcpmplus.R
import com.brewble.pocketcpmplus.databinding.MainLayoutBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainView: MainLayoutBinding = inflate(layoutInflater, R.layout.main_layout, null, false)
        setContentView(mainView.root)
        supportFragmentManager
                .beginTransaction()
                .replace(mainView.container.id, ProjectListFragment())
                .commit()
    }
}
