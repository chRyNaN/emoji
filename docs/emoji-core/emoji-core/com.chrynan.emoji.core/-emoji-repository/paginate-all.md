//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[EmojiRepository](index.md)/[paginateAll](paginate-all.md)

# paginateAll

[common]\
open suspend fun [paginateAll](paginate-all.md)(): PaginateRepository&lt;[Emoji.Key](../-emoji/-key/index.md), [Emoji](../-emoji/index.md)&gt;

Retrieves a PaginateRepository to paginate through all of the available [Emoji](../-emoji/index.md)s.

Note that this defaults to returning a BasePaginateSource implementation whose BasePaginateSource.fetch function simply returns a PagedResult containing the result of a [getAll](get-all.md) function call. Implementations of the [EmojiRepository](index.md) may override this functionality to be more performant and actually support pagination.

## See also

common

| | |
|---|---|
| [com.chrynan.emoji.core.EmojiRepository](get-all.md) |  |
| com.chrynan.paginate.core.PaginateRepository |  |
