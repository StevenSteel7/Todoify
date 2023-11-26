package ir.ehsan.asmrtodolist.data



// iF USER TAKES ACTION , UI event is triggred and capture it in LoginViewModel and update the UI state
sealed class SignupUiEvent{
    data class FirstNameChanged(val firstName: String): SignupUiEvent()
    //if user enters first name this event is triggered
    data class LastNameChanged(val lastName: String): SignupUiEvent()
    data class EmailChanged(val email: String): SignupUiEvent()
    data class PasswordChanged(val password: String): SignupUiEvent()
    data class PrivacyPolicyCheckBoxClicked(val status :Boolean ) :SignupUiEvent()


    object RegisterButtonClicked : SignupUiEvent()




}
