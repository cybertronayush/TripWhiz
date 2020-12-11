package `in`.ayushsingh.backendrestaurant.modal

data class UserRating(
    var aggregate_rating: String = "",
    var rating_text: String = "",
    var rating_color: String = "",
    var votes: String = "",
    var has_fake_reviews: Int = 0
)