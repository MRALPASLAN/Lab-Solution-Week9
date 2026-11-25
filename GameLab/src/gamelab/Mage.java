package gamelab;

public class Mage extends GameCharacter implements Attacker, Supporter {
    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        this.mana = Math.max(0, mana);
    }

    public int getMana() {
        return mana;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 8 + mana;
    }

    @Override
    public int attack(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) {
            System.out.println(getName() + " cannot attack.");
            return 0;
        }
        if (mana < 20) {
            System.out.println(getName() + " does not have enough mana to attack!");
            return 0;
        }
        int power = calculatePower();
        mana -= 20;
        int damage = power / 3;
        int dealt = target.takeDamage(damage);
        System.out.println(getName() + " (Mage) attacks " + target.getName() + " for " + dealt + " damage. (Mana left: " + mana + ")");
        return dealt;
    }

    @Override
    public int heal(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) {
            System.out.println(getName() + " cannot heal.");
            return 0;
        }
        if (mana < 15) {
            System.out.println(getName() + " does not have enough mana to heal!");
            return 0;
        }
        mana -= 15;
        int amount = getLevel() * 2;
        int restored = target.restoreHealth(amount);
        System.out.println(getName() + " (Mage) heals " + target.getName() + " for " + restored + " HP. (Mana left: " + mana + ")");
        return restored;
    }

    @Override
    public String getCharacterInfo() {
        return String.format("Mage [Name=%s, Level=%d, Health=%d/%d, Mana=%d, Power=%d, Alive=%b]",
                getName(), getLevel(), getHealth(), getMaxHealth(), mana, calculatePower(), isAlive());
    }
}
