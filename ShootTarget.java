import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import javafx.scene.transform.Transform;


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
	
	final int FPS = 30;
	public int numArrows = 4; // amount of arrows left
	Bow bow;
	Arrow arrow;
<<<<<<< HEAD
	Target target1;
	Target target2;
=======
>>>>>>> c5027763b27ef505f327c61d03b93454131059a2
	Group root;
//	Group arrow;
//	public static int bowX = 0;
//	public static int bowY = 0;
	
	private void constructWorld(Group root) 
	{
		// AmbientLight light = new AmbientLight();
//		AmbientLight light = new AmbientLight(Color.rgb(100, 100, 100));

//		PointLight pl = new PointLight();
//		pl.setTranslateX(100);
//		pl.setTranslateY(-180);
//		pl.setTranslateZ(-100);
//		root.getChildren().add(pl);
		
//		ObjView drvr = new ObjView();
//		try
//		{
//			drvr.load(ClassLoader.getSystemResource("Bow.obj").toString());
//		} 
//		catch (IOException e) 
//		{
//			System.out.println("Trouble loading model");
//			e.printStackTrace();
//		}
//		Group bow = drvr.getRoot();
//		bow.setScaleX(50);
//		bow.setScaleY(-50);
//		bow.setScaleZ(-50);
//		bow.setTranslateX(0);
//		bow.setTranslateY(0);
//		bow.setTranslateZ(0);
//		Rotate rotateBow = new Rotate(90, Rotate.Y_AXIS);
//		bow.getTransforms().addAll(rotateBow);
		
//		root.getChildren().add(bow);
//		for (Node n:bow.getChildren())
//		{
//			MeshView mv = (MeshView) n;
//			Mesh m = ((MeshView) n).getMesh();
//			//mv.setDrawMode(DrawMode.LINE);
//			System.out.println(n);
//			System.out.println(m);
//			TriangleMesh tm = (TriangleMesh) m;
//			System.out.println("Faces: "+tm.getFaceElementSize());
//			System.out.println(tm.getFaces() );
//			System.out.println(tm.getFaceSmoothingGroups());
//			System.out.println("Normals: "+tm.getNormalElementSize());
//			System.out.println(tm.getNormals());
//			System.out.println("Points: "+tm.getPointElementSize());
//			System.out.println(tm.getPoints());
//			System.out.println("TexCoords: "+tm.getTexCoordElementSize());
//			System.out.println(tm.getTexCoords());
//		}
<<<<<<< HEAD
		
		bow = new Bow();
		arrow = new Arrow();
		target1 = new Target(50, 5, Color.BLUE, 1, 300);
		target2 = new Target(50, 5, Color.GREEN, 3, 350);
=======
		
		bow = new Bow();
		arrow = new Arrow();
		
>>>>>>> c5027763b27ef505f327c61d03b93454131059a2
//		ObjView drvr2 = new ObjView();
//		try
//		{
//			drvr2.load(ClassLoader.getSystemResource("Arrow.obj").toString());
//		} 
//		catch (IOException e) 
//		{
//			System.out.println("Trouble loading model");
//			e.printStackTrace();
//		}
//		arrow = drvr2.getRoot();
//		arrow.setScaleX(30);
//		arrow.setScaleY(-30);
//		arrow.setScaleZ(30);
//		arrow.setTranslateX(150);
//		arrow.setTranslateY(-25);
//		arrow.setTranslateZ(0);
//		
//		
//		Rotate rotateArrow = new Rotate(90, Rotate.X_AXIS);
//		arrow.getTransforms().addAll(rotateArrow);
//		root.getChildren().add(arrow);
		root.getChildren().addAll(bow, arrow, target1, target2);
	}
	
	// Launch arrow
//		void fire()
//		{
//			if (arrow.getTranslateX() == 150 && arrow.getTranslateY() == -25)
//			{
////				ball = new Bubble(8, Color.BLUE);
//				Point3D loc = arrow.localToScene(0, 0, 60);
//				arrow.setTranslateX(loc.getX());
//				arrow.setTranslateY(loc.getY());
//				arrow.setTranslateZ(loc.getZ());
//				Transform rot = arrow.getTransforms().get(0);
//				Point3D vel = rot.deltaTransform(0, 0, 4);
//				//System.out.println(vel);
//				Arrow.vZ = vel.getZ();
//				arrow.setVisible(true);
//				root.getChildren().add(arrow);
//			}
//		}
		
		public void update()
		{
			bow.update();
			arrow.update();
<<<<<<< HEAD
			target1.update();
			target2.update();
=======
>>>>>>> c5027763b27ef505f327c61d03b93454131059a2
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
//			fire();
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
		
		// Setup and start animation loop (Timeline)
				KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
						e -> {
							// update position
<<<<<<< HEAD
							update();
=======
							update(sceneRoot);
>>>>>>> c5027763b27ef505f327c61d03b93454131059a2
						}
					);
				Timeline mainLoop = new Timeline(kf);
				mainLoop.setCycleCount(Animation.INDEFINITE);
				mainLoop.play();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}
