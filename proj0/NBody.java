import java.io.File;

/**
 * @Author ZJJ
 * @create 2022/4/27 13:16
 */

public class NBody {
    public static double  readRadius(String path) {
        In in = new In(path);
        in.readInt();
        return in.readDouble();
    }

//I can't figure out why this way is wrong. Why?

//    public static Planet[] readPlanets(String path) {
//        In in = new In(path);
//        int number = in.readInt();
//        Planet[] planets = new Planet[number];

//A planets array object is created, but the object inside has not been instantiated.
//Next, the object is instantiated with a for loop; if it is not instantiated, a null pointer exception will be reported.

//        for(int i = 0; i < number; i++) {
//            planets[i] = new Planet(0,0,0,0,0,"f");
//        }
//        in.readDouble();
//        for(int i = 0; i < number; i++ ) {
//            planets[i].xxPos = in.readDouble();
//            planets[i].yyPos = in.readDouble();
//            planets[i].xxVel = in.readDouble();
//            planets[i].yyPos = in.readDouble();
//            planets[i].mass = in.readDouble();
//            planets[i].imgFileName = in.readString();
//        }
//        return planets;
//    }

    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int num = in.readInt();
        Planet[] planets = new Planet[num];
        in.readDouble();
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planets[i] = planet;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int n = planets.length;

        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg" );
        for(Planet planet : planets) {
            planet.draw();
        }

        for(double t = 0.0; t < T; t += dt) {
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for(int i = 0; i < n; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg" );
            for(Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
