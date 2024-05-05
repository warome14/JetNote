package com.example.jetnote.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.R
import com.example.jetnote.components.ButtonComponents
import com.example.jetnote.components.NoteRow
import com.example.jetnote.components.TextFieldComponent
import com.example.jetnote.components.model.Note
import com.example.jetnote.ui.theme.JetNoteTheme

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteScreen(notes: List<Note>, onRemoveItem: (note: Note) -> Unit, onAddNote: (note: Note) -> Unit ) = Scaffold(topBar = {
    Surface(modifier = Modifier
        .padding(all = 6.dp)
        .border(BorderStroke(1.dp, Color.Transparent), shape = RoundedCornerShape(8.dp))
        ){
            Image(
                painter = painterResource(id = R.drawable.jnote),
                contentDescription = "App Bar Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )
            Title(title = stringResource(id = R.string.app_name), onNavigationClick = {}, onSettingClick = {})
        }
    }) {
    Content(notes, onRemoveItem, onAddNote)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Title(title: String,
          onNavigationClick: () -> Unit,
          onSettingClick: () -> Unit) =
    TopAppBar(title = { Text(title)}
        ,actions = {
            IconButton(onClick = onSettingClick) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
            }
        }
        , colors = TopAppBarDefaults.topAppBarColors( containerColor = Color.Transparent)
        , modifier = Modifier
            .padding(all = 1.dp)
            .border(BorderStroke(1.dp, Color.Transparent), shape = RoundedCornerShape(8.dp))
    )

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(notes: List<Note>, onRemoveItem: (note: Note) -> Unit, onAddNote: (note: Note) -> Unit) = Surface(modifier = Modifier
    .padding(top = 80.dp , start = 10.dp, end = 10.dp, bottom = 10.dp)
    .fillMaxWidth() ) {

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextFieldComponent(modifier = Modifier, text = title, slabel = stringResource(id = R.string.title), onTextChange = {title.value = it})
        TextFieldComponent(modifier = Modifier, text = description, slabel = stringResource(id = R.string.description), onTextChange = {description.value = it})
        ButtonComponents(title = stringResource(id = R.string.insert)
            , onClickButton = { if ( title.value.isNotEmpty()
                && description.value.isNotEmpty() )
                onAddNote(Note(title = title.value, description = description.value))
                               }
            , enabled = true
            , modifier = Modifier.padding(top = 10.dp)
        )
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes.size) { index ->
                NoteRow(note = notes[index], onNodeClicked = {onRemoveItem(notes[index])})
            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewNoteScreen() =
    JetNoteTheme {
        NoteScreen(mutableListOf(Note(title = "title", description = "description")), {},{})
    }
