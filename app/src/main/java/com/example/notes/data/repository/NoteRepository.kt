package com.example.notes.data.repository

import com.example.notes.data.model.Note

interface NoteRepository {

    fun getNotes(): List<Note>
}