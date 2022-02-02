import java.io.*;
import java.util.*;

public class Main {
    public static LinkedHashMap<String,Sports> teamsMap = new LinkedHashMap<>();
    public static ArrayList<Sports> teamsList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line;
        while ((line = reader.readLine()) != null) {
            ArrayList<String> lineArray = new ArrayList<>(Arrays.asList(line.trim().split("\t")));
            ArrayList<String> scores = new ArrayList<>(Arrays.asList(lineArray.get(3).split(":")));
            String homeTeam = lineArray.get(1);
            String awayTeam = lineArray.get(2);
            int home = Integer.parseInt(scores.get(0));
            int away = Integer.parseInt(scores.get(1));
            if(lineArray.get(0).equals("I")){
                commonMethod(homeTeam,awayTeam,home,away,"I");
            }
            else{
                commonMethod(homeTeam,awayTeam,home,away,"H");
            }
        }
        reader.close();
        teamsList.sort(Comparator.comparing(Sports::getTotalPoint).thenComparing(Sports::getAverage));
        Collections.reverse(teamsList);
        handballWrite();
        hockeyWrite();
    }

    public static void commonMethod(String homeTeam, String awayTeam, int home, int away,String type){
        if(type.equals("I")){
            if(!teamsMap.containsKey(homeTeam)){
            teamsMap.put(homeTeam,new IceHockey("IceHockey",homeTeam));
            teamsList.add(teamsMap.get(homeTeam));
        }
            if(!teamsMap.containsKey(awayTeam)){
            teamsMap.put(awayTeam,new IceHockey("IceHockey",awayTeam));
            teamsList.add(teamsMap.get(awayTeam));
            }
        }
        if(type.equals("H")){
            if(!teamsMap.containsKey(homeTeam)){
                teamsMap.put(homeTeam,new Handball("Handball",homeTeam));
                teamsList.add(teamsMap.get(homeTeam));
            }
            if(!teamsMap.containsKey(awayTeam)){
                teamsMap.put(awayTeam,new Handball("Handball",awayTeam));
                teamsList.add(teamsMap.get(awayTeam));
            }
        }
        if(home == away){
            teamsMap.get(homeTeam).tie(away,home);
            teamsMap.get(awayTeam).tie(home,away);
        }
        else if(home > away){
            teamsMap.get(homeTeam).winner(away,home);
            teamsMap.get(awayTeam).loser(home,away);
        }
        else {
            teamsMap.get(homeTeam).loser(away,home);
            teamsMap.get(awayTeam).winner(home,away);
        }
    }

    public static void handballWrite() throws IOException {
        PrintWriter outputWriter = new PrintWriter(new FileWriter("handball.txt"));
        int i = 1;
        for(Sports team: teamsList){
            if(team.getSportType().equals("Handball")){
                if(i == Handball.getNumOfTeams()){
                    outputWriter.print(i +".\t"+team.getTeamName()+"\t"+team.getAllMatches()+"\t"+team.getNumOfWon()+
                            "\t"+team.getNumOfTie()+"\t"+team.getNumOfLost()+"\t"+team.getGainedSets()+":"+
                            team.getDefeatedSets()+"\t"+team.getTotalPoint());
                    break;
                }
                else{
                    outputWriter.println(i++ +".\t"+team.getTeamName()+"\t"+team.getAllMatches()+"\t"+team.getNumOfWon()+
                            "\t"+team.getNumOfTie()+"\t"+team.getNumOfLost()+"\t"+team.getGainedSets()+":"+
                            team.getDefeatedSets()+"\t"+team.getTotalPoint());
                }
            }
        }
        outputWriter.close();
    }

    public static void hockeyWrite() throws IOException {
        PrintWriter outputWriter = new PrintWriter(new FileWriter("icehockey.txt"));
        int i = 1;
        for(Sports team: teamsList){
            if(team.getSportType().equals("IceHockey")){
                if(i == IceHockey.getNumOfTeams()){
                    outputWriter.print(i +".\t"+team.getTeamName()+"\t"+team.getAllMatches()+"\t"+team.getNumOfWon()+
                            "\t"+team.getNumOfTie()+"\t"+team.getNumOfLost()+"\t"+team.getGainedSets()+":"+
                            team.getDefeatedSets()+"\t"+team.getTotalPoint());
                    break;
                }
                else{
                    outputWriter.println(i++ +".\t"+team.getTeamName()+"\t"+team.getAllMatches()+"\t"+team.getNumOfWon()+
                            "\t"+team.getNumOfTie()+"\t"+team.getNumOfLost()+"\t"+team.getGainedSets()+":"+
                            team.getDefeatedSets()+"\t"+team.getTotalPoint());
                }
            }
        }
        outputWriter.close();
    }
}