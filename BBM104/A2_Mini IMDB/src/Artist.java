import java.util.LinkedHashMap;

public class Artist extends Person{
    private static LinkedHashMap<String,String> allArtists = new LinkedHashMap<>();

    public Artist(String uniqueID,String name,String surname,String country){
        super(uniqueID,name,surname,country);
        addArtist(uniqueID,name+" "+surname);
    }
    //This method creates a new Artist object and puts it in the allArtistMap as a value.
    public static void addArtist(String id,String nameSurname) {
        Artist.allArtists.put(id,nameSurname);
    }

    public static LinkedHashMap<String, String> getAllArtists() {
        return allArtists;
    }
}