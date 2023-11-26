package ir.ehsan.asmrtodolist.data.rules



object Validator {


    data class ValidationResult(
        val status :Boolean = false
    )

    //used in LoginViewModel
    fun validateFirstName(fName:String) : ValidationResult {
        return ValidationResult(
            (!fName.isNullOrEmpty() && fName.length>=3)
        )

    }

    fun validateLast(lName:String) : ValidationResult{
        return ValidationResult(
            (!lName.isNullOrEmpty() && lName.length>=3)
        )
    }

    fun validateEmail(email:String) : ValidationResult{
        /*  val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"   */
        return ValidationResult(
            ( !email.isNullOrEmpty() /*&& email.matches(emailRegex.toRegex())*/)
        )
    }

    fun validatePassword(pass:String): ValidationResult{
        return ValidationResult(
            (!pass.isNullOrEmpty() && pass.length>=6)
        )

    }

    fun validatePrivacyPolicyClicked(statusValue :Boolean) :ValidationResult {
        return ValidationResult(
            statusValue
        )
    }
}

