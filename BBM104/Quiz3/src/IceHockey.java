public class IceHockey extends Sports {
    private static int numOfTeams;

    public IceHockey(String sportType, String teamName) {
        super(teamName,sportType);
        numOfTeams++;
    }

    @Override
    public void winner(int defeatedSets,int gainedSets) {
        this.addPoint(3);
        super.winner(defeatedSets, gainedSets);
    }

    public static int getNumOfTeams() {
        return numOfTeams;
    }
}