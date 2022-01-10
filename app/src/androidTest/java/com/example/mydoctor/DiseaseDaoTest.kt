package com.example.mydoctor

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mydoctor.data.AppDatabase
import com.example.mydoctor.data.Disease
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DiseaseDaoTest {

    private lateinit var database: AppDatabase

    @Before
    fun init() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @Test
    fun insert_and_selects_diseases() = runBlocking {
        val disease = Disease(1, "Test 1")

        database.diseaseDao().insertDisease(disease)
        val dbDisease = database.diseaseDao().getDiseases().first()

        assertThat(dbDisease, equalTo(disease))
    }
    
    
    @After
    fun cleanUp() = database.close()
}