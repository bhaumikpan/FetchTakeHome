
# Fetch Take Home Project

This repository contains the solution for Fetch's take-home coding challenge, developed using **Kotlin**, **Hilt Dependency Injection**, and **Coroutines**. The project demonstrates a clean architecture pattern with repository implementations, handling asynchronous network requests and error scenarios effectively.

## Table of Contents
- [Getting Started](#getting-started)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [How to Run](#how-to-run)
- [Testing](#testing)
- [Future Enhancements](#future-enhancements)

## Getting Started

Follow these instructions to get a copy of the project running on your local machine.

### Prerequisites

- Android Studio (latest stable version)
- Kotlin 1.5+
- Gradle 7.0+
- Internet connection for API requests

### Installing

1. Clone the repository:

   ```bash
   git clone https://github.com/bhaumikpan/FetchTakeHome.git

   Open the project in Android Studio.

2. Sync the Gradle files by clicking on "Sync Now" if prompted.

3. Once the build completes, you can run the project on an Android device or emulator.

### Features

- API Integration: Fetches data from the provided API endpoint using Kotlin coroutines for asynchronous network calls.
- Data Filtering & Sorting: The app retrieves a list of items, filters out invalid items, and sorts them by listId and name.
- Error Handling: Graceful handling of network and data errors with retry logic using Kotlin coroutines.
- Dependency Injection: Powered by Hilt for managing dependencies in a clean and modular manner.
- MVVM Architecture: Implements the Model-View-ViewModel pattern for separation of concerns and testability.


## Tech Stack

- Kotlin: Main programming language.
- Coroutines: For handling asynchronous tasks.
- Hilt: Dependency injection framework.
- Retrofit: For network requests.
- ViewModel & LiveData: For managing UI-related data lifecycle.
- Jetpack: Core libraries including ViewModel, LiveData, and Room.

## Architecture
This project follows Clean Architecture principles with the MVVM (Model-View-ViewModel) design pattern.

- Model: Represents the business logic and data layer (repository + network/data sources).
- ViewModel: Mediates between the View and the Model, containing UI-related logic.
- View: Displays the data and reflects changes from the ViewModel.


