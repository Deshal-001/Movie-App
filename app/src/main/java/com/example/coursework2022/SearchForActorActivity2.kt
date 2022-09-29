package com.example.coursework2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.coursework2022.data.MovieDao
import com.example.coursework2022.data.MovieDatabase
import com.google.android.material.textfield.TextInputEditText

class SearchForActorActivity2 : AppCompatActivity() {

    lateinit var movieDao: MovieDao
    lateinit var search_movie:TextInputEditText
    lateinit var search_button:Button
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_for_actor2)
        supportActionBar?.hide()

        movieDao= MovieDatabase.getInstance(this)?.movieDao()!!
        search_movie=findViewById(R.id.search_actor_name)

        search_button=findViewById(R.id.search_button)


        search_button.setOnClickListener {
            var list= movieDao.getActor("%${search_movie.text.toString()}%")
            if(list.isEmpty()){
                Toast.makeText(applicationContext,"No Movie !", Toast.LENGTH_SHORT).show()
            }
            val adapter = ArrayAdapter(this,
                R.layout.listview_item, list)
            val listView:ListView = findViewById(R.id.listView)
            listView.adapter = adapter
        }


       // println(movieDao.getActor("%1%"))








    }
}