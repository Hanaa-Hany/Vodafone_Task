package com.hanaahany.task.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.hanaahany.task.model.response.repodetails.Owner

class Converter {

    @TypeConverter
    fun fromOwner(owner: Owner?): String? {
        return Gson().toJson(owner)
    }

    @TypeConverter
    fun toOwner(ownerJson: String?): Owner? {
        return Gson().fromJson(ownerJson, Owner::class.java)
    }
}