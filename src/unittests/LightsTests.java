package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class LightsTests {
	private Scene scene1 = new Scene("Test scene");
	private Scene scene2 = new Scene("Test scene") //
			.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
	private Camera camera1 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(150, 150) //
			.setDistance(1000);
	private Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(200, 200) //
			.setDistance(1000);

	private static Geometry triangle1 = new Triangle( //
			new Point3D(-150, -150, -150), new Point3D(150, -150, -150), new Point3D(75, 75, -150));
	private static Geometry triangle2 = new Triangle( //
			new Point3D(-150, -150, -150), new Point3D(-70, 70, -50), new Point3D(75, 75, -150));
	private static Geometry sphere = new Sphere( new Point3D(0, 0, -50), 50) //
			.setEmmission(new Color(java.awt.Color.BLUE)) //
			.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100));

	/**
	 * Produce a picture of a sphere lighted by a directional light
	 */
	@Test
	public void sphereDirectional() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(new Color(500, 300, 0), new Vector(1, 1, -1)));

		ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a point light
	 */
	@Test
	public void spherePoint() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new PointLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), 1, 0.00001, 0.000001)//
				.setkL(0.00001).setkQ(0.000001));

		ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void sphereSpot() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), 1, 0.00001, 0.00000001, new Vector(1, 1, -2)) //
				.setkL(0.00001).setkQ(0.00000001));

		ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a directional light
	 */
	@Test
	public void trianglesDirectional() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setkD(0.8).setkS(0.2).setnShininess(300)), //
				triangle2.setMaterial(new Material().setkD(0.8).setkS(0.2).setnShininess(300)));
		scene2.lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, -1)));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a point light
	 */
	@Test
	public void trianglesPoint() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)), //
				triangle2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)));
		scene2.lights.add(new PointLight(new Color(500, 250, 250), new Point3D(10, -10, -130),  1, 0.0005, 0.0005) //
				.setkL(0.0005).setkQ(0.0005));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light
	 */
	@Test
	public void trianglesSpot() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)),
				triangle2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)));
		scene2.lights.add(new SpotLight(new Color(500, 250, 250), new Point3D(10, -10, -130),  1, 0.0001, 0.000005, new Vector(-2, -2, -1)) //
				.setkL(0.0001).setkQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * Produce a picture of a sphere lighted by all types of lights
	 */
	@Test
	public void sphereAllLights() {
		scene1.geometries.add(sphere);
		//add all types of light source
		scene1.lights.add(new DirectionalLight(new Color(500, 300, 0), new Vector(-1, -1, -1))); //add directional light
		scene1.lights.add(new PointLight(new Color(500, 300, 0), new Point3D(50, 50, 50), 1, 0.01, 0.00000001)//add point light
				.setkL(0.01).setkQ(0.00000001));
		scene1.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(30, 30, 50), 1, 0.01, 0.00000001, new Vector(3, 1, 0)) //add spot light
				.setkL(0.01).setkQ(0.00000001));
		ImageWriter imageWriter = new ImageWriter("SphereAllLights", 500, 500);
		Render render = new Render()
				.setImageWriter(imageWriter) 
				.setCamera(camera1) 
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by all types of lights
	 */
	@Test
	public void trianglesAllLights() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)),
				triangle2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)));
		//add all types of light source
		scene2.lights.add(new DirectionalLight(new Color(200, 150, 150), new Vector(0, 0, 20))); //add directional light
		scene2.lights.add(new PointLight(new Color(500, 450, 250), new Point3D(10, 10, 130), 1, 0.5, 0.000005) //add point light
				.setkL(0.5).setkQ(0.000005));
		scene2.lights.add(new SpotLight(new Color(350, 250, 700), new Point3D(10, -10, -130), 1, 0.001, 0.0005, new Vector(2, -2, -1)) //add spot light
				.setkL(0.001).setkQ(0.0005));

		ImageWriter imageWriter = new ImageWriter("TrianglesAllLights", 500, 500);
		Render render = new Render()
				.setImageWriter(imageWriter) 
				.setCamera(camera2) 
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/*
	@Test
	public void sphereSpotSharp() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), 1,
				0.000005, 0.00000025,new Vector(1, 1, -2)));

		ImageWriter imageWriter = new ImageWriter("sphereSpotSharp", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}*/

	/**
	 * Produce a picture of a two triangles lighted by a narrow spot light
	 */
	/*
	@Test
	public void trianglesSpotSharp() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)),
				triangle2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)));
		scene2.lights.add(new SpotLight(new Color(800, 400, 400), new Point3D(10, -10, -130), 1,
				0.00005, 0.0000025,new Vector(-2, -2, -1)));

		ImageWriter imageWriter = new ImageWriter("trianglesSpotSharp", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}*/

}