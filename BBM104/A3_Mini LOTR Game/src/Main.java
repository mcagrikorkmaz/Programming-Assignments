import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader initialReader = new BufferedReader(new FileReader(args[0]));
        BufferedReader commandReader = new BufferedReader(new FileReader(args[1]));
        PrintWriter outWriter = new PrintWriter(new FileWriter(args[2]));
        String line;
        while ((line = initialReader.readLine()) != null) {
            ArrayList<String> initialArray = new ArrayList<>(Arrays.asList(line.trim().split(" ")));
            if (initialArray.size() == 1 && initialArray.get(0).contains("x")) {
                GameMap.createMap(Integer.parseInt(initialArray.get(0).split("x")[0]));
            } else if (initialArray.size() == 4) {
                String id = initialArray.get(1);
                int x = Integer.parseInt(initialArray.get(2));
                int y = Integer.parseInt(initialArray.get(3));
                if (initialArray.get(0).equals("ELF")) {
                    Characters.addCharacters(id, new Elf(id, Constants.elfMaxHP, Constants.elfAP, Constants.elfMaxMove, x, y));
                } else if (initialArray.get(0).equals("HUMAN")) {
                    Characters.addCharacters(id, new Human(id, Constants.humanMaxHP, Constants.humanAP, Constants.humanMaxMove, x, y));
                } else if (initialArray.get(0).equals("DWARF")) {
                    Characters.addCharacters(id, new Dwarf(id, Constants.dwarfMaxHP, Constants.dwarfAP, Constants.dwarfMaxMove, x, y));
                } else if (initialArray.get(0).equals("ORK")) {
                    Characters.addCharacters(id, new Ork(id, Constants.orkMaxHP, Constants.orkAP, Constants.orkMaxMove, x, y));
                } else if (initialArray.get(0).equals("GOBLIN")) {
                    Characters.addCharacters(id, new Goblin(id, Constants.goblinMaxHP, Constants.goblinAP, Constants.goblinMaxMove, x, y));
                } else {
                    Characters.addCharacters(id, new Troll(id, Constants.trollMaxHP, Constants.trollAP, Constants.trollMaxMove, x, y));
                }
            }
        }
        initialReader.close();
        GameMap.writeMap(outWriter);
        Characters.writeAllCharacters(outWriter);
        while ((line = commandReader.readLine()) != null) {
            ArrayList<String> commandArray = new ArrayList<>(Arrays.asList(line.trim().split(" ")));
            if(Characters.getAllCharacters().containsKey(commandArray.get(0))) {
                Characters character = Characters.getAllCharacters().get(commandArray.get(0));
                ArrayList<String> moveSteps = new ArrayList<>(Arrays.asList(commandArray.get(1).substring(0, commandArray.get(1).length() - 1).split(";")));
                ArrayList<Integer> intSteps = new ArrayList<>();
                int moveCount = 1;
                for (String step : moveSteps) {
                    intSteps.add(Integer.parseInt(step));
                }
                try {
                    if (character.getMaxMove() != intSteps.size() / 2) {
                        throw new MoveCountException();
                    }
                } catch (MoveCountException e) {
                    outWriter.println("Error : Move sequence contains wrong number of move steps. Input line ignored.\n");
                    continue;
                }
                for (int i = 0; i <= intSteps.size() - 2; i += 2) {
                    try {
                        if (character.moveControl(intSteps.get(i), intSteps.get(i + 1)).equals("BoundaryException")) {
                            throw new BoundaryException();
                        } else {
                            if (character instanceof Ork) {
                                ((Ork) character).heal(Constants.orkHealPoints);
                            }
                            if (character.attack(intSteps.get(i), intSteps.get(i + 1), moveCount).equals("continue")) {
                                moveCount++;
                            } else {
                                GameMap.writeMap(outWriter);
                                Characters.writeAllCharacters(outWriter);
                                break;
                            }
                        }
                    } catch (BoundaryException e) {
                        if (moveCount == 1) {
                            outWriter.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                            break;
                        }
                        GameMap.writeMap(outWriter);
                        Characters.writeAllCharacters(outWriter);
                        outWriter.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                        break;
                    }
                }
            }
        }
        if (Characters.getCallienceCount() == 0) {
            outWriter.print("Game Finished\nZorde Wins");
        }
        else if (Characters.getZordeCount() == 0) {
            outWriter.print("Game Finished\nCalliance Wins");
        }
        commandReader.close();
        outWriter.close();
    }
}