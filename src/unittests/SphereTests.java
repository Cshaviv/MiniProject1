/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 * Unit tests for geometries.Sphere class
 * @author Chagit Shaviv 322805482 and Yael Kanfi 212450886
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#get_Normal(primitives.Point3D)}.
	 */
	@Test
	public void testNormal() {
		Point3D p= new Point3D(1, 1, 6);
		Point3D o=new Point3D(1,1,1);
		Sphere s=new Sphere(o,5);
		Vector v= p.subtract(o).normalize(); //v is the normal
		assertEquals("Bad normal to sphere",v,s.get_Normal(p));
		
		// =============== Boundary Values Tests ==================
        // 
        /*try {
        	new Sphere(center, 0).get_Normal(p);
            fail("GetNormal() should throw an exception, but it failed");
        } catch (Exception e) {}*/
	}
	
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersectionPoint() {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull("Ray's line out of sphere",
                            sphere.findIntersections((new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0)))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                                                                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, 0),
                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray from inside sphere", List.of(p2), result);
        
        // TC04: Ray starts after the sphere (0 points)
        assertEquals("Sphere is behind Ray", null, sphere.findIntersections(new Ray(new Point3D(6, 3, 0), new Vector(3, 1, 0))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 0, 1)));
        assertEquals( "Ray starts inside the sphere", List.of(new Point3D(1, 0, 1)), result);
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertEquals("Sphere is behind Ray", null, sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),new Vector(1, 0, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX() > result.get(1).getX())
        	result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(new Point3D(0,0,0), new Point3D(2,0,0)), result);
        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0),new Vector(0, -1, 0)));
        assertEquals( "ray starts inside sphere and crosses sphere",List.of(new Point3D(1, -1, 0)), result);
        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0.5, 0),new Vector(0, -1, 0)));
        assertEquals( "ray starts inside sphere",List.of(new Point3D(1, -1, 0)),result);
        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0),new Vector(0, -1, 0)));
        assertEquals( "ray starts at center",List.of(new Point3D(1, -1, 0)),result);
        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0),new Vector(0, 1, 0)));
        assertEquals( "ray starts at sphere and goes outside",null, result);
        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 2, 0),new Vector(0, 1, 0)));
        assertEquals( "ray is outside sphere",null, result);

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(0, 1, 0),new Vector(0, 1, 0)));
        assertEquals( "Tangent line, ray is before sphere",null, result);
        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0),new Vector(0, 1, 0)));
        assertEquals( "Tangent line, ray at sphere",null, result);
        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(2, 1, 0),new Vector(0, 1, 0)));
        assertEquals( "Tangent line, ray is after sphere",null, result);
        
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),new Vector(0, 0, -1)));
        assertEquals( "Ray is orthogonal to ray head -> O line",null, result);
    }


}