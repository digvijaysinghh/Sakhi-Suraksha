package com.example.sakhi_seva

import 'package:flutter/material.dart';
import 'package:shake/shake.dart';
import 'package:sms_maintained/sms.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:location/location.dart';

void main() {
    runApp(MyApp());
}

class MyApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return MaterialApp(
            home: Scaffold(
                    appBar: AppBar(
                    title: Text('Flutter Shake and Send SMS App'),
        ),
        body: Center(
        child: Text('Shake your device to send an SMS with your location!'),
        ),
        ),
        );
    }
}

class ShakeAndSendSMSService {
    final SmsSender _smsSender = SmsSender();
    final Location _location = Location();

    Future<void> start() async {
        SharedPreferences prefs = await SharedPreferences.getInstance();
        String phoneNumber = prefs.getString('ENUM') ?? 'NONE';

        if (phoneNumber != 'NONE') {
            ShakeDetector detector = ShakeDetector.autoStart(onPhoneShake: () async {
                LocationData locationData = await _location.getLocation();
                String myLocation =
                'http://maps.google.com/maps?q=loc:${locationData.latitude},${locationData.longitude}';
                _smsSender.sendSms(SmsMessage(phoneNumber,
                    'Im in Trouble!\nSending My Location :\n$myLocation'));
            });
        }
    }
}