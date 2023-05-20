package com.example.notes.di

import com.example.notes.data.repository.NoteRepository
import com.example.notes.data.repository.NoteRepositoryImp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(
        database : FirebaseFirestore
    ) : NoteRepository{
        return NoteRepositoryImp(database)
    }
}