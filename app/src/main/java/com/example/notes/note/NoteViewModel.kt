package com.example.notes.note

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.data.model.Note
import com.example.notes.data.repository.NoteRepository
import com.example.notes.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _notes = MutableLiveData<UiState<List<Note>>>()
    val notes: LiveData<UiState<List<Note>>>
        get() = _notes

    private val _addNote = MutableLiveData<UiState<String>>()
    val addNote: LiveData<UiState<String>>
        get() = _addNote

    fun getNotes() {
        _notes.value = UiState.Loading
        repository.getNotes {
            _notes.value = it
        }
    }

    fun addNote(note: Note){
        _notes.value = UiState.Loading
        repository.addNote(note){
            _addNote.value = it
        }
    }
}