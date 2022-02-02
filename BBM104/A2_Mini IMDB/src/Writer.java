import java.util.LinkedHashMap;

public class Writer extends Artist{
    private final String style;
    private static LinkedHashMap<String, Writer> writerMap = new LinkedHashMap<>();

    private Writer(String uniqueID,String name,String surname,String country,String style){
        super(uniqueID,name,surname,country);
        this.style = style;
    }
    //This method creates a new Writer object and puts it in the writerMap as a value.
    public static void addWriter(String uniqueID,String name,String surname,String country,String style){
        Writer.writerMap.put(uniqueID,new Writer(uniqueID,name,surname,country,style));
    }

    public String getStyle() {
        return style;
    }

    public static LinkedHashMap<String, Writer> getWriterMap() {
        return writerMap;
    }
}