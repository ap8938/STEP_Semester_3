import java.util.Scanner;

interface Attackable {

    String attack();

    String attack(String weaponName);
}

interface Defendable {

    String defend();
}

abstract class GameCharacter {

    private static int counter = 0;

    private final String characterId;

    protected String name;

    public GameCharacter(String name) {

        counter++;

        characterId = "CHAR-" + counter;

        this.name = name;
    }

    public abstract String getSpecialMove();

    public String getCharacterId() {

        return characterId;
    }
}

class Warrior
        extends GameCharacter
        implements Attackable, Defendable {

    public Warrior(String name) {

        super(name);
    }

    @Override
    public String attack() {

        return name
                + " strikes with a blade";
    }

    @Override
    public String attack(
            String weaponName) {

        return name
                + " strikes with a "
                + weaponName;
    }

    @Override
    public String defend() {

        return name
                + " raises a shield";
    }

    @Override
    public String getSpecialMove() {

        return name
                + " unleashes Whirlwind Slash";
    }
}

class Trap implements Defendable {

    private String trapType;

    public Trap(String trapType) {

        this.trapType = trapType;
    }

    @Override
    public String defend() {

        return trapType
                + " triggers automatically";
    }
}

public class ArenaDemo {

    static void resolveDefense(
            Defendable[] combatants) {

        for (int i = 0;
             i < combatants.length;
             i++) {

            System.out.println(
                    combatants[i].defend());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter warrior name: ");

        String name = sc.nextLine();

        System.out.print(
                "Enter weapon name: ");

        String weapon = sc.nextLine();

        System.out.print(
                "Enter trap type: ");

        String trapType = sc.nextLine();

        Warrior warrior =
                new Warrior(name);

        Trap trap =
                new Trap(trapType);

        System.out.println();

        System.out.println(
                warrior.attack());

        System.out.println(
                warrior.attack(weapon));

        System.out.println(
                warrior.defend());

        System.out.println(
                warrior.getSpecialMove());

        System.out.println(
                "Character ID: "
                + warrior.getCharacterId());

        System.out.println();

        System.out.println(
                trap.defend());

        System.out.println();

        Defendable[] combatants = {
                warrior,
                trap
        };

        System.out.println(
                "Defense resolution:");

        resolveDefense(combatants);

        sc.close();
    }
}