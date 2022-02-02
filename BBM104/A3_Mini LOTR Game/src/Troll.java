public class Troll extends ZordeCharacters {
    public Troll(String uniqueID, int characterMaxHP, int characterAP, int maxMove, int positionX, int positionY) {
        super(uniqueID, characterMaxHP, characterAP, maxMove, positionX, positionY);
    }

    @Override
    public String attack(int moveX, int moveY, int moveCount) {
        return this.classicFinalStep(moveX,moveY,moveCount);
    }
}