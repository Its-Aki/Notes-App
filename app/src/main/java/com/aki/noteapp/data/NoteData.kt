package com.aki.noteapp.data

import com.aki.noteapp.model.Note

class NoteDataSource{
    fun loadNotes():List<Note>{
        return listOf(
            Note(title = "A good day1", description = "we went to party1"),
            Note(title = "A good day2", description = "we went to party2"),
            Note(title = "A good day3", description = "we went to party3"),
            Note(title = "A good day4", description = "we went to party4"),
            Note(title = "A good day5", description = "we went to party5"),
            Note(title = "A good day6", description = "we went to party6"),
        )
    }
}