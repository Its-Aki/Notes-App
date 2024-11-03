package com.aki.noteapp.components
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier=Modifier,
    text:String,
    label:String,
    maxLines:Int=1,
    onTextChange:(String) ->Unit,
    onImeAction:() ->Unit={}
){
    val keyboardController=LocalSoftwareKeyboardController.current
    TextField(value = text,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        label = {Text(text = label)},
        maxLines=maxLines,
        keyboardActions =KeyboardActions(onDone ={
            onImeAction()
            keyboardController?.hide()
        } )
        )
}
@Composable
fun NoteButton(
    modifier: Modifier=Modifier,
    text:String,
    onClick:()->Unit,
    enabled: Boolean=true,
){
    Button(onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier
    ) {
       Text(text = text)
    }
}