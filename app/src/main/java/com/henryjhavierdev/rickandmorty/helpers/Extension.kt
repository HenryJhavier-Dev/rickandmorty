package com.henryjhavierdev.rickandmorty.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//Funciones de extension

fun Context.generalToast(message: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message,   length).show()
}

//Toast Recycler view
fun RecyclerView.ViewHolder.generalToast(message: String){
    itemView.context.generalToast( message, Toast.LENGTH_SHORT)
}

// Sobre viewGroup que nos permite inflar vistas
fun ViewGroup.inflateView(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutRes,
        this, false)
}

//En este caso esta función nos abstraera de la libreria que se usa y
//De ser el caso la podemos cambiar solo aqui
fun ImageView.loadImageViewFromUrl(url: String) = Glide.with(this)
    .load(url).into(this)
