# Lab 05 – Form Validation App

**IT2010 – Mobile Application Development**  
**BSc (Hons) in Information Technology – SLIIT**

## Overview

This lab exercise focuses on **form validation** in a Kotlin-based Android application. The app allows students to register by entering their Student ID, selecting the academic year and semester, and agreeing to terms and conditions. Proper input validation is implemented using a custom validation model.

## Features

- Custom UI with form inputs using **LinearLayout**
- **Spinner-based** selection for Academic Year and Semester
- Validation logic using a **sealed class** for different validation results
- Alerts for user guidance and form submission confirmation
- Error handling with inline input field feedback and alert dialogs

## Validation Logic

- `ValidationResult`: A sealed class with `Valid`, `Empty`, and `Invalid` states.
- `FormData`: Validates:
  - Student ID (must start with "IT" and be 10 characters long)
  - Year and Semester (not empty)
  - Agreement (must be checked)

## Key Functions

- `displayAlert(title: String, message: String)`: Shows dialog boxes.
- `submit(v: View)`: Validates inputs and gives feedback.

## How to Run

1. Open the project in **Android Studio**.
2. Build and run on an emulator or Android device.
3. Test all input scenarios (empty, invalid, valid) for validation responses.
