public class Sports {
    private int numOfLost = 0;
    private int numOfWon = 0;
    private int numOfTie = 0;
    private int allMatches = 0;
    private int average = 0;
    private int totalPoint = 0;
    private int gainedSets = 0;
    private int defeatedSets = 0;
    private final String sportType;
    private final String teamName;

    public Sports(String teamName,String sportType) {
        this.teamName = teamName;
        this.sportType = sportType;
    }

    public void winner(int defeatedSets,int gainedSets){
        this.numOfWon++;
        updateResult(defeatedSets, gainedSets);
    }

    public void loser(int defeatedSets,int gainedSets){
        this.numOfLost++;
        updateResult(defeatedSets, gainedSets);
    }

    public void tie(int defeatedSets,int gainedSets){
        this.numOfTie++;
        this.totalPoint++;
        updateResult(defeatedSets, gainedSets);
    }

    public void updateResult(int defeatedSets,int gainedSets){
        this.allMatches++;
        this.defeatedSets += defeatedSets;
        this.gainedSets += gainedSets;
        this.average = this.gainedSets - this.defeatedSets;
    }

    public void addPoint(int i){
        this.totalPoint += i;
    }

    public int getAverage() {
        return average;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public String getSportType() {
        return sportType;
    }

    public int getNumOfLost() {
        return numOfLost;
    }

    public int getNumOfWon() {
        return numOfWon;
    }

    public int getNumOfTie() {
        return numOfTie;
    }

    public int getAllMatches() {
        return allMatches;
    }

    public int getGainedSets() {
        return gainedSets;
    }

    public int getDefeatedSets() {
        return defeatedSets;
    }

    public String getTeamName() {
        return teamName;
    }
}