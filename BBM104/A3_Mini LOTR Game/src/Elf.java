public class Elf extends CallianceCharacters {
    public Elf(String uniqueID, int characterMaxHP, int characterAP, int maxMove, int positionX, int positionY) {
        super(uniqueID, characterMaxHP, characterAP, maxMove, positionX, positionY);
    }

    @Override
    public String attack(int moveX, int moveY, int moveCount) {
        if (this.moveControl(moveX, moveY).equals("empty")) {
            this.move(moveX, moveY);
            if (moveCount == this.getMaxMove()) {
                for (Characters character : this.findNeighbour(2)) {
                    if (character instanceof ZordeCharacters) {
                        if (character.getCurrentHP() - this.getCharacterAP() > 0) {
                            character.setCurrentHP(character.getCurrentHP() - this.getCharacterAP());
                        } else {
                            delCharacters(character);
                        }
                    }
                }
                return "break";
            } else {
                this.emptyCellAttack();
                return "continue";
            }
        } else if (this.moveControl(moveX, moveY).equals("enemy")) {
            this.fightToDeath(Characters.getAllCharacters().get(GameMap.map[this.getPositionY() + moveY][this.getPositionX() + moveX]), moveX, moveY);
            return "break";
        } else {
            return "break";
        }
    }
}