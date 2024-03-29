package com.durov.solutions.manager.domain.navigation

import com.durov.solutions.manager.navigation.Dialog
import com.durov.solutions.manager.navigation.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface NavigationRepository {
    val screenState: StateFlow<Screen>
    val dialogState: StateFlow<Dialog>
    val finishApp: Flow<Unit>
    suspend fun back()

    fun navigate(screen: Screen)

    fun replace(screen: Screen)

    fun showDialog(dialog: Dialog)

    fun clearDialogs()

}
