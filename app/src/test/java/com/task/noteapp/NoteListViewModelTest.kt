package com.task.noteapp

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.data.dto.NoteDTO
import com.task.noteapp.ui.notes.NotesListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@ExperimentalCoroutinesApi
class NoteListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    // Subject under test
    private lateinit var notesListViewModel: NotesListViewModel

    private lateinit var datasource: FakeDataSource


    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getNotes when Notes Table is empty returns noData`() {
        datasource = FakeDataSource()
        notesListViewModel = NotesListViewModel(ApplicationProvider.getApplicationContext(), datasource)

        // Given a fresh TasksViewModel
        notesListViewModel.getNotes()

        // Then the new task event is triggered
        val value = notesListViewModel.showNoData.getOrAwaitValue()

        MatcherAssert.assertThat(true, `is`(value))
    }

    @Test
    fun `getNotes when query error returns error Msg`() {

        datasource = FakeDataSource()
        datasource.setReturnError(true)
        notesListViewModel = NotesListViewModel(ApplicationProvider.getApplicationContext(), datasource)

        // Given a fresh TasksViewModel
        notesListViewModel.getNotes()

        // Then the new task event is triggered
        val value = notesListViewModel.errorMessage.getOrAwaitValue()
        val valueNoData = notesListViewModel.showNoData.getOrAwaitValue()

        MatcherAssert.assertThat("Test exception", `is`(value))
        MatcherAssert.assertThat(true, `is`(valueNoData))
    }

    @Test
    fun `getNotes when Notes Table is not empty returns notes`() {

        datasource = FakeDataSource()
        datasource.addNotes(NoteDTO("Note 1", "Description1","14-08-2021",null,null))
        datasource.addNotes(NoteDTO("Note 2", "Description2","14-08-2021","15-08-2021",null))
        datasource.setMockData(true)

        notesListViewModel = NotesListViewModel(ApplicationProvider.getApplicationContext(), datasource)

        // Given a fresh TasksViewModel
        notesListViewModel.getNotes()

        // Then the new task event is triggered
        val value = notesListViewModel.notesList.getOrAwaitValue()
        val valueNoData = notesListViewModel.showNoData.getOrAwaitValue()

        MatcherAssert.assertThat(false, `is`(value.isEmpty()))
        MatcherAssert.assertThat(2, `is`(value.size))
        MatcherAssert.assertThat(false, `is`(valueNoData))

    }
}