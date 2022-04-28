/**
 * @Author ZJJ
 * @create 2022/4/26 20:17
 */
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;

//another answer: use "this"
//    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
//        this.xxPos = xxPos;
//        this.yyPos = yyPos;
//        this.xxVel = xxVel;
//        this.yyVel = yyVel;
//        this.mass = mass;
//        this.imgFileName = imgFileName;
//    }

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass  = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

//    public  Planet() {}

    public double calcDistance(Planet other) {
        double distance;
        distance = Math.sqrt((this.xxPos - other.xxPos) * (this.xxPos - other.xxPos) +
                             (this.yyPos - other.yyPos) * (this.yyPos - other.yyPos));
        return distance;
    }

    public double calcForceExertedBy(Planet other) {
        double force;
        force = G * this.mass * other.mass / (calcDistance(other) * calcDistance(other));
        return force;
    }

    public double calcForceExertedByX(Planet other){
        double forceByX;
        forceByX = calcForceExertedBy(other) * ((other.xxPos - this.xxPos) / calcDistance(other));
        return forceByX;
    }

    public double calcForceExertedByY(Planet other){
        double forceByY;
        forceByY = calcForceExertedBy(other) * ((other.yyPos - this.yyPos) / calcDistance(other));
        return forceByY;
    }

    public double calcNetForceExertedByX(Planet[] others) {
        double totalForceByX = 0.0;
        for(int i = 0; i < others.length; i++){
            if(!this.equals(others[i])) {
                totalForceByX += calcForceExertedByX(others[i]);
            }
        }
//another way: use "enhanced for"
//        for (Planet other : others) {
//            if (!this.equals(other)){
//                totalForce += calcForceExertedByX(other);
//            }
//        }
        return totalForceByX;
    }

    public double calcNetForceExertedByY(Planet[] others) {
        double totalForceByY = 0.0;
        for(int i = 0; i < others.length; i++){
            if(!this.equals(others[i])) {
                totalForceByY += calcForceExertedByY(others[i]);
            }
        }
        return totalForceByY;
    }

    public void update(double dt, double xForce, double yForce){
        this.xxVel += xForce * dt / this.mass;
        this.yyVel += yForce * dt / this.mass;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
