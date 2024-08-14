package com.shcl.smarthealth.presentation.view.survey.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType.DiseaseHistoryDto
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.CancerType
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DiseaseType
import com.shcl.smarthealth.domain.utils.Utils
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.ui.common.CustomComboBox
import com.shcl.smarthealth.presentation.ui.common.CustomRadioButton
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeBoolean
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun DiseaseDetail(
    selectChange: (Any)->Unit,
    diseaseType: String,
){

    var diagnosisYear by remember { mutableStateOf(0) }
    var medicationPeriod by remember { mutableStateOf(0) }

    val diseaseTitle = DiseaseType.getKorName(diseaseType)

    var diseaseHistoryDto = DiseaseHistoryDto(
        diseaseType = diseaseType,
        yearOfDiagnosis = diagnosisYear,
        medication = false,
        durationOfMedication = medicationPeriod
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ){

        Text(modifier = Modifier.width(180f.pxToDp()) ,text = "$diseaseTitle" ,  color = Color143F91 ,style = Typography.bodyMedium , fontSize = 20f.pxToSp() , textAlign = TextAlign.Center)

        VerticalDivider(
            modifier = Modifier.padding(vertical = 12f.pxToDp(), horizontal = 8f.pxToDp()),
            thickness = 2f.pxToDp(),
        )

        Column(modifier = Modifier.width(950f.pxToDp())) {
            Row {
                Text(modifier = Modifier.weight(0.5f) , text = "언제 진단을 받으셨나요?" , style = Typography.labelMedium , fontSize = 25f.pxToSp())
                Spacer(modifier = Modifier.width(20f.pxToDp()))
                CustomComboBox(
                    subject = "",
                    selected = { value->
                        diagnosisYear = Integer.parseInt(value.toString())
                        diseaseHistoryDto.yearOfDiagnosis = diagnosisYear
                        selectChange(diseaseHistoryDto)
                    },
                    list = Utils.makeYearRange(1920,2024))
            }

            Row {
                Text(modifier = Modifier.weight(0.5f) , text ="현재 투약을 받고 계신가요?" , style = Typography.labelMedium , fontSize = 25f.pxToSp())
                Spacer(modifier = Modifier.width(20f.pxToDp()))
                CustomRadioButton(options = typeBoolean , selectionChanged = {
                        value->
                    diseaseHistoryDto.medication = value as Boolean
                    selectChange(diseaseHistoryDto)
                })
                /*
                CustomGroupButtons(
                    options = typeBoolean,
                    unSelectedColor = ColorD4D9E1 ,
                    selectedColor = Color143F91,
                    containerColor = Color.White,
                    selectionChanged = {
                        value->

                        isSurgery = value as Boolean
                        cancerHistoryDto.operation = isSurgery
                        selectChange(cancerHistoryDto)
                    }
                )*/
            }

            Row {
                Text(modifier = Modifier.weight(0.5f) , text ="얼마동안 투약을 받으셨나요?" , style = Typography.labelMedium , fontSize = 25f.pxToSp())
                Spacer(modifier = Modifier.width(20f.pxToDp()))
                CustomComboBox(
                    subject = "",
                    selected = { value->
                        medicationPeriod = Integer.parseInt(value.toString())
                        diseaseHistoryDto.durationOfMedication = medicationPeriod
                        selectChange(diseaseHistoryDto)
                    },
                    list = Utils.makeAgeRange(0,60))
            }
        }
    }



}