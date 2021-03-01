package com.chrynan.emoji.presentation.android.dialog

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

internal class DelegateFragmentFactory(initialFactories: Set<FragmentFactory>) :
    FragmentFactory() {

    internal var factories: Set<FragmentFactory> = initialFactories

    private val delegateFactories: List<DelegatableFragmentFactory>
        get() = factories.filterIsInstance<DelegatableFragmentFactory>()
    private val normalFactories: Set<FragmentFactory>
        get() = factories.minus(delegateFactories)

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val factory = delegateFactories.firstOrNull {
            it.handlesFragment(classLoader, className, loadFragmentClass(classLoader, className))
        } ?: normalFactories.firstOrNull()

        return factory?.instantiate(classLoader, className)
            ?: super.instantiate(classLoader, className)
    }
}
