package com.shcl.smarthealth.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeConvert : JsonDeserializer<LocalTime> , JsonSerializer<LocalTime> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalTime {
        return LocalTime.parse(json.asString, DateTimeFormatter.ofPattern("HH:mm"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun serialize(
        src: LocalTime,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        val dateTimeString = src.format(DateTimeFormatter.ofPattern("HH:mm"))
        return JsonPrimitive(dateTimeString)
    }
}