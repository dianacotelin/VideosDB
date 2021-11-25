package commands;

import myclasses.Movie;
import myclasses.Serial;
import myclasses.User;

import java.util.ArrayList;
import java.util.List;
import entertainment.Season;
import myclasses.Action;

public class Command {
    private final List<User> users;
    private final List<Movie> movies;
    private final List<Serial> serials;
    private Action action;
    private ArrayList<Season> seasons;

    public Command(List<User> users, List<Movie> movies, List<Serial> serials, Action action) {
        this.users = users;
        this.movies = movies;
        this.action = action;
        this.serials = serials;
    }
    /**
     * This method adds a show to the list of favorite
     * of a given user
     */
    public String favorites() {

        String message = "";
        for (User user : users) {

            if (user.getUsername().equals(action.getUsername())) {
                if (user.getHistory().get(action.getTitle()) != null) {
                    if (user.getFavoriteMovies().contains(action.getTitle())) {
                        message = "error -> " + action.getTitle() + " is already in favourite list";
                    } else {
                        user.getFavoriteMovies().add(action.getTitle());
                        message = "success -> " + action.getTitle() + " was added as favourite";
                    }
                    break;
                } else {
                    message = "error -> " + action.getTitle() + " is not seen";
                }
            }
        }
        return message;
    }
    /**
     * This method adds a show to the history
     * of a given user
     */
    public String view() {

        String message = "";
        int noViews = 1;
        for (User user : users) {

            if (user.getUsername().equals(action.getUsername())) {
                if (user.getHistory().get(action.getTitle()) != null) {
                    noViews = user.getHistory().get(action.getTitle());
                    noViews++;
                    user.getHistory().put(action.getTitle(), noViews);
                } else {
                    user.getHistory().put(action.getTitle(), noViews);
                }
                    message = "success -> " + action.getTitle()
                            + " was viewed with total views of " + noViews;
            }
        }
        return message;
    }
    /**
     * This method adds a show to the list of rated movies
     * of a given user
     */
    public String rating() {
        String message = "";

        for (User user : users) {

            if (user.getUsername().equals(action.getUsername())) {
                if (user.getHistory().get(action.getTitle()) != null) {

                    for (Movie movie : movies) {
                        if (movie.getTitle().equals(action.getTitle())) {
                            if (user.getRatedMovies().get(movie.getTitle()) != null) {
                                message = "error -> " + movie.getTitle()
                                        + " has been already rated";
                                break;
                            } else {
                                movie.getRating().add(action.getGrade());
                                user.getRatedMovies().put(movie.getTitle(), action.getGrade());
                                message = "success -> " + action.getTitle()
                                        + " was rated with " + action.getGrade() + " by "
                                        + action.getUsername();
                            }
                        }
                    }

                    for (Serial serial : serials) {
                        if (serial.getTitle().equals(action.getTitle())) {
                            for (Season season : serial.getSeasons()) {
                                if (action.getSeasonNumber() == season.getCurrentSeason()) {
                                    if (season.getRatedByUsers().contains(user.getUsername())) {
                                        message = "error -> " + serial.getTitle()
                                                + " has been already rated";
                                    } else {
                                        season.getRatedByUsers().add(user.getUsername());
                                        season.getRatings().add(action.getGrade());
                                        double noGrades = 0.0;
                                        if (user.getRatedSerials().get(serial.getTitle())
                                                != null) {
                                            String title;
                                            title = serial.getTitle();
                                            noGrades = user.getRatedSerials().get(title);
                                        }
                                        noGrades++;
                                        user.getRatedSerials().put(serial.getTitle(), noGrades);
                                        message = "success -> " + action.getTitle()
                                                + " was rated with " + action.getGrade() + " by "
                                                + action.getUsername();
                                    }
                                }
                            }
                        }
                    }

                } else {
                    message = "error -> " + action.getTitle() + " is not seen";
                }
            }


        }

        return message;
    }
}
