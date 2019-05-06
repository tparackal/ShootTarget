import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
public class Target extends Cylinder {
	public double vX = 10;
	public double vY = 10;
	int direction;
	int dirCount = 25;
	Rotate rotateTarget = new Rotate(90, Rotate.X_AXIS);
	public Target(double radius, double height, Color fill, int direct, int z) {
		super(radius, height);
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
	public void update() {
		switch(direction) {
			case 1:
				setTranslateY(getTranslateY() - vY);
				dirCount++;
				if(dirCount > 50) {
					dirCount = 0;
					direction = 2;
					break;
				}
				break;
			case 2:
				setTranslateY(getTranslateY() + vY);
				dirCount++;
				if(dirCount > 50) {
					dirCount = 0;
					direction = 1;
					break;
				}
				break;
			case 3:
				setTranslateX(getTranslateX() - vX);
				dirCount++;
				if(dirCount > 50) {
					dirCount = 0;
					direction = 4;
					break;
				}
				break;
			case 4:
				setTranslateX(getTranslateX() + vX);
				dirCount++;
				if(dirCount > 50) {
					dirCount = 0;
					direction = 3;
					break;
				}
				break;
		}
//		setTranslateY(getTranslateY() - vY);
		
	}
	
	
}
