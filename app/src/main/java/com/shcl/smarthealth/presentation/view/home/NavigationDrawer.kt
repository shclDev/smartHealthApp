package com.shcl.smarthealth.presentation.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp

@Composable
fun DrawerHeader(){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 84f.pxToDp(),
                top = 84f.pxToDp(),
                end = 84f.pxToDp(),
                bottom = 40f.pxToDp()
            ),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier.size(160f.pxToDp(), 61f.pxToDp()),
            painter = painterResource(id = R.drawable.dashbaord_logo),
            contentDescription = null
        )
    }

}

@Composable
fun DrawerBody(
    menus : List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick : (MenuItem)->Unit
){
    LazyColumn(modifier) {
        items(menus){ menu->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(menu) }
                    .padding(16.dp)
            ){
                Icon(imageVector = menu.image, contentDescription = menu.contentDesc)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = menu.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        }
}
