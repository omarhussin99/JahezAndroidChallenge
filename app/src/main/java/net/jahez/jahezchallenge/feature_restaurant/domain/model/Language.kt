package net.jahez.jahezchallenge.feature_restaurant.domain.model

enum class Language {

    ENGLISH {
        override fun toString(): String {
            return "en"
        }
    }

    , ARABIC {
        override fun toString(): String {
            return "ar"
        }
    }
}