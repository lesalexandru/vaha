package com.alex.vaha

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vahaComponentName = VahaAdminReceiver.getComponentName(applicationContext)
        val policyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        if (policyManager.isDeviceOwnerApp(packageName)) {
            setKioskPolicies(policyManager, vahaComponentName)
        }
    }

    private fun setKioskPolicies(policyManager: DevicePolicyManager, vahaComponentName: ComponentName) {
        policyManager.apply {
            IntentFilter(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                addCategory(Intent.CATEGORY_DEFAULT)
                val activityComponent = ComponentName(packageName, MainActivity::class.java.name)
                addPersistentPreferredActivity(vahaComponentName, this, activityComponent)
            }
            setKeyguardDisabled(vahaComponentName, true)
            setStatusBarDisabled(vahaComponentName, true)
            setLockTaskPackages(vahaComponentName, arrayOf(packageName))
            startLockTask()
        }
    }
}