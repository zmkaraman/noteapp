package com.task.noteapp

import android.app.Application
import com.task.noteapp.data.NoteDataSource
import com.task.noteapp.data.local.LocalDB
import com.task.noteapp.data.local.NotesLocalRepository
import com.task.noteapp.ui.newnote.AddNoteViewModel
import com.task.noteapp.ui.notes.NotesListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * use Koin Library as a service locator
         */
        val myModule = module {
            //Declare a ViewModel - be later inject into Fragment with dedicated injector using by viewModel()
            viewModel {
                NotesListViewModel(
                    get(),
                    get() as NoteDataSource
                )
            }

            viewModel {
                AddNoteViewModel(
                    get(),
                    get() as NoteDataSource
                )
            }

            single { NotesLocalRepository(get()) as NoteDataSource }
            single { LocalDB.createNotesDao(this@MyApp) }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}