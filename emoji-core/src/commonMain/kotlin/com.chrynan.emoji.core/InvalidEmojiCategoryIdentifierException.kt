package com.chrynan.emoji.core

/**
 * A [RuntimeException] that is thrown when querying an [EmojiRepository] with an invalid
 * identifier value for an [EmojiCategory].
 */
class InvalidEmojiCategoryIdentifierException(identifier: String) :
    RuntimeException("Invalid EmojiCategory identifier when attempting to retrieve an EmojiCategory. identifier = $identifier")
