import java.util.*;

public class CollaborativeRecommender {

    Map<Integer, Map<Integer, Integer>> userRatings;

    public CollaborativeRecommender(Map<Integer, Map<Integer, Integer>> userRatings) {
        this.userRatings = userRatings;
    }

    private double similarity(int u1, int u2) {
        Map<Integer, Integer> r1 = userRatings.get(u1);
        Map<Integer, Integer> r2 = userRatings.get(u2);

        Set<Integer> common = new HashSet<>(r1.keySet());
        common.retainAll(r2.keySet());

        if (common.isEmpty()) return 0;

        double dot = 0, a = 0, b = 0;
        for (int m : common) {
            dot += r1.get(m) * r2.get(m);
            a += r1.get(m) * r1.get(m);
            b += r2.get(m) * r2.get(m);
        }
        return dot / (Math.sqrt(a) * Math.sqrt(b));
    }

    public Set<Integer> recommend(int userId) {
        Set<Integer> recs = new HashSet<>();
        Map<Integer, Integer> user = userRatings.get(userId);

        for (int other : userRatings.keySet()) {
            if (other == userId) continue;
            if (similarity(userId, other) > 0.5) {
                for (int movie : userRatings.get(other).keySet()) {
                    if (!user.containsKey(movie)) {
                        recs.add(movie);
                    }
                }
            }
        }
        return recs;
    }
}
