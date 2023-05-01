import java.util.ArrayList;

public class Cartoon extends Film {
    private ArrayList<String> animators;

    private int recommendedAge;
    private ArrayList<Integer> ratings;

    public Cartoon(String name, String director, int year, ArrayList<String> animators, ArrayList<Integer> ratings, int recommendedAge) {
        super(name, director, year);
        this.animators = animators;
        this.ratings = ratings;
        this.recommendedAge = recommendedAge;
    }

    public ArrayList<String> getAnimators(){return animators;}
    public ArrayList<Integer> getRatings(){return ratings;}
    public int getRecommendedAge(){return recommendedAge;}

    public void setAnimators(ArrayList<String> animators){
        this.animators=animators;
    }
    public void setRatings(ArrayList<Integer> ratings){this.ratings=ratings;}
    public void setRecommendedAge(int recommendedAge){this.recommendedAge=recommendedAge;}



}