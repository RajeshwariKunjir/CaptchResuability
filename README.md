

# ReusableCaptcha
Project is based on reusable of captcha for logging the account

# Reusable CAPTCHA ✉️

Reusable CAPTCHA is an Android application that generates and verifies CAPTCHA (Completely Automated Public Turing test to tell Computers and Humans Apart) for user registration. It provides a simple and secure way to prevent bots from registering in your application. 🤖🚫

## Table of Contents 📚

- [Overview](#overview)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Overview 📝

The Reusable CAPTCHA application allows users to register with a username, password, and CAPTCHA verification. It generates a random CAPTCHA text and displays it as an image to the user. The user needs to enter the CAPTCHA correctly to complete the registration process. 🔐✅

The application utilizes the following components: 🛠️

- `MainActivity`: The main activity of the application that handles user interaction and registration process.
- `DatabaseHelper`: A helper class that manages the SQLite database for storing user information.
- `CaptchaGenerator`: A utility class for generating CAPTCHA images.
- `activity_main.xml`: The XML layout file that defines the user interface of the main activity.

## Installation ⚙️

To use the Reusable CAPTCHA application, follow these steps:

1. Clone the repository:
   ```
   git clone https://github.com/your-username/reusable-captcha.git
   ```

2. Open the project in Android Studio or your preferred IDE.

3. Build and run the project on an Android device or emulator.

## Usage 📱

1. Launch the Reusable CAPTCHA application on your Android device.

2. Enter a username and password in the respective fields.

3. The application will generate a CAPTCHA image consisting of random alphanumeric characters. 🔢🎨

4. Enter the CAPTCHA text exactly as shown in the image in the "Enter CAPTCHA" field.

5. Tap the "Submit" button to register.

6. If the CAPTCHA is entered correctly, a toast message will confirm the successful registration. ✅📝

7. If the CAPTCHA is incorrect, a toast message will indicate an invalid CAPTCHA. ❌⚠️

## Contributing 🤝

Thank you for considering contributing to the Reusable CAPTCHA project! To contribute, follow these steps:

1. Fork the repository.

2. Create a new branch for your feature or bug fix.

3. Make your changes and commit them with descriptive commit messages.

4. Push your changes to your fork.

5. Submit a pull request, describing your changes and the problem they solve.

## License 📜

This project is licensed under the [MIT License](LICENSE.md).
