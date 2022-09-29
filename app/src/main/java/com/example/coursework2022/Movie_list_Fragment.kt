package com.example.coursework2022

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coursework2022.data.Movie
import com.example.coursework2022.data.MovieDao
import com.example.coursework2022.data.MovieDatabase


lateinit var movieDao: MovieDao


class Movie_list_Fragment : Fragment() {
    lateinit var delete_button: Button

    lateinit var title: TextView
    lateinit var year: TextView
    lateinit var released: TextView
    lateinit var director: TextView
    lateinit var actor: TextView
    lateinit var rate: TextView
    lateinit var genre: TextView
    lateinit var runtime: TextView
    lateinit var writer: TextView
    lateinit var plot: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_movie_list_, container, false)
            movieDao= MovieDatabase.getInstance(requireContext())?.movieDao()!!



        title=view.findViewById(R.id.movie_title_label)
        year=view.findViewById(R.id.movie_year_label)
        released=view.findViewById(R.id.movie_released_label)
        director=view.findViewById(R.id.movie_director_label)
        actor=view.findViewById(R.id.movie_actor_label)
        rate=view.findViewById(R.id.movie_rate_label)
        genre=view.findViewById(R.id.movie_genre_label)
        runtime=view.findViewById(R.id.movie_runtime_label)
        writer=view.findViewById(R.id.movie_writer_label)
        plot=view.findViewById(R.id.movie_plot_label)



        val movie: Movie = movieDao.getLast()

        title.text="Title :"+movie.movieName
        year.text="Year :"+movie.movieYear
        released.text="Released :"+movie.movieReleased
        director.text="Dirrector :"+movie.movieDirector
        actor.text="Actor :"+movie.movieActors
        rate.text="Rate :"+movie.movieRated
        genre.text="Genre :"+movie.movieGenre
        runtime.text="Runtime :"+movie.movieRuntime
        writer.text="Writer :"+movie.movieWriter
        plot.text="Plot :"+movie.moviePlot



        delete_button=view.findViewById(R.id.delete_action)
        delete_button.setOnClickListener {
            movieDao.delete(movie)
            Toast.makeText(requireContext(),"Deleted",Toast.LENGTH_SHORT).show()
            activity?.onBackPressed();
        }



        // Inflate the layout for this fragment
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val movie=movieDao.getLast()
        val previousMovie:CharSequence=movieDao.getLast().movieName
        outState.putCharSequence("movieAppera",previousMovie)
    }









}