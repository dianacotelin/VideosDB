package myclasses;

import java.util.List;

public final class Input {
    /**
     * List of actors
     */
    private final List<Actor> actorsData;
    /**
     * List of users
     */
    private final List<User> usersData;
    /**
     * List of commands
     */
    private final List<Action> commandsData;
    /**
     * List of movies
     */
    private final List<Movie> moviesData;
    /**
     * List of serials aka tv shows
     */
    private final List<Serial> serialsData;

    public Input() {
        this.actorsData = null;
        this.usersData = null;
        this.commandsData = null;
        this.moviesData = null;
        this.serialsData = null;
    }

    public Input(final List<Actor> actors, final List<User> users,
                 final List<Action> commands,
                 final List<Movie> movies,
                 final List<Serial> serials) {
        this.actorsData = actors;
        this.usersData = users;
        this.commandsData = commands;
        this.moviesData = movies;
        this.serialsData = serials;
    }

    public List<Actor> getActors() {
        return actorsData;
    }

    public List<User> getUsers() {
        return usersData;
    }

    public List<Action> getCommands() {
        return commandsData;
    }

    public List<Movie> getMovies() {
        return moviesData;
    }

    public List<Serial> getSerials() {
        return serialsData;
    }
}
