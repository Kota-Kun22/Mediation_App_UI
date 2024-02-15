package com.example.mediation_app_ui.ui.theme

import android.graphics.LightingColorFilter
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.mediation_app_ui.BottomMenuData
import com.example.mediation_app_ui.Features
import com.example.mediation_app_ui.R
import com.example.mediation_app_ui.standardQuadFromTo

@Composable
// IN this project there are some hardcoded value that are just done by hit and trail
fun HomeScreen()
{

    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()){
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Sweet Seep", "Insomnia", "Depression", "anxiety",))
            DailyMeditation(LightRed)
            FeaturesSection(
                features = listOf(
                    Features(
                        title = "Sleep Meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Features(
                        title = "Tips for Sleep",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Features(
                        title = "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Features(
                        title = "Calming Sounds",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )

        }
        BottomMenu(items = listOf(
            BottomMenuData("Home", R.drawable.ic_home),
            BottomMenuData("Meditate",R.drawable.ic_bubble),
            BottomMenuData("Sleep",R.drawable.ic_moon),
            BottomMenuData("Music",R.drawable.ic_music),
            BottomMenuData("Profie",R.drawable.ic_profile),
        ),
            modifier=Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun BottomMenu(items:List<BottomMenuData>,
               modifier: Modifier=Modifier,
               activeHighLightColor:Color= ButtonBlue,
               activeTextColor:Color= Color.White,
               inactiveTextColor:Color= AquaBlue,
               initialSelectedItemIndex:Int=0
){
    var selectedItemIndex by remember { mutableStateOf(initialSelectedItemIndex) }
    Row (horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ){
        items.forEachIndexed{index,item->
            BottomItemMenu(
                item = item,
                isSelected =(index==selectedItemIndex) ,
                activeHighLightColor = activeHighLightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor=inactiveTextColor)
            {
                selectedItemIndex= index

            }

        }

    }

}
@Composable
fun BottomItemMenu(item:BottomMenuData,
                   isSelected:Boolean=false,
                   activeHighLightColor:Color= ButtonBlue,
                   activeTextColor:Color= Color.White,
                   inactiveTextColor:Color= AquaBlue,
                   onItemClick:()->Unit)
{
    Column(horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { 
            onItemClick()
        }) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) activeHighLightColor else Color.Transparent)
            .padding(10.dp)
        )
        {
            Icon(painter = painterResource(id = item.iconIdRes),
                contentDescription = item.title,
                tint= if (isSelected) activeTextColor else  inactiveTextColor,
                modifier = Modifier.size(20.dp))

        }
        Text(text = item.title,
            color= if(isSelected) activeTextColor else inactiveTextColor)

    }

}




@Composable
fun GreetingSection(name:String="Harsh")
{
    Row (horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp))
    {
        Column(verticalArrangement = Arrangement.Center)
        {
            Text(
                text = "Good Morning,$name",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium//h2
                )
            Text(
                text = "We wish you have a Good Day",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium//body1
            )

        }
        Icon(painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(25.dp)
        )

    }

}

@Composable
fun ChipSection(chips:List<String>)
{
    var selectedChipIndex by remember{ mutableStateOf(0) }
    LazyRow(){
        items(chips.size){
            Box(modifier= Modifier
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .clickable { //updating the state of
                    selectedChipIndex = it
                }
                .clip(RoundedCornerShape(15.dp))
                .background(
                    if (selectedChipIndex == it) ButtonBlue
                    else DarkerButtonBlue
                )
                .padding(15.dp),
                contentAlignment = Alignment.Center

            ){
                Text(text = chips[it],color= TextWhite)
            }

        }
    }

}

@Composable
fun DailyMeditation(color:Color= LightRed){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ){
        Column(verticalArrangement = Arrangement.Center)
        {
            Text(
                text = "Daily Thoughts",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium//h2
            )
            Text(
                text = "Meditation . 3-10min",
                style = MaterialTheme.typography.bodyMedium,
                color = TextWhite
            )

        }
        Box( contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp),
        )
        {
            Icon(painter = painterResource(id = R.drawable.ic_play),
                contentDescription ="play Button",
                tint = TextWhite,
                modifier = Modifier.size(16.dp))

        }


    }

}

@Composable
fun FeaturesSection(features:List<Features>){
    Column(modifier= Modifier.fillMaxWidth()) {
        Text(text = "Features",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            modifier= Modifier.padding(15.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start=7.5.dp, end=7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size){
                FeaturesItems(feature = features[it])

            }

        }


    }

}

@Composable
fun FeaturesItems(feature:Features)
{
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkerColor)
    ){
        val width= constraints.maxWidth
        val height=constraints.maxHeight

        //mediium Colored Path
        val mediumColorPoint1= Offset(0f,height*0.3f)
        val mediumColorPoint2= Offset(width*0.1f,height*0.35f)
        val mediumColorPoint3= Offset(width*0.4f,height*0.05f)
        val mediumColorPoint4= Offset(width*0.75f,height*0.7f)
        val mediumColorPoint5= Offset(width*1.4f,-height.toFloat())

        val mediumColorPath= Path().apply{
            moveTo(mediumColorPoint1.x,mediumColorPoint1.y)
            quadraticBezierTo(//we can make a curve like this also but creating class for this will be more efficient
                mediumColorPoint2.x,
                mediumColorPoint2.y,
                (mediumColorPoint1.x+mediumColorPoint2.x)/2f,
                (mediumColorPoint1.y+mediumColorPoint2.y)/2f
            )
            /*here we have pass the offset or can say that point from
            where to where we want to make a curve and in this only we have made the curve by just using this
             (mediumColorPoint1.x+mediumColorPoint2.x)/2f */
            standardQuadFromTo(mediumColorPoint2,mediumColorPoint3)
            standardQuadFromTo(mediumColorPoint3,mediumColorPoint4)
            standardQuadFromTo(mediumColorPoint4,mediumColorPoint5)
            lineTo(width.toFloat()+100f,height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()
        }

        //Light Colored Path
        val LightColorPoint1= Offset(0f,height*0.35f)
        val LightColorPoint2= Offset(width*0.1f,height*0.4f)
        val LightColorPoint3= Offset(width*0.3f,height*0.35f)
        val LightColorPoint4= Offset(width*0.65f,height.toFloat())
        val LightColorPoint5= Offset(width*1.4f,-height.toFloat()/3f)

        val LightColorPath= Path().apply{
            moveTo(LightColorPoint1.x,LightColorPoint1.y)
            quadraticBezierTo(//we can make a curve like this also but creating class for this will be more efficient
                LightColorPoint2.x,
                LightColorPoint2.y,
                (LightColorPoint1.x+LightColorPoint2.x)/2f,
                (LightColorPoint1.y+LightColorPoint2.y)/2f
            )
            /*here we have pass the offset or can say that point from
            where to where we want to make a curve and in this only we have made the curve by just using this
             (mediumColorPoint1.x+mediumColorPoint2.x)/2f */
            standardQuadFromTo(LightColorPoint2,LightColorPoint3)
            standardQuadFromTo(LightColorPoint3,LightColorPoint4)
            standardQuadFromTo(LightColorPoint4,LightColorPoint5)
            lineTo(width.toFloat()+100f,height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize() )
        {
            drawPath(path=mediumColorPath, color = feature.mediumColor)
            drawPath(path=LightColorPath, color = feature.lightColor)

        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)){
            Text(text =feature.title,
                style=MaterialTheme.typography.headlineMedium,
                lineHeight = 25.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(10.dp))
                .padding(vertical = 6.dp, horizontal = 10.dp))
        {
            Text(
                text = "start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


