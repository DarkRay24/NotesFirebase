package com.example.notes.data.repository

import com.example.notes.data.model.Note
import com.google.firebase.firestore.FirebaseFirestore

class NoteRepositoryImp(
    val database: FirebaseFirestore
) : NoteRepository {

    override fun getNotes(): List<Note> {
        // We will get data from Firestore
        return arrayListOf()
    }
}