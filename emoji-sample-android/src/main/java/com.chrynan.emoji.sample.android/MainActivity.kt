package com.chrynan.emoji.sample.android

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chrynan.emoji.presentation.android.dialog.EmojiBottomSheetDialogFragment
import com.chrynan.emoji.presentation.android.dialog.EmojiBottomSheetDialogFragmentFactory
import com.chrynan.emoji.presentation.android.util.showEmojiBottomSheetDialogFragment
import com.chrynan.emoji.presentation.core.listener.EmojiListItemSelectedListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import com.chrynan.emoji.repo.sqlite.EmojiDatabase
import com.chrynan.emoji.repo.sqlite.SqliteEmojiRepository
import com.chrynan.sample.R
import com.squareup.sqldelight.android.AndroidSqliteDriver

class MainActivity : AppCompatActivity(),
    EmojiListItemSelectedListener {

    private val repository =
        SqliteEmojiRepository(EmojiDatabase(AndroidSqliteDriver(EmojiDatabase.Schema, this)))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emojiDialogButton = findViewById<Button>(R.id.emojiDialogButton)

        emojiDialogButton.setOnClickListener {
            supportFragmentManager.showEmojiBottomSheetDialogFragment(repository = repository)
        }
    }

    override fun onEmojiListItemSelected(item: EmojiViewModel) {

    }
}
