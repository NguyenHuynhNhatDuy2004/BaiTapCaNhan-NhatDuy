package com.example.baitaptrenlop

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.baitaptrenlop.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE = "extra_phone"
        const val EXTRA_MSV = "extra_msv"
        const val EXTRA_CLASS = "extra_class"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        binding.tvName.text = intent.getStringExtra(EXTRA_NAME)
        binding.tvEmail.text = intent.getStringExtra(EXTRA_EMAIL)
        binding.tvPhone.text = intent.getStringExtra(EXTRA_PHONE)
        binding.tvMsv.text = intent.getStringExtra(EXTRA_MSV)
        binding.tvClass.text = intent.getStringExtra(EXTRA_CLASS)

        // Gọi intent quay về màn Đăng kí (MainActivity)
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}