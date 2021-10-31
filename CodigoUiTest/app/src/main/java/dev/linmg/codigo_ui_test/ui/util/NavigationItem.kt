package dev.linmg.uitestpractise.ui.util

import dev.linmg.codigo_ui_test.R


sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object RentACar : NavigationItem("rent_a_car", R.drawable.ic_group23, "Rent A Car")
    object Bookings : NavigationItem("bookings", R.drawable.ic_icodated, "Bookings")
    object ReferAFriend : NavigationItem("refer_a_friend", R.drawable.parcel, "Refer A Friend")
    object Account : NavigationItem("account", R.drawable.settings, "Account")

}