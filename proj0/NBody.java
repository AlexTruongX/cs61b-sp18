public class NBody {
    
    public static double readRadius(String fileName) {
        /* Returns radius of the entire universe given a file 
        *   This method is static because it's a class method and does not require an instance
        */ 
        In in = new In(fileName);
        in.readLine(); // Skips the first line (# of planets)
        double radius = in.readDouble(); // Stores the radius 

        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int arraySize = in.readInt();
        Planet[] planetList = new Planet[arraySize];
        in.readDouble(); // Skips the universe radius

        /** Creates an array of planets using data from fileName
        *   Every loop corresponds to 1 Planet instance created. 
        */
        for (int i=0; i < planetList.length; i++) {
            Planet tempPlanet = new Planet(0.0, 0.0, 0.0, 0.0, 0.0, "temp");
            tempPlanet.xxPos = in.readDouble();
            tempPlanet.yyPos = in.readDouble();
            tempPlanet.xxVel = in.readDouble();
            tempPlanet.yyVel = in.readDouble();
            tempPlanet.mass = in.readDouble();
            tempPlanet.imgFileName = in.readString();
            planetList[i] = tempPlanet; // Arrays 
        }
        return planetList;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        // Creates planets array & records universe radius
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        /* Sets scale to universe radius & draws background image */
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
        StdDraw.enableDoubleBuffering();

        for(int time=0; time <= T; time += dt) {
            /* Stores the netForceExertedBy each planet */
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            /** Calculats the net x and y forces for each planet
             * and stores them in xForces and yForces respectively.
            */
            for (int i=0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /* Updates each planet's position, velocity, and acceleration */
            for (int i=0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /* Draw the background image */
            StdDraw.picture(0.0, 0.0, "images/starfield.jpg");

            /* Draw all of the planets */
            for (int i=0; i < planets.length; i++) {
                planets[i].draw();
            }

            /* Show offscreen buffer & pause for 10ms */
            StdDraw.show();
            StdDraw.pause(10);
        }
        /** Prints out the final state of the universe
         *  in the same format as the input.  
         */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}