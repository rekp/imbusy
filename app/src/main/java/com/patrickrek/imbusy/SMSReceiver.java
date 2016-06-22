package com.patrickrek.imbusy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class SMSReceiver extends BroadcastReceiver {


    public void onReceive(Context context, Intent intent) {



        // Get the SMS message received
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                // A PDU is a "protocol data unit". This is the industrial standard for SMS message
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    // This will create an SmsMessage object from the received pdu
                    SmsMessage sms;
                    if (Build.VERSION.SDK_INT >= 19) { //KITKAT
                        SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                        sms = msgs[0];
                    } else {
                        Object pdus[] = (Object[]) bundle.get("pdus");
                        sms = SmsMessage.createFromPdu((byte[]) pdus[0]);
                    }

                    // Get sender phone number
                    String senderPhoneNumber = sms.getDisplayOriginatingAddress();
                    // Get message
                    String message = sms.getDisplayMessageBody();
                    // Format message
                    String formattedText = String.format(context.getResources().getString(R.string.sms_message), senderPhoneNumber, message);

                    // Display the SMS message in a Toast
                    Toast.makeText(context, formattedText, Toast.LENGTH_LONG).show();

                    LogActivity inst = LogActivity.instance();

                    //TelephonyManager tm = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
                    //String deviceNumber = tm.getLine1Number();

                    // ISSUE::::: Needs fine tuning, this is not a 100% accurate way of getting the devices phone number
                    //if(!inst.checkIfDuplicate("7",message)) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(senderPhoneNumber, null, MainActivity.getGeneralResponse(), null, null);
                    //}

                    inst.updateList(formattedText);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}