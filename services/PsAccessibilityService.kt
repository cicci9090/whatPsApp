package com.whats2ps.services

import android.accessibilityservice.*
import android.os.Bundle
import android.view.accessibility.*
import com.whats2ps.data.SettingsStore
import com.whats2ps.logic.PsSender

class PsAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (!SettingsStore.isPsChatReady(this)) return
        val text = PsSender.dequeue() ?: return
        val root = rootInActiveWindow ?: return

        val input = root.findAccessibilityNodeInfosByViewId(
            "com.scee.psxandroid:id/message_input"
        ).firstOrNull() ?: return

        val args = Bundle()
        args.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text)
        input.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args)

        root.findAccessibilityNodeInfosByViewId(
            "com.scee.psxandroid:id/send_button"
        ).firstOrNull()?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
    }

    override fun onInterrupt() {}
}
