package com.task.noteapp.addnote


import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.FakeDataSource
import com.task.noteapp.data.dto.NoteDTO
import com.task.noteapp.getOrAwaitValue
import com.task.noteapp.ui.newnote.AddNoteViewModel
import com.task.noteapp.ui.notes.NoteDataItem
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
class AddNoteViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    // Subject under test
    private lateinit var addNoteViewModel: AddNoteViewModel

    private lateinit var datasource: FakeDataSource


    @Before
    fun setupViewModel() {

        var notes: MutableList<NoteDTO> = mutableListOf()
        datasource = FakeDataSource()
        notes.add(NoteDTO("Note 1", "Description1","14-08-2021",null,null))
        notes.add(NoteDTO("Note 2", "Description2","14-08-2021","15-08-2021",null))

        addNoteViewModel = AddNoteViewModel(ApplicationProvider.getApplicationContext(), datasource)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `addNote when Title is empty returns error Msg`() {

        var noteDataItem = NoteDataItem("","","", null, null, "")
        // Given a fresh TasksViewModel
        addNoteViewModel.validateAndAddNote(noteDataItem)

        // Then the new task event is triggered
        val value = addNoteViewModel.errorMsg.getOrAwaitValue()
        MatcherAssert.assertThat("Please enter valid data", `is`(value))

    }


    @Test
    fun `addNote when Title is Correct returns navigte back`() {

        var noteDataItem = NoteDataItem("Title","Description","", null, null, "")
        // Given a fresh TasksViewModel
        addNoteViewModel.validateAndAddNote(noteDataItem)

        // Then the new task event is triggered
        val value = addNoteViewModel.navigateBackToList.getOrAwaitValue()
        MatcherAssert.assertThat(true, `is`(value))

    }


}