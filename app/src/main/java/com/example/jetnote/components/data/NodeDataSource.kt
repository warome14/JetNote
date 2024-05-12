package com.example.jetnote.components.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.jetnote.components.model.Note
import kotlinx.coroutines.flow.Flow


@RequiresApi(Build.VERSION_CODES.O)
@androidx.room.Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NodeDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
}

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_tbl")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note_tbl WHERE id = :id")
    fun getNote(id: String): Note

    @Update(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun removeNote(note: Note)
}