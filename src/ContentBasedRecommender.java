import java.util.*;

public class ContentBasedRecommender {

    Map<Integer, Movie> movies;
    Map<String, Integer> genreIndex;

    public ContentBasedRecommender(Map<Integer, Movie> movies) {
        this.movies = movies;
        genreIndex = new HashMap<>();

        int idx = 0;
        for (Movie m : movies.values()) {
            for (String g : m.genres) {
                if (!genreIndex.containsKey(g)) {
                    genreIndex.put(g, idx++);
                }
            }
        }
    }

    private double[] buildVector(Movie movie) {
        double[] vec = new double[genreIndex.size()];
        for (String g : movie.genres) {
            vec[genreIndex.get(g)] = 1;
        }
        return vec;
    }

    public List<Movie> recommend(Movie likedMovie) {
        List<Movie> result = new ArrayList<>();

        double[] base = buildVector(likedMovie);

        movies.values().forEach(m -> {
            if (m.id != likedMovie.id) {
                double score = Similarity.cosine(base, buildVector(m));
                if (score > 0.5) result.add(m);
            }
        });

        return result;
    }
}
