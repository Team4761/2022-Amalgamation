import java.lang.Math;
class Trajectory
{
    // All math is done using METERS
    public static double velocity=0;
    public static double angle;
    
    final static double max_height = 8;
    final static double xg = 0;
    final static double yg = 2.64;
    final static double xc = 0.46;
    final static double yc = 2.77;
    final static double yb = 0;
    
    // Math for finding this can be found here: https://www.desmos.com/calculator/hjpblf25yh
    public void calculateTrajectory(double xb){
        // xb is equal to the distance between the center of the goal and the shooter
        xb = 0-xb; // Cause my math got messed up
        double a = (yg-yb + ((yc*yb-xb*yb-yc*xg+yb*xg) / (xc-xb))) / ((xg*xg-xb*xb)+((xc*xc*xg-xb*xb*xg-xc*xc*xb+xb*xb*xb) / (xb-xc)));
        double b = (yc-a*xc*xc-yb+a*xb*xb) / (xc-xb);
        double c = yb-a*xb*xb-b*xb;
        
        double mp = (((0-b-Math.sqrt(b*b-4*a*c)) / (2*a)) + xb) / 2; // Will be used to calculate highest point
        double high = a*mp*mp+b*mp+c;
        
        double angle = Math.atan(2*a*xb+b); // In radians
        
        if (high>max_height){
            double yt = max_height;
            double xt = mp;
            a = (yt-yb + ((yc*yb-xb*yb-yc*xt+yb*xt) / (xc-xb))) / ((xt*xt-xb*xb)+((xc*xc*xt-xb*xb*xt-xc*xc*xb+xb*xb*xb) / (xb-xc)));
            b = (yc-a*xc*xc-yb+a*xb*xb) / (xc-xb);
            c = yb-a*xb*xb-b*xb;
            
            high = 8.0;
        
            angle = Math.atan(2*a*xb+b); // In radians
        }
        double SOA = Math.sin(angle);
        double velocity = Math.sqrt((19.6*8)/(SOA*SOA));
        
        this.velocity = velocity;
        this.angle = angle * 57.2958; // Conversion from radians to degrees
        
        // Make an object of this class and OBJ.velocity is velocity and OBJ.angle is the angle
    }
}
