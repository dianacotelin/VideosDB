package myclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Information about an user, retrieved from parsing the input test files
 */
public class User {
    /**
     * User's username
     */
    private final String username;
    /**
     * Subscription Type
     */
    private final String subscriptionType;
    /**
     * The history of the movies seen
     */
    private final Map<String, Integer> history;
    /**
     * Movies added to favorites
     */
    private final ArrayList<String> favoriteMovies;
    private Map<String, Double> ratedMovies = new HashMap<>();

    /**
     * The serials rated by the user and their grades
     */
    private Map<String, Double> ratedSerials = new HashMap<>();
    private Map<String, Double> ratedSeasons = new HashMap<>();


    public User(final String username, final String subscriptionType,
                         final Map<String, Integer> history,
                         final ArrayList<String> favoriteMovies) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.favoriteMovies = favoriteMovies;
        this.history = history;
    }
    /**
     * User's username
     */
    public String getUsername() {
        return username;
    }
    /**
     * The history of the movies seen
     */
    public Map<String, Integer> getHistory() {
        return history;
    }
    /**
     * Subscription Type
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }
    /**
     * Movies added to favorites
     */
    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }
    /**
     * Return user
     */
    @Override
    public String toString() {
        return "UserInputData{" + "username='"
                + username + '\'' + ", subscriptionType='"
                + subscriptionType + '\'' + ", history="
                + history + ", favoriteMovies="
                + favoriteMovies + '}';
    }
    /**
     * Movies added to rated
     */
    public Map<String, Double> getRatedMovies() {
        return ratedMovies;
    }
    /**
     * Serials added to rated
     */
    public Map<String, Double> getRatedSerials() {
        return ratedSerials;
    }
    /**
     * Seasons added to rated
     */
    public Map<String, Double> getRatedSeasons() {
        return ratedSeasons;
    }
}
