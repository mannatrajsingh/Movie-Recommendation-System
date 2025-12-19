import java.util.*;

public class DataLoader {

    public static Map<Integer, Movie> loadMovies() {
        Map<Integer, Movie> movies = new HashMap<>();

        movies.put(1, new Movie(1, "Inception", List.of("Sci-Fi", "Action")));
        movies.put(2, new Movie(2, "Interstellar", List.of("Sci-Fi", "Drama")));
        movies.put(3, new Movie(3, "Titanic", List.of("Romance", "Drama")));
        movies.put(4, new Movie(4, "The Matrix", List.of("Sci-Fi", "Action")));
        movies.put(5, new Movie(5, "Notebook", List.of("Romance")));
        movies.put(6, new Movie(6, "John Wick", List.of("Action")));
        movies.put(7, new Movie(7, "La La Land", List.of("Romance", "Drama")));

        return movies;
    }

    public static Map<Integer, Map<Integer, Integer>> loadRatings() {
        Map<Integer, Map<Integer, Integer>> ratings = new HashMap<>();

        ratings.put(1, Map.of(1,5, 2,4, 3,1));
        ratings.put(2, Map.of(1,5, 4,5, 6,4));
        ratings.put(3, Map.of(3,5, 5,5, 7,4));
        ratings.put(4, Map.of(2,5, 7,4));

        return ratings;
    }
}
