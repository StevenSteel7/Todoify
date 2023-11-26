package ir.ehsan.asmrtodolist.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.ehsan.asmrtodolist.data.SignUpViewModel

import ir.ehsan.asmrtodolist.navigation.Screen
import ir.ehsan.asmrtodolist.navigation.pageRouter
import ir.ehsan.asmrtodolist.screens.HomeScreen

//import ir.ehsan.asmrtodolist.screens.HomeScreen
import ir.ehsan.asmrtodolist.screens.Homescreen
import ir.ehsan.asmrtodolist.screens.Login
import ir.ehsan.asmrtodolist.screens.SignUpScreen
import ir.ehsan.asmrtodolist.screens.TermsAndCodition


@Composable
fun theApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {   //actually changing the page...
        Crossfade(targetState = pageRouter.currentScreen, label = "") { currentState->
            when(currentState.value){
                is Screen.SignUpScreen ->{ SignUpScreen(signUpViewModel = SignUpViewModel()) }
                is Screen.TermsAndConditionScreen -> { TermsAndCodition() }
                is Screen.Login -> { Login() }
                is Screen.Homescreen1 -> { HomeScreen()}
               /* is Screen.Homescreen -> { HomeScreen() }*/
                
            }
            
        }
    }
}

