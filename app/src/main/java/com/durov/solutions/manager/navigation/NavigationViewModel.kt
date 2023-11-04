package com.durov.solutions.manager.navigation


import com.durov.solutions.manager.core.BaseViewModel

class NavigationViewModel : BaseViewModel() {
    val dialogState = currentDialogState
    val screenState = currentScreenState
    val finishAppFlow = finishApp
}
