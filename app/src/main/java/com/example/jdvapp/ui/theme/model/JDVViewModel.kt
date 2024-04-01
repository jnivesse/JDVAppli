package com.example.jdvapp.ui.theme.model

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class JDVViewModel:ViewModel() {
    @SuppressLint("MutableCollectionMutableState")
    var grid = mutableStateOf(ArrayList<ArrayList<Int>>())
    var user = mutableStateOf(UserBean(userPseudo = ""))

}