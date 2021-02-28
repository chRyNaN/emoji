@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.util

import android.app.Activity
import androidx.fragment.app.Fragment

/**
 * Retrieves the interface (specified as a generic parameter to this function) from this Fragment's parent Fragment or
 * Activity or null otherwise.
 */
internal inline fun <reified T> Fragment.getParentCallbackOrNull(): T? {
    val parentFragment: Fragment? = this.parentFragment

    if (parentFragment != null && parentFragment is T) {
        return parentFragment
    } else {
        val activity: Activity? = this.activity

        if (activity != null && activity is T) {
            return activity
        }
    }

    return null
}

/**
 * Retrieves the interface (specified as a generic parameter to this function) from this Fragment's parent Fragment or
 * Activity or throws an [InvalidFragmentParentException].
 */
internal inline fun <reified T> Fragment.getParentCallbackOrThrow(): T =
    getParentCallbackOrNull<T>()
        ?: throw InvalidFragmentParentException(this::class.java, T::class.java)

/**
 * Exception thrown when a fragment is instantiated and its parent activity or fragment doesn't implement its callback
 * listener.
 */
internal class InvalidFragmentParentException(childClass: Class<*>, callbackClass: Class<*>) :
    RuntimeException("Parent of ${childClass.name} must implement ${callbackClass.name}")
