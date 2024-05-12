package com.example.jetnote.components.model

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.UUID
@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "note_tbl")
data class Note(@PrimaryKey var id: String = UUID.randomUUID().toString(),
                @ColumnInfo("title") var title: String,
                @ColumnInfo("description") var description: String,
                @SuppressLint("SimpleDateFormat") @ColumnInfo("data") var date: String = SimpleDateFormat("yyyy MM dd").format(Date.from(Instant.now())))