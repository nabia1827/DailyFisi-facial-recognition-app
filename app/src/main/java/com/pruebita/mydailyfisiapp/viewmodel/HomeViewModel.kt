package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.Event
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.helpers.TokenManager
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.repositories.CourseRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.EventRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.RolRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val context: Context,private val apiService: ApiService): ViewModel(){



}