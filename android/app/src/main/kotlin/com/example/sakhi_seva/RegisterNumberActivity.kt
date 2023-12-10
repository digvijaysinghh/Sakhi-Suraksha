package com.example.sakhi_seva

import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
    runApp(MyApp());
}

class MyApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return MaterialApp(
            home: RegisterNumberPage(),
        );
    }
}

class RegisterNumberPage extends StatefulWidget {
    @override
    _RegisterNumberPageState createState() => _RegisterNumberPageState();
}

class _RegisterNumberPageState extends State<RegisterNumberPage> {
    final TextEditingController _numberController = TextEditingController();

    Future<void> _saveNumber() async {
        String numberString = _numberController.text;
        if (numberString.length == 10) {
            SharedPreferences prefs = await SharedPreferences.getInstance();
            await prefs.setString('ENUM', numberString);
            Navigator.of(context).pop();
        } else {
            ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(content: Text('Enter Valid Number!')),
            );
        }
    }

    @override
    Widget build(BuildContext context) {
        return Scaffold(
            appBar: AppBar(
                    title: Text('Register Number'),
        ),
        body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
        children: <Widget>[
        TextField(
            controller: _numberController,
            decoration: InputDecoration(
                    labelText: 'Enter your number',
        ),
        keyboardType: TextInputType.phone,
        ),
        ElevatedButton(
            child: Text('Save Number'),
            onPressed: _saveNumber,
        ),
        ],
        ),
        ),
        );
    }
}