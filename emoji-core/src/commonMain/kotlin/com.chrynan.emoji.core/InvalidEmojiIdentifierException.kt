package com.chrynan.emoji.core

class InvalidEmojiIdentifierException(identifier: String) :
    RuntimeException("Invalid Emoji identifier when attempting to retrieve an Emoji. identifier = $identifier")