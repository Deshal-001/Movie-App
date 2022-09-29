package com.example.coursework2022

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coursework2022.data.Movie
import com.example.coursework2022.data.MovieDatabase
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class SearchForMovieActivity : AppCompatActivity() {
    lateinit var title: String //title var
    lateinit var year: String//year var
    lateinit var released: String//released var
    lateinit var director: String//director var
    lateinit var actor: String//actor var
    lateinit var rate: String//rate var
    lateinit var genre: String//genre var
    lateinit var runtime: String//runtime var
    lateinit var writer: String//writer var
    lateinit var plot: String//plot var


    lateinit var movie: String


    var actors = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_for_movie)
        supportActionBar?.hide()

        //set buttons
        val searchButton = findViewById<ExtendedFloatingActionButton>(R.id.search_button)
        val search_movie = findViewById<TextInputEditText>(R.id.search_movie_)
        val save_button = findViewById<ExtendedFloatingActionButton>(R.id.save_button)

        //set save button's functionality
        save_button.setOnClickListener {
            var movie: Movie
            //creates/gets exist database
            movieDao = MovieDatabase.getInstance(this)?.movieDao()!!
            //check is there a same movie
            if (movieDao.getAll(title).isEmpty()) {
                movie = Movie(
                    0,
                    title,
                    year,
                    rate,
                    released,
                    runtime,
                    genre,
                    director,
                    writer,
                    actor,
                    plot
                )
                //insert movie to table
                movieDao.insert(movie)
            } else {
                Toast.makeText(applicationContext, "Already saved", Toast.LENGTH_SHORT).show()
            }


        }










        searchButton.setOnClickListener {
            //declare StringBuilder var
            var stb = StringBuilder()
            //get user input
            movie = search_movie.text.toString()
            //set url
            val url_string = "https://www.omdbapi.com/?t=$movie&apikey=30b94d06";
            val url = URL(url_string)
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection
            runOnUiThread(Runnable {
                runBlocking {
                    launch {
                        // run the code of the coroutine in a new thread
                        withContext(Dispatchers.IO) {
                            var bf = BufferedReader(InputStreamReader(con.inputStream))
                            var line: String? = bf.readLine()
                            while (line != null) {
                                stb.append(line + "\n")
                                line = bf.readLine()
                            }
                            //call the method
                            parseJSON(stb)
                        }
                    }
                }
            })
        }

    }


    @SuppressLint("SetTextI18n", "CutPasteId")
    private suspend fun parseJSON(stb: java.lang.StringBuilder) {
        //declare variables
        val yearLbl = findViewById<TextView>(R.id.movie_year_label)
        val rateLbl = findViewById<TextView>(R.id.movie_rate_label)
        val releasedLbl = findViewById<TextView>(R.id.movie_released_label)
        val runtimeLbl = findViewById<TextView>(R.id.movie_runtime_label)
        val genreLbl = findViewById<TextView>(R.id.movie_genre_label)
        val directorLbl = findViewById<TextView>(R.id.movie_director_label)
        val writerLbl = findViewById<TextView>(R.id.movie_writer_label)
        val plotLbl = findViewById<TextView>(R.id.plot_label)
        val actorLbl = findViewById<TextView>(R.id.movie_actor_label)
        val titleLbl = findViewById<TextView>(R.id.title_lbl)

       //set json var
        val json = JSONObject(stb.toString())
        //set values that were taken from url
        if (!json.getString("Response").equals("False")) {
            title = json.getString("Title")
            year = json.getString("Year")
            rate = json.getString("Rated")
            released = json.getString("Released")
            runtime = json.getString("Runtime")
            genre = json.getString("Genre")
            director = json.getString("Director")
            writer = json.getString("Writer")
            actor = json.getString("Actors")
            plot = json.getString("Plot")

            //add values for text views
            runOnUiThread(Runnable {
                titleLbl.text = "Title: $title"
                yearLbl.text = "Year: $year"
                rateLbl.text = "Rate: $rate"
                plotLbl.text = "Plot: $plot"
                releasedLbl.text = "Released: $released"
                runtimeLbl.text = "Runtime: $runtime"
                genreLbl.text = "Genre: $genre"
                directorLbl.text = "Director: $director"
                writerLbl.text = "Writer: $writer"
                actorLbl.text = "Actor: $actor"
            })
        } else {
            runOnUiThread(Runnable {
                titleLbl.setText("No Found !")
                yearLbl.text = ""
                rateLbl.text = ""
                plotLbl.text = ""
                releasedLbl.text = ""
                runtimeLbl.text = ""
                genreLbl.text = ""
                directorLbl.text = ""
                writerLbl.text = ""
                actorLbl.text = ""
            })
        }


        // display.text="Title : $title " +
        //   " Year : $year"


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val movie = findViewById<TextInputEditText>(R.id.search_movie_)
        val previousMovie: CharSequence = movie.text.toString()
        outState.putCharSequence("search_movie_", previousMovie)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val movie = findViewById<TextInputEditText>(R.id.search_movie_)
        val newMovie: CharSequence = savedInstanceState.getCharSequence("search_movie_").toString()
        // movie.text=newMovie.toString()

    }


}