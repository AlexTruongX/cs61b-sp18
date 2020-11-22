/**  Creates two planets and prints
 *   the pairwise force between them.
 */ 
public class TestPlanet {


    /**
     *  Tests calcForceExertedBy.
     */
    public static void main(String[] args) {
        testPlanet();
    }

    /**
     *  Checks the Planet class to make sure calcForceExertedBy works.
     */
    private static void testPlanet() {
        System.out.println("Checking calcForceExertedBy...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        Planet p3 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        System.out.println("The pairwise force between p1 and p2 is: " + p1.calcForceExertedBy(p2));
    }
}
