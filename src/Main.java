import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Load data
        Map<Integer, Movie> movies = DataLoader.loadMovies();
        Map<Integer, Map<Integer, Integer>> ratings = DataLoader.loadRatings();

        // Create recommenders ONCE
        ContentBasedRecommender cb =
                new ContentBasedRecommender(movies);

        CollaborativeRecommender cf =
                new CollaborativeRecommender(ratings);

        HybridRecommender hybrid =
                new HybridRecommender(cb, cf);

        // -------- Content-Based --------
        System.out.println("Content-based recommendations for Inception:");
        for (Movie m : cb.recommend(movies.get(1))) {
            System.out.println(m.title);
        }

        // -------- Collaborative --------
        System.out.println("\nCollaborative recommendations for User 1:");
        for (int id : cf.recommend(1)) {
            System.out.println(movies.get(id).title);
        }

        // -------- Hybrid --------
        System.out.println("\nHybrid recommendations for User 1:");
        for (Movie m : hybrid.recommend(movies.get(1), 1)) {
            System.out.println(m.title);
        }
    }
}
