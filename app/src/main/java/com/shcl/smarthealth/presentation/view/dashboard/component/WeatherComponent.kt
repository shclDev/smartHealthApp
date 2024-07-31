package com.shcl.smarthealth.presentation.view.dashboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.remote.weather.WeatherResponse
import com.shcl.smarthealth.domain.utils.Utils
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color3AA3FF
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun WeatherComponent(
    weatherResponse: WeatherResponse?,
    refreshClick : ()-> Unit
){

    Box(

        modifier = Modifier
            .size(447f.pxToDp(), 210f.pxToDp())
            .clip(shape = RoundedCornerShape(18f.pxToDp()))
            .background(color = Color3AA3FF)
            .padding(horizontal = 30f.pxToDp(), vertical = 25f.pxToDp())
    ) {

        weatherResponse?.let {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    Row {
                        Text(
                            "${Utils.getCurrentDate()},",
                            style = Typography.titleMedium,
                            fontSize = 15f.pxToSp(),
                            color = Color.White
                        )
                    }
                    Text(
                        "${Utils.getCurrentTime()}",
                        style = Typography.titleMedium,
                        fontSize = 14f.pxToSp(),
                        color = Color.White
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment =Alignment.CenterVertically){

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier
                                .size(79.pxToDp(), 79.pxToDp()),
                            painter = painterResource(id = R.drawable.sunny),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10f.pxToDp()))
                        Text("서울시", style = Typography.titleMedium, fontSize = 20f.pxToSp() , color = Color.White)
                    }

                    Row(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            textAlign = TextAlign.End,
                            text = "${weatherResponse.weather.first().description}",
                            style = Typography.titleMedium,
                            fontSize = 14f.pxToSp(),
                            color = Color.White,
                        )
                        Spacer(modifier = Modifier.width(10f.pxToDp()))
                        Text(
                            "${weatherResponse.main.temp.toString().substring(0,2)}",
                            style = Typography.titleMedium,
                            fontSize = 20f.pxToSp(),
                            color = Color.White
                        )
                    }
                }

                Row(verticalAlignment = Alignment.Top , horizontalArrangement = Arrangement.End){
                    Text(
                        "최근 업데이트 ",
                        style = Typography.titleMedium,
                        fontSize = 14f.pxToSp(),
                        color = Color.White,
                        textAlign = TextAlign.End
                    )

                    Text(
                        modifier = Modifier.clickable { refreshClick()  },
                        text = "${Utils.getCurrentTime()}",
                        style = Typography.titleMedium,
                        fontSize = 14f.pxToSp(),
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.width(10f.pxToDp()))
                    Image(
                        modifier = Modifier
                            .size(15.pxToDp(), 15.pxToDp())
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.icon_refresh),
                        contentDescription = null
                    )
                }
            }
        } ?: run{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

    }

}