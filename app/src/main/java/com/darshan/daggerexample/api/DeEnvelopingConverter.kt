package com.darshan.daggerexample.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import javax.inject.Inject

class DeEnvelopingConverter @Inject constructor(val gson: Gson) : Converter.Factory() {

  override fun responseBodyConverter(
    type: Type,
    annotations: Array<Annotation>,
    retrofit: Retrofit
  ): Converter<ResponseBody, *>? {

    // This converter requires an annotation providing the name of the payload in the envelope;
    // if one is not supplied then return null to continue down the converter chain.
    val payloadName = getPayloadName(annotations) ?: return null

    val adapter = gson.getAdapter(TypeToken.get(type))
    return Converter<ResponseBody, Any> { body ->
      body.use {
        gson.newJsonReader(it.charStream()).use { jsonReader ->
          jsonReader.beginObject()
          while (jsonReader.hasNext()) {
            if (payloadName == jsonReader.nextName()) {
              return@Converter adapter.read(jsonReader)
            } else {
              jsonReader.skipValue()
            }
          }
          return@Converter null
        }
      }
    }

  }

  private fun getPayloadName(annotations: Array<Annotation>?): String? {
    if (annotations == null) return null
    for (annotation in annotations) {
      if (annotation is EnvelopePayload) {
        return annotation.value
      }
    }
    return null
  }

}
