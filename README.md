# CropIndex-development

Hello!

Welcome to the source code of MTRI/IRI 's field data collection app for Android devices.

The Git repository is created in the root directory of a project created in Android Studio 1.1.0 using OpenJDK 1.7 on Ubuntu 14.04.

Everything should still work with Oracle JDK, or on Macintosh or Windows systems, as long as the directory structure stays consistent.

  * To immediately deploy the app as-is on an Android device, copy `app/build/outputs/apk/app-debug.apk` onto the device and install it. Make sure "Unknown Sources" is enabled in Settings under Security or Developer Options.

  * To open this project in Android Studio, view the "VCS" menu and choose "Checkout from Version Control" > "Git", and for the "Vcs Repository URL" enter:

	`https://github.com/staden/CropIndex-development.git`

Depending on how powerful your computer is, and if you have access to an Android device, you may opt not to use Android Studio. Android Studio is useful for emulation if no Android device is available, and alternatively, if an Android device is available for testing the app then more lightweight text editors can be used instead of Android Studio. 

Regardless of what editor you use, the main source files are located in `app/src/main/`.
