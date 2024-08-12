package com.shcl.smarthealth.presentation.view.survey.content

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.CancerType
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DiseaseType
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.FamilyDiseaseType
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
import com.shcl.smarthealth.presentation.ui.common.CustomTwoComboBox
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel
import com.shcl.smarthealth.presentation.view.survey.SurveyViewModel
import com.shcl.smarthealth.presentation.view.survey.component.CancerDetail
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeBoolean
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeCancer
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeDisease
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeFamilyDisease
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeGoodBad
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger0123
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger1234567
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeLittleBig
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeSleep1GoodBad
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeSleepGoodBad
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.ColorECF098
import com.shcl.smarthealth.ui.theme.ColorECF0F8
import com.shcl.smarthealth.ui.theme.Typography
import java.util.Arrays

@Composable
fun surveyAct(viewModel: SurveyViewModel){


    val scrollState = rememberScrollState()
    val questions by viewModel.questions.collectAsStateWithLifecycle()

    //val type_freq : HashMap<String , Any> = hashMapOf("항상 그런 편이다" to 2 , "보통이다" to 1 ,  "아닌편이다" to 0 )
    //val question2 : HashMap<String , Any> = hashMapOf("항상 그런 편이다" to 2 , "보통이다" to 1 ,  "아닌편이다" to 0 )
    var question1Answer by remember { mutableStateOf(0) }
    var question2Answer by remember { mutableStateOf(0) }
    var question3Answer by remember { mutableStateOf(0) }
    var question4Answer by remember { mutableStateOf(0) }
    var question5Answer by remember { mutableStateOf(0) }
    var question6Answer by remember { mutableStateOf(0) }
    var question7Answer by remember { mutableStateOf(0) }
    var question8Answer by remember { mutableStateOf(0) }
    var question9Answer by remember { mutableStateOf(0) }
    var question10Answer by remember { mutableStateOf(0) }
    var question11Answer by remember { mutableStateOf(0) }

    var cancerDetailVisible by remember { mutableStateOf(false) }
    var selectedCancerList by remember { mutableStateOf( emptyList<String>()) }
    var cacner by remember { mutableStateOf("") }

    val checkImageIcon = Icons.Default.CheckCircle

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        NumberButton("1")
        Text("지난 7일간 무거운 물건 나르기, 달리기, 에어로빅, 빠른 속도로 자전거 타기 등과 같은 격렬한 신체 활동을 며칠간 하셨습니까?" , style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        Spacer(modifier = Modifier.height(48f.pxToDp()))
        Box( modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18f.pxToDp()))
            .background(ColorECF0F8)
            .padding(40f.pxToDp())){
                Text("스스로 활동적이지 않다고 생각되시더라도 질문에 응답해 주세요.\n" +
                     "직장과 집에서 하는 활동, 교통수단을 이용할 때 하는 활동, 여가 시간에 행하는 활동, 운동 또는 스포츠 모두를 포함하여 생각해\n " +
                     "주시기 바랍니다." , color = Color757575 , fontSize = 20f.pxToSp())
        }

        CustomGroupButtons(
            options = typeInteger1234567 ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("2")
        Text("그런 날 중 하루에 격렬한 신체활동을 하면서 보낸 시간이 보통 얼마나 됩니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomTwoComboBox(
            subject = "하루에",
            firstList =  Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"),
            secondList = Arrays.asList("00","30"),
            firstSelected = { Log.d("smarthealth","f selected") },
            firstUnit = "시간",
            secondUnit = "분",
            secondSelected = {Log.d("smarthealth","2 selected") }
        )

        NumberButton("3")
        Text("지난 7일간 가벼운 물건 나르기, 보통 속도로 자전거 타기, 복식 테니스 등과 같은 중간 정도 신체 활동을 며칠간 하였습니까? 걷기는 포함시키지 마십시오" ,style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)

        Spacer(modifier = Modifier.height(48f.pxToDp()))
        Box( modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18f.pxToDp()))
            .background(ColorECF0F8)
            .padding(40f.pxToDp())){
            Text("귀하가 지난 7일간 하신 모든 중간 정도 신체활동을 생각해 보십시오.\n" +
                    "중간 정도 신체활동이란 중간 정도 힘들게 움직이는 활동으로서 평소보다 숨이 조금 더 차게 만드는 활동입니다.\n" +
                    "한 번에 적어도 10분 이상 지속한 활동만을 생각하여 응답해주시기 바랍니다." , color = Color757575 , fontSize = 20f.pxToSp())
        }

        CustomGroupButtons(
            options = typeInteger1234567 ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("4")
        Text("그런 날 중 하루에 중간 정도의 신체활동을 하면서 보낸 시간이 보통 얼마나 됩니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomTwoComboBox(
            subject = "하루에",
            firstList =  Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"),
            secondList = Arrays.asList("00","30"),
            firstSelected = { Log.d("smarthealth","f selected") },
            firstUnit = "시",
            secondUnit = "분",
            secondSelected = {Log.d("smarthealth","2 selected") }
        )


        NumberButton("5")
        Text("지난 7일간, 한 번에 적어도 10분 이상 걸은 날이 며칠입니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)

        Spacer(modifier = Modifier.height(48f.pxToDp()))
        Box( modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18f.pxToDp()))
            .background(ColorECF0F8)
            .padding(40f.pxToDp())){
            Text("지난 7일간 걸은 시간을 생각해 보십시오.\n" +
                    "직장이나 집에서, 교통 수단을 이용할 때 걸은 것 뿐만 아니라 오락 활동, 스포츠, 운동, 여가 시간에 걸은 것도 포함됩니다." , color = Color757575 , fontSize = 20f.pxToSp())
        }

        CustomGroupButtons(
            options = typeInteger1234567,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("6")
        Text("그런 날 중 하루에 걸으면서 보낸 시간이 보통 얼마나 됩니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomTwoComboBox(
            subject = "하루에",
            firstList =  Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"),
            secondList = Arrays.asList("00","30"),
            firstSelected = { Log.d("smarthealth","f selected") },
            firstUnit = "시간",
            secondUnit = "분",
            secondSelected = {Log.d("smarthealth","2 selected") }
        )

        NumberButton("7")
        Text("지난 7일간, 주중에 앉아서 보낸 시간이 보통 얼마나 됩니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("8")
        Text("다음 암을 진단받은 적이 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = CancerType.convertHashMap(SurveyByLevel.LEVEL5),
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                cacner = it.toString()
                cancerDetailVisible = true
                Log.d("survey" , "answer : ${it}")
            }
        )

        AnimatedVisibility(
            visible = cancerDetailVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {

            CancerDetail(
                selectChange = { data->
                Log.d("smarthealth" , "${data}")
            }, cancerType = "$cacner" )

        }

        NumberButton("9")
        Text("지금까지 아래의 질병이 있다고 진단받은 적이 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = DiseaseType.convertHashMap(SurveyByLevel.LEVEL5),
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("10")
        Text("부모, 형제, 자매, 삼촌 이내의 가까운 친척 중 아래의 질병을 앓았거나 그로 인해 사망한 경우가 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = FamilyDiseaseType.convertHashMap(SurveyByLevel.LEVEL5),
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

    }

}
