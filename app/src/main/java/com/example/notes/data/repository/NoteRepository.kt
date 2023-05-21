package com.example.notes.data.repository

import com.example.notes.data.model.Note
import com.example.notes.util.UiState

interface NoteRepository {

    fun getNotes(result: (UiState<List<Note>>) -> Unit)

    fun addNote(note: Note, result: (UiState<String>) -> Unit)
}