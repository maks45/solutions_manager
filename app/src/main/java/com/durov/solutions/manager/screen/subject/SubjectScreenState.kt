package com.durov.solutions.manager.screen.subject

import com.durov.solutions.manager.model.Subject

sealed interface SubjectScreenState {
    object None : SubjectScreenState
    class UpdateState(val subject: Subject) : SubjectScreenState

}
