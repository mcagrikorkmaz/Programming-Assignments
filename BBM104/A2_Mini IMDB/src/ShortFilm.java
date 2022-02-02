import java.util.*;
import java.util.stream.Collectors;

public class ShortFilm extends Films {
    private final String date;
    private final String writers;
    private final String genre;
    private static LinkedHashMap<String, ShortFilm> shortFilmMap = new LinkedHashMap<>();//key = filmID, value = ShortFilm object
    private static LinkedHashMap<String, Double> sortedByPoint = new LinkedHashMap<>();//key = filmID, value = rating
    private final String directorNames;
    private final String starsNames;
    private final String writerNames;
    private final String year;

    private ShortFilm(String uniqueID, String title, String language, String directors, int runtime, String country,
                      String performers, String genre, String date, String writers) {
        super(uniqueID, title, language, directors, runtime, country, performers);
        ArrayList<String> separateDate = new ArrayList<>(Arrays.asList(date.split("\\.")));
        this.year = "(" + separateDate.get(separateDate.size() - 1) + ")";
        this.date = date;
        this.writers = writers;
        this.genre = String.join(", ", Arrays.asList(genre.split(",")));
        this.directorNames = super.artistNames(Films.artistIDList(directors));
        this.starsNames = super.artistNames(Films.artistIDList(performers));
        this.writerNames = super.artistNames(Films.artistIDList(writers));
        ShortFilm.sortedByPoint.put(uniqueID, 0.0);// to add a film to the sortedByPoint map.
        addFilms(uniqueID,title,language,directors,runtime,country,performers);//to create a film object
    }
    //This method creates a new ShortFilm object and puts it to the ShortFilm as a value.
    public static void addShortFilm(String uniqueID, String title, String language, String directors, int runtime,
                                    String country, String performers, String genre, String date, String writers) {
        ShortFilm.shortFilmMap.put(uniqueID, new ShortFilm(uniqueID, title, language, directors, runtime,
                country, performers, genre, date, writers));
    }
    //rate,edit,remove and rateChange methods are inherited from the Films class.
    @Override
    public void rate(String userID, Films film, int point) {
        super.rate(userID, film, point);
    }

    @Override
    public void edit(String userID, Films film, int newPoint) {
        super.edit(userID, film, newPoint);
    }

    @Override
    public void remove(String userID, Films film) {
        super.remove(userID, film);
    }

    @Override
    public void rateChange(Films film) {
        super.rateChange(film);
        ShortFilm.sortedByPoint.put(film.getUniqueID(), film.getRating());//updating rating to sort correctly
    }

    public static LinkedHashMap<String, ShortFilm> getShortFilmMap() {
        return shortFilmMap;
    }

    public static LinkedHashMap<String, Double> getSortedByPoint() {
        return sortedByPoint;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirectorNames() {
        return directorNames;
    }

    public String getStarsNames() {
        return starsNames;
    }

    public String getWriterNames() {
        return writerNames;
    }

    public String getYear() {
        return year;
    }
    //this method is used for sorting the films by their points in descending order.
    public static void sorting(){
        ShortFilm.sortedByPoint = ShortFilm.sortedByPoint.entrySet().stream().sorted
                (Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap
                (Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}