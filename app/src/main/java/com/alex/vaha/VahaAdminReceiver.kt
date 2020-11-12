package com.alex.vaha

import android.app.admin.DeviceAdminReceiver
import android.content.ComponentName
import android.content.Context

class VahaAdminReceiver : DeviceAdminReceiver() {
    companion object {
        fun getComponentName(context: Context) = ComponentName(context, VahaAdminReceiver::class.java)
    }
}