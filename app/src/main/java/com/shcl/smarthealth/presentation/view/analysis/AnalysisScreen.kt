package com.shcl.smarthealth.presentation.view.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Tab
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
import com.shcl.smarthealth.presentation.view.analysis.component.DetailGuidePager
import com.shcl.smarthealth.presentation.view.analysis.component.HealthAgePager
import com.shcl.smarthealth.presentation.view.analysis.component.RiskInfoPager
import com.shcl.smarthealth.presentation.view.analysis.component.TotalGuidePager
import com.shcl.smarthealth.ui.theme.Color143F91
import kotlinx.coroutines.launch


enum class AnalysisItem(val title : String, val num : Int){
    HEALTH_AGE("건강나이" , num = 0) ,
    TOTAL_GUIDE("종합 지표" , num = 1) ,
    DETAIL_GUIDE("항목별 수치" , num = 2) ,
    RISK_INFO("위험도" , num =3 );

    companion object {

        private val answerMap : HashMap<String , Any> = HashMap()

        fun convertHashMap() : HashMap<String , Any>{
            answerMap.clear()

            enumValues<AnalysisItem>().map {
                answerMap.put(it.title , it.num)
            }.forEach {  }

            return answerMap
        }
    }

}


@Composable
fun AnalysisScreen(nav : NavHostController , viewModel: AnalysisViewModel = hiltViewModel()){

    val coroutineScope = rememberCoroutineScope()
    var pagerSelectNumber by remember { mutableStateOf(AnalysisItem.HEALTH_AGE.num) }
    //val pagerState = rememberPagerState(pageCount = {analysisPages.size})

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(80f.pxToDp())
            .background(Color.White)
    ){

        val pages = listOf(AnalysisItem.HEALTH_AGE , AnalysisItem.TOTAL_GUIDE , AnalysisItem.DETAIL_GUIDE , AnalysisItem.RISK_INFO)

        val pagerState = rememberPagerState { pages.size }

        Column(
            modifier  = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .background(Color.White)) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                text = "건강상태 분석 보고서",
                textAlign = TextAlign.Start
            )

            CustomGroupButtons(
                options = AnalysisItem.convertHashMap(),
                unSelectedColor = Color.White,
                selectedColor = Color143F91,
                containerColor = Color.White,
                selectionChanged = { selected ->
                    pagerSelectNumber = selected.toString().toInt()
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerSelectNumber)
                    }
                }
            )

            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
                state = pagerState,
            ){
                index->

                when(pages[index]){
                    AnalysisItem.HEALTH_AGE-> HealthAgePager()
                    AnalysisItem.TOTAL_GUIDE-> TotalGuidePager()
                    AnalysisItem.DETAIL_GUIDE -> DetailGuidePager()
                    AnalysisItem.RISK_INFO -> RiskInfoPager()
                }
            }
        }
    }

}