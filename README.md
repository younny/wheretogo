Where To Go
===========

Where To Go is a simple transit Android application.

Features
-----
Where To Go provides the following features:
* Prediction and auto complete places in search view
* Calculate likelihood for current Location
* Detecting nearby places
* Drawing polyline in route view

Setup
-----
It requires Android 6.0 Marshmallow version or higher.

Enter your API key in the api xml. You can find it in AndroidMenifest.xml

And, add the following to your `build.gradle` if you don't have :

	dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:23.0.1'
    compile 'com.android.support:support-v4:23.0.1'
    compile 'com.android.support:design:23.0.1'
    compile 'com.google.android.gms:play-services:8.4.0'
    compile 'com.google.code.gson:gson:2.5'
    compile 'com.google.android.gms:play-services-maps:8.4.0'
    compile 'com.google.maps.android:android-maps-utils:0.4'
    }



Developed By
-----
* Dooyoung Gi

***
