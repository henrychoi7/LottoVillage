package com.example.wschd.client.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import com.example.wschd.client.activities.RegisterActivity


class SmsBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle: Bundle? = intent.extras  // Bundle 객체에 문자를 받아온다
            if (bundle != null) {
                val pduObjects = bundle.get("pdus") as Array<*>
                for (i in pduObjects.indices) {
                    val currentSms = getIncomingMessage(pduObjects[i] as ByteArray, bundle)
                    //val senderNo = currentSms.displayOriginatingAddress
                    val message = currentSms.displayMessageBody
                    if (message.contains("LottoVillage")) {
                        val certifiedNumber = message.substring(message.indexOf("[") + 1, message.indexOf("]"))
                        intent.putExtra("certified_number", certifiedNumber)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        intent.setClass(context, RegisterActivity::class.java)
                        context.startActivity(intent)
                    }
                }
                this.abortBroadcast()
            }
        }
    }

    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val format = bundle.getString("format")
            return SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else {
            return SmsMessage.createFromPdu(aObject as ByteArray)
        }
    }
}