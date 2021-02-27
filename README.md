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

As shown in the pictures below : 

![1](https://user-images.githubusercontent.com/43971721/109394858-7cd6bd00-7929-11eb-92f5-8c943705dd66.png)
![2](https://user-images.githubusercontent.com/43971721/109394860-7d6f5380-7929-11eb-9b3e-5020fe29b190.png)
![3](https://user-images.githubusercontent.com/43971721/109394862-7d6f5380-7929-11eb-9eec-160b19572268.png)
![4](https://user-images.githubusercontent.com/43971721/109394864-7e07ea00-7929-11eb-9b39-8e4de63c4a01.png)

2. How do you securely save user's data on your phone ?

In order to save user's data on the phone we will use encrypted shared preferences.

We first create a masterkey to encrypt the file in which we will store the user's data. Then we use this masterkey to create an ecrypted shared preferences named "EncryptedSharedPreferences".

When the user access its account details, the app will search for the details previously stored on the phone. If the user didn't store data, the application will try to reach the api to download and store securely the data. Each time the user will update it's accounts details, the application will download data from the api and overwrite data.

![6](https://user-images.githubusercontent.com/43971721/109394958-1e5e0e80-792a-11eb-9d3d-119859c6b28c.png)

3. How did you hide the API url ?

We implemented the AESUtils class in order to encrypt and decrypt Strings in our project.

The url of the API was encrypted and stored in the method getUrl() that return the decrypted url whenever it is call.
