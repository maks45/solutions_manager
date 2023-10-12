package com.durov.solutions.manager.ui

import com.durov.solutions.manager.core.BaseViewModel

class NavigationViewModel: BaseViewModel() {
    val screenState = currentScreenState
    val finishAppFlow = finishApp
}