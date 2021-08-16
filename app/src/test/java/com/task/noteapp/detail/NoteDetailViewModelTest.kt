package com.task.noteapp.detail


import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.FakeDataSource
import com.task.noteapp.data.dto.NoteDTO
import com.task.noteapp.getOrAwaitValue
import com.task.noteapp.ui.detail.NoteDetailViewModel
import com.task.noteapp.model.NoteDataItem
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
class NoteDetailViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    // Subject under test
    private lateinit var noteDetailViewModel: NoteDetailViewModel

    private lateinit var datasource: FakeDataSource


    @Before
    fun setupViewModel() {

        var notes: MutableList<NoteDTO> = mutableListOf()
        datasource = FakeDataSource()
        notes.add(NoteDTO("Note 1", "Description1","14-08-2021",null,null,"1"))
        notes.add(NoteDTO("Note 2", "Description2","14-08-2021","15-08-2021",null, "2"))

        noteDetailViewModel = NoteDetailViewModel(ApplicationProvider.getApplicationContext(), datasource)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `delete Note when Title is Correct returns navigate back`() {

        // Given a fresh TasksViewModel
        noteDetailViewModel.deleteNote("1")

        // Then the new task event is triggered
        val value = noteDetailViewModel.navigateBackToList.getOrAwaitValue()
        MatcherAssert.assertThat(true, `is`(value))

    }

    @Test
    fun `updateNote when Title is empty returns error Msg`() {

        var noteDataItem = NoteDataItem("","","", null, null, "")
        // Given a fresh TasksViewModel
        noteDetailViewModel.validateAndUpdateNote(noteDataItem)

        // Then the new task event is triggered
        val value = noteDetailViewModel.errorMsg.getOrAwaitValue()
        MatcherAssert.assertThat("Please enter valid data", `is`(value))

    }


    @Test
    fun `addNote when Title is Correct returns navigate back`() {

        var noteDataItem = NoteDataItem("Title","Description","", null, null, "")
        // Given a fresh TasksViewModel
        noteDetailViewModel.validateAndUpdateNote(noteDataItem)

        // Then the new task event is triggered
        val value = noteDetailViewModel.navigateBackToList.getOrAwaitValue()
        MatcherAssert.assertThat(true, `is`(value))

    }


}