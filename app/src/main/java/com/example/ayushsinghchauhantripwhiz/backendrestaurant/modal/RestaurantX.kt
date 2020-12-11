package `in`.ayushsingh.backendrestaurant.modal


data class RestaurantX(
    var R: R = R(),
    var apikey: String = "",
    var id: String = "",
    var name: String = "",
    var url: String = "",
    var location: Location = Location(),
    var switch_to_order_menu: Int = 0,
    var cuisines: String = "",
    var average_cost_for_two: Int = 0,
    var price_range: Int = 0,
    var currency: String = "",
    var offers: List<Any> = listOf(),
    var opentable_support: Int = 0,
    var is_zomato_book_res: Int = 0,
    var mezzo_provider: String = "",
    var is_book_form_web_view: Int = 0,
    var book_form_web_view_url: String = "",
    var book_again_url: String = "",
    var thumb: String = "",
    var user_rating: UserRating = UserRating(),
    var photos_url: String = "",
    var menu_url: String = "",
    var featured_image: String = "",
    var has_online_delivery: Int = 0,
    var is_delivering_now: Int = 0,
    var has_fake_reviews: Int = 0,
    var include_bogo_offers: Boolean = false,
    var deeplink: String = "",
    var is_table_reservation_supported: Int = 0,
    var has_table_booking: Int = 0,
    var events_url: String = "",
    var establishment_types: List<Any> = listOf()
)