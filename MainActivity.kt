package com.whats2ps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.whats2ps.data.SettingsStore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var chats by remember { mutableStateOf(SettingsStore.getWhatsAppChats(this)) }

            Scaffold {
                Column {
                    Text("Seleziona chat WhatsApp")
                    chats.forEach {
                        Button(onClick = {
                            SettingsStore.setSelectedWhatsAppChat(this, it)
                        }) {
                            Text(it)
                        }
                    }

                    Divider()

                    Button(onClick = {
                        SettingsStore.setPsChatReady(this, true)
                    }) {
                        Text("Usa chat PS aperta")
                    }
                }
            }
        }
    }
}
