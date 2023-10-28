package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.model.UserManager
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.navigation.ItemMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class RecognizingViewModel @Inject constructor(private val context: Context) : ViewModel() {
    private val userManager: UserManager = UserManager(context)

    fun getMainRoute():String{
        var route = when (userManager.getIdRol()) {
            1 -> { AppScreens.MainStudentScreen.route }
            2 -> { AppScreens.MainDeleScreen.route }
            else -> { AppScreens.MainTeacherScreen.route }
        }
        return route

    }
}