package gamelab;


public class Healer extends GameCharacter implements Supporter {
    private int healAmount;

    public Healer(String name, int level, int maxHealth, int healAmount) {
        super(name, level, maxHealth);
        this.healAmount = Math.max(0, healAmount);
    }

    public int getHealAmount() {
        return healAmount;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 5 + healAmount;
    }

    @Override
    public int heal(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) {
            System.out.println(getName() + " cannot heal.");
            return 0;
        }
        int amount = healAmount + getLevel();
        int restored = target.restoreHealth(amount);
        System.out.println(getName() + " (Healer) heals " + target.getName() + " for " + restored + " HP.");
        return restored;
    }

    @Override
    public String getCharacterInfo() {
        return String.format("Healer [Name=%s, Level=%d, Health=%d/%d, HealAmount=%d, Power=%d, Alive=%b]",
                getName(), getLevel(), getHealth(), getMaxHealth(), healAmount, calculatePower(), isAlive());
    }
}
