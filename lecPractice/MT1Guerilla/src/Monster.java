public class Monster {
    public String noise = "blargh";
    public static int spookFactor = 5;

    public Monster() {
        System.out.println("Muhahaha!!!");
    }

    public void spook(Monster m) {
        System.out.println("I go " + noise);
    }

    public void spook(Ghoul g) {
        System.out.println("I am " + spookFactor + " spooky.");
    }
}
