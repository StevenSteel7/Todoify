package ir.ehsan.asmrtodolist.data


import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

import ir.ehsan.asmrtodolist.navigation.Screen
import ir.ehsan.asmrtodolist.navigation.pageRouter
import ir.ehsan.asmrtodolist.data.rules.Validator

class LoginViewModel : ViewModel() {
    private val tag = LoginViewModel::class.simpleName

    //when user enters name.. we'll update value inside registrationUIState
    var LoginUIState = mutableStateOf(LoginUIState())
    var allValidationResult = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUiEvent){
        when(event){

            is LoginUiEvent.EmailChanged->{
                LoginUIState.value = LoginUIState.value.copy(
                    email = event.email
                )
                validateLoginUIDataWithRules()

            }
            is LoginUiEvent.PasswordChanged->{
                LoginUIState.value = LoginUIState.value.copy(
                passowrd = event.password
            )
                validateLoginUIDataWithRules()
            }
            is LoginUiEvent.LoginButtonClicked ->{
                login()
            }



        }

    }


    private fun login(){
        val email = LoginUIState.value.email
        val pass = LoginUIState.value.passowrd

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    pageRouter.navigateTo(Screen.Homescreen1)
                }
            }
            .addOnFailureListener{

            }

    }

    private fun validateLoginUIDataWithRules() {


        val emailResult = Validator.validateEmail(
            email = LoginUIState.value.email
        )

        val passResult = Validator.validatePassword(
            pass = LoginUIState.value.passowrd
        )


        //update the state from the validation result that we got
        LoginUIState.value = LoginUIState.value.copy(

            emailError = emailResult.status,
            passError = passResult.status,

            )

        if (emailResult.status && passResult.status) {
            allValidationResult.value = true
        } else allValidationResult.value = false



    }
}


