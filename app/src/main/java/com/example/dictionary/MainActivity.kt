package com.example.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.Model.Model
import com.example.dictionary.adapter.WordAdapter
import com.example.dictionary.database.Database
import com.example.dictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var data: Model
    private val adapter by lazy {
        WordAdapter()
    }
    var pressFavourite = false
    var press = false
    var favourite = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = ""

        if (intent.getIntExtra("ID", 0) == 1) {
            favourite = 1
            adapter.addAllWords(Database.getDatabase().getWords(1))
        } else if (intent.getIntExtra("ID", 0) == 2) {
            favourite = 2
            adapter.addAllWords(Database.getDatabase().getWords(2))
        }

        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter

//        binding.en.setOnClickListener {
//            adapter.addAllWords(Database.getDatabase().getWords(1))
//        }
//
//        binding.uz.setOnClickListener {
//            adapter.addAllWords(Database.getDatabase().getWords(2))
//        }

        binding.swap.setOnClickListener {
            if (!press) {
                favourite = 1
                press = true
                adapter.addAllWords(Database.getDatabase().getWords(1))
            } else {
                favourite = 2
                press = false
                adapter.addAllWords(Database.getDatabase().getWords(2))
            }
        }

        adapter.setClickFavouriteImage { it ->
            if (adapter.data[it].favourite == 0) {
                adapter.data[it].favourite = 1
                Database.getDatabase().editFavourite(Model(1), adapter.data[it].id)
            } else if (adapter.data[it].favourite == 1) {
                adapter.data[it].favourite = 0
                Database.getDatabase().editFavourite(Model(0), adapter.data[it].id)
            }
            adapter.notifyItemChanged(it)
        }

        binding.search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (pressFavourite) {
                    adapter.addAllWords(Database.getDatabase().searchFavourite(p0,favourite,1))
                } else {
                    adapter.addAllWords(Database.getDatabase().search(p0,favourite))
                }
                return true
            }
        })

        binding.favourite.setOnClickListener {
            if (!pressFavourite) {
                pressFavourite = true
                adapter.addAllWords(Database.getDatabase().sortFavourite(1))
            } else {
                pressFavourite = false
                adapter.addAllWords(Database.getDatabase().getWords(favourite))
            }
        }
    }
}
