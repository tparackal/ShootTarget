import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

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
//		arrow.setTranslateX(-120);
//		arrow.setTranslateY(-150);
		arrow.setTranslateX(150);
		arrow.setTranslateY(-25);
		arrow.setTranslateZ(0); 
		
//		Rotate rotateArrow = new Rotate(90, Rotate.X_AXIS);
		getTransforms().addAll(rotateArrow);
		this.getChildren().addAll(arrow);
	}
	
	public void update() // arrow only moves in the Z direction
	{
//		setTranslateX(getTranslateX() + vX);
//	    setTranslateY(getTranslateY() + vY);
	    setTranslateZ(getTranslateZ() + vZ);
	}
}
