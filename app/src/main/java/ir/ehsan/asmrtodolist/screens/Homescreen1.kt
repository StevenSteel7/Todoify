package ir.ehsan.asmrtodolist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.ehsan.asmrtodolist.R
import ir.ehsan.asmrtodolist.components.ButtonComp
import ir.ehsan.asmrtodolist.components.HeadingTextComponent
import ir.ehsan.asmrtodolist.data.SignUpViewModel
import ir.ehsan.asmrtodolist.navigation.Screen
import ir.ehsan.asmrtodolist.navigation.SystemBackButtonHandler
import ir.ehsan.asmrtodolist.navigation.pageRouter

@Composable
fun Homescreen(loginViewModel: SignUpViewModel){
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        SystemBackButtonHandler {
            pageRouter.navigateTo(Screen.SignUpScreen)
        }

        Column {

            HeadingTextComponent(value = stringResource(id = R.string.homescreen))

            ButtonComp(
                value = stringResource(id = R.string.Signout), onButtonClicked = {
                    loginViewModel.logout()}
                    , isEnabled = true
            )

        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfhome(){
    Homescreen(loginViewModel = SignUpViewModel())
}
