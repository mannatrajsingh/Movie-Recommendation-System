import java.util.*;

public class HybridRecommender {

    ContentBasedRecommender contentRec;
    CollaborativeRecommender collabRec;

    public HybridRecommender(ContentBasedRecommender contentRec,
                             CollaborativeRecommender collabRec) {
        this.contentRec = contentRec;
        this.collabRec = collabRec;
    }

    public Set<Movie> recommend(Movie likedMovie, int userId) {

        Set<Movie> result = new HashSet<>();

        // Content-based recommendations
        List<Movie> contentBased =
                contentRec.recommend(likedMovie);

        result.addAll(contentBased);

        // Collaborative recommendations
        Set<Integer> collabMovieIds =
                collabRec.recommend(userId);

        for (int id : collabMovieIds) {
            result.add(contentRec.movies.get(id));
        }

        return result;
    }
}
