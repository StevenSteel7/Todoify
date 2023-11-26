package ir.ehsan.asmrtodolist.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import ir.ehsan.asmrtodolist.R
import ir.ehsan.asmrtodolist.ui.theme.GreyColor
import ir.ehsan.asmrtodolist.ui.theme.Primary
import ir.ehsan.asmrtodolist.ui.theme.Secondary
import ir.ehsan.asmrtodolist.ui.theme.TextColor

// This is fo type of text and their Props
@Composable
// These are simply being used in SignUp.kt
fun NormalTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        , color = TextColor,
        textAlign = TextAlign.Center// We can use it from colors or ||
        //                                                          \/
        //                                                          
    )
}


@Composable
fun HeadingTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelValue: String, painterResource: Painter,
                onTextChanged: (String) -> Unit,  errorStatus :Boolean = false// to See errors
                ) {
    // Use remember in a composable function
    val textVal = remember {
        mutableStateOf("")
    }

    // Now you can safely use the remembered state in your composable
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = colorResource(id = R.color.primaryColor),
            cursorColor = colorResource(id = R.color.primaryColor)
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true, // 'SingleLine' should be lowercase
        value = textVal.value,
        onValueChange = {
            textVal.value = it
            onTextChanged(it)
                        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPassField(labelValue: String , painterResource: Painter,
                onTextChanged: (String) -> Unit ,errorStatus :Boolean = false ){
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = {Text(text = labelValue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = colorResource(id = R.color.primaryColor),
            cursorColor = colorResource(id = R.color.primaryColor)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,imeAction = ImeAction.Done),
        value  = password.value,
        singleLine = true,
        onValueChange = { password.value = it
                        onTextChanged(it)
                        },
        //TO add icons
        leadingIcon = {
            Icon(painter = painterResource  , contentDescription ="" )
        },
        trailingIcon = {
            val iconImage =
                if(passwordVisible.value){
                Icons.Filled.Visibility

            }
            else{
                Icons.Filled.VisibilityOff
            }

            var description = if(passwordVisible.value){
                stringResource(id = R.string.hide_password )
            }
            else{
                stringResource(id = R.string.show_password )
            }

            IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                Icon(imageVector = iconImage, contentDescription = description)
            } // eye button Logic


        },
        visualTransformation = if(passwordVisible.value ) VisualTransformation.None
        else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}


@Composable                                                     //void
fun CheckBoxComp(value: String , onTextSelected : (String) -> Unit ,onCheckedChange : (Boolean) -> Unit){
    Row( modifier = Modifier
        .fillMaxWidth()
        .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        val checkedState = remember {
        mutableStateOf<Boolean>(false)
        }
/*
        Checkbox(checked = checkedState.value, onCheckedChange ={
            checkedState.value = checkedState.value

        } )*/

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {

                checkedState.value = it // Use the assignment operator to update the state
                onCheckedChange(it) // Notify the outer component about the change
            }
        )


        ClickableTextComponent(value = value,onTextSelected)

    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected : (String) -> Unit){
    val initialText = "By contuning you accept our "
    val privacyPolicy = "Privacy Policy "
    val andText = "and "
    val termsAndConditionText = "Terms of use"
    val annonatedString  = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = privacyPolicy, annotation = privacyPolicy)
            append(privacyPolicy)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = termsAndConditionText, annotation = termsAndConditionText)
            append(termsAndConditionText)
        }       //       ^
    //Just for apperance |

    }



    ClickableText(text =annonatedString , onClick ={offset -> // This offest is use to find the part of span that we have clicked
        annonatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span ->
            Log.d("ClickableTextComponent","{$span}")
                if(span.item == termsAndConditionText ||span.item == privacyPolicy){
                    onTextSelected(span.item)
                    // onTextSelected is returned
                    // CallBack implemented in signup
                }
        }
    } )
}


@Composable
fun ButtonComp(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false){
    Button(onClick = { onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
    contentPadding = PaddingValues(),
        //if enabled or not....
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(Color.Transparent))
    {
        //For gradient
         Box(modifier = Modifier
             .fillMaxWidth()
             .heightIn(48.dp)
             .background(
                 brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                 shape = RoundedCornerShape(50.dp)
             ),
            contentAlignment = Alignment.Center
        )
         {
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)

        }

    }

}

@Composable
fun DividerTextComp(){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){

        Divider(
            modifier =Modifier.fillMaxWidth().weight(1f),
            color = GreyColor,
            thickness = 1.dp
        )
        Text(modifier = Modifier.padding(8.dp),
            text = "or", fontSize = 18.sp,color = TextColor)
        Divider(
            modifier =Modifier.fillMaxWidth().weight(1f),
            color = GreyColor,
            thickness = 1.dp
        )

    }
}



//I made it
@Composable
fun AlreadyAMemberComp(text1: String, text2: String, onTextSelected : (String) -> Unit){
    val initialText = text1

    val BlueText = text2
    val annonatedString  = buildAnnotatedString {
        append(initialText)

        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = BlueText, annotation = BlueText)
            append(BlueText)
        }           //       ^
        //Just for apperance |

    }

    ClickableText(text =annonatedString , onClick ={offset -> // This offest is use to find the part of span that we have clicked
        annonatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent","{$span}")
                if(span.item == BlueText){
                    onTextSelected(span.item)
                    // onTextSelected is returned
                    // CallBack implemented in signup
                }
            }
    } )
}





