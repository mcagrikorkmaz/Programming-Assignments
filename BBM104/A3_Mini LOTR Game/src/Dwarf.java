public class Dwarf extends CallianceCharacters {
    public Dwarf(String uniqueID, int characterMaxHP, int characterAP, int maxMove, int positionX, int positionY) {
        super(uniqueID, characterMaxHP, characterAP, maxMove, positionX, positionY);
    }

    @Override
    public String attack(int moveX, int moveY, int moveCount) {
        return this.classicEveryStep(moveX, moveY, moveCount);
    }
}