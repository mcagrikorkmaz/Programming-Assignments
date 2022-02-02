import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;

public abstract class Characters {
    private final String uniqueID;
    private int positionX;
    private int positionY;
    private int currentHP;
    private final int characterMaxHP;
    private final int characterAP;
    private final int maxMove;
    private static int callienceCount;
    private static int zordeCount;
    private static TreeMap<String, Characters> allCharacters = new TreeMap<>();

    public Characters(String uniqueID, int characterMaxHP, int characterAP, int maxMove, int positionX, int positionY) {
        this.uniqueID = uniqueID;
        this.positionX = positionX;
        this.positionY = positionY;
        this.currentHP = characterMaxHP;
        this.characterMaxHP = characterMaxHP;
        this.characterAP = characterAP;
        this.maxMove = maxMove;
        GameMap.map[positionY][positionX] = uniqueID;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getMaxMove() {
        return maxMove;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public int getCharacterAP() {
        return characterAP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public static int getCallienceCount() {
        return callienceCount;
    }

    public static int getZordeCount() {
        return zordeCount;
    }

    public static TreeMap<String, Characters> getAllCharacters() {
        return allCharacters;
    }

    public static void writeAllCharacters(PrintWriter outWriter) {
        for (Characters character : allCharacters.values()) {
            outWriter.println(character.uniqueID + "\t" + character.currentHP + "\t(" + character.characterMaxHP + ")");
        }
        outWriter.println();
    }

    public static void addCharacters(String key, Characters character) {
        allCharacters.put(key, character);
        if (character instanceof CallianceCharacters) {
            callienceCount++;
        } else {
            zordeCount++;
        }
    }

    public static void delCharacters(Characters character) {
        GameMap.map[character.positionY][character.positionX] = "  ";
        if (character instanceof CallianceCharacters) {
            callienceCount--;
        } else {
            zordeCount--;
        }
        allCharacters.remove(character.getUniqueID());
    }

    public abstract String attack(int moveX, int moveY, int moveCount);

    public String moveControl(int moveX, int moveY) {
        if (this.positionX + moveX < 0 || this.positionX + moveX > GameMap.map.length - 1 ||
                this.positionY + moveY < 0 || this.positionY + moveY > GameMap.map.length - 1) {
            return "BoundaryException";
        } else if (GameMap.map[this.positionY + moveY][this.positionX + moveX].equals("  ")) {
            return "empty";
        } else if (this instanceof ZordeCharacters) {
            if (allCharacters.get(GameMap.map[this.positionY + moveY][this.positionX + moveX]) instanceof ZordeCharacters) {
                return "ally";
            }
        } else if (this instanceof CallianceCharacters) {
            if (allCharacters.get(GameMap.map[this.positionY + moveY][this.positionX + moveX]) instanceof CallianceCharacters) {
                return "ally";
            }
        }
        return "enemy";
    }

    public void move(int moveX, int moveY) {
        GameMap.map[this.positionY + moveY][this.positionX + moveX] = this.uniqueID;
        GameMap.map[this.positionY][this.positionX] = "  ";
        this.positionX = this.positionX + moveX;
        this.positionY = this.positionY + moveY;
    }

    public void emptyCellAttack(){
        boolean toDefineEnemy1 = this instanceof ZordeCharacters;
        boolean toDefineEnemy2 = this instanceof CallianceCharacters;
        for (Characters character : this.findNeighbour(1)) {
            if ((toDefineEnemy1 && character instanceof CallianceCharacters)||(toDefineEnemy2 && character instanceof ZordeCharacters)) {
                if(character.currentHP - this.characterAP > 0){
                    character.setCurrentHP(character.currentHP - this.characterAP);
                }else {
                    delCharacters(character);
                }
            }
        }
    }

    public String classicFinalStep(int moveX, int moveY, int moveCount){
        if (this.moveControl(moveX, moveY).equals("empty")) {
            this.move(moveX, moveY);
            if (moveCount == this.maxMove) {
                this.emptyCellAttack();
                return "break";
            }
            return "continue";
        } else if (this.moveControl(moveX, moveY).equals("enemy")) {
            this.fightToDeath(Characters.getAllCharacters().get(GameMap.map[this.positionY + moveY][this.positionX + moveX]),moveX,moveY);
            return "break";
        } else {
            return "break";
        }
    }

    public String classicEveryStep(int moveX, int moveY, int moveCount){
        if (this.moveControl(moveX, moveY).equals("empty")) {
            this.move(moveX, moveY);
            this.emptyCellAttack();
            if (moveCount == this.maxMove) {
                return "break";
            }
            return "continue";
        } else if (this.moveControl(moveX, moveY).equals("enemy")) {
            this.fightToDeath(Characters.getAllCharacters().get(GameMap.map[this.positionY + moveY][this.positionX + moveX]),moveX,moveY);
            return "break";
        } else {
            return "break";
        }
    }

    public ArrayList<Characters> findNeighbour(int range) {
        ArrayList<Characters> neighbours = new ArrayList<>();
        int MAX_X = GameMap.map.length - 1;
        int MAX_Y = GameMap.map.length - 1;
        int startPosX = Math.max(this.positionX - range, 0);
        int startPosY = Math.max(this.positionY - range, 0);
        int endPosX = Math.min(this.positionX + range, MAX_X);
        int endPosY = Math.min(this.positionY + range, MAX_Y);
        for (int rowNum = startPosY; rowNum <= endPosY; rowNum++) {
            for (int colNum = startPosX; colNum <= endPosX; colNum++) {
                if (allCharacters.containsKey(GameMap.map[rowNum][colNum])) {
                    neighbours.add(allCharacters.get(GameMap.map[rowNum][colNum]));
                }
            }
        }
        return neighbours;
    }

    public void fightToDeath(Characters enemy, int moveX, int moveY) {
        enemy.currentHP = enemy.currentHP - this.characterAP;
        if (enemy.currentHP == this.currentHP) {
            delCharacters(enemy);
            delCharacters(this);
        } else if (this.currentHP > enemy.currentHP) {
            if (enemy.currentHP > 0) {
                this.currentHP = this.currentHP - enemy.currentHP;
            }
            delCharacters(enemy);
            this.move(moveX, moveY);
        } else {
            enemy.currentHP = enemy.currentHP - this.currentHP;
            delCharacters(this);
        }
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = Math.min(currentHP, this.characterMaxHP);
    }
}