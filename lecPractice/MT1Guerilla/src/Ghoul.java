public class Ghoul extends Monster {
    public Ghoul() {
        System.out.println("I am a ghoul");
    }

    public void spook(Monster m) {
        System.out.println("Verified!");
    }

    public void spook(Ghoul g) {
        System.out.println("I'm so ghoul");
        ((Monster) g).spook(g);
    }

    public void haunt() {
        Monster m = this;
        System.out.println(noise);
        m.spook(this);
    }

    public static void main(String[] args) {
        Monster m = new Monster();
        m.spook(m);

        Monster g = new Ghoul();
        g.spook(m);
        g.spook(g);

        Monster.spookFactor = 10;
        Ghoul ghastly = new Ghoul();
        ghastly.haunt();
    }

}