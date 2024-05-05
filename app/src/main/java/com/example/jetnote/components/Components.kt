package com.example.jetnote.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.jetnote.components.model.Note
import com.example.jetnote.screen.NoteScreen
import com.example.jetnote.ui.theme.JetNoteTheme
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldComponent(
    modifier: Modifier,
    text: MutableState<String>,
    slabel: String,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
    maxLength: Int = 100
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(modifier = Modifier,
        value = text.value,
        onValueChange = onTextChange,
        maxLines = maxLength,
        enabled = true,
        label = { Text(text = slabel)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii , imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction
                keyboardController?.hide()
            },
        )
    )


}

@Composable
fun ButtonComponents(
    title: String,
    onClickButton: () -> Unit,
    enabled: Boolean,
    modifier: Modifier,
) = Button(enabled = enabled, onClick = onClickButton, modifier = modifier) {
    Text(text = title)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(note: Note, onNodeClicked: (note: Note) -> Unit ) =
    Surface(modifier = Modifier.fillMaxWidth()) {
        Surface(modifier = Modifier.padding(8.dp)
            , shape = RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)
            , border = BorderStroke(1.dp, Color.Black)
            , color = Color.Yellow
        ) {
            Column(modifier = Modifier
                .clickable { onNodeClicked(note) }
                .padding(horizontal = 14.dp, vertical = 14.dp)) {
                Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                Text(text = note.description, style = MaterialTheme.typography.bodyMedium)
                Text(text = note.data.format(DateTimeFormatter.ofPattern("dd MM yyyy")), style = MaterialTheme.typography.labelMedium)

            }
    }



}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewNoteScreen() =
    JetNoteTheme {
        NoteRow(Note(title = "title", description = "description")) {}
    }
