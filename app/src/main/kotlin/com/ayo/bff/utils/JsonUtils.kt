package com.ayo.bff.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.sql.ColumnType
import org.postgresql.util.PGobject


class JsonElementColumnType : ColumnType() {
    override fun sqlType(): String = "JSONB"

    override fun valueFromDB(value: Any): JsonElement = when (value) {
        is PGobject -> Json.decodeFromString(JsonElement.serializer(), value.value ?: "{}")
        is String -> Json.decodeFromString(JsonElement.serializer(), value)
        else -> error("Impossible de convertir en JsonElement : $value")
    }

    override fun notNullValueToDB(value: Any): Any {
        return PGobject().apply {
            type = "jsonb"
            this.value = Json.encodeToString(JsonElement.serializer(), value as JsonElement)
        }
    }
}

class TextArrayColumnType : ColumnType() {
    override fun sqlType(): String = "TEXT[]"

    override fun valueFromDB(value: Any): List<String> = when (value) {
        is PGobject -> value.value?.removeSurrounding("{", "}")?.split(",") ?: emptyList()
        is String -> value.removeSurrounding("{", "}").split(",")
        is java.sql.Array -> (value.array as Array<*>).filterIsInstance<String>()
        else -> error("Unsupported TEXT[] value: $value")
    }

    override fun notNullValueToDB(value: Any): Any {
        val list = (value as List<*>).joinToString(",", "{", "}") { it.toString() }
        return PGobject().apply {
            type = "text[]"
            this.value = list
        }
    }
}