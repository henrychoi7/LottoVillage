package com.jjosft.android.lottovillage.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import com.jjosft.android.lottovillage.activities.RegisterActivity


/**
 * Created by JJSOFT-DESKTOP on 2017-08-15.
 */
class SmsBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        /*if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val sms = StringBuilder()   // SMS문자를 저장할 곳
            val bundle : Bundle? = intent.extras  // Bundle객체에 문자를 받아온다

            if (bundle != null) {
                // 번들에 포함된 문자 데이터를 객체 배열로 받아온다
                val pdusObj = bundle.get("pdus") as Array<*>

                // SMS를 받아올 SmsMessage 배열을 만든다
                val messages = arrayOfNulls<SmsMessage>(pdusObj.size)
                for (i in pdusObj.indices) {
                    messages[i] = SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
                    // SmsMessage의 static메서드인 createFromPdu로 pdusObj의
                    // 데이터를 message에 담는다
                    // 이 때 pdusObj는 byte배열로 형변환을 해줘야 함
                }

                // SmsMessage배열에 담긴 데이터를 append메서드로 sms에 저장
                for (smsMessage in messages) {
                    // getMessageBody메서드는 문자 본문을 받아오는 메서드
                    sms.append(smsMessage!!.messageBody)
                }
            }
        }*/
        if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle: Bundle? = intent.extras  // Bundle객체에 문자를 받아온다
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