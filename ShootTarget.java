import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class ShootTarget extends Application
{
	private PerspectiveCamera camera;
	private Group cameraDolly;
	private final double cameraQuantity = 10.0;
	private final double sceneWidth = 600;
	private final double sceneHeight = 600;

	private double mousePosX;
	private double mousePosY;
	private double mouseOldX;
	private double mouseOldY;
	private double mouseDeltaX;
	private double mouseDeltaY;
	
	private void constructWorld(Group root) 
	{
		// AmbientLight light = new AmbientLight();
//		AmbientLight light = new AmbientLight(Color.rgb(100, 100, 100));

//		PointLight pl = new PointLight();
//		pl.setTranslateX(100);
//		pl.setTranslateY(-180);
//		pl.setTranslateZ(-100);
//		root.getChildren().add(pl);
		
		ObjView drvr = new ObjView();
		try
		{
			drvr.load(ClassLoader.getSystemResource("Bow.obj").toString());
		} 
		catch (IOException e) 
		{
			System.out.println("Trouble loading model");
			e.printStackTrace();
		}
		Group bow = drvr.getRoot();
		bow.setScaleX(70);
		bow.setScaleY(-70);
		bow.setScaleZ(-70);
		bow.setTranslateX(110);
		bow.setTranslateY(-150);
		
//		Rotate rotateBow = new Rotate(90, Rotate.Y_AXIS);
//		bow.getTransforms().addAll(rotateBow);
		
//		root.getChildren().add(bow);
		for (Node n:bow.getChildren())
		{
			MeshView mv = (MeshView) n;
			Mesh m = ((MeshView) n).getMesh();
			//mv.setDrawMode(DrawMode.LINE);
			System.out.println(n);
			System.out.println(m);
			TriangleMesh tm = (TriangleMesh) m;
			System.out.println("Faces: "+tm.getFaceElementSize());
			System.out.println(tm.getFaces() );
			System.out.println(tm.getFaceSmoothingGroups());
			System.out.println("Normals: "+tm.getNormalElementSize());
			System.out.println(tm.getNormals());
			System.out.println("Points: "+tm.getPointElementSize());
			System.out.println(tm.getPoints());
			System.out.println("TexCoords: "+tm.getTexCoordElementSize());
			System.out.println(tm.getTexCoords());
		}
		ObjView drvr2 = new ObjView();
		try
		{
			drvr.load(ClassLoader.getSystemResource("Arrow.obj").toString());
		} 
		catch (IOException e) 
		{
			System.out.println("Trouble loading model");
			e.printStackTrace();
		}
		Group arrow = drvr2.getRoot();
		arrow.setScaleX(70);
		arrow.setScaleY(-70);
		arrow.setScaleZ(70);
		arrow.setTranslateX(-120);
		arrow.setTranslateY(-150);
		
		
		Rotate rotateArrow = new Rotate(90, Rotate.X_AXIS);
		arrow.getTransforms().addAll(rotateArrow);
//		root.getChildren().add(arrow);
		root.getChildren().addAll(bow, arrow);
	}

	@Override
	public void start(Stage primaryStage) 
	{

		// Build your Scene and Camera
		Group sceneRoot = new Group();
		constructWorld(sceneRoot);

		Scene scene = new Scene(sceneRoot, sceneWidth, sceneHeight, true);
		scene.setFill(Color.BLACK);
		camera = new PerspectiveCamera(true);
		camera.setNearClip(0.1);
		camera.setFarClip(10000.0);
		scene.setCamera(camera);
		// translations through dolly
		cameraDolly = new Group();
		cameraDolly.setTranslateZ(-1000);
		cameraDolly.setTranslateX(200);
		cameraDolly.getChildren().add(camera);
		sceneRoot.getChildren().add(cameraDolly);
		// rotation transforms
		Rotate xRotate = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
		Rotate yRotate = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
		camera.getTransforms().addAll(xRotate, yRotate);

		// Use keyboard to control camera position
		scene.setOnKeyPressed(event -> {
			double change = cameraQuantity;
			// What key did the user press?
			KeyCode keycode = event.getCode();

			Point3D delta = null;
			if (keycode == KeyCode.COMMA) 
			{
				delta = new Point3D(0, 0, change);
			}
			if (keycode == KeyCode.PERIOD) 
			{
				delta = new Point3D(0, 0, -change);
			}
			if (keycode == KeyCode.A) 
			{
				delta = new Point3D(-change, 0, 0);
			}
			if (keycode == KeyCode.D) 
			{
				delta = new Point3D(change, 0, 0);
			}
			if (keycode == KeyCode.W) 
			{
				delta = new Point3D(0, -change, 0);
			}
			if (keycode == KeyCode.S) 
			{
				delta = new Point3D(0, change, 0);
			}
			if (delta != null) 
			{
				Point3D delta2 = camera.localToParent(delta);
				cameraDolly.setTranslateX(cameraDolly.getTranslateX() + delta2.getX());
				cameraDolly.setTranslateY(cameraDolly.getTranslateY() + delta2.getY());
				cameraDolly.setTranslateZ(cameraDolly.getTranslateZ() + delta2.getZ());

			}
		});

		// Use mouse to control camera rotation
		scene.setOnMousePressed(me -> 
		{
			mousePosX = me.getSceneX();
			mousePosY = me.getSceneY();
		});

		scene.setOnMouseDragged(me -> 
		{
			mouseOldX = mousePosX;
			mouseOldY = mousePosY;
			mousePosX = me.getSceneX();
			mousePosY = me.getSceneY();
			mouseDeltaX = (mousePosX - mouseOldX);
			mouseDeltaY = (mousePosY - mouseOldY);

			yRotate.setAngle(((yRotate.getAngle() - mouseDeltaX * 0.2) % 360 + 540) % 360 - 180); // +
			xRotate.setAngle(((xRotate.getAngle() + mouseDeltaY * 0.2) % 360 + 540) % 360 - 180); // -
		});

		primaryStage.setTitle("Shoot Target");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}
