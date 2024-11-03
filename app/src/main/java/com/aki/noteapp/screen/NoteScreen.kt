package com.aki.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aki.noteapp.R
import com.aki.noteapp.components.NoteButton
import com.aki.noteapp.components.NoteInputText
import com.aki.noteapp.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  NoteScreen(
    notes:List<Note>,
    onAddNote: (Note) ->Unit,
    onRemove:(Note) ->Unit,
){
    var title by remember{
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context= LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) },
            actions ={
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription ="Icon")},
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF223BDD))
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 9.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            NoteInputText(text = title, label ="Title" , onTextChange = {
                if(it.all { char->
                char.isLetter()||char.isWhitespace()
                })
                    title=it
            })
            NoteInputText(text = description, label ="Add a note" , onTextChange = {
                if(it.all { char->
                        char.isLetter()||char.isWhitespace()
                    })
                    description=it
            })
            NoteButton(text = "Save", onClick = {
            if(title.isNotEmpty()||description.isNotEmpty()){

                onAddNote(Note(title = title, description = description))

                title=""
                description=""
                Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
            }
            })
        }
        HorizontalDivider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){ note ->
                NoteRow(note = note, onNoteClick ={onRemove(note)} )
            }
        }
    }
}
@Composable
fun NoteRow(modifier: Modifier=Modifier ,
            note:Note,
            onNoteClick:(Note) ->Unit
){
    Surface(modifier = modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),
        color = Color(0xffdfe6eb),
        shadowElevation = 6.dp
    ) {
        Column(modifier = modifier
            .clickable { onNoteClick(note) }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.bodyMedium
                )
            Text(
                text = note.description,
                style = MaterialTheme.typography.bodySmall
                )
            Text(
                text = note.entryDate.toString(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
@Preview(showBackground = true) 
@Composable
fun NoteScreenPreview(){
     NoteScreen(notes= emptyList(), onAddNote = {}, onRemove = {})
}