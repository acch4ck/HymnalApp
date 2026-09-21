package com.faith.hymnal

import android.app.Application
import com.faith.hymnal.data.HymnRepository
import com.faith.hymnal.data.local.HymnDatabase

class HymnalApplication : Application() {

    val database: HymnDatabase by lazy { HymnDatabase.getInstance(this) }
    val repository: HymnRepository by lazy { HymnRepository.getInstance(database) }
}
