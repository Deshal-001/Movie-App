package com.example.coursework2022

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coursework2022.data.Movie
import com.example.coursework2022.data.MovieDao
import com.example.coursework2022.data.MovieDatabase
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddMovie_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddMovie_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var title: TextInputEditText
    lateinit var year: TextInputEditText
    lateinit var released: TextInputEditText
    lateinit var director: TextInputEditText
    lateinit var actor: TextInputEditText
    lateinit var rate: TextInputEditText
    lateinit var genre: TextInputEditText
    lateinit var runtime: TextInputEditText
    lateinit var writer: TextInputEditText
    lateinit var plot: TextInputEditText




    lateinit var prefs: SharedPreferences
    lateinit var movieDao: MovieDao


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view=inflater.inflate(R.layout.fragment_add_movie_, container, false)

        title=view.findViewById(R.id.movie_title)
        year=view.findViewById(R.id.movie_year)
        released=view.findViewById(R.id.movie_released)
        director=view.findViewById(R.id.movie_director)
        actor=view.findViewById(R.id.movie_actor)
        rate=view.findViewById(R.id.movie_rate)
        genre=view.findViewById(R.id.movie_genre)
        runtime=view.findViewById(R.id.movie_runtime)
        writer=view.findViewById(R.id.movie_writer)
        plot=view.findViewById(R.id.movie_plot)


        movieDao= MovieDatabase.getInstance(requireContext())?.movieDao()!!

        val save_button=view.findViewById<Button>(R.id.save_action)
        save_button.setOnClickListener {
            if(title.text.toString() == "" || year.text.toString().equals("")||rate.text.toString().equals("")||released.text.toString().equals("")||runtime.text.toString().equals("")||genre.text.toString().equals("")||director.text.toString().equals("")||writer.text.toString().equals("")||actor.text.toString().equals("")||plot.text.toString().equals("")){
                Toast.makeText(context,"Please fill all", Toast.LENGTH_SHORT).show()
            }
            else {
                if (movieDao.getAll(title.text.toString()).isEmpty()) {
                    /*    var actors=ArrayList<String>()
                var str: Array<String> = actor.text.toString().split(",").toTypedArray()
                for (s in str) actors.add(s) */
                    var movie = Movie(
                        0,
                        title.text.toString(),
                        year.text.toString(),
                        rate.text.toString(),
                        released.text.toString(),
                        runtime.text.toString(),
                        genre.text.toString(),
                        director.text.toString(),
                        writer.text.toString(),
                        actor.text.toString(),
                        plot.text.toString()
                    )
                    movieDao.insert(movie)
                } else {
                    Toast.makeText(context, "Already saved", Toast.LENGTH_SHORT).show()
                }
                val fragment=Movie_list_Fragment()
                val transtraction=fragmentManager?.beginTransaction()
                transtraction?.replace(R.id.nav_container,fragment)?.commit()

            }








        }

        // Inflate the layout for this fragment
        return view
    }

}