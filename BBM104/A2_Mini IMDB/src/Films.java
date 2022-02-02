import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class Films {
    private final String title;
    private final String uniqueID;
    private final String language;
    private final int runtime;
    private final String country;
    private final String directors;
    private final String performers;
    private String outRating = "0";
    private double rating = 0;
    private int numberOfUser = 0;
    private LinkedHashMap<String, Integer> userPoints = new LinkedHashMap<>();//key = userID, value = user rating
    private static ArrayList<Films> allFilms = new ArrayList<>();

    public Films(String uniqueID, String title, String language, String directors,
                 int runtime, String country, String performers) {
        this.title = title;
        this.uniqueID = uniqueID;
        this.language = language;
        this.runtime = runtime;
        this.country = country;
        this.directors = directors;
        this.performers = performers;
    }
    //to create a list from the userIDs of the artists that are in string format in the command line.
    public static ArrayList<String> artistIDList(String allID) {
        return new ArrayList<>(Arrays.asList(allID.split(",")));
    }
    //This method is used for getting artist names from their userID(key of the name of artist in the allArtist map).
    public String artistNames(ArrayList<String> artistList) {
        ArrayList<String> namesList = new ArrayList<>();
        for (String id : artistList) {
            for (String key : Artist.getAllArtists().keySet()) {
                if (id.equals(key) && !StuntPerformer.getStuntPerformerMap().containsKey(id)){
                    namesList.add(Artist.getAllArtists().get(key));//contains only Actor and ChildActor names as Star names.
                }
            }
        }
        return String.join(", ", namesList);
    }
    //This method is used for creating a Films object and adding this object to the allFilms map as a value.
    public static void addFilms(String uniqueID, String title, String language, String directors,
                                int runtime, String country, String performers){
        allFilms.add(new Films(uniqueID,title,language,directors,runtime,country,performers));
    }

    public static ArrayList<Films> getAllFilms() {
        return allFilms;
    }

    public String getCountry() {
        return country;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getUniqueID() {
        return uniqueID;
    }
    //to rate the films and to update all data that stored in films and users instances.
    public void rate(String userID, Films film, int point) {
        film.userPoints.put(userID, point);
        film.numberOfUser++;
        User.getUserMap().get(userID).addRatedFilms(film.getTitle(), point);
        film.rateChange(film);
    }
    //to edit rating of the films and to update all data that stored in films and users instances.
    public void edit(String userID, Films film, int newPoint) {
        film.userPoints.put(userID, newPoint);
        User.getUserMap().get(userID).addRatedFilms(film.getTitle(), newPoint);
        film.rateChange(film);
    }
    //to remove the films and to update all data that stored in films and users instances.
    public void remove(String userID, Films film) {
        film.userPoints.remove(userID);
        film.numberOfUser--;
        User.getUserMap().get(userID).removeRatedFilms(film.getTitle());
        film.rateChange(film);
    }
    // This method is used for updating the rating of the films
    public void rateChange(Films film) {
        double total = 0;
        for (Integer i : film.userPoints.values()) {
            total += i;
        }
        double result;
        if(film.numberOfUser == 0) result = 0;
        else{result = total/film.numberOfUser;}
        DecimalFormat value = new DecimalFormat("##.#");//to change the (number.number) ----> (number,number)
        if(value.format(result).contains(",0")){
            film.outRating = value.format(result).replace(",0","");//to avoid (number,0)
        }
        else{
            film.outRating = value.format(result);
        }
        film.rating = Double.parseDouble(String.join(".",Arrays.asList(value.format(result).split(","))));
    }

    public String getOutRating() {
        return outRating;
    }

    public double getRating() {
        return rating;
    }

    public int getNumberOfUser() {
        return numberOfUser;
    }

    public LinkedHashMap<String, Integer> getUserPoints() {
        return userPoints;
    }
}