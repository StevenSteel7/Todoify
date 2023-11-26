package ir.ehsan.asmrtodolist.data


// iF USER TAKES ACTION , UI event is triggred and capture it in LoginViewModel and update the UI state
sealed class LoginUiEvent{

    data class EmailChanged(val email: String): LoginUiEvent()
    data class PasswordChanged(val password: String): LoginUiEvent()
    object LoginButtonClicked   : LoginUiEvent() // Its implementation in loginviewmodel




}
