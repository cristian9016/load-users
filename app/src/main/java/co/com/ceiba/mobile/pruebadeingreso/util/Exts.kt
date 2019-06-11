package co.com.ceiba.mobile.pruebadeingreso.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup

fun ViewGroup.inflate(layout:Int) = LayoutInflater.from(context).inflate(layout,this,false)

inline fun <reified T : ViewModel> AppCompatActivity.buildViewModel(): T
        = ViewModelProviders.of(this).get(T::class.java)