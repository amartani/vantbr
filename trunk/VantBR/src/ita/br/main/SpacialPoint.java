package ita.br.main;

public class SpacialPoint implements WayPoint, Cloneable {

	private double pointX;

	private double pointY;

	private double pointZ;

	public SpacialPoint(double X, double Y, double Z) {
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
		if (other instanceof SpacialPoint) {
			SpacialPoint o = (SpacialPoint) other;
			return o.getPointX() == this.getPointX()
					&& o.getPointY() == this.getPointY()
					&& o.getPointZ() == this.getPointZ();
		}
		return false;
	}

	// public double getHorizontalDistanceFrom(SpacialPoint other) {
	// double deltaX = other.getPointX() - this.getPointX();
	// double deltaY = other.getPointY() - this.getPointY();
	// return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
	// }

	public SpacialPoint clone() {
		return new SpacialPoint(this.getPointX(), this.getPointY(), this
				.getPointZ());
	}

	public String toString() {
		return "SpacialPoint(" + this.getPointX() + ", " + this.getPointY()
				+ ", " + this.getPointZ() + ")";
	}

	@Override
	public double toPointDistance(WayPoint p) {
		if (!(p instanceof SpacialPoint)) {
			throw new WayPointRuntimeException("Incompatible waypoints.");
		}
		SpacialPoint other = (SpacialPoint) p;
		double deltaX = other.getPointX() - this.getPointX();
		double deltaY = other.getPointY() - this.getPointY();
		double deltaZ = other.getPointZ() - this.getPointZ();
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)
				+ Math.pow(deltaZ, 2));
	}

	public PlanPoint toPlanPoint() {
		return new PlanPoint(getPointX(), getPointY());
	}

}
