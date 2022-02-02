public class Handball extends Sports{
    private static int numOfTeams;

    public Handball(String sportType,String teamName) {
        super(teamName,sportType);
        numOfTeams++;
    }

    @Override
    public void winner(int defeatedSets,int gainedSets) {
        this.addPoint(2);
        super.winner(defeatedSets, gainedSets);
    }

    public static int getNumOfTeams() {
        return numOfTeams;
    }
}