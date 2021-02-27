# M1 Secure Development : Mobile applications

Made by : Thomas Ngo in IOS1

In this repository you will find my submition workshop.

## Exercice

Using Android Studio (or any editor of your choice), you will have to create a mobile application.  
You can choose the language you want between Kotlin and Java.  
  
The goal is to create a secure application to see your bank accounts.   

### Report

This project was made using java langague.

1. Explain how you ensure user is the right one starting the app

Using the androidx.biometric library, we set up a fingerprint login in order to verify that only the right user can start the app. If the user don't have fingerprint reader, he will not be able to login.

The BiomectricManager class will get information of the biometrics, it will inform the user if his fingerprint sensor is available or not and if he can use it or not.

The BiometricPrompt class will verify that the user's finger is the right one and if he can launch the app. 

As shown in the pictures below: 

1, 2, 3, 4

2. How do you securely save user's data on your phone ?

In order to save user's data on the phone we will use encrypted shared preferences.

We first create a masterkey to encrypt the file in which we will store the user's data. Then we use this masterkey to create an ecrypted shared preferences named "EncryptedSharedPreferences".


3. How did you hide the API url ?

We implemented the AESUtils class in order to encrypt and decrypt Strings in our project.

The url of the API was encrypted and stored in the method getUrl() that return the decrypted url whenever it is call.
