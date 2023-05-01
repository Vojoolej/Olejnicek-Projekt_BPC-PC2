import java.util.ArrayList;
import java.util.Calendar;

public class Movie extends Film {
    private ArrayList<String> actors;
    private ArrayList<Integer> ratings;

    public Movie(String name, String director, int year, ArrayList<String> actors, ArrayList<Integer> ratings) {
        super(name, director, year);
        this.actors = actors;
        this.ratings = ratings;
    }

    public ArrayList<String> getActors() {
        return actors;
    }
    public ArrayList<Integer> getRatings(){return ratings;}

    public void setActors(ArrayList<String> actors){
        this.actors=actors;
    }
    public void setRatings(ArrayList<Integer> ratings){this.ratings=ratings;}





    //gettery a settery
}