package com.example.notes.data.repository

import com.example.notes.data.model.Note
import com.example.notes.util.UiState

interface NoteRepository {

    fun getNotes(): UiState<List<Note>>
}