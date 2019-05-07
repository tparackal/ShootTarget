import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

/**
 * Class for Arrow
 * 
 * @author Patrick Hara, Tharun Parackal
 */

public class Arrow extends Group
{

	public static double vZ = 0;
	
	Rotate rotateArrow = new Rotate(-90, Rotate.X_AXIS);
	
	public Arrow()
	{
		super();
		ObjView drvr2 = new ObjView();
		try
		{
			drvr2.load(ClassLoader.getSystemResource("Arrow.obj").toString());
		} 
		catch (IOException e) 
		{
			System.out.println("Trouble loading model");
			e.printStackTrace();
		}
		Group arrow = drvr2.getRoot();
		arrow.setScaleX(30);
		arrow.setScaleY(-30);
		arrow.setScaleZ(30);
		arrow.setTranslateX(150);
		arrow.setTranslateY(-25);
		arrow.setTranslateZ(0); 
		
		getTransforms().addAll(rotateArrow);
		this.getChildren().addAll(arrow);
	}
	
	public void update() // arrow only moves in the Z direction
	{
	    setTranslateZ(getTranslateZ() + vZ);
	}
}
