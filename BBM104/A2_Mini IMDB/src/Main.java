import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        readPeople(args[0]);
        readFilm(args[1]);
        PrintWriter outputWriter = new PrintWriter(new FileWriter(args[3]));
        BufferedReader commandReader = new BufferedReader(new FileReader(args[2]));
        String line;
        // This "while loop" reads the commands.txt and writes the output.txt in according to the commands.txt file content.
        while ((line = commandReader.readLine()) != null) {
            ArrayList<String> commandArray = new ArrayList<>(Arrays.asList(line.trim().split("\t")));
            if(commandArray.get(0).equals("RATE")){
                if(1>Integer.parseInt(commandArray.get(3))||Integer.parseInt(commandArray.get(3))>10){
                    System.out.println("Error: Rating point must be between 1 and 10");
                }
                else{
                    outputWriter.println(line+"\n");
                    if(validation(commandArray.get(1),commandArray.get(2)).equals("failed")){
                        outputWriter.println("Command Failed\nUser ID: "+commandArray.get(1)+"\nFilm ID: " +
                                commandArray.get(2)+"\n");
                    }
                    else if(validation(commandArray.get(1),commandArray.get(2)).equals("FeatureFilm")){
                        FeatureFilm film = FeatureFilm.getFeatureFilmMap().get(commandArray.get(2));
                        String userID = commandArray.get(1);
                        if(film.getUserPoints().containsKey(userID)){
                            outputWriter.println("This film was earlier rated\n");
                        }
                        else{
                            film.rate(userID,film,Integer.parseInt(commandArray.get(3)));
                            outputWriter.println("Film rated successfully\nFilm type: FeatureFilm\nFilm title: " +
                                    film.getTitle()+"\n");
                        }
                    }
                    else if(validation(commandArray.get(1),commandArray.get(2)).equals("ShortFilm")){
                        ShortFilm film = ShortFilm.getShortFilmMap().get(commandArray.get(2));
                        String userID = commandArray.get(1);
                        if(film.getUserPoints().containsKey(userID)){
                            outputWriter.println("This film was earlier rated\n");
                        }
                        else{
                            film.rate(userID,film,Integer.parseInt(commandArray.get(3)));
                            outputWriter.println("Film rated successfully\nFilm type: ShortFilm\nFilm title: " +
                                    film.getTitle()+"\n");
                        }
                    }
                    else if(validation(commandArray.get(1),commandArray.get(2)).equals("Documentary")){
                        Documentary film = Documentary.getDocumentaryMap().get(commandArray.get(2));
                        String userID = commandArray.get(1);
                        if(film.getUserPoints().containsKey(userID)){
                            outputWriter.println("This film was earlier rated\n");
                        }
                        else{
                            film.rate(userID,film,Integer.parseInt(commandArray.get(3)));
                            outputWriter.println("Film rated successfully\nFilm type: Documentary\nFilm title: " +
                                    film.getTitle()+"\n");
                        }
                    }
                    else{
                        Series film = Series.getSeriesMap().get(commandArray.get(2));
                        String userID = commandArray.get(1);
                        if(film.getUserPoints().containsKey(userID)){
                            outputWriter.println("This film was earlier rated\n");
                        }
                        else{
                            film.rate(userID,film,Integer.parseInt(commandArray.get(3)));
                            outputWriter.println("Film rated successfully\nFilm type: TVSeries\nFilm title: " +
                                    film.getTitle()+"\n");
                        }
                    }
                    outputWriter.println("----------------------------------------------" +
                            "-------------------------------------------------------");
                }
            }
            else if(commandArray.get(0).equals("ADD")){
                outputWriter.println(line+"\n");
                if(FeatureFilm.addMethod(commandArray.get(2),commandArray.get(3),commandArray.get(4),commandArray.get(5),
                        Integer.parseInt(commandArray.get(6)),commandArray.get(7),commandArray.get(8),commandArray.get(9),
                        commandArray.get(10),commandArray.get(11),commandArray.get(12)).equals("successful")){
                    outputWriter.println("FeatureFilm added successfully\nFilm ID: "+commandArray.get(2)+"\n" +
                            "Film title: "+commandArray.get(3)+"\n");
                }
                else{
                    outputWriter.println("Command Failed\nFilm ID: "+commandArray.get(2)+"\n" +
                            "Film title: "+commandArray.get(3)+"\n");
                }
                outputWriter.println("----------------------------------------------" +
                        "-------------------------------------------------------");
            }
            else if(commandArray.get(0).equals("VIEWFILM")){
                outputWriter.println(line+"\n");
                if(FeatureFilm.getFeatureFilmMap().containsKey(commandArray.get(1))){
                    FeatureFilm film = FeatureFilm.getFeatureFilmMap().get(commandArray.get(1));
                    viewFilm(outputWriter, film.getTitle(), film.getYear(), film.getGenre(), film.getWriterNames(),
                            film.getDirectorNames(), film.getStarsNames(), film.getOutRating(), film.getNumberOfUser());

                }
                else if(ShortFilm.getShortFilmMap().containsKey(commandArray.get(1))){
                    ShortFilm film = ShortFilm.getShortFilmMap().get(commandArray.get(1));
                    viewFilm(outputWriter, film.getTitle(), film.getYear(), film.getGenre(), film.getWriterNames(),
                            film.getDirectorNames(), film.getStarsNames(), film.getOutRating(), film.getNumberOfUser());

                }
                else if(Documentary.getDocumentaryMap().containsKey(commandArray.get(1))){
                    Documentary film = Documentary.getDocumentaryMap().get(commandArray.get(1));
                    outputWriter.println(film.getTitle() + " " + film.getYear() + "\n");
                    outputWriter.println("Directors: " + film.getDirectorNames());
                    outputWriter.println("Stars: " + film.getStarsNames());
                    if(film.getNumberOfUser()==0) outputWriter.println("Awaiting for votes\n");
                    else{outputWriter.println("Ratings: " + film.getOutRating() + "/10 from " + film.getNumberOfUser() + " users\n");}
                }
                else{
                    Series film = Series.getSeriesMap().get(commandArray.get(1));
                    outputWriter.println(film.getTitle() + " " + film.getStartEnd());
                    outputWriter.println(film.getNumberOfSeason()+" seasons, "+film.getNumberOfEpisode()+" episodes");
                    outputWriter.println(film.getGenre());
                    outputWriter.println("Writers: "+ film.getWriterNames());
                    outputWriter.println("Directors: " + film.getDirectorNames());
                    outputWriter.println("Stars: " + film.getStarsNames());
                    if(film.getNumberOfUser()==0) outputWriter.println("Awaiting for votes\n");
                    else{outputWriter.println("Ratings: " + film.getOutRating() + "/10 from " + film.getNumberOfUser() + " users\n");}                }
                outputWriter.println("----------------------------------------------" +
                        "-------------------------------------------------------");
            }
            else if(commandArray.get(1).equals("USER")){
                outputWriter.println(line+"\n");
                if(User.getUserMap().containsKey(commandArray.get(2))){
                    User user = User.getUserMap().get(commandArray.get(2));
                    if(user.getRatedFilms().size()==0){
                        outputWriter.println("There is not any rating so far\n");
                    }
                    else{
                        for(String filmTitle: user.getRatedFilms().keySet()){
                            outputWriter.println(filmTitle+": "+user.getRatedFilms().get(filmTitle));
                        }
                        outputWriter.println();
                    }
                }
                else{
                    outputWriter.println("Command Failed\nUser ID: "+commandArray.get(2)+"\n");
                }
                outputWriter.println("----------------------------------------------" +
                        "-------------------------------------------------------");
            }
            else if(commandArray.get(0).equals("EDIT")||commandArray.get(0).equals("REMOVE")){
                outputWriter.println(line+"\n");
                if(validation(commandArray.get(2),commandArray.get(3)).equals("failed")){
                    outputWriter.println("Command Failed\nUser ID: "+commandArray.get(2)+"\nFilm ID: " +
                            commandArray.get(3)+"\n");
                }
                else if(validation(commandArray.get(2),commandArray.get(3)).equals("FeatureFilm")){
                    FeatureFilm film = FeatureFilm.getFeatureFilmMap().get(commandArray.get(3));
                    if(!film.getUserPoints().containsKey(commandArray.get(2))){
                        outputWriter.println("Command Failed\nUser ID: "+commandArray.get(2)+"\nFilm ID: " +
                                commandArray.get(3)+"\n");
                    }
                    else{
                        if(commandArray.get(0).equals("EDIT")){
                            film.edit(commandArray.get(2),film,Integer.parseInt(commandArray.get(4)));
                            outputWriter.println("New ratings done successfully");
                            outputWriter.println("Film title: "+film.getTitle());
                            outputWriter.println("Your rating: "+commandArray.get(4)+"\n");
                        }
                        else{
                            film.remove(commandArray.get(2),film);
                            outputWriter.println("Your film rating was removed successfully\nFilm title: "+film.getTitle()+"\n");
                        }
                    }
                }
                else if(validation(commandArray.get(2),commandArray.get(3)).equals("ShortFilm")){
                    ShortFilm film = ShortFilm.getShortFilmMap().get(commandArray.get(3));
                    if(!film.getUserPoints().containsKey(commandArray.get(2))){
                        outputWriter.println("Command Failed\nUser ID: "+commandArray.get(2)+"\nFilm ID: " +
                                commandArray.get(3)+"\n");
                    }
                    else{
                        if(commandArray.get(0).equals("EDIT")){
                            film.edit(commandArray.get(2),film,Integer.parseInt(commandArray.get(4)));
                            outputWriter.println("New ratings done successfully\n");
                            outputWriter.println("Film title: "+film.getTitle());
                            outputWriter.println("Your rating: "+commandArray.get(4)+"\n");
                        }
                        else{
                            film.remove(commandArray.get(2),film);
                            outputWriter.println("Your film rating was removed successfully\nFilm title: "+film.getTitle()+"\n");
                        }
                    }
                }
                else if(validation(commandArray.get(2),commandArray.get(3)).equals("Documentary")){
                    Documentary film = Documentary.getDocumentaryMap().get(commandArray.get(3));
                    if(!film.getUserPoints().containsKey(commandArray.get(2))){
                        outputWriter.println("Command Failed\nUser ID: "+commandArray.get(2)+"\nFilm ID: " +
                                commandArray.get(3)+"\n");
                    }
                    else{
                        if(commandArray.get(0).equals("EDIT")){
                            film.edit(commandArray.get(2),film,Integer.parseInt(commandArray.get(4)));
                            outputWriter.println("New ratings done successfully\n");
                            outputWriter.println("Film title: "+film.getTitle());
                            outputWriter.println("Your rating: "+commandArray.get(4)+"\n");
                        }
                        else{
                            film.remove(commandArray.get(2),film);
                            outputWriter.println("Your film rating was removed successfully\nFilm title: "+film.getTitle()+"\n");
                        }
                    }
                }
                else{
                    Series film = Series.getSeriesMap().get(commandArray.get(3));
                    if(!film.getUserPoints().containsKey(commandArray.get(2))){
                        outputWriter.println("Command Failed\nUser ID: "+commandArray.get(2)+"\nFilm ID: " +
                                commandArray.get(3)+"\n");
                    }
                    else{
                        if(commandArray.get(0).equals("EDIT")){
                            film.edit(commandArray.get(2),film,Integer.parseInt(commandArray.get(4)));
                            outputWriter.println("New ratings done successfully\n");
                            outputWriter.println("Film title: "+film.getTitle());
                            outputWriter.println("Your rating: "+commandArray.get(4)+"\n");
                        }
                        else{
                            film.remove(commandArray.get(2),film);
                            outputWriter.println("Your film rating was removed successfully\nFilm title: "+film.getTitle()+"\n");
                        }
                    }
                }
                outputWriter.println("----------------------------------------------" +
                        "-------------------------------------------------------");
            }
            else if(commandArray.get(2).equals("SERIES")){
                outputWriter.println(line+"\n");
                if(Series.getSeriesMap().size()==0){
                    outputWriter.println("No result\n");
                }
                else{
                    for(Series film : Series.getSeriesMap().values()){
                        outputWriter.println(film.getTitle()+" "+film.getStartEnd());
                        outputWriter.println(film.getNumberOfSeason()+" seasons and "+film.getNumberOfEpisode()+" episodes\n");
                    }
                }
                outputWriter.println("----------------------------------------------" +
                        "-------------------------------------------------------");
            }
            else if(commandArray.get(2).equals("FROM")){
                outputWriter.println(line+"\n");
                outputWriter.println("Directors:");
                int count = 0;
                for(Director director: Director.getDirectorMap().values()){
                    if(director.getCountry().equals(commandArray.get(3))){
                        count++;
                        outputWriter.println(director.getName()+" "+director.getSurname()+" "+director.getAgent());
                    }
                }
                if(count == 0)outputWriter.println("No Result");
                outputWriter.println("\nWriters:");
                count = 0;
                for(Writer writer: Writer.getWriterMap().values()){
                    if(writer.getCountry().equals(commandArray.get(3))){
                        count++;
                        outputWriter.println(writer.getName()+" "+writer.getSurname()+" "+writer.getStyle());
                    }
                }
                if(count == 0)outputWriter.println("No Result");
                outputWriter.println("\nActor:");
                count = 0;
                for(Actor actor: Actor.getActorMap().values()){
                    if(actor.getCountry().equals(commandArray.get(3))){
                        count++;
                        outputWriter.println(actor.getName()+" "+actor.getSurname()+" "+actor.getHeight()+" cm");
                    }
                }
                if(count == 0)outputWriter.println("No Result");
                outputWriter.println("\nChildActor:");
                count = 0;
                for(ChildActor childActor: ChildActor.getChildActorMap().values()){
                    if(childActor.getCountry().equals(commandArray.get(3))){
                        count++;
                        outputWriter.println(childActor.getName()+" "+childActor.getSurname()+" "+childActor.getAge());
                    }
                }
                if(count == 0)outputWriter.println("No Result");
                outputWriter.println("\nStuntPerformer:");
                count = 0;
                for(StuntPerformer stuntPerformer: StuntPerformer.getStuntPerformerMap().values()){
                    if(stuntPerformer.getCountry().equals(commandArray.get(3))){
                        count++;
                        outputWriter.println(stuntPerformer.getName()+" "+stuntPerformer.getSurname()+" "+stuntPerformer.getHeight()+" cm");
                    }
                }
                if(count == 0)outputWriter.println("No Result");
                outputWriter.println("\n----------------------------------------------" +
                        "-------------------------------------------------------");
            }
            else if(commandArray.get(3).equals("COUNTRY")){
                outputWriter.println(line+"\n");
                int count = 0;
                for(Films film : Films.getAllFilms()){
                    if(film.getCountry().equals(commandArray.get(4))){
                        count++;
                        outputWriter.println("Film title: "+film.getTitle());
                        outputWriter.println(film.getRuntime()+" min");
                        outputWriter.println("Language: "+film.getLanguage()+"\n");
                    }
                }
                if(count == 0) outputWriter.println("No result\n");// there is no film from this country
                outputWriter.println("----------------------------------------------" +
                        "-------------------------------------------------------");
            }
            else if(commandArray.get(2).equals("BEFORE")){
                outputWriter.println(line+"\n");
                int count = 0;
                for(FeatureFilm film: FeatureFilm.getFeatureFilmMap().values()){
                    if(film.getCompareDate()<Integer.parseInt(commandArray.get(3))){
                        count++;
                        outputWriter.println("Film title: "+film.getTitle()+" "+film.getYear());
                        outputWriter.println(film.getRuntime()+" min");
                        outputWriter.println("Language: "+film.getLanguage()+"\n");
                    }
                }
                if(count == 0) outputWriter.println("No result\n");
                outputWriter.println("----------------------------------------------" +
                        "-------------------------------------------------------");
            }
            else if(commandArray.get(2).equals("AFTER")){
                outputWriter.println(line+"\n");
                int count = 0;
                for(FeatureFilm film: FeatureFilm.getFeatureFilmMap().values()){
                    if(film.getCompareDate()>=Integer.parseInt(commandArray.get(3))){
                        count++;
                        outputWriter.println("Film title: "+film.getTitle()+" "+film.getYear());
                        outputWriter.println(film.getRuntime()+" min");
                        outputWriter.println("Language: "+film.getLanguage()+"\n");
                    }
                }
                if(count == 0) outputWriter.println("No result\n");
                outputWriter.println("----------------------------------------------" +
                        "-------------------------------------------------------");
            }
            else{
                outputWriter.println(line+"\n");
                outputWriter.println("FeatureFilm:");
                if(FeatureFilm.getSortedByPoint().size()==0) {outputWriter.println("No result");}
                else{
                    FeatureFilm.sorting();
                    for(String filmID : FeatureFilm.getSortedByPoint().keySet()){
                        FeatureFilm film = FeatureFilm.getFeatureFilmMap().get(filmID);
                        outputWriter.println(film.getTitle()+" "+film.getYear()+" Ratings: "+film.getOutRating()+
                                "/10 from "+film.getNumberOfUser()+" users");
                    }
                }
                outputWriter.println("\nShortFilm:");
                if(ShortFilm.getSortedByPoint().size()==0) {outputWriter.println("No result");}
                else{
                    ShortFilm.sorting();
                    for(String filmID : ShortFilm.getSortedByPoint().keySet()){
                        ShortFilm film = ShortFilm.getShortFilmMap().get(filmID);
                        outputWriter.println(film.getTitle()+" "+film.getYear()+" Ratings: "+film.getOutRating()+
                                "/10 from "+film.getNumberOfUser()+" users");
                    }
                }
                outputWriter.println("\nDocumentary:");
                if(Documentary.getSortedByPoint().size()==0) {outputWriter.println("No result");}
                else{
                    Documentary.sorting();
                    for(String filmID : Documentary.getSortedByPoint().keySet()){
                        Documentary film = Documentary.getDocumentaryMap().get(filmID);
                        outputWriter.println(film.getTitle()+" "+film.getYear()+" Ratings: "+film.getOutRating()+
                                "/10 from "+film.getNumberOfUser()+" users");
                    }
                }
                outputWriter.println("\nTVSeries:");
                if(Series.getSortedByPoint().size()==0) {outputWriter.println("No result\n");}
                else{
                    Series.sorting();
                    for(String filmID : Series.getSortedByPoint().keySet()){
                        Series film = Series.getSeriesMap().get(filmID);
                        outputWriter.println(film.getTitle()+" "+film.getStartEnd()+" Ratings: "+film.getOutRating()+
                                "/10 from "+film.getNumberOfUser()+" users");
                    }
                }
                outputWriter.println("\n----------------------------------------------" +
                        "-------------------------------------------------------");
            }

        }
        commandReader.close();
        outputWriter.close();
    }
// This method avoids the duplicate code, because FeatureFilm and ShortFilm have same output content.
    private static void viewFilm(PrintWriter outputWriter, String title, String year, String genre,
                                 String writerNames, String directorNames, String performerNames,
                                 String rating, int numberOfUser) {
        outputWriter.println(title + " " + year + "");
        outputWriter.println(genre);
        outputWriter.println("Writers: " + writerNames);
        outputWriter.println("Directors: " + directorNames);
        outputWriter.println("Stars: " + performerNames);
        if(numberOfUser==0) outputWriter.println("Awaiting for votes\n");
        else{outputWriter.println("Ratings: " + rating + "/10 from " + numberOfUser + " users\n");}


    }
// This method reads the people.txt file content. We can assign all people to their class thanks to this method.
    public static void readPeople(String peopleTxt) throws IOException {
        BufferedReader peopleReader = new BufferedReader(new FileReader(peopleTxt));
        String line;
        while ((line = peopleReader.readLine()) != null) {
            ArrayList<String> people = new ArrayList<>(Arrays.asList(line.trim().split("\t")));
            switch (people.get(0)) {
                case "Actor:":
                    Actor.addActor(people.get(1), people.get(2), people.get(3), people.get(4), people.get(5));
                    break;
                case "ChildActor:":
                    ChildActor.addChildActor(people.get(1), people.get(2), people.get(3), people.get(4), people.get(5));
                    break;
                case "Director:":
                    Director.addDirector(people.get(1), people.get(2), people.get(3), people.get(4), people.get(5));
                    break;
                case "Writer:":
                    Writer.addWriter(people.get(1), people.get(2), people.get(3), people.get(4), people.get(5));
                    break;
                case "StuntPerformer:":
                    StuntPerformer.addStuntPerformer(people.get(1), people.get(2), people.get(3),
                                                    people.get(4), people.get(5), people.get(6));
                    break;
                default:
                    User.addUser(people.get(1), people.get(2), people.get(3), people.get(4));
                    break;
            }
        }
    }
// This method reads the films.txt file content. We can assign all films to their class thanks to this method.
    public static void readFilm(String filmTxt) throws IOException {
        BufferedReader filmReader = new BufferedReader(new FileReader(filmTxt));
        String line;
        while ((line = filmReader.readLine()) != null) {
            ArrayList<String> film = new ArrayList<>(Arrays.asList(line.trim().split("\t")));
            switch (film.get(0)) {
                case "FeatureFilm:":
                    FeatureFilm.addFeatureFilm(film.get(1), film.get(2), film.get(3), film.get(4), Integer.parseInt
                            (film.get(5)), film.get(6), film.get(7), film.get(8), film.get(9), film.get(10), film.get(11));
                    break;
                case "ShortFilm:":
                    if (Integer.parseInt(film.get(5)) <= 40) {
                        ShortFilm.addShortFilm(film.get(1),film.get(2),film.get(3),film.get(4),
                                Integer.parseInt(film.get(5)),film.get(6),film.get(7),film.get(8),film.get(9),film.get(10));
                    } else {
                        System.out.println("Error: ShortFilm cannot be longer than 40 min");
                    }
                    break;
                case "TVSeries:":
                    Series.addSeries(film.get(1), film.get(2), film.get(3), film.get(4), Integer.parseInt(film.get(5)),
                                    film.get(6), film.get(7), film.get(8), film.get(9),
                                    film.get(10), film.get(11), film.get(12), film.get(13));
                    break;
                default:
                    Documentary.addDocumentary(film.get(1), film.get(2), film.get(3), film.get(4),
                            Integer.parseInt(film.get(5)), film.get(6), film.get(7), film.get(8));
                    break;
            }
        }
    }
// This method is used for validation of the rate,edit and remove commands.
    public static String validation(String userID,String filmID){
        if(!User.getUserMap().containsKey(userID)) return "failed";
        else if(FeatureFilm.getFeatureFilmMap().containsKey(filmID)) return "FeatureFilm";
        else if(ShortFilm.getShortFilmMap().containsKey(filmID)) return "ShortFilm";
        else if(Documentary.getDocumentaryMap().containsKey(filmID)) return "Documentary";
        else if(Series.getSeriesMap().containsKey(filmID)) return "Series";
        else{return "failed";}
    }
}