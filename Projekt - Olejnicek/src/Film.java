import java.util.ArrayList;

public class Film {
    private String name;
    private String director;
    private int year;



    public Film(String name, String director, int year) {
        this.name = name;
        this.director = director;
        this.year = year;

    }

    public String getName(){return name;}
    public String getDirector(){return director;}
    public int getYear(){return year;}

    public void setName(String name){
        this.name=name;
    }
    public void setDirector(String director){
        this.director=director;
    }
    public void setYear(int year){
        this.year=year;
    }







    // Gettery a Settery pro title, director a year
}
