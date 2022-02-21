package com.example.secondhomework

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.secondhomework.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val sharedPreferencesFile = getSharedPreferences("loginDataFile", MODE_PRIVATE)
        val editor = sharedPreferencesFile.edit()
        val currentUserName = sharedPreferencesFile.getString("userNameKey", null)
        val currentPassword = sharedPreferencesFile.getString("passwordKey", null)
        val currentCheckedBoxStatus = sharedPreferencesFile.getBoolean("checkBoxStatus", false)
        binding.etUserNameLogin.setText(currentUserName)
        binding.etPasswordLogin.setText(currentPassword)
        binding.cbRememberMe.isChecked = currentCheckedBoxStatus

        Toast(this).apply {
            duration = Toast.LENGTH_LONG
            view = layoutInflater.inflate(R.layout.custom_toast, findViewById(R.id.clToast))
            show()
        }

        binding.btnSignIn.setOnClickListener {
            val userName = binding.etUserNameLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()
            if (userName.isEmpty() || password.isEmpty()) {
                if (userName.isEmpty())
                    binding.etUserNameLogin.error = "Please Enter User Name"
                if (password.isEmpty())
                    binding.etPasswordLogin.error = "Please Enter Password"
            } else {
                val checkBoxStatus = binding.cbRememberMe.isChecked
                if(checkBoxStatus) {
                    editor.apply {
                        putString("userNameKey", userName)
                        putString("passwordKey", password)
                        putBoolean("checkBoxStatusKey", checkBoxStatus)
                        apply()
                    }
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.tvNewUserSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}