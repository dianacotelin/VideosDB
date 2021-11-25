package myclasses;

import actor.ActorsAwards;
import entertainment.Season;


import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {
    private  List<Actor> actors = new ArrayList<>();
    private  List<User> users = new ArrayList<>();
    private  List<Movie> movies = new ArrayList<>();
    private  List<Serial> serials = new ArrayList<>();
    private final Action action;


    public Database(final List<Actor> actors, final List<User> users,
                     final List<Movie> movies, final List<Serial> serials, final Action action) {
        this.actors = actors;
        this.serials = serials;
        this.movies = movies;
        this.users = users;
        this.action = action;
    }
    /**
     * Query average actor
     */
    public String averageActor() {
        HashMap<String, Double> actorsSort = new HashMap<>();
        String message = "Query result: [";
        HashMap<String, Integer> rating = new HashMap<>();


        for (Actor actor: actors) {
            ArrayList<String> films = actor.getFilmography();
            Double actorRating = 0.0;
            Integer count = 0;
            for (String film : films) {
                 for (Movie movie : movies) {
                     if (film.equals(movie.getTitle())) {
                         if (movie.getAverageRating() != 0) {
                             actorRating += movie.getAverageRating();
                             count++;
                         }

                     }
                 }
            }

            for (String film : films) {
                for (Serial serial : serials) {
                    if (film.equals(serial.getTitle())) {
                        if (serial.averageSerial() != 0) {
                            actorRating += serial.averageSerial();
                            count++;
                        }
                    }
                }
            }
            if (actorRating != 0.0) {
                actorRating = actorRating / count;
                actorsSort.put(actor.getName(), actorRating);
            }
        }
        HashMap<String, Double> actorsSorted;
        if (action.getSortType().equals("asc")) {
            actorsSorted = sortByValue1(actorsSort);
        } else {
            actorsSorted = sortByValue2(actorsSort);
        }

        Integer n = action.getNumber();
        Integer count = 0;
        for (String actorName : actorsSorted.keySet()) {
            message += actorName;
            count++;
            if (n == count) {
                break;
            }
            if (actorsSort.size() == count) {
                break;
            }
            message += ", ";
        }

        return message + "]";
    }
    /**
     * Sorting Hashmap
     * @param hm - hashmap to sort
     */
    public HashMap<String, Double> sortByValue1(final HashMap<String, Double> hm) {
        // Create a list for elements of HashMap
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(final Map.Entry<String, Double> o1,
                               final Map.Entry<String, Double> o2) {
                if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o1.getValue().compareTo(o2.getValue());
                }

            }
        });

        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa: list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    /**
     * Sorting Hashmap
     * @param hm - hashmap to sort
     */
    public HashMap<String, Double> sortByValue2(final HashMap<String, Double> hm) {
        // Create a list for elements of HashMap
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(final Map.Entry<String, Double> o1,
                               final Map.Entry<String, Double> o2) {
                if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    return o2.getKey().compareTo(o1.getKey());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }

            }
        });

        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa: list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    /**
     * Query actor's awards
     */
    public String awardActor() {
        String message = "Query result: [";
        List<String> listAwards = new ArrayList<>();
        Integer number;
        number = action.getFilters().size() - 1;
        listAwards = action.getFilters().get(number);
        HashMap<String, Double> actorsAwards = new HashMap<>();

        for (Actor actor: actors) {
            int received = 1;
            for (String award : listAwards) {
                if (!actor.getAwards().containsKey(ActorsAwards.valueOf(award))) {
                    received = 0;
                }
            }
            if (received == 1) {
                double noAwards = (double) actor.totalAwards();
                actorsAwards.put(actor.getName(), noAwards);
            }
        }
        HashMap<String, Double> actorsSorted;
        if (action.getSortType().equals("asc")) {
            actorsSorted = sortByValue1(actorsAwards);
        } else {
            actorsSorted = sortByValue2(actorsAwards);
        }
        //int n = actorsSorted.size();
        Integer count = 0;
        for (String actorName : actorsSorted.keySet()) {
            message += actorName;
            count++;
            if (actorsSorted.size() == count) {
                break;
            }
            message += ", ";
        }

        message += "]";
        return message;
    }
    /**
     * Query actors - filter
     */
    public String filterActor() {
        String message;
        message = "Query result: [";
        List<String> words = new ArrayList<>();
        words = action.getFilters().get(2);
        HashMap<String, Double> actorsDesc = new HashMap<>();
        for (Actor actor: actors) {
            int verify = 0;
            for (String word: words) {
                Pattern pattern = Pattern.compile("[^a-zA-Z]"
                        + word + "[^a-zA-Z]", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(actor.getCareerDescription());
                boolean match = matcher.find();
                if (!match) {
                    verify = 1;
                }
            }
            if (verify == 0) {
                actorsDesc.put(actor.getName(), 1.0);
            }
        }

        HashMap<String, Double> actorsSorted;
        if (action.getSortType().equals("asc")) {
            actorsSorted = sortByValue1(actorsDesc);
        } else {
            actorsSorted = sortByValue2(actorsDesc);
        }
        int n = actorsDesc.size();
        for (String actorName : actorsSorted.keySet()) {
            message += actorName;
            n--;
            if (n == 0) {
                break;
            }
            message += ", ";
        }

        message += "]";
        return message;
    }
    /**
     * Query video - rating
     */
    public String videoRating() {
        String message;
        message = "Query result: [";
        Integer year = 0;
        if (action.getFilters().get(0).get(0) != null) {
            year = Integer.parseInt(action.getFilters().get(0).get(0));
        }
        String genre;
        genre = action.getFilters().get(1).get(0);
        HashMap<String, Double> videos = new HashMap<>();
        if (action.getObjectType().equals("movies")) {
            for (Movie movie : movies) {
                if (movie.getAverageRating() != 0) {
                    if (((year == movie.getYear()) || (year == 0))
                            && (movie.getGenres().contains(genre))) {
                        videos.put(movie.getTitle(), movie.getAverageRating());
                    }
                }
            }
        } else {
            for (Serial serial : serials) {
                if (serial.averageSerial() != 0) {
                    if (((year == serial.getYear()) || (year == 0))
                            && (serial.getGenres().contains(genre))) {
                        videos.put(serial.getTitle(), serial.averageSerial());
                    }
                }
            }
        }
        HashMap<String, Double> videosSorted;
        if (action.getSortType().equals("asc")) {
            videosSorted = sortByValue1(videos);
        } else {
            videosSorted = sortByValue2(videos);
        }
        Integer n = action.getNumber();
        Integer count = 0;
        for (String videoName : videosSorted.keySet()) {
            message += videoName;
            count++;
            if (n == count) {
                break;
            }
            if (count == videosSorted.keySet().size()) {
                break;
            }
            message += ", ";
        }
        message += "]";
        return message;
    }
    /**
     * Query videos - favorite
     */
    public String videosFavorite() {
        String message;
        message = "Query result: [";
        Integer year = 0;
        if (action.getFilters().get(0).get(0) != null) {
            year = Integer.parseInt(action.getFilters().get(0).get(0));
        }
        String genre;
        genre = action.getFilters().get(1).get(0);
        HashMap<String, Double> videosFav = new HashMap<>();
        if (action.getObjectType().equals("movies")) {
            for (Movie movie : movies) {
                double views = 0.0;
                for (User user : users) {
                    if (user.getFavoriteMovies().contains(movie.getTitle())) {
                        views++;
                    }
                }
                if (((year == movie.getYear()) || (year == 0))
                        && (movie.getGenres().contains(genre) || (genre == null))) {
                    if (views != 0.0) {
                        videosFav.put(movie.getTitle(), views);
                    }
                }
            }
        } else {
            for (Serial serial : serials) {
                double views = 0.0;
                for (User user : users) {
                    if (user.getFavoriteMovies().contains(serial.getTitle())) {
                        views++;
                    }
                }
                if (((year == serial.getYear()) || (year == 0))
                        && (serial.getGenres().contains(genre))) {
                    if (views != 0.0) {
                        videosFav.put(serial.getTitle(), views);
                    }
                }

            }
        }
        HashMap<String, Double> videosSorted;
        if (action.getSortType().equals("asc")) {
            videosSorted = sortByValue1(videosFav);
        } else {
            videosSorted = sortByValue2(videosFav);
        }
        Integer n = action.getNumber();
        Integer count = 0;
        for (String videoName : videosSorted.keySet()) {
            message += videoName;
            count++;
            if (n == count) {
                break;
            }
            if (videosSorted.size() == count) {
                break;
            }
            message += ", ";
        }
        message += "]";
        return message;
    }
    /**
     * Query video longest
     */
    public String videosLongest() {
        String message;
        message = "Query result: [";
        Integer year = 0;
        if (action.getFilters().get(0).get(0) != null) {
            year = Integer.parseInt(action.getFilters().get(0).get(0));
        }
        String genre;
        genre = action.getFilters().get(1).get(0);
        HashMap<String, Double> videosLong = new HashMap<>();

        if (action.getObjectType().equals("movies")) {
            for (Movie movie : movies) {
                double duration;
                duration = (double) movie.getDuration();
                if (((year == movie.getYear()) || (year == 0))
                        && (movie.getGenres().contains(genre)
                        || (genre == null))) {
                    videosLong.put(movie.getTitle(), duration);
                }
            }

        } else {
            for (Serial serial : serials) {
                double duration = 0.0;
                for (Season season : serial.getSeasons()) {
                    duration += (double) season.getDuration();
                }
                if (((year == serial.getYear()) || (year == 0))
                        && (serial.getGenres().contains(genre) || (genre == null))) {
                    videosLong.put(serial.getTitle(), duration);
                }
            }
        }
        HashMap<String, Double> videosSorted;
        if (action.getSortType().equals("asc")) {
            videosSorted = sortByValue1(videosLong);
        } else {
            videosSorted = sortByValue2(videosLong);
        }
        Integer n = action.getNumber();
        Integer count = 0;
        for (String videoName : videosSorted.keySet()) {
            message += videoName;
            count++;
            if (n == count) {
                break;
            }
            if (videosSorted.size() == count) {
                break;
            }
            message += ", ";
        }

        message += "]";
        return message;
    }
    /**
     * Query video - most viewed
     */
    public String videosMostV() {
        String message;
        message = "Query result: [";
        Integer year = 0;
        if (action.getFilters().get(0).get(0) != null) {
            year = Integer.parseInt(action.getFilters().get(0).get(0));
        }
        String genre;
        genre = action.getFilters().get(1).get(0);
        HashMap<String, Double> videosMv = new HashMap<>();
        if (action.getObjectType().equals("movies")) {
            for (Movie movie : movies) {
                double views = 0.0;
                for (User user : users) {
                    if (user.getHistory().get(movie.getTitle()) != null) {
                        views += (double) user.getHistory().get(movie.getTitle());
                    }
                }
                if (((year == movie.getYear()) || (year == 0))
                        && (movie.getGenres().contains(genre) || (genre == null))) {
                    if (views != 0.0) {
                        videosMv.put(movie.getTitle(), views);
                    }
                }
            }
        } else {
            for (Serial serial : serials) {
                double views = 0.0;
                for (User user : users) {
                    if (user.getHistory().get(serial.getTitle()) != null) {
                        views += (double) user.getHistory().get(serial.getTitle());
                    }
                }
                if (((year == serial.getYear()) || (year == 0))
                        && (serial.getGenres().contains(genre))) {
                    if (views != 0.0) {
                        videosMv.put(serial.getTitle(), views);
                    }
                }

            }
        }
        HashMap<String, Double> videosSorted;
        if (action.getSortType().equals("asc")) {
            videosSorted = sortByValue1(videosMv);
        } else {
            videosSorted = sortByValue2(videosMv);
        }
        Integer n = action.getNumber();
        Integer count = 0;
        for (String videoName : videosSorted.keySet()) {
            message += videoName;
            count++;
            if (n == count) {
                break;
            }
            if (videosSorted.size() == count) {
                break;
            }
            message += ", ";
        }
        message += "]";
        return message;
    }
    /**
     * Query user ratings
     */
    public String userRatings() {
        String message;
        message = "Query result: [";
        HashMap<String, Double> usersNoR = new HashMap<>();
        for (User user : users) {
            double no = 0.0;
            if (user.getRatedMovies() != null) {
                no += (double) user.getRatedMovies().size();
            }
            if (user.getRatedSerials() != null) {
                no += (double) user.getRatedSerials().size();
            }
            if (no != 0.0) {
                usersNoR.put(user.getUsername(), no);
            }
        }

        HashMap<String, Double> usersSorted;
        if (action.getSortType().equals("asc")) {
            usersSorted = sortByValue1(usersNoR);
        } else {
            usersSorted = sortByValue2(usersNoR);
        }
        Integer n = action.getNumber();
        Integer count = 0;
        Integer size = usersSorted.size();
        for (String userName : usersSorted.keySet()) {
            message += userName;
            count++;
            if (count == n) {
                break;
            }
            if (size == count) {
                break;
            }
            message += ", ";
        }

        message += "]";
        return message;
    }
    /**
     * Recommendation standard
     */
    public String recStandard() {
        String message;
        message = "StandardRecommendation ";
        String recVideo = null;

        for (User user : users) {
            if (user.getUsername().equals(action.getUsername())) {
                for (Movie movie : movies) {
                    if (!user.getHistory().containsKey(movie.getTitle())) {
                        recVideo = movie.getTitle();
                        break;
                    }
                }

                if (recVideo == null) {
                    for (Serial serial : serials) {
                        if (!user.getHistory().containsKey(serial.getTitle())) {
                            recVideo = serial.getTitle();
                            break;
                        }
                    }
                }
            }
        }

        if (recVideo == null) {
            message += "cannot be applied!";
        } else {
            message += "result: " + recVideo;
        }

        return message;
    }
    /**
     * Recommendation best
     */
    public String recBest() {
        String message;
        Double rating = -1.0;
        message = "BestRatedUnseenRecommendation ";
        String video = null;

        for (User user : users) {
            if (user.getUsername().equals(action.getUsername())) {
                for (Movie movie : movies) {
                    if (!user.getHistory().containsKey(movie.getTitle())) {
                        if (movie.getAverageRating() > rating) {
                            rating = movie.getAverageRating();
                            video = movie.getTitle();
                        }
                    }
                }
                for (Serial serial : serials) {
                    if (!user.getHistory().containsKey(serial.getTitle())) {
                        if (serial.averageSerial() > rating) {
                            rating = serial.averageSerial();
                            video = serial.getTitle();
                        }
                    }
                }
            }
        }

        if (video == null) {
            message += "cannot be applied!";
        } else {
            message += "result: " + video;
        }

        return message;
    }
    /**
     * Recommendation popular
     */
    public String recPop() {
        String message;
        message = "PopularRecommendation ";
        String video;
        video = null;
        HashMap<String, Double> genreMap = new HashMap<>();
        boolean verif = true;

        for (User user: users) {
            if (user.getUsername().equals(action.getUsername())) {
                if (!user.getSubscriptionType().equals("PREMIUM")) {
                    verif = false;
                    break;
                }
            } else {
                for (Movie movie : movies) {
                    Integer noViews = 0;
                    if (user.getHistory().containsKey(movie.getTitle())) {
                        noViews = user.getHistory().get(movie.getTitle());
                        movie.setNoViews(movie.getNoViews() + noViews);
                        for (String genre : movie.getGenres()) {
                            if (genreMap.containsKey(genre)) {
                                genreMap.put(genre, genreMap.get(genre)
                                        + (double) movie.getNoViews());
                            } else {
                                genreMap.put(genre, (double) movie.getNoViews());
                            }
                        }
                    }
                }
                for (Serial serial : serials) {
                    Integer noViews = 0;
                    if (user.getHistory().containsKey(serial.getTitle())) {
                        noViews = user.getHistory().get(serial.getTitle());
                        serial.setNoViews(serial.getNoViews() + noViews);
                        for (String genre : serial.getGenres()) {
                            if (genreMap.containsKey(genre)) {
                                genreMap.put(genre, genreMap.get(genre)
                                        + (double) serial.getNoViews());
                            } else {
                                genreMap.put(genre, (double) serial.getNoViews());
                            }
                        }
                    }
                }
            }

        }
        HashMap<String, Double> genreSorted;
        genreSorted = sortByValue2(genreMap);

        if (!verif) {
            message += "cannot be applied!";
        } else {
            for (User user: users) {
                if (user.getUsername().equals(action.getUsername())) {
                    for (String genre : genreSorted.keySet()) {
                        for (Movie movie : movies) {
                            if (!user.getHistory().containsKey(movie.getTitle())) {
                                if (movie.getGenres().contains(genre)) {
                                    video = movie.getTitle();
                                    break;
                                }
                            }
                        }
                         if (video == null) {
                             for (Serial serial : serials) {
                                 if (!user.getHistory().containsKey(serial.getTitle())) {
                                     if (serial.getGenres().contains(genre)) {
                                         video = serial.getTitle();
                                         break;
                                     }
                                 }
                             }
                         }
                         if (video != null) {
                             break;
                         }
                    }
                }
            }
            if (video == null) {
                message += "cannot be applied!";
            } else {
                message += "result: " + video;
            }
        }


        return message;
    }
    /**
     * Sorting Hashmap
     * @param hm - hashmap to sort
     */
    public HashMap<String, Double> sortByValue3(final HashMap<String, Double> hm) {
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(final Map.Entry<String, Double> o1,
                               final Map.Entry<String, Double> o2) {
                    return o2.getValue().compareTo(o1.getValue());
            }
        });

        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa: list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    /**
     * Recommendation favorite
     */
    public String recFav() {
        String message;
        message = "FavoriteRecommendation ";
        String video;
        video = null;
        HashMap<String, Double> favMap = new HashMap<>();
        boolean verif = true;

        for (User user: users) {
            if (user.getUsername().equals(action.getUsername())) {
                if (!user.getSubscriptionType().equals("PREMIUM")) {
                    verif = false;
                    break;
                }
            }
            for (Movie movie : movies) {
                if (user.getFavoriteMovies().contains(movie.getTitle())) {
                    if (favMap.containsKey(movie.getTitle())) {
                        double value;
                        value = favMap.get(movie.getTitle());
                        value++;
                        favMap.put(movie.getTitle(), value);
                    } else {
                        favMap.put(movie.getTitle(), 1.0);
                    }
                }
            }
            for (Serial serial : serials) {
                if (user.getFavoriteMovies().contains(serial.getTitle())) {
                    if (favMap.containsKey(serial.getTitle())) {
                        double value;
                        value = favMap.get(serial.getTitle());
                        value++;
                        favMap.put(serial.getTitle(), value);
                    } else {
                        favMap.put(serial.getTitle(), 1.0);
                    }
                }
            }
        }
        HashMap<String, Double> favSorted;
        favSorted = sortByValue3(favMap);

        if (!verif) {
            message += "cannot be applied!";
        } else {
            for (User user : users) {
                if (user.getUsername().equals(action.getUsername())) {
                    for (String favorite : favSorted.keySet()) {
                        for (Movie movie : movies) {
                            if (!user.getHistory().containsKey(movie.getTitle())) {
                                if (movie.getTitle().equals(favorite)) {
                                    video = movie.getTitle();
                                    break;
                                }
                            }
                        }
                        if (video == null) {
                            for (Serial serial : serials) {
                                if (!user.getHistory().containsKey(serial.getTitle())) {
                                    if (serial.getTitle().equals(favorite)) {
                                        video = serial.getTitle();
                                        break;
                                    }
                                }
                            }
                        }
                        if (video != null) {
                            break;
                        }
                    }
                }
            }
            if (video == null) {
                message += "cannot be applied!";
            } else {
                message += "result: " + video;
            }
        }

        return message;
    }
    /**
     * Recommendation search
     */
    public String recSe() {
        String message;
        message = "SearchRecommendation ";
        HashMap<String, Double> searchMap = new HashMap<>();

        for (User user : users) {
            if (user.getUsername().equals(action.getUsername())) {
                for (Movie movie : movies) {
                    if (!user.getHistory().containsKey(movie.getTitle())) {
                        for (String genre: movie.getGenres()) {
                            if (genre.equals(action.getGenre())) {
                                searchMap.put(movie.getTitle(), movie.getAverageRating());
                            }
                        }
                    }
                }
                for (Serial serial : serials) {
                    if (!user.getHistory().containsKey(serial.getTitle())) {
                        for (String genre: serial.getGenres()) {
                            if (genre.equals(action.getGenre())) {
                                searchMap.put(serial.getTitle(), serial.averageSerial());
                            }
                        }
                    }
                }
            }
        }
        if (searchMap.size() != 0) {
            HashMap<String, Double> searchSorted;
            searchSorted = sortByValue1(searchMap);
            Integer count = 0;
            message += "result: [";
            for (String videoName : searchSorted.keySet()) {
                message += videoName;
                count++;

                if (searchSorted.size() == count) {
                    break;
                }
                message += ", ";
            }
            message += "]";
        } else {
            message += "cannot be applied!";
        }

        return message;
    }

}
