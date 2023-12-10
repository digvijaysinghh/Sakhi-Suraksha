package com.example.sakhi_seva

import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';

void main() {
    runApp(MyApp());
}

class MyApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return MaterialApp(
            home: Scaffold(
                    appBar: AppBar(
                    title: Text('Flutter Permissions and Notifications App'),
        ),
        body: Center(
        child: Text('Check permissions and show notifications!'),
        ),
        ),
        );
    }
}

class PermissionsService {
    Future<bool> hasPermissions(List<Permission> permissions) async {
        bool hasPermissions = true;
        for (var permission in permissions) {
            if (!await permission.isGranted) {
                hasPermissions = false;
                break;
            }
        }
        return hasPermissions;
    }

    Future<void> requestPermissions(List<Permission> permissions) async {
        await permissions.request();
    }
}

class SharedPreferencesService {
    Future<String> getPhoneNumber() async {
        SharedPreferences prefs = await SharedPreferences.getInstance();
        return prefs.getString('phoneNumber') ?? 'NONE';
    }

    Future<void> setPhoneNumber(String phoneNumber) async {
        SharedPreferences prefs = await SharedPreferences.getInstance();
        await prefs.setString('phoneNumber', phoneNumber);
    }
}

class NotificationsService {
    final FlutterLocalNotificationsPlugin flutterLocalNotificationsPlugin =
    FlutterLocalNotificationsPlugin();

    Future<void> showNotification() async {
        var androidPlatformChannelSpecifics = AndroidNotificationDetails(
            'channel_ID', 'channel_name', 'channel_description',
            importance: Importance.max, priority: Priority.high, showWhen: false);
        var platformChannelSpecifics =
            NotificationDetails(android: androidPlatformChannelSpecifics);
        await flutterLocalNotificationsPlugin.show(
                0, 'title', 'body', platformChannelSpecifics,
        payload: 'item x');
    }
}