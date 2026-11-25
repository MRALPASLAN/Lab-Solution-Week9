package gamelab;

public class Main {
    
    public static void main(String[] args) {
        
        
        System.out.println("--- Testing Invalid Inputs ---");
        Warrior invalidChar = new Warrior("Shaco", 150, -20, 10);
        System.out.println(invalidChar.getCharacterInfo());

        Team team = new Team();
        Warrior warrior = new Warrior("Garen", 10, 200, 20);
        Mage mage = new Mage("Lux", 12, 120, 80);
        Healer healer = new Healer("Fedva", 8, 130, 25);

        team.addCharacter(warrior);
        team.addCharacter(mage);
        team.addCharacter(healer);

        
        Warrior duplicate = new Warrior("Garen", 5, 100, 10);
        team.addCharacter(duplicate);

        System.out.println("\n--- Initial Team ---");
        team.displayTeam();

        Warrior enemy = new Warrior("Darius", 15, 350, 30);
        System.out.println("\nEnemy appeared: " + enemy.getCharacterInfo());

        
        for (int round = 1; round <= 2; round++) {
            System.out.println("\n=== Round " + round + " ===");
           
            team.attackAll(enemy);

            
            if (enemy.isAlive()) {
                enemy.attack(warrior);
                enemy.attack(mage);
            }

            
            team.healTeam();

            System.out.println("Enemy status: " + enemy.getCharacterInfo());
            team.displayTeam();
        }

        
        System.out.println("\n--- Removing Character ---");
        team.removeCharacter("Lux");

       
        System.out.println("\n--- Searching for Character ---");
        GameCharacter found = team.findByName("Fedva");
        if (found != null) {
            System.out.println("Found: " + found.getCharacterInfo());
        } else {
            System.out.println("Character not found.");
        }

        GameCharacter notFound = team.findByName("Lux");
        System.out.println("Search for Merlin: " + (notFound != null ? notFound.getCharacterInfo() : "Not found"));

       
        System.out.println("\n--- Final Team ---");
        team.displayTeam();
    }
}
