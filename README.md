[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ee59ce6acc27454a9b5d94889ec3bf80)](https://app.codacy.com/manual/aballiet/SaveScreen?utm_source=github.com&utm_medium=referral&utm_content=aballiet/SaveScreen&utm_campaign=Badge_Grade_Dashboard)
[![Build Status](https://travis-ci.com/aballiet/SaveScreen.svg?branch=master)](https://travis-ci.com/aballiet/SaveScreen)

# SaveScreen Android App

SaveScreen allows you to turn off the screen of your Android device while keeping your current app running (Youtube, Chrome or a game for example).
It uses the proximity sensor to turn the screen off.

This project has been initialized in order to fulfill user of reddit : [see here](https://www.reddit.com/r/deeptown/comments/ffsl49/tip_app_to_turn_off_your_screen_while_maximizing/)

## Screenshot
<img src="https://github.com/aballiet/SaveScreen/raw/master/readme_ressources/screenshot.jpg" width="300">

## Install the app 
You can either use the APK given [here](https://github.com/aballiet/SaveScreen/raw/master/app/release/app-release.apk) or use the one generated by yourself from the source code (see instructions below)

Copy it (or download it) on your Android device and proceed to its installation.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

```bash
$ git clone https://github.com/aballiet/SaveScreen.git
$ cd SaveScreen
$ gradle assemble             # This will generate two files in project/build/apk/
```

- app-debug-unaligned.apk 
- app-release-unsigned.apk

### Prerequisites

Android Studio with SDK >= 21

## Generate an APK

- Open the project in Android Studio
- Go to : Build -> Generate Signed Bundle / APK
- You can find the generated APK in : app/release/app-release.apk


## Built With

* [Android Studio ](https://developer.android.com/studio) - The IDE to create Android app


## Authors

* **Antoine Balliet** 


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Very simple app yet, need a notification system to ensure app is running or not.
