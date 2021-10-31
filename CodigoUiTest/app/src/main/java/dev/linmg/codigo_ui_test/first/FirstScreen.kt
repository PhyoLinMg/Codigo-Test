package dev.linmg.codigo_ui_test.first

import ZoomableImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import dev.linmg.codigo_ui_test.R
import dev.linmg.codigo_ui_test.ui.theme.greenBtnColor
import dev.linmg.codigo_ui_test.ui.theme.pinkTextColor
import dev.linmg.codigo_ui_test.ui.route.Screens

import dev.linmg.uitestpractise.ui.util.BottomNavigationBar


@Composable
fun FirstScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar()}
    ) {
        Box(Modifier.fillMaxSize().padding(bottom = 56.dp)) {
            ZoomableImage()
            Card(shape = CircleShape,elevation = 3.dp,modifier = Modifier.align(Alignment.TopEnd).padding(horizontal = 20.dp,vertical = 40.dp)){
                Image(painter = painterResource(R.drawable.ic_icoactionalert),contentDescription = "")
            }
            Column(modifier = Modifier.align(Alignment.BottomCenter)){
                Text(
                    "Rent A Car",
                    style = MaterialTheme.typography.h4.copy(color = pinkTextColor),
                    modifier = Modifier.padding(start=10.dp)
                )
                Card(
                    elevation = 10.dp,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(12.dp))
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
                            Spacer(modifier = Modifier.width(70.dp))
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
                            Spacer(modifier = Modifier.width(20.dp))
                            Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.clickable {
                                navController.navigate(Screens.SecondScreen.route)
                            }) {
                                Box(modifier = Modifier.background(color= greenBtnColor).fillMaxSize().padding(horizontal = 35.dp,vertical = 20.dp)){
                                    Text(
                                        "GO",
                                        style = MaterialTheme.typography.h5.copy(color = Color.Black,fontWeight = FontWeight.Bold),
                                        )

                                }
                            }


                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}