package com.learn.view_homework

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.learn.view_homework", appContext.packageName)
    }

    val mockWebServer = MockWebServer()

    @Before
    @Throws(IOException::class)
    fun setUp() {
        mockWebServer.start()
    }
    @Test
    fun test_retrofit()
    {
        launchActivity<MainActivity>().use { scenario ->
            scenario.onActivity { activity ->
                val retrofit = (activity as MyApplication).retrofit

               //TODO
            // val response =  mockWebServer.enqueue(object : MockResponse)
            }

        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}