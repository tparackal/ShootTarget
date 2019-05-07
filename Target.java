import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

/**
 * Class for Target
 * 
 * @author Patrick Hara, Tharun Parackal
 */

public class Target extends Cylinder 
{
	public double vX = 10;
	public double vY = 10;
	public double r;
	int direction;
	int dirCount = 25;
	Rotate rotateTarget = new Rotate(90, Rotate.X_AXIS);
	public Target(double radius, double height, Color fill, int direct, int z) 
	{
		super(radius, height);
		r = radius;
		direction = direct;
		final PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(fill);
		mat.setSpecularColor(Color.WHITE);
		setMaterial(mat);
		setTranslateX(150);
		setTranslateY(-25);
		setTranslateZ(z); 
		getTransforms().addAll(rotateTarget);
	}
	public void update() 
	{
		switch(direction) 
		{
			case 1:
				setTranslateY(getTranslateY() - vY);
				dirCount++;
				if(dirCount > 50)
				{
					dirCount = 0;
					direction = 2;
					break;
				}
				break;
			case 2:
				setTranslateY(getTranslateY() + vY);
				dirCount++;
				if(dirCount > 50) 
				{
					dirCount = 0;
					direction = 1;
					break;
				}
				break;
			case 3:
				setTranslateX(getTranslateX() - vX);
				dirCount++;
				if(dirCount > 50) 
				{
					dirCount = 0;
					direction = 4;
					break;
				}
				break;
			case 4:
				setTranslateX(getTranslateX() + vX);
				dirCount++;
				if(dirCount > 50) 
				{
					dirCount = 0;
					direction = 3;
					break;
				}
				break;
		}
		
	}
	
	public boolean isTouching(Arrow arrow) // collision detection
	{
		if(getTranslateZ() == (arrow.getTranslateZ() + 100)) // check if arrow and target have the same Z coordinate
		{
			double dist = distance(arrow.getTranslateX(), arrow.getTranslateY(), getTranslateX()-150, getTranslateY()+25);
			return (dist <= r);
		}
		return false;
			
	}
	
	public static double distance(double x1, double y1, double x2, double y2) // distance formula
	{
		double xsquared = Math.pow(x2 - x1, 2);
		double ysquared = Math.pow(y2 - y1, 2);
		return Math.sqrt(xsquared + ysquared);
	}
}
