package com.shcl.smarthealth.presentation.view.survey.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun NumberButton(
    number: String
){

    Column {
        Text(
            modifier = Modifier
                .size(54f.pxToDp(), 40f.pxToDp())
                .clip(RoundedCornerShape(22f.pxToDp()))
                //.padding(horizontal = 5f.pxToDp())
                .background(Color143F91),
            text = "$number",
            fontSize = 18f.pxToSp(),
            color = Color.White,
            textAlign = TextAlign.Center,
            style = Typography.labelMedium,
            fontWeight = FontWeight.W700
        )
        Spacer(modifier = Modifier.height(20f.pxToDp()))
    }

}