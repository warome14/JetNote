package com.example.jetnote.components.model

import android.accounts.AuthenticatorDescription
import android.icu.util.LocaleData
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.UUID
@RequiresApi(Build.VERSION_CODES.O)
data class Note (var id: String = UUID.randomUUID().toString(),
                                                                var title: String,
                                                                var description: String,
                                                                var data: LocalDateTime = LocalDateTime.now())