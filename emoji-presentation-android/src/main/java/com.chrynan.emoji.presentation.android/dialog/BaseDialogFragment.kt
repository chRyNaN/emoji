package com.chrynan.emoji.presentation.android.dialog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

abstract class BaseDialogFragment : AppCompatDialogFragment(),
    CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = lifecycleScope.coroutineContext

    private var isBound = false

    open fun onBind() {}

    open fun onUnbind() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind()
    }

    override fun onResume() {
        super.onResume()

        bind()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        unbind()

        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        unbind()

        super.onPause()
    }

    override fun onDestroyView() {
        unbind()

        super.onDestroyView()
    }

    private fun bind() {
        if (!isBound) {
            onBind()
            isBound = true
        }
    }

    private fun unbind() {
        if (isBound) {
            onUnbind()
            isBound = false
        }
    }
}