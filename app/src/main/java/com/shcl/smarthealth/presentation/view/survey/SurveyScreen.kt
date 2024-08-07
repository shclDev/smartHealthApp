package com.shcl.smarthealth.presentation.view.survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.navigation.OuterScreen
import com.shcl.smarthealth.presentation.ui.common.CustomButton
import com.shcl.smarthealth.presentation.view.survey.component.Step
import com.shcl.smarthealth.presentation.view.survey.content.surveyAct
import com.shcl.smarthealth.presentation.view.survey.content.surveyDepression
import com.shcl.smarthealth.presentation.view.survey.content.surveyFood
import com.shcl.smarthealth.presentation.view.survey.content.surveySleep
import com.shcl.smarthealth.presentation.view.survey.content.surveySmoking
import com.shcl.smarthealth.ui.theme.BackGroundColor
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.Typography
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun LinearVerticalLine() {

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(60f.pxToDp())
            .background(brush = Brush.verticalGradient(BackGroundColor))
    )

}

@Composable
fun SurveyScreen(nav: NavHostController, viewModel: SurveyViewModel = hiltViewModel()) {

    val level by viewModel.levelState.collectAsStateWithLifecycle()
    val levelTitle by viewModel.levelTitleState.collectAsStateWithLifecycle()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Row{
            LinearVerticalLine()

            Column(modifier = Modifier
                .padding(
                    start = 80f.pxToDp(),
                    top = 70f.pxToDp(),
                    bottom = 40f.pxToDp(),
                    end = 85f.pxToDp()
                )
                .fillMaxWidth()){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    , horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    surveyTitle(level = level, levelTitle = levelTitle)
                    stepProgressBar(level)
                }

                Box(modifier = Modifier.weight(0.8f)) {
                    when(level){
                        1-> surveyFood()
                        2-> surveySleep()
                        3-> surveyDepression()
                        4-> surveySmoking()
                        5-> surveyAct()
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 100f.pxToDp())
                ){
                    CustomButton(
                        buttonWidth = 553f,
                        btnClick = {
                            viewModel.prev()
                            //nav.navigate(route = OuterScreen.registser.route)
                        },
                        contentColor = Color143F91, containerColor = Color.White , withBoard = true, text = "이전" , leftIcon = painterResource(id = R.drawable.left_arrow)
                    )
                    CustomButton(
                        btnClick = {
                            viewModel.next()
                        },
                        withBoard = false,
                        buttonWidth = 553f,
                        contentColor = Color.White, containerColor = Color143F91 , text = "다음" , rightIcon = painterResource(id = R.drawable.arrow) )
                }
            }
        }
    }
}

@Composable
fun surveyTitle(level : Int, levelTitle : SurveyByLevel){

    Column() {
        Row(){
            Text("${level}단계" , style = Typography.headlineMedium , fontSize = 35f.pxToSp() , fontWeight = FontWeight.W700)
            Spacer(modifier = Modifier.width(10f.pxToDp()))
            Text("${levelTitle.title}" , style = Typography.headlineMedium , fontSize = 35f.pxToSp())
        }
        Text("* 다음 질문들은 귀하의 ${levelTitle.desc}에 관한 질문입니다." , style = Typography.headlineMedium , fontSize = 22f.pxToSp() , color = Color757575)
    }
}

@Composable
fun stepProgressBar(currentStep : Int){

    Row(){
         for(step in 1..currentStep){
             Step(step)
         }
    }


}

