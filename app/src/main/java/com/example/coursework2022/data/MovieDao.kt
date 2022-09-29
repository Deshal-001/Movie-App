package com.example.coursework2022.data

import androidx.room.*

@Dao
interface MovieDao {


    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.

    //insert items
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public fun insert(item: Movie)

    //update items
    @Update
    public fun update(item: Movie)

    //delete items
    @Delete
    public fun delete(item: Movie)

    //get data of items
    @Query("Select * from movie where title = :name")
      fun getAll(name: String?): List<Movie>

   //get last item
    @Query("SELECT * FROM movie ORDER BY id DESC LIMIT 1")
    fun getLast(): Movie

    //get item
    @Query("SELECT * from movie WHERE id = :id")
    fun getItem(id: Int): Movie

   //find movies
    @Query("Select title from movie where actors LIKE :name")
    fun getActor(name: String?): List<String>




}