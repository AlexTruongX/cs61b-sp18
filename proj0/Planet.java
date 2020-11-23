public class Planet {
	/* Instance Variables */
	public double xxPos; // Current X position
	public double yyPos; // Current y position
	public double xxVel; // Current velocity in X direciton 
	public double yyVel; // Current velocity in Y direction
	public double mass; // Mass
	public String imgFileName; // Name of file that corresponds to img in body e.g jupiter.gif
	static final double gravity = 6.67e-11; // Good practice to declare any constants as "static final variable in class"

	public Planet(double xP, double yP, double xV,
				double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;

	}
	public Planet(Planet b) {
		/**	1) New keyword is a Java operator that creates an object. 
		   	2) New operator is followed by a call to a constructor,
			which initializes the new object.
		*/
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;

	}

	public double calcDistance(Planet n) {
		/* Applies Distance Formula between Planet's positions */
		double x1 = this.xxPos;
		double y1 = this.yyPos;
		double x2 = n.xxPos;
		double y2 = n.yyPos; 
		double x_Diff_squared = Math.pow((x1-x2), 2);
		double y_Diff_squared = Math.pow((y1-y2), 2); 

		return Math.pow((x_Diff_squared + y_Diff_squared), 0.5); 
	}
	
	public double calcForceExertedBy(Planet n) {
		/**	Instance Method that calculates the force Planet n exerts onto this instance's Planet 
		* 	EQUATION:  F = (G * m1 * m2)/r^2
		*	Planet n's mass comes first because Planet n is EXERTING FORCE onto "this planet"
		*/
		double m1 = n.mass; 
		double m2 = this.mass; 
		double x_Diff = n.xxPos - this.xxPos;
		double y_Diff = n.yyPos - this.yyPos;
		double r_squared = Math.pow(x_Diff, 2) + Math.pow(y_Diff, 2);

		return ((gravity*m1*m2)/r_squared);
	}
	public double calcForceExertedByX(Planet n) {
		/* Calculates net Force in x direction between two planets */
		double force = this.calcForceExertedBy(n);
		double dx = n.xxPos - this.xxPos;
		double dy = n.yyPos - this.yyPos;
		double r = Math.pow((Math.pow(dx, 2) + Math.pow(dy, 2)), 0.5);

		return ((force*dx)/r);
	}
	public double calcForceExertedByY(Planet n) {
		/* Calculates net Force in y direction between two planets */
		double force = this.calcForceExertedBy(n);
		double dx = n.xxPos - this.xxPos;
		double dy = n.yyPos - this.yyPos;
		double r = Math.pow((Math.pow(dx, 2) + Math.pow(dy, 2)), 0.5);

		return ((force*dy)/r);
	}
	public double calcNetForceExertedByX(Planet[] n) {
		/* Calcuates TOTAL net X force of ALL planets within n array and the current planet "this" */
		double netForceExertedByX = 0.0;
		for (Planet i: n) {
			if (this.equals(i)) {
				continue;
			}
			else {
				netForceExertedByX += this.calcForceExertedByX(i);
			}	
		}
		return netForceExertedByX; 
	}
	public double calcNetForceExertedByY(Planet[] n) {
		/* The sum of all Planet's forces within the n[] EXERTED ONTO the current planet */
		double netForceExertedByY = 0.0;
		for (Planet i : n) { // Enhanced for loop where "i" takes on the identity of each Planet in n exactly once 
			if (this.equals(i)) { // It continues from n[0] -> n[n.length-1]
				continue;
			}
			else {
				netForceExertedByY += this.calcForceExertedByY(i);
			}	
		}
		return netForceExertedByY; 
	}

	public void update(double dt, double fX, double fY) {
		/* 	ax = Fx/m (netForceExertedByX/mass)
			ay = Fy/m (netForceExertedByY/mass)

			1. Calculate the acceleration using the provided x- and y- forces (fX and fY)
			2. Calculate new velocity by using the acceleration AND current velocity
				New velocity = (vx + dt * ax, vy + dt * ay)
			3. Calculate new position by using the velocity computed in Step 2 and the current position. 
				The new position is (px + dt * vx, py + dt * vy) */

		/* Net acceleration */
		double xAccel = fX/this.mass;
		double yAccel = fY/this.mass;

		/* New Velocity, using new acceleration */ 
		double newVelX = this.xxVel + (dt * xAccel);
		double newVelY = this.yyVel + (dt * yAccel);

		/* Position of the planet */
		double newPosX = this.xxPos + (dt * newVelX);
		double newPosY = this.yyPos + (dt * newVelY);

		/* Updating Planet's Position and Velocity*/ 
		this.xxPos = newPosX;
		this.yyPos = newPosY;
		this.xxVel = newVelX;
		this.yyVel = newVelY;

	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}