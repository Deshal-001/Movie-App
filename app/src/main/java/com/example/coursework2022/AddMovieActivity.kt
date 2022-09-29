package com.example.coursework2022

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coursework2022.data.MovieDao


class AddMovieActivity : AppCompatActivity() {



    lateinit var movieDao: MovieDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)


        supportFragmentManager.beginTransaction().replace(R.id.nav_container,AddMovie_Fragment()).commit()




    }


}