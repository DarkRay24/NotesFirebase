package com.example.notes.data.repository

import com.example.notes.data.model.Note
import com.example.notes.util.FireStoreTables
import com.example.notes.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.Date

class NoteRepositoryImp(
    val database: FirebaseFirestore
) : NoteRepository {

    override fun getNotes(result: (UiState<List<Note>>) -> Unit) {
        database.collection(FireStoreTables.NOTE)
            .get()
            .addOnSuccessListener {
                val notes = arrayListOf<Note>()
                for (document in it){
                    val note = document.toObject(Note::class.java)
                    notes.add(note)
                }
                result.invoke(
                    UiState.Success(notes)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage!!
                    )
                )
            }
    }


    override fun addNote(note: Note, result: (UiState<String>) -> Unit) {
        database.collection(FireStoreTables.NOTE)
            .add(note)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success(it.id)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage!!
                    )
                )
            }
    }
}