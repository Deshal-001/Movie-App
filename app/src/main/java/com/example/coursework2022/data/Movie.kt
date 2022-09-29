package com.example.coursework2022.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val movieName: String,
    @ColumnInfo(name = "year")
    val movieYear: String,
    @ColumnInfo(name = "rated")
    val movieRated: String,
    @ColumnInfo(name="released")
    val movieReleased:String,
    @ColumnInfo(name="runtime")
    val movieRuntime:String,
    @ColumnInfo(name="genre")
    val movieGenre:String,
    @ColumnInfo(name="director")
    val movieDirector:String,
    @ColumnInfo(name="writer")
    val movieWriter:String,
    @ColumnInfo(name="actors")
    val movieActors: String,
    @ColumnInfo(name="plot")
    val moviePlot: String,
)