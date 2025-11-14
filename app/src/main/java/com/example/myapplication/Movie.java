package com.example.myapplication;

public class Movie {
    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private double vote_average;

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getOverview() { return overview; }
    public String getPosterPath() { return poster_path; }
    public double getVoteAverage() { return vote_average; }

    // Setters (необходимы для Gson)
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setOverview(String overview) { this.overview = overview; }
    public void setPosterPath(String poster_path) { this.poster_path = poster_path; }
    public void setVoteAverage(double vote_average) { this.vote_average = vote_average; }
}
