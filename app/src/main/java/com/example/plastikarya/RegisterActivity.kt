// RegisterActivity.kt
package com.example.plastikarya

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var tvLogin: TextView

    // Role selection
    private lateinit var layoutRoleSelection: LinearLayout
    private lateinit var cardKreator: CardView
    private lateinit var cardPembeli: CardView
    private lateinit var cardMentor: CardView
    private lateinit var rbKreator: RadioButton
    private lateinit var rbPembeli: RadioButton
    private lateinit var rbMentor: RadioButton

    // Registration form
    private lateinit var layoutRegistrationForm: LinearLayout
    private lateinit var layoutCommonFields: LinearLayout
    private lateinit var layoutKreatorFields: LinearLayout
    private lateinit var layoutMentorFields: LinearLayout

    // Common fields
    private lateinit var etNama: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText

    // Kreator fields
    private lateinit var etNamaToko: TextInputEditText
    private lateinit var etAlamatToko: TextInputEditText
    private lateinit var spinnerJenisProduk: AutoCompleteTextView
    private lateinit var btnUploadFotoProduk: AppCompatButton
    private lateinit var btnUploadKtpKreator: AppCompatButton

    // Mentor fields
    private lateinit var spinnerKategoriKursus: AutoCompleteTextView
    private lateinit var btnUploadKtpMentor: AppCompatButton
    private lateinit var btnUploadCv: AppCompatButton

    private lateinit var btnRegister: AppCompatButton

    private var selectedRole: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupViews()
        setupClickListeners()
        setupSpinners()
    }

    private fun setupViews() {
        btnBack = findViewById(R.id.btn_back)
        tvLogin = findViewById(R.id.tv_login)

        // Role selection
        layoutRoleSelection = findViewById(R.id.layout_role_selection)
        cardKreator = findViewById(R.id.card_kreator)
        cardPembeli = findViewById(R.id.card_pembeli)
        cardMentor = findViewById(R.id.card_mentor)
        rbKreator = findViewById(R.id.rb_kreator)
        rbPembeli = findViewById(R.id.rb_pembeli)
        rbMentor = findViewById(R.id.rb_mentor)

        // Registration form
        layoutRegistrationForm = findViewById(R.id.layout_registration_form)
        layoutCommonFields = findViewById(R.id.layout_common_fields)
        layoutKreatorFields = findViewById(R.id.layout_kreator_fields)
        layoutMentorFields = findViewById(R.id.layout_mentor_fields)

        // Common fields
        etNama = findViewById(R.id.et_nama)
        etPhone = findViewById(R.id.et_phone)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)

        // Kreator fields
        etNamaToko = findViewById(R.id.et_nama_toko)
        etAlamatToko = findViewById(R.id.et_alamat_toko)
        spinnerJenisProduk = findViewById(R.id.spinner_jenis_produk)
        btnUploadFotoProduk = findViewById(R.id.btn_upload_foto_produk)
        btnUploadKtpKreator = findViewById(R.id.btn_upload_ktp_kreator)

        // Mentor fields
        spinnerKategoriKursus = findViewById(R.id.spinner_kategori_kursus)
        btnUploadKtpMentor = findViewById(R.id.btn_upload_ktp_mentor)
        btnUploadCv = findViewById(R.id.btn_upload_cv)

        btnRegister = findViewById(R.id.btn_register)
    }

    private fun setupClickListeners() {
        // Back button
        btnBack.setOnClickListener {
            finish()
        }

        // Login link
        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Role selection cards
        cardKreator.setOnClickListener {
            selectRole("kreator")
        }

        cardPembeli.setOnClickListener {
            selectRole("pembeli")
        }

        cardMentor.setOnClickListener {
            selectRole("mentor")
        }

        // Radio buttons
        rbKreator.setOnClickListener {
            selectRole("kreator")
        }

        rbPembeli.setOnClickListener {
            selectRole("pembeli")
        }

        rbMentor.setOnClickListener {
            selectRole("mentor")
        }

        // Upload buttons (TODO: implement file upload)
        btnUploadFotoProduk.setOnClickListener {
            // TODO: Implement image picker
        }

        btnUploadKtpKreator.setOnClickListener {
            // TODO: Implement image picker
        }

        btnUploadKtpMentor.setOnClickListener {
            // TODO: Implement image picker
        }

        btnUploadCv.setOnClickListener {
            // TODO: Implement file picker
        }

        // Register button
        btnRegister.setOnClickListener {
            performRegistration()
        }
    }

    private fun setupSpinners() {
        // Jenis Produk options
        val jenisProdukOptions = arrayOf(
            "Tas & Aksesoris",
            "Dekorasi Rumah",
            "Mainan & Kerajinan",
            "Peralatan Rumah Tangga",
            "Fashion & Pakaian",
            "Furniture",
            "Lainnya"
        )

        val jenisProdukAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, jenisProdukOptions)
        spinnerJenisProduk.setAdapter(jenisProdukAdapter)

        // Kategori Kursus options
        val kategoriKursusOptions = arrayOf(
            "Teknik Dasar Upcycling",
            "Desain & Kreativitas",
            "Kewirausahaan",
            "Pemasaran Digital",
            "Manajemen Bisnis",
            "Teknologi Ramah Lingkungan",
            "Lainnya"
        )

        val kategoriKursusAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, kategoriKursusOptions)
        spinnerKategoriKursus.setAdapter(kategoriKursusAdapter)
    }

    private fun selectRole(role: String) {
        selectedRole = role

        // Update radio buttons
        rbKreator.isChecked = role == "kreator"
        rbPembeli.isChecked = role == "pembeli"
        rbMentor.isChecked = role == "mentor"

        // Show registration form
        layoutRegistrationForm.visibility = View.VISIBLE

        // Show/hide specific fields based on role
        when (role) {
            "kreator" -> {
                layoutKreatorFields.visibility = View.VISIBLE
                layoutMentorFields.visibility = View.GONE
            }
            "mentor" -> {
                layoutKreatorFields.visibility = View.GONE
                layoutMentorFields.visibility = View.VISIBLE
            }
            else -> { // pembeli
                layoutKreatorFields.visibility = View.GONE
                layoutMentorFields.visibility = View.GONE
            }
        }
    }

    private fun performRegistration() {
        if (validateInput()) {
            // TODO: Implement actual registration logic
            // For now, just show success message and redirect to login

            // Example: After successful registration, go to login
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun validateInput(): Boolean {
        var isValid = true

        // Validate role selection
        if (selectedRole.isEmpty()) {
            // Show error message
            return false
        }

        // Validate common fields
        if (etNama.text.toString().trim().isEmpty()) {
            etNama.error = "Nama tidak boleh kosong"
            isValid = false
        }

        if (etPhone.text.toString().trim().isEmpty()) {
            etPhone.error = "Nomor telepon tidak boleh kosong"
            isValid = false
        }

        if (etEmail.text.toString().trim().isEmpty()) {
            etEmail.error = "Email tidak boleh kosong"
            isValid = false
        }

        if (etPassword.text.toString().trim().isEmpty()) {
            etPassword.error = "Password tidak boleh kosong"
            isValid = false
        } else if (etPassword.text.toString().length < 6) {
            etPassword.error = "Password minimal 6 karakter"
            isValid = false
        }

        // Validate role-specific fields
        when (selectedRole) {
            "kreator" -> {
                if (etNamaToko.text.toString().trim().isEmpty()) {
                    etNamaToko.error = "Nama toko tidak boleh kosong"
                    isValid = false
                }

                if (etAlamatToko.text.toString().trim().isEmpty()) {
                    etAlamatToko.error = "Alamat toko tidak boleh kosong"
                    isValid = false
                }

                if (spinnerJenisProduk.text.toString().trim().isEmpty()) {
                    spinnerJenisProduk.error = "Pilih jenis produk"
                    isValid = false
                }
            }

            "mentor" -> {
                if (spinnerKategoriKursus.text.toString().trim().isEmpty()) {
                    spinnerKategoriKursus.error = "Pilih kategori kursus"
                    isValid = false
                }
            }
        }

        return isValid
    }
}