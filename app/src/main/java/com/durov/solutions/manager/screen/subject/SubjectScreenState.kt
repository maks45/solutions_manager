package com.durov.solutions.manager.screen.subject

import com.durov.solutions.manager.model.Subject

sealed interface SubjectScreenState {
    object StartLoading : SubjectScreenState

    object FinishLoading : SubjectScreenState
    class UpdateState(val subject: Subject) : SubjectScreenState

}