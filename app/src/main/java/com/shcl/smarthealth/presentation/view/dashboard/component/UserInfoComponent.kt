package com.shcl.smarthealth.presentation.view.dashboard.component

import android.net.Uri
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.remote.user.ProfileResponse
import com.shcl.smarthealth.domain.utils.Utils
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.view.dashboard.DashBoardViewModel
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography


@Composable
fun UserInfo(user : ProfileResponse? , userPicture : String? = null ) {

    Box(
        modifier = Modifier
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {


            Row(verticalAlignment = Alignment.CenterVertically){
                userPicture?.let {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(userPicture).build(),
                        //model =ImageRequest.Builder(LocalContext.current).data(Uri.parse(user.profileUri)).placeholder(R.drawable.reg_picture).build(),
                        //painter = painterResource(id = R.drawable.top_profile_img),
                        contentScale = ContentScale.Crop,
                        error = painterResource(id = R.drawable.dummy_profile),
                        contentDescription = "User",
                        modifier = Modifier
                            .size(120.pxToDp())
                            .clip(CircleShape)
                    )
                }
             user?.let {
                Spacer(modifier = Modifier.width(40.pxToDp()))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text("${user.name}님, 안녕하세요" , style = Typography.headlineLarge , color = Color1E1E1E , fontSize = 25f.pxToSp())
                        /*
                        Image(
                            modifier = Modifier.size(63.pxToDp() , 63.pxToDp()),
                            painter = painterResource(id = R.drawable.ani_hand),
                            contentDescription = null
                        )*/
                    }
                    Spacer(modifier = Modifier.height(20.pxToDp()))
                    Row(
                        modifier = Modifier
                            .defaultMinSize(minWidth = 530.pxToDp(), minHeight = 80.pxToDp())
                            .align(Alignment.Start)
                            .border(
                                width = 1.dp,
                                color = ColorD4D9E1,
                                shape = RoundedCornerShape(18.pxToDp())
                            )
                            .padding(horizontal = 40.pxToDp(), vertical = 28.pxToDp())){
                        Text("성별 " , style = Typography.bodySmall)
                        Spacer(modifier = Modifier.width(30.pxToDp()))
                        Text(user.gender, style = Typography.bodySmall , fontWeight = FontWeight.W700)

                        Spacer(modifier = Modifier.width(80f.pxToDp()))
                        /*
                        VerticalDivider(
                            modifier = Modifier.padding(horizontal = 2f.pxToDp()),
                            thickness = 2f.pxToDp(),
                            color = ColorD4D9E1
                        )*/

                        Text("나이 " , style = Typography.bodySmall)
                        Spacer(modifier = Modifier.width(30.pxToDp()))
                        Text("${Utils.calcProfileAge(user.birthDate)}세 " , style = Typography.bodySmall, fontWeight = FontWeight.W700)
                    }
                }
            }
        }?:run{

        }


    }
}

@Composable
fun UserInfo(user : LastedLoginUserRoom?) {

    Box(
        modifier = Modifier
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        user?.let {

            //val profile = Utils.uriFromFilePath(user.profileUri.toUri())

            Row(verticalAlignment = Alignment.CenterVertically){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(Uri.parse(user.profileUri)).crossfade(true).build(),
                    //painter = painterResource(id = R.drawable.top_profile_img),
                    //model = ImageRequest.Builder( LocalContext.current).data(user.profileUri.toUri()).build(),
                    error = painterResource(id = R.drawable.dummy_profile),
                    contentScale = ContentScale.Crop,
                    contentDescription = "User",
                    onError = { Log.d("smarthealth","${it.result.toString()}")},
                    modifier = Modifier
                        .size(138.pxToDp())
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(40.pxToDp()))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text("${user.name}님, 안녕하세요" , style = Typography.headlineLarge , color = Color1E1E1E , fontSize = 25f.pxToSp())
                        /*
                        Image(
                            modifier = Modifier.size(63.pxToDp() , 63.pxToDp()),
                            painter = painterResource(id = R.drawable.ani_hand),
                            contentDescription = null
                        )*/
                    }
                    Spacer(modifier = Modifier.height(20.pxToDp()))
                    Row(
                        modifier = Modifier
                            .defaultMinSize(minWidth = 530.pxToDp(), minHeight = 80.pxToDp())
                            .align(Alignment.Start)
                            .border(
                                width = 1.dp,
                                color = ColorD4D9E1,
                                shape = RoundedCornerShape(18.pxToDp())
                            )
                            .padding(horizontal = 40.pxToDp(), vertical = 28.pxToDp())){
                        Text("성별 " , style = Typography.bodySmall)
                        Text(Utils.convertGender(user.gender) , style = Typography.bodySmall , fontWeight = FontWeight.W700)

                        Spacer(modifier = Modifier.width(80f.pxToDp()))
                        /*
                        VerticalDivider(
                            modifier = Modifier.padding(horizontal = 2f.pxToDp()),
                            thickness = 2f.pxToDp(),
                            color = ColorD4D9E1
                        )*/
                        
                        Text("나이 " , style = Typography.bodySmall)
                        Text("${user.age}세 " , style = Typography.bodySmall, fontWeight = FontWeight.W700)
                    }
                }
            }
        }?:run{

        }


    }
}