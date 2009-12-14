package ita.br.main;

public class WayPoint implements Cloneable {

	private double pointX;

	private double pointY;

	private double pointZ;

	public WayPoint(double X, double Y, double Z) {
		this.setPointX(X);
		this.setPointY(Y);
		this.setPointZ(Z);
	}

	public double getPointX() {
		return pointX;
	}

	public double getPointY() {
		return pointY;
	}

	public double getPointZ() {
		return pointZ;
	}

	private void setPointX(double pointX) {
		this.pointX = pointX;
	}

	private void setPointY(double pointY) {
		this.pointY = pointY;
	}

	private void setPointZ(double pointZ) {
		this.pointZ = pointZ;
	}

	public boolean equals(Object other) {
		if (other instanceof WayPoint) {
			WayPoint o = (WayPoint) other;
			return o.getPointX() == this.getPointX()
					&& o.getPointY() == this.getPointY()
					&& o.getPointZ() == this.getPointZ();
		}
		return false;
	}

	public double getHorizontalDistanceFrom(WayPoint other) {
		double deltaX = other.getPointX() - this.getPointX();
		double deltaY = other.getPointY() - this.getPointY();
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
	}

	public WayPoint clone() {
		return new WayPoint(this.getPointX(), this.getPointY(), this
				.getPointZ());
	}

	public String toString() {
		return "WayPoint(" + this.getPointX() + ", "
				+ this.getPointY() + ", "
				+ this.getPointZ() + ")";
	}

}
