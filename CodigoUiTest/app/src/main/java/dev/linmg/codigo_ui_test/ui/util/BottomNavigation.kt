package dev.linmg.uitestpractise.ui.util

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.linmg.codigo_ui_test.R
import dev.linmg.codigo_ui_test.ui.theme.PrimaryColor
import dev.linmg.codigo_ui_test.ui.theme.greyTextColor


@Composable
fun BottomNavigationBar() {
    val items = listOf(
        NavigationItem.RentACar,
        NavigationItem.Bookings,
        NavigationItem.ReferAFriend,
        NavigationItem.Account,
    )
    BottomNavigation (
        backgroundColor = PrimaryColor,
        contentColor = greyTextColor,
    ) {
        items.forEach { item ->
            val modifier=if(item.icon== R.drawable.ic_group23) Modifier.size(40.dp) else Modifier.size(30.dp)
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title,modifier = modifier)},
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    /* Add code later */
                }
            )
        }
    }
}

