import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

/**
 * Class for Bow
 * 
 * @author Patrick Hara, Tharun Parackal
 */

public class Bow extends Group
{	
	Rotate rotateBow = new Rotate(90, Rotate.Y_AXIS);
	
	public Bow()
	{
		super();
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
		bow.setScaleX(50);
		bow.setScaleY(-50);
		bow.setScaleZ(-50);
		bow.setTranslateX(0);
		bow.setTranslateY(0);
		bow.setTranslateZ(0);
		bow.getTransforms().addAll(rotateBow);
		this.getChildren().addAll(bow);
	}
	
	public void update() // bow stays fixed in one location
	{
		setTranslateX(0);
		setTranslateY(0);
		setTranslateZ(0);
	}
	
}
