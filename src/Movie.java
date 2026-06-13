import java.util.List;

public class Movie {
    int id;
    String title;
    List<String> genres;

    public Movie(int id, String title, List<String> genres) {
        this.id = id;
        this.title = title;
        this.genres = genres;
    }
}

