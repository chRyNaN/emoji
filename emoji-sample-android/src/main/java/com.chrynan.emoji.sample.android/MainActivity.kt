package com.chrynan.emoji.sample.android

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chrynan.emoji.presentation.android.util.showEmojiPicker
import com.chrynan.emoji.presentation.android.widget.EmojiWidget
import com.chrynan.emoji.presentation.core.listener.EmojiListItemSelectedListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import com.chrynan.emoji.repo.sqlite.EmojiDatabase
import com.chrynan.emoji.repo.sqlite.SqliteEmojiRepository
import com.chrynan.sample.R
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.chrynan.emoji.presentation.android.widget.EmojiChipGroup
import com.chrynan.emoji.presentation.core.listener.EmojiChipGroupListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiChipGroupViewModel
import com.chrynan.emoji.presentation.core.viewmodel.EmojiChipViewModel

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

        val emojiChipGroup = findViewById<EmojiChipGroup>(R.id.emojiDialogChipGroup)

        emojiChipGroup?.listener = object : EmojiChipGroupListener {

            override fun onChipSelected(emojiChipViewModel: EmojiChipViewModel) {

            }

            override fun onAddEmojiSelected() {
                supportFragmentManager.showEmojiPicker(
                    repository = repository,
                    emojiListItemSelectedListener = { emoji ->
                        emojiChipGroup?.addEmojiChip(
                            EmojiChipViewModel(
                                emojiViewModel = emoji,
                                isSelected = true
                            )
                        )
                    })
            }
        }
    }

    override fun onEmojiListItemSelected(item: EmojiViewModel) {
        emojiWidget.viewModel = item
    }
}
