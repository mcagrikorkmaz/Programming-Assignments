public class Tokens {
    private String uniqueID;
    private int value;
    private final String type;
    public static Queue allTokens = new Queue();

    public Tokens(){
        this("default","default",0);
    }

    public Tokens(String uniqueID, String type,int value){
        this.uniqueID = uniqueID;
        this.type = type;
        this.value = value;
    }

    public int getValue() {
        return value;

    }

    public String getType() {
        return type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUniqueID() {
        return uniqueID;
    }
}