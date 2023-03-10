package ru.fitnessapp.domain.usecase

class ValidateLogin {
    companion object {

        private val EMAIL_REGEX = Regex(
            "[a-zA-Z0-9+._%\\-]{1,20}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,15}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,4}" +
                    ")+")
    }

    operator fun invoke(email: String): Boolean {

        return (email.matches(EMAIL_REGEX))
    }
}