# Swipe Assignment Android App

This Android app is developed for the Swipe Assignment, showcasing skills in building a functional and visually appealing product listing and addition system.

## Project Overview

The project comprises two main screens:

### Product Listing Screen

- Displays a list of products retrieved from the provided API endpoint.
- Allows users to search for products, scroll through the list, and navigate to the Add Product screen.
- Utilizes Retrofit for API communication, Kotlin coroutines for asynchronous operations, and Coil for image loading.
- Implements MVVM architecture for a clean and organized code structure.

### Add Product Screen

- Enables users to add a new product with details such as product type, name, price, and tax.
- Validates user input and submits the data using the POST method to the provided API endpoint.
- Leverages Jetpack Compose for building the UI, along with KOIN for dependency injection.

## Getting Started

Follow these steps to run the app locally:

1. Clone the repository: `git clone https://github.com/GoelYash1/SwipeAssignment`
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or physical device.

## Dependencies

The app uses several dependencies to enhance functionality and follow best practices:

- Retrofit: For handling API communication.
- Kotlin Coroutines: For asynchronous programming.
- KOIN: For dependency injection.
- Jetpack Compose: For building the modern UI.
- Coil: For image loading.
- Lifecycle components: For handling the lifecycle of Android components.
