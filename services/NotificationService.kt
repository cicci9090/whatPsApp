package com.whats2ps.services

import android.app.Notification
import android.service.notification.*
import com.whats2ps.data.*

class NotificationService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        if (sbn.packageName != "com.whatsapp") return

        val extras = sbn.notification.extras
        val sender = extras.getString(Notification.EXTRA_TITLE) ?: return
        val text = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: return

        SettingsStore.addWhatsAppChat(this, sender)
        if (sender != SettingsStore.getSelectedWhatsAppChat(this)) return

        AppDatabase.get(this).messageDao().insert(
            MessageEntity(sender = sender, text = text, timestamp = System.currentTimeMillis())
        )

        PsSender.queue(text)
    }
}
