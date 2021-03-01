package com.chrynan.emoji.presentation.android.dialog

import androidx.fragment.app.FragmentFactory

abstract class DelegatableFragmentFactory internal constructor() : FragmentFactory() {

    abstract fun handlesFragment(
        classLoader: ClassLoader,
        className: String,
        klass: Class<*>
    ): Boolean
}
