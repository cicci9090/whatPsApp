package com.whats2ps.data

import android.content.Context

object SettingsStore {
    private const val PREFS = "settings"

    fun addWhatsAppChat(ctx: Context, chat: String) {
        val set = getWhatsAppChats(ctx).toMutableSet()
        set.add(chat)
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putStringSet("wa_chats", set).apply()
    }

    fun getWhatsAppChats(ctx: Context): Set<String> =
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getStringSet("wa_chats", emptySet()) ?: emptySet()

    fun setSelectedWhatsAppChat(ctx: Context, chat: String) {
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putString("selected_wa", chat).apply()
    }

    fun getSelectedWhatsAppChat(ctx: Context): String? =
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString("selected_wa", null)

    fun setPsChatReady(ctx: Context, ready: Boolean) {
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putBoolean("ps_ready", ready).apply()
    }

    fun isPsChatReady(ctx: Context): Boolean =
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getBoolean("ps_ready", false)
}
