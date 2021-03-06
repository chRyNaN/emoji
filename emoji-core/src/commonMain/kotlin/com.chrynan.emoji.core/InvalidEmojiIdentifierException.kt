package com.chrynan.emoji.core

/**
 * A [RuntimeException] that is thrown when querying an [EmojiRepository] with an invalid
 * identifier value for an [Emoji].
 */
class InvalidEmojiIdentifierException(identifier: String?) :
    RuntimeException("Invalid Emoji identifier when attempting to retrieve an Emoji. identifier = $identifier")
