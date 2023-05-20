package com.example.notes.data.repository

import com.example.notes.data.model.Note
import com.example.notes.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class NoteRepositoryImp(
    val database: FirebaseFirestore
) : NoteRepository {

    override fun getNotes(): UiState<List<Note>> {
        // We will get data from Firestore
        val data = listOf<Note>()

        if(data.isEmpty()){
            return UiState.Failure("Data is Empty")
        }else{
            return UiState.Success(data)
        }
    }
}