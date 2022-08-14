package com.qxy.victory.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class StringListTypeConverter @Inject constructor(
  private val moshi: Moshi
) {

  @TypeConverter
  fun fromString(value: String): List<String>? {
    val listType = Types.newParameterizedType(List::class.java, String::class.java)
    val adapter: JsonAdapter<List<String>?> = moshi.adapter(listType)
    return adapter.fromJson(value)
  }

  @TypeConverter
  fun toStringList(type: List<String>?): String {
    val listType = Types.newParameterizedType(List::class.java, String::class.java)
    val adapter: JsonAdapter<List<String>?> = moshi.adapter(listType)
    return adapter.toJson(type)
  }
}