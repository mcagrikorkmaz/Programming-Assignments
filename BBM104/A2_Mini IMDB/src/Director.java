import java.util.LinkedHashMap;

public class Director extends Artist{
    private final String agent;
    private static LinkedHashMap<String, Director> directorMap = new LinkedHashMap<>();

    private Director(String uniqueID,String name,String surname,String country,String agent){
        super(uniqueID,name,surname,country);
        this.agent = agent;
    }
    //This method creates a new Director object and puts it in the directorMap as a value.
    public static void addDirector(String uniqueID,String name,String surname,String country,String agent){
        Director.directorMap.put(uniqueID,new Director(uniqueID,name,surname,country,agent));
    }

    public String getAgent() {
        return agent;
    }

    public static LinkedHashMap<String, Director> getDirectorMap() {
        return directorMap;
    }
}