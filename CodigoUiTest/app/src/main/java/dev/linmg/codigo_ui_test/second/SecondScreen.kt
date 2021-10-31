package dev.linmg.codigo_ui_test.second

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import dev.linmg.codigo_ui_test.R

import dev.linmg.codigo_ui_test.model.Car
import dev.linmg.codigo_ui_test.model.cars
import dev.linmg.codigo_ui_test.ui.route.Screens
import dev.linmg.codigo_ui_test.ui.theme.*



@Composable
fun SecondScreen(navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    "Search results",
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            backgroundColor = Color.White,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Filled.KeyboardArrowLeft,
                        contentDescription = ""
                    )
                }
            },

            )
    }) {
        Column(modifier = Modifier.padding(top = 12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.LocationOn,
                    contentDescription = "",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Column {
                    Text(
                        "Location",
                        style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                    )
                    Text("Current Location", style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.padding(bottom = 12.dp))
                }
            }
            Divider(modifier = Modifier.fillMaxWidth().then(Modifier.padding(top = 8.dp)))

            Spacer(modifier = Modifier.padding(top = 12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.Start
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        Icons.Outlined.Face,
                        contentDescription = "",
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Column {
                        Text(
                            "Date & Time",
                            style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                        )
                        Text("Now", style = MaterialTheme.typography.body1)
                    }
                }

                Spacer(modifier = Modifier.width(100.dp))
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 0.dp)
                ) {
                    Icon(
                        Icons.Outlined.Face,
                        contentDescription = "",
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Column {
                        Text(
                            "Duration",
                            style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                        )
                        Text("1hr", style = MaterialTheme.typography.body1)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CarList(navController)
        }
    }
}

@Composable
fun CarList(navController: NavHostController) {
    Column(modifier = Modifier.background(color = PrimaryColor)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row() {
                Text("300+", style = MaterialTheme.typography.body1.copy(color = pinkTextColor))
                Text(
                    " Cars Found",
                    style = MaterialTheme.typography.body1.copy(color = captionTextColor)
                )
            }

            Row() {
                Button(
                    onClick = { /* ... */ },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)

                ) {
                    // Inner content including an icon and a text label
                    Text("Filter")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(
                    onClick = { /* ... */ },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),


                    ) {
                    // Inner content including an icon and a text label
                    Text("Map")
                }
            }
        }
        LazyColumn {
            items(cars) { car ->
                CarItem(car,onClick = {navController.navigate(Screens.ThirdScreen.route.plus("/${car.name}/${car.type}"),)})
            }
        }
    }


}

@Composable
fun CarItem(car: Car, onClick:()->Unit) {
    val img = if (car.id % 2 == 0) R.drawable.car else R.drawable.car1
    Row(modifier = Modifier.padding(10.dp).clickable { onClick() }) {
        Card(
            modifier = Modifier.size(100.dp, 80.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 2.dp

        ) {
            Image(
                painterResource(img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                car.name,
                style = MaterialTheme.typography.body2.copy(
                    color = headerTextColor,
                    fontWeight = FontWeight.Bold
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    car.type,
                    style = MaterialTheme.typography.caption.copy(color = captionTextColor)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    "${car.noOfSeat} Seater",
                    style = MaterialTheme.typography.caption.copy(color = captionTextColor)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Card(
                    border = BorderStroke(2.dp, Color.Gray),
                    backgroundColor = PrimaryColor,
                    modifier = Modifier.padding(horizontal = 3.dp)
                ) {
                    Text(
                        "0.5 KM AWAY",
                        color = greenBtnColor,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(
                        "Rental Fee",
                        style = MaterialTheme.typography.caption.copy(color = greyTextColor)
                    )
                    Text(
                        "Fr. $3.0/hr",
                        style = MaterialTheme.typography.caption.copy(color = captionTextColor)
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column {
                    Text(
                        "Mileage Fee",
                        style = MaterialTheme.typography.caption.copy(color = greyTextColor)
                    )
                    Text(
                        "$0.40/ km",
                        style = MaterialTheme.typography.caption.copy(color = greyTextColor)
                    )
                }
            }
        }

    }
}
