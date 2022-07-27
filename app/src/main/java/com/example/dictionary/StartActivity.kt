package com.example.dictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dictionary.database.Database
import com.example.dictionary.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.english.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java).putExtra("ID",1))
        }

        binding.uzbek.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java).putExtra("ID",2))
        }
    }
}