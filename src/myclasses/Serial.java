package myclasses;

import java.util.ArrayList;
/**
 * Information about a tv show, retrieved from parsing the input test files
 */
import entertainment.Season;

public final class Serial extends ShowInput {
    /**
     * Number of seasons
     */
    private final int numberOfSeasons;
    /**
     * Season list
     */
    private final ArrayList<Season> seasons;
    private int noViews = 0;

    //private Map<String, Double> ratings;

    public Serial(final String title, final ArrayList<String> cast,
                           final ArrayList<String> genres,
                           final int numberOfSeasons, final ArrayList<Season> seasons,
                           final int year) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    @Override
    public String toString() {
        return "SerialInputData{" + " title= "
                + super.getTitle() + " " + " year= "
                + super.getYear() + " cast {"
                + super.getCast() + " }\n" + " genres {"
                + super.getGenres() + " }\n "
                + " numberSeason= " + numberOfSeasons
                + ", seasons=" + seasons + "\n\n" + '}';
    }
    /**
     * Rating of a serial
     */
    public double averageSerial() {
        double sum = 0.0;
        for (Season season: seasons) {
            sum += season.rating();
        }
        return sum / numberOfSeasons;
    }
    public int getNoViews() {
        return noViews;
    }

    public void setNoViews(final int noViews) {
        this.noViews = noViews;
    }
}
