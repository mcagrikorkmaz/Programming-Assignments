public class Ork extends ZordeCharacters {
    public Ork(String uniqueID, int characterMaxHP, int characterAP, int maxMove, int positionX, int positionY) {
        super(uniqueID, characterMaxHP, characterAP, maxMove, positionX, positionY);
    }

    @Override
    public String attack(int moveX, int moveY, int moveCount) {
        return this.classicFinalStep(moveX,moveY,moveCount);
    }

    public void heal(int healPoint) {
        for (Characters character : this.findNeighbour(1)) {
            if (character instanceof ZordeCharacters) {
                character.setCurrentHP(character.getCurrentHP() + healPoint);
            }
        }
    }
}