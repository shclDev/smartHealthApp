package com.shcl.smarthealth.presentation.view.survey.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.shcl.smarthealth.domain.model.remote.survey.Question
import com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType.CancerHistoryDto
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.CancerType
import com.shcl.smarthealth.domain.utils.Utils
import com.shcl.smarthealth.domain.utils.Utils.dp
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.ui.common.CustomComboBox
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeBoolean
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography


@Composable
fun CancerDetail(
    selectChange: (Any)->Unit,
    cancerType: String,
    ) {

    var diagnosisYear by remember { mutableStateOf(0) }
    var isSurgery by remember { mutableStateOf(false) }
    var isChemotherapy by remember { mutableStateOf(false)}
    var isHormoneTreatment by remember { mutableStateOf(false) }
    var isRadiationTherapy by remember { mutableStateOf( false ) }

    var cancerHistoryDto : CancerHistoryDto = CancerHistoryDto(
        diseaseType = cancerType,
        yearOfDiagnosis = 0,
        operation = false,
        chemoTherapy = false,
        hormoneTherapy = false,
        radiationTherapy = false
    )

    Row(
       modifier = Modifier
           .fillMaxWidth()
    ){

        Text("$cancerType" , style = Typography.bodyMedium , fontSize = 20f.pxToSp() )

        VerticalDivider(
            modifier = Modifier.padding(vertical = 12f.pxToDp(), horizontal = 8f.pxToDp()),
            thickness = 2f.pxToDp(),
        )

        Column {
            Row {
                Text("언제 진단을 받으셨나요?" , style = Typography.labelMedium , fontSize = 25f.pxToSp())
                Spacer(modifier = Modifier.width(20f.pxToDp()))
                CustomComboBox(
                    subject = "",
                    selected = { value->
                        diagnosisYear = Integer.parseInt(value.toString())
                        cancerHistoryDto.yearOfDiagnosis = diagnosisYear
                        selectChange(cancerHistoryDto)
                    },
                    list = Utils.makeYearRange(1920,2024))
            }

            Row {
                Text("수술을 받으셨나요?" , style = Typography.labelMedium , fontSize = 25f.pxToSp())
                Spacer(modifier = Modifier.width(20f.pxToDp()))
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
                )
            }

            Row {
                Text("항암치료를 받으셨나요?" , style = Typography.labelMedium , fontSize = 25f.pxToSp())
                Spacer(modifier = Modifier.width(20f.pxToDp()))
                CustomGroupButtons(
                    options = typeBoolean,
                    unSelectedColor = ColorD4D9E1 ,
                    selectedColor = Color143F91,
                    containerColor = Color.White,
                    selectionChanged = {
                        value->
                        isChemotherapy = value as Boolean
                        cancerHistoryDto.chemoTherapy = isChemotherapy
                        selectChange(cancerHistoryDto)
                    }
                )
            }

            Row {
                Text("호르몬 치료를 받으셨나요?" , style = Typography.labelMedium , fontSize = 25f.pxToSp())
                Spacer(modifier = Modifier.width(20f.pxToDp()))
                CustomGroupButtons(
                    options = typeBoolean,
                    unSelectedColor = ColorD4D9E1 ,
                    selectedColor = Color143F91,
                    containerColor = Color.White,
                    selectionChanged = {
                            value->
                        isHormoneTreatment = value as Boolean
                        cancerHistoryDto.hormoneTherapy = isHormoneTreatment
                        selectChange(cancerHistoryDto)
                    }
                )
            }

            Row {
                Text("방사선 치료를 받으셨나요?" , style = Typography.labelMedium , fontSize = 25f.pxToSp())
                Spacer(modifier = Modifier.width(20f.pxToDp()))
                CustomGroupButtons(
                    options = typeBoolean,
                    unSelectedColor = ColorD4D9E1 ,
                    selectedColor = Color143F91,
                    containerColor = Color.White,
                    selectionChanged = {
                        value-> isRadiationTherapy = value as Boolean
                        cancerHistoryDto.radiationTherapy = isRadiationTherapy
                        selectChange(cancerHistoryDto)
                    }
                )
            }
        }
    }


}