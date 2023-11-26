package ir.ehsan.asmrtodolist.data

data class RegistrationUIState(

    var firstName  :String = "",
    var lastName   :String = "",
    var email      :String = "",
    var passowrd   :String = "",
    var privacyPolicyAccepted :Boolean = false,

    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var emailError: Boolean = false,
    var passError: Boolean = false



)