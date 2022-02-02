public class Human extends CallianceCharacters {
    public Human(String uniqueID, int characterMaxHP, int characterAP, int maxMove, int positionX, int positionY) {
        super(uniqueID, characterMaxHP, characterAP, maxMove, positionX, positionY);
    }

    @Override
    public String attack(int moveX, int moveY, int moveCount) {
        return classicFinalStep(moveX, moveY, moveCount);
    }
}