package gamelab;

import java.util.ArrayList;

public class Team {
    private ArrayList<GameCharacter> members;

    public Team() {
        this.members = new ArrayList<>();
    }

    public boolean addCharacter(GameCharacter character) {
        if (character == null) {
            return false;
        }
        if (findByName(character.getName()) != null) {
            System.out.println("Error: A character with the name '" + character.getName() + "' is already in the team.");
            return false;
        }
        members.add(character);
        System.out.println(character.getName() + " joined the team.");
        return true;
    }

    public boolean removeCharacter(String name) {
        GameCharacter found = findByName(name);
        if (found != null) {
            members.remove(found);
            System.out.println(name + " removed from the team.");
            return true;
        }
        System.out.println("Character '" + name + "' not found in the team.");
        return false;
    }

    public GameCharacter findByName(String name) {
        if (name == null) {
            return null;
        }
        for (GameCharacter c : members) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public void attackAll(GameCharacter target) {
        if (target == null || !target.isAlive()) {
            System.out.println("Target is already defeated or invalid.");
            return;
        }
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Attacker) {
                if (!target.isAlive()) {
                    break;
                }
                ((Attacker) c).attack(target);
            }
        }
    }

    public void healTeam() {
        for (GameCharacter supporter : members) {
            if (supporter.isAlive() && supporter instanceof Supporter) {
                GameCharacter lowest = findLowestHealthRatioAliveMember();
                if (lowest != null) {
                    ((Supporter) supporter).heal(lowest);
                }
            }
        }
    }

    private GameCharacter findLowestHealthRatioAliveMember() {
        GameCharacter lowest = null;
        double minRatio = Double.MAX_VALUE;
        for (GameCharacter c : members) {
            if (c.isAlive()) {
                double ratio = (double) c.getHealth() / c.getMaxHealth();
                if (ratio < minRatio) {
                    minRatio = ratio;
                    lowest = c;
                }
            }
        }
        return lowest;
    }

    public int getTotalPower() {
        int total = 0;
        for (GameCharacter c : members) {
            if (c.isAlive()) {
                total += c.calculatePower();
            }
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("=== Team Members ===");
        if (members.isEmpty()) {
            System.out.println("(No members in team)");
        } else {
            for (GameCharacter c : members) {
                System.out.println(c.getCharacterInfo());
            }
        }
        System.out.println("Total Alive Team Power: " + getTotalPower());
        System.out.println("====================");
    }
}
