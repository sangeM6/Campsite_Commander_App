# Campasite Commander App
## Description
The Canpsite Commander App is a mobile application built using Kotlin. It allows the user to add, view, and 
organise camping and grocery items e.eg shelter equipment, cooking tools, and first aid supplies. The app stores data using parallel 
arrays and displats the information in a structured list format.

## Purpose of the App
The purpose of this application is to help users:
* Keep track of camping gear and supllies.
* Organise items into categories (shelter, Cokking, First Aid).
* Monitor total packed items using a calculated loop.
* Improve packing efficiency before outdoor trips.

## Features
* Adding camping gear items with:
* Item name
* Category
* Quantity
* Notes/Comments
* View full list gear in structured table format.
* Calculate total number of items packed.
* Splash screen with logo and title (3 seconds delay).
* Back Navigation to main screen.
* Parallel array data storage.

## Design Considerations
* Clean and simple user interface.
* Nature-inspired dark theme for outdoor relevance.
* Input validation to prevent Jetpack Compose Column and Row.
* Table-style layout for easy readability of data.
* Responsive design using padding and spacing.

## Technology Used
* Kotlin
* Jetpack Compose
* Android Studio
* Coroutines

##Refences
W3Schools n.d. _Kotlin Arrays_ [online]. Available at:https://www.w3schools.com/kotlin/kotlin_arrays.php (Accessed on:10 June 2026).
Kotlin n.d. _Condition and Loops_ [online]. Available at: https://kotlinlang.org/docs/control-flow.html#when-expressions-and-statements (Accessed on:10 June 2026).
* Material 3 Design Components

## Code Example
var totalItems =0
for (q in quantities) {
    totalItems += q
    }

this code adds the total packed items, it loops through the quantities and then store the number in q and then go add it to totalItems then the new value of totalItems will be whatever number was found in q .

## GitHub Usage
* Version control of the project.
* Storing and tracking code changes.
* Uploading project files and documentation.
* Collaborating and maintaining backups of the app.

## GitHub Actions
* Automatically build the when code is pushed.
* Run basic checks to enure code compiles correctly.
* Maintain continuou integration workflow.

## How To Run The App
* Open Anfroid Studio.
* Download the repository.
* Open the project in Android Studio.
* Let Gradle sync complete.
* Connect an emulator or physical device.
* Click Run.

## Summary
This project demonstrates the use of Jetpack Compose, state management, and parallel array to build a functional mobile application for managing camping gear efficiently. It focuses on usability, organisation, and clean UI design.
