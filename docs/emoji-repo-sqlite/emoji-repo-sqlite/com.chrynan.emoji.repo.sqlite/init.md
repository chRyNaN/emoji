//[emoji-repo-sqlite](../../index.md)/[com.chrynan.emoji.repo.sqlite](index.md)/[init](init.md)

# init

[common]\
suspend fun [SqliteEmojiRepository](-sqlite-emoji-repository/index.md).[init](init.md)()

Initializes this [SqliteEmojiRepository](-sqlite-emoji-repository/index.md) with the initial data set.

Note that this should be called before calling any of the other [SqliteEmojiRepository](-sqlite-emoji-repository/index.md) functions, otherwise the internal database will be empty and no values can be retrieved.
