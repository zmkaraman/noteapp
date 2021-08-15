package com.task.noteapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.util.AppUtil
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*

@RunWith(AndroidJUnit4::class)
class AppUtilTest {

    @Test
    fun `appUtil getSystemTimeDate return SysDateString`() {

        val sysDateString = Date()
        val sdf = SimpleDateFormat("dd-MM-yyyy")

        val value = AppUtil.getSystemTimeDate()

        MatcherAssert.assertThat(sdf.format(sysDateString), `is`(value))
    }

}