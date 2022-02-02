import java.util.*;
import java.util.stream.Collectors;

public class Series extends Films {
    private final String startDate;
    private final String endDate;
    private final String writers;
    private final String numberOfSeason;
    private final String numberOfEpisode;
    private final String genre;
    private static LinkedHashMap<String, Series> seriesMap = new LinkedHashMap<>();//key = filmID, value = ShortFilm object
    private static LinkedHashMap<String, Double> sortedByPoint = new LinkedHashMap<>();//key = filmID, value = rating
    private final String directorNames;
    private final String starsNames;
    private final String writerNames;
    private final String startEnd;

    private Series(String uniqueID, String title, String language, String directors, int runtime,
                   String country, String performers, String genre, String writers, String startDate,
                   String endDate, String numberOfSeason, String numberOfEpisode) {
        super(uniqueID, title, language, directors, runtime, country, performers);
        ArrayList<String> separateStart = new ArrayList<>(Arrays.asList(startDate.split("\\.")));
        ArrayList<String> separateEnd = new ArrayList<>(Arrays.asList(endDate.split("\\.")));
        this.startEnd = "(" + separateStart.get(separateStart.size() - 1) + "-" + separateEnd.get(separateEnd.size() - 1) + ")";
        this.genre = String.join(", ", Arrays.asList(genre.split(",")));
        this.startDate = startDate;
        this.endDate = endDate;
        this.writers = writers;
        this.numberOfSeason = numberOfSeason;
        this.numberOfEpisode = numberOfEpisode;
        this.directorNames = super.artistNames(Films.artistIDList(directors));
        this.starsNames = super.artistNames(Films.artistIDList(performers));
        this.writerNames = super.artistNames(Films.artistIDList(writers));
        Series.sortedByPoint.put(uniqueID, 0.0);// to add a film to the sortedByPoint map.
        addFilms(uniqueID,title,language,directors,runtime,country,performers);//to create a film object
    }
    //This method creates a new Series object and puts it to the seriesMap as a value.
    public static void addSeries(String uniqueID, String title, String language, String directors, int runtime,
                                 String country, String performers, String genre, String writers, String startDate,
                                 String endDate, String numberOfSeason, String numberOfEpisode) {
        Series.seriesMap.put(uniqueID, new Series(uniqueID, title, language, directors, runtime, country, performers,
                genre, writers, startDate, endDate, numberOfSeason, numberOfEpisode));
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
        Series.sortedByPoint.put(film.getUniqueID(), film.getRating());//updating rating to sort correctly
    }

    public static LinkedHashMap<String, Series> getSeriesMap() {
        return seriesMap;
    }

    public static LinkedHashMap<String, Double> getSortedByPoint() {
        return sortedByPoint;
    }

    public String getNumberOfSeason() {
        return numberOfSeason;
    }

    public String getNumberOfEpisode() {
        return numberOfEpisode;
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

    public String getStartEnd() {
        return startEnd;
    }
    //this method is used for sorting the films by their points in descending order.
    public static void sorting(){
        Series.sortedByPoint = Series.sortedByPoint.entrySet().stream().sorted
                (Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap
                (Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}