package com.durov.solutions.manager.screen.subject

import com.durov.solutions.manager.model.Subject

sealed interface SubjectScreenState {
    object LoadingState : SubjectScreenState

    class SubjectState(val subject: Subject) : SubjectScreenState

}