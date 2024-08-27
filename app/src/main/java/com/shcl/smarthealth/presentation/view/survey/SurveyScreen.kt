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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.shcl.smarthealth.presentation.ui.common.CustomAlertDialog
import com.shcl.smarthealth.presentation.ui.common.CustomButton
import com.shcl.smarthealth.presentation.ui.common.CustomConfirmDialog
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

    val uploadSuccess by viewModel.uploadSuccess.collectAsStateWithLifecycle()
    val complete by viewModel.surveyComplete.collectAsStateWithLifecycle()

    var showDialogState by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){

        when{
            showDialogState->{

                CustomAlertDialog(
                    onShowDialog = showDialogState,
                    title = "건강정보 입력을 마치시겠습니까?" ,
                    desc = "아직 수정할 내용이 있거나, 답하지 못한 질문은 없는지 확인해주세요.\n확인을 마치셨다면 '완료'버튼을 눌러주세요.",
                    onClickCancel = {showDialogState = false},
                    onClickConfirm = {
                        viewModel.uploadAnswer()
                        showDialogState = false
                    },
                )
            }
        }

        if(uploadSuccess){
             viewModel.next()
        }

        if(complete && level == viewModel.MAX_LEVEL){
             nav.navigate(route = OuterScreen.introduce.route)
        }


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
                    stepProgressBar(level , 5)
                }

                Box(modifier = Modifier.weight(0.8f)) {
                    when(level){
                        1-> surveyFood(viewModel)
                        2-> surveySleep(viewModel)
                        3-> surveyDepression(viewModel)
                        4-> surveySmoking(viewModel)
                        5-> surveyAct(viewModel)
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
                        btnClick =  {
                            if(viewModel.levelState.value == viewModel.MAX_LEVEL){
                                showDialogState = true
                            }else{
                                viewModel.uploadAnswer()
                            }

                            //viewModel.next()
                        },

                        buttonWidth = 553f,
                        contentColor = Color.White, containerColor = Color143F91 , text = "${level}단계 완료" , rightIcon = painterResource(id = R.drawable.arrow) )
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
fun stepProgressBar(currentStep : Int , maxStep : Int){


    Step(currentStep , maxStep)

    /*
    Row(){
         for(step in 1..maxStep){
             Step(step , 5)
         }
    }*/

}

