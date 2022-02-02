import java.util.*;
import java.util.stream.Collectors;

public class Documentary extends Films {
    private final String date;
    private static LinkedHashMap<String, Documentary> documentaryMap = new LinkedHashMap<>();//key = filmID, value = ShortFilm object
    private static LinkedHashMap<String, Double> sortedByPoint = new LinkedHashMap<>();      //key = filmID, value = rating
    private final String directorNames;
    private final String starsNames;
    private final String year;

    private Documentary(String uniqueID, String title, String language, String directors, int runtime, String country,
                        String performers, String date) {
        super(uniqueID, title, language, directors, runtime, country, performers);
        ArrayList<String> separateDate = new ArrayList<>(Arrays.asList(date.split("\\.")));
        this.year = "(" + separateDate.get(separateDate.size() - 1) + ")";
        this.date = date;
        this.directorNames = super.artistNames(Films.artistIDList(directors));
        this.starsNames = super.artistNames(Films.artistIDList(performers));
        Documentary.sortedByPoint.put(uniqueID, 0.0);// to add a film to the sortedByPoint map.
        addFilms(uniqueID,title,language,directors,runtime,country,performers);//to create a film object
    }
    //This method creates a new Documentary object and puts it to the documentaryMap as a value.
    public static void addDocumentary(String uniqueID, String title, String language, String directors,
                                      int runtime, String country, String performers, String date) {
        Documentary.documentaryMap.put(uniqueID, new Documentary(uniqueID, title, language, directors,
                runtime, country, performers, date));
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
        Documentary.sortedByPoint.put(film.getUniqueID(), film.getRating());//updating rating to sort correctly
    }

    public static LinkedHashMap<String, Documentary> getDocumentaryMap() {
        return documentaryMap;
    }

    public static LinkedHashMap<String, Double> getSortedByPoint() {
        return sortedByPoint;
    }


    public String getDirectorNames() {
        return directorNames;
    }

    public String getStarsNames() {
        return starsNames;
    }


    public String getYear() {
        return year;
    }
    //this method is used for sorting the films by their points in descending order.
    public static void sorting(){
        Documentary.sortedByPoint = Documentary.sortedByPoint.entrySet().stream().sorted
                (Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}