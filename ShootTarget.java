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

/**
 * Main class
 * 
 * Press the Space Bar to shoot an arrow 
 * Try to hit the two targets
 * You win when you hit both targets
 * You lose when you run out of arrows (start with 4 arrows)
 * 
 * @author Patrick Hara, Tharun Parackal
 */

public class ShootTarget extends Application
{
	private PerspectiveCamera camera;
	private Group cameraDolly;
	private final double cameraQuantity = 10.0;
	private final double sceneWidth = 600;
	private final double sceneHeight = 600;

	
	final int FPS = 30;
	public int numArrows = 4; // amount of arrows left
	Bow bow;
	Arrow arrow;

	public boolean canReload, target1Hit, target2Hit = false;
	
	Target target1;
	Target target2;
	
	final double BOUND = 2000;

	Group root;
	
	private void constructWorld(Group root) 
	{
		bow = new Bow();
		arrow = new Arrow();
		target1 = new Target(50, 5, Color.BLUE, 1, 300);
		target2 = new Target(50, 5, Color.GREEN, 3, 680);
		root.getChildren().addAll(bow, arrow, target1, target2);
	}
	
	// Launch arrow
		void fire()
		{
			arrow.vZ = 20;
			numArrows--;
			System.out.println("ARROWS LEFT: " + numArrows);
		}
		
		void win()
		{
			if(target1Hit && target2Hit)
			{
				System.out.println("YOU WIN!!!!");
				System.out.println("ARROWS LEFT: " + numArrows);
			}
		}
		
		void lose()
		{
			if((numArrows <= 0) && (!target1Hit || !target2Hit))
			{
				System.out.println("YOU LOSE!!!!");
			}
		}
		
		public void update()
		{
			bow.update();
			target1.update();
			target2.update();
			arrow.update();
			win();
			if (arrow.getTranslateZ() != 0) 
			{
				if (target1.isTouching(arrow)) // if the arrow hits the first target
				{
					target1.setTranslateX(-2000);
					target1.setTranslateY(2000);
					target1.vY = 0;
					target1Hit = true;
					System.out.println("Target 1 is Hit");
					canReload = true;
					reload();
					
				}
				if (target2.isTouching(arrow)) // if the arrow hits the second target
				{
					target2.setTranslateX(-2000);
					target2.setTranslateY(2000);
					target2.vX = 0;
					target2Hit = true;
					System.out.println("Target 2 is Hit");
					canReload = true;
					reload();
					

				}
				else if (outside(arrow)) // if the arrow misses and goes past the target
				{
					canReload = true;
					reload();
					lose();
				}
			}

		}
		
		public void reload() // prepares another arrow for launch
		{
			if(canReload && numArrows > 0)
			{
				arrow.vZ = 0;
				arrow.setTranslateX(0);
				arrow.setTranslateY(0);
				arrow.setTranslateZ(0);
				canReload = false;
			}
		}
		
		private boolean outside(Arrow b) 
		{
	        // Check if b is outside BOUND
			double x = b.getTranslateX();
			double y = b.getTranslateY();
			double z = b.getTranslateZ();

	        return (x*x + y*y + z*z > BOUND * BOUND);
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

			if (keycode == KeyCode.SPACE) 
			{
				fire();
			}
		});

		primaryStage.setTitle("Shoot Target");
		
		// Setup and start animation loop (Timeline)
				KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
						e -> {
							// update position

							update();
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
