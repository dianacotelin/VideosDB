package myclasses;

import java.util.ArrayList;
import java.util.List;
/**
 * Information about a movie, retrieved from parsing the input test files
 */
public final class Movie extends ShowInput {
    /**
     * Duration in minutes of a season
     */
    private final int duration;
    /**
     * number of views
     */
    private int noViews = 0;
    private List<Double> rating = new ArrayList<>();

    public Movie(final String title, final ArrayList<String> cast,
                          final ArrayList<String> genres, final int year,
                          final int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }

    public List<Double> getRating() {
        return rating;
    }
    /**
     * Average Rating of a movie
     */
    public double getAverageRating() {
        double avgRating = 0.0;
        for (Double rat : rating) {
            avgRating += rat;
        }
        if (rating.size() == 0.0) {
            return 0.0;
        }
        avgRating = avgRating / rating.size();
        return avgRating;
    }
    public void setRating(List<Double> ratings) {
        this.rating = ratings;
    }
    public int getNoViews() {
        return noViews;
    }

    public void setNoViews(int noViews) {
        this.noViews = noViews;
    }
}
