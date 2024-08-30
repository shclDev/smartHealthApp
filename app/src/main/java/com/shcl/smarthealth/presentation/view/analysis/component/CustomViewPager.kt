package com.shcl.smarthealth.presentation.view.analysis.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.shcl.smarthealth.domain.utils.pxToSp

@Composable
fun CustomViewPager(pagerState : PagerState){
    HorizontalPager(
        state = pagerState
    ){
        page->

        Text(
            modifier = Modifier.wrapContentSize(),
            text = page.toString(),
            textAlign = TextAlign.Center,
            fontSize = 30f.pxToSp()
        )
    }

}