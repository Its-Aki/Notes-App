package com.aki.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Date
import java.util.UUID
@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
    val id:UUID= UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
    val title:String,

    @ColumnInfo(name = "note_description")
    val description:String,

    @ColumnInfo(name = "note_entry_date")
    val entryDate: Date =Calendar.getInstance().time
)
