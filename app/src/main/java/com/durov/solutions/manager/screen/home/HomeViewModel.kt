package com.durov.solutions.manager.screen.home

import com.durov.solutions.manager.core.BaseViewModel
import com.durov.solutions.manager.navigation.Screen

class HomeViewModel : BaseViewModel() {
    fun addNewSubject() {
        navigate(Screen.Subject(123))
    }
}

