package com.brewble.pocketcpmplus.controllers

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.brewble.pocketcpmplus.R
import android.databinding.DataBindingUtil.inflate
import com.brewble.pocketcpmplus.databinding.MainLayoutBinding

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)

        val mainView: MainLayoutBinding = inflate(layoutInflater, R.layout.main_layout, null, false )

        setContentView(mainView.root)

        supportFragmentManager
                .beginTransaction()
                .replace(mainView.container.id, ProjectListFragment())
                .commit()
    }
}
