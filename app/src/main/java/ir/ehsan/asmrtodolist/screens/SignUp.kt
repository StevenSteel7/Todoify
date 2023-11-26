package ir.ehsan.asmrtodolist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.ehsan.asmrtodolist.R
import ir.ehsan.asmrtodolist.components.AlreadyAMemberComp
import ir.ehsan.asmrtodolist.components.ButtonComp
import ir.ehsan.asmrtodolist.components.CheckBoxComp
import ir.ehsan.asmrtodolist.components.DividerTextComp
import ir.ehsan.asmrtodolist.components.HeadingTextComponent
import ir.ehsan.asmrtodolist.components.MyPassField
import ir.ehsan.asmrtodolist.components.MyTextField
import ir.ehsan.asmrtodolist.components.NormalTextComponent
import ir.ehsan.asmrtodolist.data.SignUpViewModel
import ir.ehsan.asmrtodolist.data.SignupUiEvent
import ir.ehsan.asmrtodolist.navigation.Screen
import ir.ehsan.asmrtodolist.navigation.pageRouter

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel)
    {

    Surface(
        color = Color.White,
        modifier  = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(38.dp)
    ) {             // using String from strings.xml

        //for one below other
        Column(modifier = Modifier.fillMaxSize()){

        NormalTextComponent(value = stringResource(id = R.string.hello))
        HeadingTextComponent(value = stringResource(id = R.string.create_account))

            Spacer(modifier = Modifier.height(20.dp))

            MyTextField(labelValue = stringResource(id = R.string.first_name ),
                    painterResource(id = R.drawable.user_24)
                ,//we need to tell view model the changed text
                onTextChanged = {
                    //When text is changed.. we call on event
                    signUpViewModel.onEvent(SignupUiEvent.FirstNameChanged(it))
                                    //from loginView that we created in UiEvent
                },

                errorStatus = signUpViewModel.registrationUIState.value.firstNameError

            )

            MyTextField(labelValue = stringResource(id = R.string.last_name),
                painterResource(id = R.drawable.user_24)// for icon
                , onTextChanged = {signUpViewModel.onEvent(SignupUiEvent.LastNameChanged(it))},
                errorStatus = signUpViewModel.registrationUIState.value.lastNameError

            )

            MyTextField(labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.email_24)
                , onTextChanged = {signUpViewModel.onEvent(SignupUiEvent.EmailChanged(it))},
                errorStatus = signUpViewModel.registrationUIState.value.emailError
            )

            MyPassField(labelValue = stringResource(id = R.string.pass),
                painterResource(id = R.drawable.baseline_lock_24)
                , onTextChanged = {signUpViewModel.onEvent(SignupUiEvent.PasswordChanged(it))},
                errorStatus = signUpViewModel.registrationUIState.value.passError
            )


            CheckBoxComp(
                value = stringResource(id = R.string.terms_and_conditions)
                ,onTextSelected = {
                pageRouter.navigateTo(Screen.TermsAndConditionScreen)
                }
                ,onCheckedChange = {
                    signUpViewModel.onEvent(SignupUiEvent.PrivacyPolicyCheckBoxClicked(it))
                }

            )

            Spacer(modifier = Modifier.height(40.dp))

                // When button is clicked.. this is triggred
            ButtonComp(value = stringResource(id = R.string.signup), onButtonClicked = {
                signUpViewModel.onEvent(SignupUiEvent.RegisterButtonClicked)
                //From LoginViewModel.kt }
            },
            isEnabled = signUpViewModel.allValidationResult.value

            )
            DividerTextComp()


            AlreadyAMemberComp(text1 = "Already a Member? ",text2 = "Login ",onTextSelected = {
                pageRouter.navigateTo(Screen.Login)
                }
            )





        }

    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUp(){
    SignUpScreen(signUpViewModel = SignUpViewModel())
}