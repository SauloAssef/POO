package drawables;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;

import core.BaseLimitedDrawable;

public class CartesianPlane extends BaseLimitedDrawable {
	private final int recuo = 30;
	
	public CartesianPlane(double maxX, double maxY) {
		super(maxX, maxY);
	}
	public void draw(Graphics2D g2d) {
		Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		g2d.setStroke(dashed);
		
		g2d.draw(new Line2D.Double(-1*this.MAX_X+recuo, 0, this.MAX_X-recuo, 0));
		g2d.draw(new Line2D.Double(0, -1*this.MAX_Y+recuo, 0, this.MAX_Y-recuo));
	}
}
