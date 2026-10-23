package com.example.baitaptrenlop

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.example.baitaptrenlop.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Validation thời gian thực
        binding.edtName.doOnTextChanged { _, _, _, _ -> validateName() }
        binding.edtEmail.doOnTextChanged { _, _, _, _ -> validateEmail() }
        binding.edtPhone.doOnTextChanged { _, _, _, _ -> validatePhone() }
        binding.edtMsv.doOnTextChanged { _, _, _, _ -> validateMsv() }
        binding.edtClass.doOnTextChanged { _, _, _, _ -> validateClass() }

        binding.btnRegister.setOnClickListener {
            // Dùng "and" để hiện lỗi của tất cả các ô cùng lúc
            val ok = validateName() and validateEmail() and validatePhone() and
                    validateMsv() and validateClass()

            if (ok) {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_NAME, binding.edtName.text.toString().trim())
                    putExtra(DetailActivity.EXTRA_EMAIL, binding.edtEmail.text.toString().trim())
                    putExtra(DetailActivity.EXTRA_PHONE, binding.edtPhone.text.toString().trim())
                    putExtra(DetailActivity.EXTRA_MSV, binding.edtMsv.text.toString().trim())
                    putExtra(DetailActivity.EXTRA_CLASS, binding.edtClass.text.toString().trim())
                }
                startActivity(intent)
            }
        }
    }

    private fun showError(layout: TextInputLayout, message: String?): Boolean {
        layout.error = message
        return message == null
    }

    private fun validateName(): Boolean {
        val v = binding.edtName.text.toString().trim()
        return showError(
            binding.tilName, when {
                v.isEmpty() -> "Họ tên không được để trống"
                !Regex("^[\\p{L} ]{2,50}$").matches(v) -> "Họ tên chỉ gồm chữ cái và khoảng trắng"
                else -> null
            }
        )
    }

    private fun validateEmail(): Boolean {
        val v = binding.edtEmail.text.toString().trim()
        return showError(
            binding.tilEmail, when {
                v.isEmpty() -> "Email không được để trống"
                !Patterns.EMAIL_ADDRESS.matcher(v).matches() -> "Email không hợp lệ"
                else -> null
            }
        )
    }

    private fun validatePhone(): Boolean {
        val v = binding.edtPhone.text.toString().trim()
        return showError(
            binding.tilPhone, when {
                v.isEmpty() -> "Số điện thoại không được để trống"
                !Regex("^0\\d{9}$").matches(v) -> "SĐT gồm 10 số, bắt đầu bằng 0"
                else -> null
            }
        )
    }

    private fun validateMsv(): Boolean {
        val v = binding.edtMsv.text.toString().trim()
        return showError(
            binding.tilMsv, when {
                v.isEmpty() -> "Mã sinh viên không được để trống"
                !Regex("^\\d{6,12}$").matches(v) -> "MSV gồm 6-12 chữ số"
                else -> null
            }
        )
    }

    private fun validateClass(): Boolean {
        val v = binding.edtClass.text.toString().trim()
        return showError(
            binding.tilClass, when {
                v.isEmpty() -> "Lớp không được để trống"
                !Regex("^[A-Za-z0-9_\\-]{3,20}$").matches(v) -> "Lớp chỉ gồm chữ, số, '_' hoặc '-'"
                else -> null
            }
        )
    }
}