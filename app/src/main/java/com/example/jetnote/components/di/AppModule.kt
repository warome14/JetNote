package com.example.jetnote.components.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.jetnote.components.data.NodeDatabase
import com.example.jetnote.components.data.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideNoteDao(nodeDatabase: NodeDatabase) : NoteDao = nodeDatabase.noteDao()


    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): NodeDatabase = Room.databaseBuilder(
        applicationContext,
        NodeDatabase::class.java,
        "note_database"
    ).fallbackToDestructiveMigration().build()


}