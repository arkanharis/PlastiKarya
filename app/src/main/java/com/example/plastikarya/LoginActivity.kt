// LoginActivity.kt
package com.example.plastikarya

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: AppCompatButton
    private lateinit var tvRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        btnBack = findViewById(R.id.btn_back)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        tvRegister = findViewById(R.id.tv_register)
    }

    private fun setupClickListeners() {
        // Back button click listener
        btnBack.setOnClickListener {
            finish() // Kembali ke activity sebelumnya
        }

        // Login button click listener
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInput(username, password)) {
                // Proses login
                performLogin(username, password)
            }
        }

        // Register link click listener
        tvRegister.setOnClickListener {
            // Intent untuk pindah ke RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        var isValid = true

        if (username.isEmpty()) {
            etUsername.error = "Username/Email tidak boleh kosong"
            isValid = false
        }

        if (password.isEmpty()) {
            etPassword.error = "Password tidak boleh kosong"
            isValid = false
        } else if (password.length < 6) {
            etPassword.error = "Password minimal 6 karakter"
            isValid = false
        }

        return isValid
    }

    private fun performLogin(username: String, password: String) {
        // Simulasi login berhasil - dalam implementasi nyata, Anda akan melakukan
        // validasi dengan server atau database

        try {
            // Tampilkan pesan sukses
            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()

            // Intent untuk pindah ke HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            // Menutup LoginActivity dan semua activity sebelumnya
            finishAffinity()

        } catch (e: Exception) {
            // Jika ada error, tampilkan pesan error
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}