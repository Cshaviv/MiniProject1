/**
 * 
 */
package geometries;
import static primitives.Util.*;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Chagit Shaviv 322805482 and Yael Kanfi 212450886
 *
 */
public class Sphere implements Geometry 
{
	Point3D center;
	double radius;
	@Override
	public Vector get_Normal(Point3D p) 
	{
		// TODO Auto-generated method stub
		return p.subtract(this.center).normalize();
	}	
	/**
	 * constructor that get a point and radius and return a sphere
	 * @param center Point3D
	 * @param radius double
	 */
	public Sphere(Point3D center, double radius) {
		super();
		this.center = center;
		this.radius = radius;
	}
	/**the function return the center point of the circle 
	 * @return the center- point3D
	 */
	public Point3D getCenter() {
		return center;
	}
	/**the function return radius
	 * @return the radius-- double value
	 */
	public double getRadius() {
		return radius;
	}
	
	@Override
	public List<Point3D> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		//point and vector of ray
		Point3D p0 = ray.getP0();//ray point
        Vector v = ray.getDir();//ray vector
       //check if ray point is the center
        if(p0.equals(center))       //
        	return List.of(ray.getPoint(radius));
        Vector u=center.subtract(p0);// the vector between center and ray
		double tm=v.dotProduct(u); //the scale for the ray in order to get parallel to the sphere center
		double d=Math.sqrt(u.lengthSquared()-tm*tm);//get the distance between the ray and the sphere center
		//check if d is bigger then radius-the ray doesn't cross the sphere
		if (d>radius)
			return null;
		double th=Math.sqrt(radius*radius-d*d);//according pitagoras
		double t1=tm+th;
		double t2=tm-th;
		if(t1>0&&t2>0&&!isZero(ray.getPoint(t1).subtract(center).dotProduct(v))&&!isZero(ray.getPoint(t2).subtract(center).dotProduct(v)))
			return List.of(ray.getPoint(t1),ray.getPoint(t2));
		else if(t1>0&&!isZero(ray.getPoint(t1).subtract(center).dotProduct(v)))
			return List.of(ray.getPoint(t1));
		else if(t2>0&&!isZero(ray.getPoint(t2).subtract(center).dotProduct(v)))
			return List.of(ray.getPoint(t2));
		else
			return null;
	}
	@Override
	public String toString() {
		return "Sphere [center=" + center.toString() + ", radius=" + radius + "]";
	}
	
	
	
	
 
}
