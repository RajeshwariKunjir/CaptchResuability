package com.example.reusablecaptcha;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private ImageView imageViewCaptcha;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextCaptcha;
    private Button buttonSubmit;

    private String currentCaptchaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Get the writable database
        database = databaseHelper.getWritableDatabase();

        boolean isUsersTableExists = isTableExists(database, "users");

        if (isUsersTableExists) {
            // The "users" table exists
            // Perform necessary actions
            Toast.makeText(this, "Users table exists", Toast.LENGTH_SHORT).show();
            // Perform other actions for an existing "users" table
        } else {
            // The "users" table does not exist
            // Perform necessary actions
            Toast.makeText(this, "Users table does not exist", Toast.LENGTH_SHORT).show();
            // Perform other actions for a non-existing "users" table
        }

        // Initialize UI elements
        imageViewCaptcha = findViewById(R.id.imageViewCaptcha);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCaptcha = findViewById(R.id.editTextCaptcha);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Generate initial CAPTCHA image
        generateCaptchaImage();

        // Set click listener for the submit button
        buttonSubmit.setOnClickListener(v -> onSubmitClicked());
    }

    private void generateCaptchaImage() {
        // Generate CAPTCHA text
        currentCaptchaText = generateCaptcha();

        // Generate CAPTCHA image
        Bitmap captchaImage = CaptchaGenerator.generateCaptchaImage(currentCaptchaText);

        // Display CAPTCHA image
        imageViewCaptcha.setImageBitmap(captchaImage);
    }

    private void onSubmitClicked() {
        // Get user input
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String userInputCaptcha = editTextCaptcha.getText().toString();

        // Verify CAPTCHA
        if (!CaptchaVerification.verifyCaptcha(userInputCaptcha, currentCaptchaText)) {
            Toast.makeText(this, "Invalid CAPTCHA", Toast.LENGTH_SHORT).show();
            return;
        }

        // Hash the password
        String hashedPassword = PasswordHasher.hashPassword(password);

        // Store user information in the database
        // Store user information in the database
        DatabaseManager.createUser(getApplicationContext(), username, hashedPassword);

        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
    }

    private String generateCaptcha() {
        int length = 6; // Length of the CAPTCHA text
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            captcha.append(characters.charAt(index));
        }

        return captcha.toString();
    }

    private static class PasswordHasher {
        public static String hashPassword(String password) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(password.getBytes());
                byte[] hashedBytes = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : hashedBytes) {
                    sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class DatabaseManager {
        public static void createUser(Context context, String username, String hashedPassword) {

            String databasePath = context.getDatabasePath("captcha.db").getPath();

            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databasePath, null);

            // Create a new ContentValues object to store the user information
            ContentValues values = new ContentValues();
            values.put("username", username);
            values.put("password", hashedPassword);

            // Insert the user information into the database
            long rowId = database.insert("users", null, values);

            // Close the database connection
            database.close();

            if (rowId != -1) {
                // User created successfully
                // You can perform any additional actions here if needed
                Toast.makeText(context, "User created successfully", Toast.LENGTH_SHORT).show();
            } else {
                // Error occurred while creating the user
                // Handle the error appropriately
                Toast.makeText(context, "Error occurred while creating the user", Toast.LENGTH_SHORT).show();
            }
            database.close();
        }
    }

    private boolean isTableExists(SQLiteDatabase database, String tableName) {
        Cursor cursor = null;
        try {
            // Execute the PRAGMA statement to get table information
            cursor = database.rawQuery("PRAGMA table_info(" + tableName + ")", null);
            return (cursor != null && cursor.getCount() > 0);
        } finally {
            // Close the cursor to release resources
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private static class CaptchaVerification {
        public static boolean verifyCaptcha(String userInput, String expectedCaptcha) {
            // CAPTCHA verification logic
            // Compare the user input with the expected CAPTCHA text
            return userInput.equals(expectedCaptcha);
        }
    }
}

