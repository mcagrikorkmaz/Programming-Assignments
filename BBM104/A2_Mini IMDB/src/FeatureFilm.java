import java.util.*;
import java.util.stream.Collectors;

public class FeatureFilm extends Films {
    private final String date;
    private final String budget;
    private final String writers;
    private final String genre;
    private static LinkedHashMap<String, FeatureFilm> featureFilmMap = new LinkedHashMap<>();//key = filmID, value = ShortFilm object
    private static LinkedHashMap<String, Double> sortedByPoint = new LinkedHashMap<>();//key = filmID, value = rating
    private final String directorNames;
    private final String starsNames;
    private final String writerNames;
    private final int compareDate;
    private final String year;

    private FeatureFilm(String uniqueID, String title, String language, String directors, int runtime, String country,
                        String performers, String genre, String date, String writers, String budget) {
        super(uniqueID, title, language, directors, runtime, country, performers);
        ArrayList<String> separateDate = new ArrayList<>(Arrays.asList(date.split("\\.")));
        this.date = date;
        this.year = "("+separateDate.get(separateDate.size() - 1)+")";
        this.compareDate = Integer.parseInt(separateDate.get(separateDate.size() - 1));
        this.budget = budget;
        this.writers = writers;
        this.genre = String.join(", ", Arrays.asList(genre.split(",")));
        this.directorNames = super.artistNames(Films.artistIDList(directors));
        this.starsNames = super.artistNames(Films.artistIDList(performers));
        this.writerNames = super.artistNames(Films.artistIDList(writers));
        FeatureFilm.sortedByPoint.put(uniqueID, 0.0);// to add a film to the sortedByPoint map.
        addFilms(uniqueID,title,language,directors,runtime,country,performers);//to create a film object
    }

    //This method creates a new FeatureFilm object and puts it to the featureFilmMap as a value.
    public static void addFeatureFilm(String uniqueID, String title, String language, String directors, int runtime, String country,
                                      String performers, String genre, String date, String writers, String budget) {
        FeatureFilm.featureFilmMap.put(uniqueID, new FeatureFilm(uniqueID, title, language, directors, runtime,
                country, performers, genre, date, writers, budget));
    }

    //This method is used for adding a new FeatureFilm command. It controls the validation of the commands arguments.
    //If arguments are valid, method returns "successful" and adds a new FeatureFilm.If not, it returns "failed".
    public static String addMethod(String uniqueID, String title, String language, String directors, int runtime, String country,
                                   String performers, String genre, String date, String writers, String budget) {
        ArrayList<String> control = new ArrayList<>(Films.artistIDList(directors));
        control.addAll(Films.artistIDList(performers));
        control.addAll(Films.artistIDList(writers));
        for (String id : control) {
            if (!Artist.getAllArtists().containsKey(id)) return "failed";
        }

        if (FeatureFilm.featureFilmMap.containsKey(uniqueID)) return "failed";
        else {
            addFeatureFilm(uniqueID, title, language, directors, runtime, country, performers, genre, date, writers, budget);
            return "successful";
        }

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
        FeatureFilm.sortedByPoint.put(film.getUniqueID(), film.getRating());//updating rating to sort correctly
    }

    public static LinkedHashMap<String, FeatureFilm> getFeatureFilmMap() {
        return featureFilmMap;
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


    public int getCompareDate() {
        return compareDate;
    }

    public String getYear() {
        return year;
    }
    // this method is used for sorting the films by their points in descending order.
    public static void sorting(){
        FeatureFilm.sortedByPoint = FeatureFilm.sortedByPoint.entrySet().stream().
                sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}