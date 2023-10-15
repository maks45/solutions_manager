package com.durov.solutions.manager.screen.home

import com.durov.solutions.manager.core.BaseViewModel
import com.durov.solutions.manager.model.Subject
import com.durov.solutions.manager.navigation.Screen

class HomeViewModel : BaseViewModel() {
    fun editSubject(subject: Subject) {
        navigate(Screen.Subject(subject.id))
    }

    fun addNewSubject() {
        navigate(Screen.Subject())
    }
}

