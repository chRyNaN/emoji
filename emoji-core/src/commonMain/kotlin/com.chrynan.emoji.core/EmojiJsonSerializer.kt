package com.chrynan.emoji.core

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * A [KSerializer] for the [Emoji] class.
 */
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
object EmojiJsonSerializer : KSerializer<Emoji> {

    override val descriptor: SerialDescriptor =
        buildSerialDescriptor("Emoji", PolymorphicKind.SEALED) {
            element<Emoji.Unicode>(elementName = "Unicode")
            element<Emoji.Custom>(elementName = "Custom")
        }

    override fun serialize(encoder: Encoder, value: Emoji) =
        when (value) {
            is Emoji.Unicode -> Emoji.Unicode.serializer().serialize(encoder, value)
            is Emoji.Custom -> Emoji.Custom.serializer().serialize(encoder, value)
        }

    override fun deserialize(decoder: Decoder): Emoji {
        val jsonDecoder =
            decoder as? JsonDecoder
                ?: throw SerializationException("Expected Json Decoder for ${decoder}.")
        val jsonObject = jsonDecoder.decodeJsonElement().jsonObject

        return when (jsonObject.getValue("type").jsonPrimitive.content) {
            Emoji.Unicode.TYPE_NAME -> decodeUnicode(jsonObject)
            Emoji.Custom.TYPE_NAME -> decodeCustom(jsonObject)
            else -> throw SerializationException("Invalid Emoji Marker Type.")
        }
    }

    private fun decodeUnicode(jsonObject: JsonObject): Emoji =
        Emoji.Unicode(
            unicodeString = jsonObject["unicode"]!!.jsonPrimitive.content,
            char = jsonObject["char"]!!.jsonPrimitive.content,
            name = jsonObject["name"]!!.jsonPrimitive.content,
            aliases = jsonObject.getAliases(),
            category = jsonObject["category"]?.jsonPrimitive?.content,
            group = jsonObject["group"]?.jsonPrimitive?.content,
            iconUri = jsonObject["icon"]?.jsonPrimitive?.content
                ?: jsonObject["uri"]?.jsonPrimitive?.content
        )

    private fun decodeCustom(jsonObject: JsonObject): Emoji =
        Emoji.Custom(
            name = jsonObject["name"]!!.jsonPrimitive.content,
            aliases = jsonObject.getAliases(),
            category = jsonObject["category"]?.jsonPrimitive?.content,
            group = jsonObject["group"]?.jsonPrimitive?.content,
            uri = jsonObject["uri"]?.jsonPrimitive?.content
                ?: jsonObject["icon"]!!.jsonPrimitive.content,
            staticUri = jsonObject["static_uri"]?.jsonPrimitive?.content,
            mimeType = jsonObject["mime_type"]?.jsonPrimitive?.content
        )

    private fun JsonObject.getAliases(): List<String> =
        when (val value = this["aliases"]) {
            is JsonPrimitive -> listOf(value.content)
            is JsonArray -> value.map { it.jsonPrimitive.content }
            null -> emptyList()
            else -> throw SerializationException("Invalid aliases type")
        }
}
