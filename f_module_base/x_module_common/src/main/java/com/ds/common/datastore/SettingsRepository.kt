package com.ds.common.datastore

import com.dylanc.datastore.DataStoreOwner

object SettingsRepository : DataStoreOwner(name = "food_settings") {
    val isFirst by booleanPreference(true)
}