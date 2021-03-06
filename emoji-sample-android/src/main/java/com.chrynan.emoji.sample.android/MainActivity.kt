package com.chrynan.emoji.sample.android

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chrynan.dispatchers.dispatchers
import com.chrynan.emoji.presentation.android.util.showEmojiPicker
import com.chrynan.emoji.presentation.android.widget.EmojiChip
import com.chrynan.emoji.presentation.android.widget.EmojiWidget
import com.chrynan.emoji.presentation.core.listener.EmojiListItemSelectedListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import com.chrynan.emoji.repo.sqlite.EmojiDatabase
import com.chrynan.emoji.repo.sqlite.SqliteEmojiRepository
import com.chrynan.sample.R
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.chrynan.emoji.presentation.core.viewmodel.toViewModel
import com.chrynan.emoji.core.getByNameOrNull

class MainActivity : AppCompatActivity(),
    EmojiListItemSelectedListener {

    private val repository =
        SqliteEmojiRepository(EmojiDatabase(AndroidSqliteDriver(EmojiDatabase.Schema, this)))

    private val emojiWidget by lazy { findViewById<EmojiWidget>(R.id.emojiDialogEmojiWidget) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emojiDialogButton = findViewById<Button>(R.id.emojiDialogButton)

        emojiDialogButton.setOnClickListener {
            supportFragmentManager.showEmojiPicker(repository = repository)
        }

        setupChips()
    }

    override fun onEmojiListItemSelected(item: EmojiViewModel) {
        emojiWidget.emojiViewModel = item
    }

    private fun setupChips() {
        val emojiChipOne = findViewById<EmojiChip>(R.id.emojiDialogChipOne)
        val emojiChipTwo = findViewById<EmojiChip>(R.id.emojiDialogChipTwo)
        val emojiChipThree = findViewById<EmojiChip>(R.id.emojiDialogChipThree)

        GlobalScope.launch {
            val emojiOne =
                withContext(dispatchers.io) { repository.getByNameOrNull("grinning face with smiling eyes") }
            val emojiTwo =
                withContext(dispatchers.io) { repository.getByNameOrNull("rolling on the floor laughing") }
            val emojiThree =
                withContext(dispatchers.io) { repository.getByNameOrNull("face with tears of joy") }

            withContext(dispatchers.main) {
                emojiOne?.let { emojiChipOne.setEmoji(emojiViewModel = it.toViewModel()) }
                emojiTwo?.let {
                    emojiChipTwo.setEmoji(
                        emojiViewModel = it.toViewModel(),
                        count = 2
                    )
                }
                emojiThree?.let {
                    emojiChipThree.setEmoji(
                        emojiViewModel = it.toViewModel(),
                        count = 3
                    )
                }
            }
        }
    }
}
