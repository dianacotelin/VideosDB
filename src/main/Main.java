package main;

import common.Constants;
import checker.Checker;
import checker.Checkstyle;

import org.json.simple.JSONArray;
import myclasses.Serial;
import myclasses.Movie;
import myclasses.User;
import myclasses.Action;
import myclasses.Actor;
import myclasses.Input;
import myclasses.Database;
import myclasses.InputLoader;
import myclasses.Writer;
import commands.Command;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //TODO add here the entry point to your implementation
        List<Action> actions = input.getCommands();
        List<Actor> actors = input.getActors();
        List<User> users = input.getUsers();
        List<Movie> movies = input.getMovies();
        List<Serial> serials = input.getSerials();

        for (Action action : actions) {
            if (action.getActionType().equals("command")) {
                Command command = new Command(users, movies, serials, action);
                if (action.getType().equals("favorite")) {
                    String message = command.favorites();
                    JSONObject obj = new JSONObject();
                    obj = fileWriter.writeFile(action.getActionId(), "", message);
                    arrayResult.add(obj);
                }
                if (action.getType().equals("view")) {
                    String message = command.view();
                    JSONObject obj = new JSONObject();
                    obj = fileWriter.writeFile(action.getActionId(), "", message);
                    arrayResult.add(obj);
                }
                if (action.getType().equals("rating")) {
                    String message = command.rating();
                    JSONObject obj = new JSONObject();
                    obj = fileWriter.writeFile(action.getActionId(), "", message);
                    arrayResult.add(obj);
                }
            }
            Database database = new Database(actors, users, movies, serials, action);
            if (action.getActionType().equals("query")) {
                if (action.getObjectType().equals("actors")) {
                    if (action.getCriteria().equals("average")) {
                        String message = database.averageActor();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }
                    if (action.getCriteria().equals("awards")) {
                        String message = database.awardActor();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }
                    if (action.getCriteria().equals("filter_description")) {
                        String message = database.filterActor();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }
                }
                if (action.getCriteria().equals("ratings")) {
                    if (action.getObjectType().equals("movies")) {
                        String message = database.videoRating();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }

                    if (action.getObjectType().equals("shows")) {
                        String message = database.videoRating();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }
                }
                if (action.getCriteria().equals("favorite")) {
                    if (action.getObjectType().equals("movies")) {
                        String message = database.videosFavorite();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }

                    if (action.getObjectType().equals("shows")) {
                        String message = database.videosFavorite();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }
                }
                if (action.getCriteria().equals("longest")) {
                    if (action.getObjectType().equals("movies")) {
                        String message = database.videosLongest();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }

                    if (action.getObjectType().equals("shows")) {
                        String message = database.videosLongest();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }
                }
                if (action.getCriteria().equals("most_viewed")) {
                    if (action.getObjectType().equals("movies")) {
                        String message = database.videosMostV();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }

                    if (action.getObjectType().equals("shows")) {
                        String message = database.videosMostV();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }
                }
                if (action.getObjectType().equals("users")) {
                    if (action.getCriteria().equals("num_ratings")) {
                        String message = database.userRatings();
                        JSONObject obj = new JSONObject();
                        obj = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(obj);
                    }
                }

            }
            if (action.getActionType().equals("recommendation")) {
                if (action.getType().equals("standard")) {
                    String message = database.recStandard();
                    JSONObject obj = new JSONObject();
                    obj = fileWriter.writeFile(action.getActionId(), "", message);
                    arrayResult.add(obj);
                }
                if (action.getType().equals("best_unseen")) {
                    String message = database.recBest();
                    JSONObject obj = new JSONObject();
                    obj = fileWriter.writeFile(action.getActionId(), "", message);
                    arrayResult.add(obj);
                }
                if (action.getType().equals("popular")) {
                    String message = database.recPop();
                    JSONObject obj = new JSONObject();
                    obj = fileWriter.writeFile(action.getActionId(), "", message);
                    arrayResult.add(obj);
                }
                if (action.getType().equals("favorite")) {
                    String message = database.recFav();
                    JSONObject obj = new JSONObject();
                    obj = fileWriter.writeFile(action.getActionId(), "", message);
                    arrayResult.add(obj);
                }
                if (action.getType().equals("search")) {
                    String message = database.recSe();
                    JSONObject obj = new JSONObject();
                    obj = fileWriter.writeFile(action.getActionId(), "", message);
                    arrayResult.add(obj);
                }

            }

        }
        fileWriter.closeJSON(arrayResult);
    }
}
