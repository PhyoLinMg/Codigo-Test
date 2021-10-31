package dev.linmg.codigo_ui_test.third

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dev.linmg.codigo_ui_test.R
import dev.linmg.codigo_ui_test.ui.theme.*


@ExperimentalPagerApi
@Composable
fun ThirdScreen(navController: NavHostController, carName: String?, carType: String?) {
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = { topAppBar(navController) }
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                "$carName",
                style = MaterialTheme.typography.h4.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(top = 12.dp, start = 10.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    "$carType",
                    style = MaterialTheme.typography.body1.copy(color = Color.Black)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier.background(
                        color = statusColor,
                        shape = RoundedCornerShape(5.dp)
                    ).padding(2.dp)
                ) {
                    Text(
                        "Confirmed",
                        style = MaterialTheme.typography.body2.copy(color = Color.White)
                    )
                }
            }
            Row(modifier = Modifier.padding(vertical = 12.dp)) {
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        "Start Date",
                        style = MaterialTheme.typography.caption.copy(color = greyTextColor)
                    )
                    Text(
                        "Mon 1, Nov 28",
                        style = MaterialTheme.typography.body2.copy(color = Color.Black)
                    )
                    Text(
                        "10:47 AM",
                        style = MaterialTheme.typography.body2.copy(color = Color.Black)
                    )
                }
                Spacer(modifier = Modifier.width(80.dp))
                Column {
                    Text(
                        "End Date",
                        style = MaterialTheme.typography.caption.copy(color = greyTextColor)
                    )
                    Text(
                        "Tue 2, Nov 28",
                        style = MaterialTheme.typography.body2.copy(color = Color.Black)
                    )
                    Text(
                        "11:47 AM",
                        style = MaterialTheme.typography.body2.copy(color = Color.Black)
                    )
                }
                Spacer(modifier = Modifier.width(60.dp))
                Card(shape = RoundedCornerShape(5.dp), backgroundColor = PrimaryColor) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Text(
                            "Duration",
                            style = MaterialTheme.typography.caption.copy(color = captionTextColor),
                            textAlign = TextAlign.Center
                        )
                        Row {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "02",
                                    style = MaterialTheme.typography.body2.copy(color = Color.White)
                                )
                                Text(
                                    "Days",
                                    style = MaterialTheme.typography.caption.copy(color = Color.White)
                                )
                            }
                            Text(
                                ":",
                                style = MaterialTheme.typography.body2.copy(color = Color.White)
                            )
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "00",
                                    style = MaterialTheme.typography.body2.copy(color = Color.White)
                                )
                                Text(
                                    "hours",
                                    style = MaterialTheme.typography.caption.copy(color = Color.White)
                                )
                            }
                        }

                    }

                }
                Spacer(modifier = Modifier.width(10.dp))
            }
            Row(horizontalArrangement = Arrangement.SpaceAround,modifier = Modifier.fillMaxWidth()){
                Spacer(modifier = Modifier.width(10.dp))
                Box(modifier=Modifier.border(BorderStroke(1.dp,Color.Black)).clip(RoundedCornerShape(3.dp)).weight(0.2f).padding(horizontal = 20.dp,vertical = 10.dp),contentAlignment = Alignment.Center){
                    Row{
                        Icon(Icons.Filled.Add,contentDescription = "")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Add to Calendar")
                    }
                }
                Spacer(modifier = Modifier.width(40.dp))
                Box(modifier=Modifier.border(BorderStroke(1.dp,Color.Black)).clip(RoundedCornerShape(3.dp)).weight(0.2f).padding(horizontal = 20.dp,vertical = 10.dp),contentAlignment = Alignment.Center){
                    Row{
                        Icon(Icons.Filled.CheckCircle,contentDescription = "")
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Extend")
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalPager(
                    count = 10,
                    state = pagerState,
                    // Add 32.dp horizontal padding to 'center' the pages
                    modifier = Modifier.size(300.dp),
                ) { page ->
                    Image(
                        painterResource(R.drawable.car1),
                        contentDescription = "",
                        modifier = Modifier.size(300.dp, 200.dp),
                    )
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)

                )
            }
            Text(
                "Pick Up Car At",
                style = MaterialTheme.typography.h5.copy(color = pinkTextColor),
                modifier = Modifier.padding(10.dp),
            )
            Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(horizontal= 10.dp)) {
                Image(
                    painterResource(R.drawable.mapview_2),
                    contentDescription = "",
                    modifier = Modifier.size(120.dp, 200.dp).clip(RoundedCornerShape(5.dp)),
                )
                Column (modifier = Modifier.padding(start=10.dp)){
                    Text("194 Panngugl Road P 100 Multi Storey Car Park", maxLines = 2,style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Level 4/ Lot -23 -3432",style = MaterialTheme.typography.body2)
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(border = BorderStroke(1.dp,Color.Black)) {
                        Row(modifier = Modifier.padding(horizontal = 10.dp,vertical = 8.dp)) {
                            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "")
                            Spacer(modifier = Modifier.width(10.dp))
                            Text("GET DIRECTIONS")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        box(painterResource(R.drawable.img_car_park))
                        Spacer(modifier = Modifier.width(10.dp))
                        box(painterResource(R.drawable.img_car_park))
                        Spacer(modifier = Modifier.width(10.dp))
                        box(painterResource(R.drawable.img_car_park))
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier.size(45.dp, 45.dp).clip(RoundedCornerShape(3.dp))
                                .border(
                                    BorderStroke(
                                        1.dp,
                                        captionTextColor
                                    )
                                ).padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("+10",style = MaterialTheme.typography.caption)
                        }
                    }

                }

            }

            Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text("Estimated Total",style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold))
                    Text("*Amount is not Final",style = MaterialTheme.typography.caption.copy(color= captionTextColor))
                }
                Row {
                    Text("SG $3200",style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold))
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "")
                }

            }
            Divider(modifier = Modifier.fillMaxWidth().then(Modifier.padding(top = 8.dp)))
            Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()) {
                Text("Need Help?",style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold))
                Image(painterResource(R.drawable.ic_icohelphelp), contentDescription = "")

            }
            Divider(modifier = Modifier.fillMaxWidth().then(Modifier.padding(top = 8.dp)))

            Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()) {
                Text("Cancel This Meeting",style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold))
                Image(painterResource(R.drawable.ic_icohelpcancel), contentDescription = "")

            }
            Divider(modifier = Modifier.fillMaxWidth().then(Modifier.padding(top = 8.dp)))
        }
    }

}

@Composable
fun box(painter: Painter) {
    Box(modifier = Modifier.size(60.dp, 60.dp).clip(RoundedCornerShape(3.dp))) {
        Image(painter, contentDescription = "")
    }
}

@Composable
fun topAppBar(navController: NavHostController) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(text = "")
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "")
            }
        },

        actions = {
            Text("Booking ID:232323 ",style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold,color = Color.Black))
            Image(painterResource(R.drawable.ic_icoactioncopy), contentDescription = "")
        },
        elevation = AppBarDefaults.TopAppBarElevation
    )
}