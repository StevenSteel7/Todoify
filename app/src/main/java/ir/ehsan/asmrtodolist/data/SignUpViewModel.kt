package ir.ehsan.asmrtodolist.data


import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import ir.ehsan.asmrtodolist.data.rules.Validator
import ir.ehsan.asmrtodolist.navigation.Screen
import ir.ehsan.asmrtodolist.navigation.pageRouter

class SignUpViewModel : ViewModel() {

    //when user enters name.. we'll update value inside registrationUIState
    var registrationUIState = mutableStateOf(RegistrationUIState())
    var allValidationResult = mutableStateOf(false)
    // Same as
    // var allValidationResult = false

    fun onEvent(event: SignupUiEvent){
        when(event){
            is SignupUiEvent.FirstNameChanged ->{
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                // To validate whenever textfield is changed
                validateDataWithRules()

            }
            is SignupUiEvent.LastNameChanged->{
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                validateDataWithRules()


            }
            is SignupUiEvent.EmailChanged->{
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                validateDataWithRules()

            }
            is SignupUiEvent.PasswordChanged->{
                registrationUIState.value = registrationUIState.value.copy(
                passowrd = event.password
            )
                validateDataWithRules()
            }
            is SignupUiEvent.RegisterButtonClicked ->{
               /* signUp()*/
                signUp()

            }

            is SignupUiEvent.PrivacyPolicyCheckBoxClicked ->{
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status  // as we are passing the status from our signup screen

                )
                validateDataWithRules()
            }

        }

    }


    private fun signUp(){
        Log.d(tag ,"inside_signUp")
        printState()
        createUserInFirebase(email =registrationUIState.value.email,
            password = registrationUIState.value.passowrd)
    }

    private fun validateDataWithRules() {

        val fNameResult = Validator.validateFirstName(//pass firstnme
            fName = registrationUIState.value.firstName
        )
        val lNameResult = Validator.validateLast(
            lName = registrationUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val passResult = Validator.validatePassword(
            pass = registrationUIState.value.passowrd
        )

        val privacyPolicyResult  = Validator.validatePrivacyPolicyClicked(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )

        //update the state form the validation result that we got
        registrationUIState.value = registrationUIState.value.copy(
        firstNameError = fNameResult.status,
        lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passError = passResult.status,
            privacyPolicyAccepted = privacyPolicyResult.status
        )



        if(fNameResult.status && lNameResult.status && emailResult.status && passResult.status && privacyPolicyResult.status)
        {allValidationResult.value = true}
        else allValidationResult.value = false

/*      OR.....
        allValidationResult.value = fNameResult.status && lNameResult.status && emailResult.status && passResult.status
*/


    }


    private val tag = SignUpViewModel::class.simpleName

    // to see how we get value form textfields
    private fun printState(){
        Log.d(tag ,"inside_printState")
        Log.d(tag ,registrationUIState.value.toString())

    }


    //FireBase Login intergation
    fun createUserInFirebase(email: String ,password :String ) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)

            .addOnCompleteListener {
            Log.d(TAG,"Inside Complete Listner")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}" )


            if(it.isSuccessful){
                pageRouter.navigateTo(Screen.Homescreen1)
            }
            }

            .addOnFailureListener{
                Log.d(TAG,"inside onfaliure Listner")
                Log.d(TAG,"Exception = ${it.message}")
                Log.d(TAG,"Exception = ${it.localizedMessage}")


            }
    }

    fun logout(){

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut() // method of firebase
        val authStateListener = AuthStateListener{
            if(it.currentUser == null){
                // signout scuccessfull
                Log.d(TAG,"signout success")
                pageRouter.navigateTo(Screen.Login)
            }
            else{Log.d(TAG,"signout failed")}
        }

        firebaseAuth.addAuthStateListener( authStateListener )


    }
}
