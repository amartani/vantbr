package ita.br.main;

public class PlanPoint implements WayPoint, Cloneable {

	private double pointX;

	private double pointY;

	public PlanPoint(double X, double Y) {
		this.setPointX(X);
		this.setPointY(Y);
	}

	public double getPointX() {
		return pointX;
	}

	public double getPointY() {
		return pointY;
	}

	private void setPointX(double pointX) {
		this.pointX = pointX;
	}

	private void setPointY(double pointY) {
		this.pointY = pointY;
	}

	public boolean equals(Object other) {
		if (other instanceof PlanPoint) {
			PlanPoint o = (PlanPoint) other;
			return o.getPointX() == this.getPointX()
					&& o.getPointY() == this.getPointY();
		}
		return false;
	}

//	public double getHorizontalDistanceFrom(PlanPoint other) {
//		double deltaX = other.getPointX() - this.getPointX();
//		double deltaY = other.getPointY() - this.getPointY();
//		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
//	}

	public PlanPoint clone() {
		return new PlanPoint(this.getPointX(), this.getPointY());
	}

	public String toString() {
		return "PlanPoint(" + this.getPointX() + ", "
				+ this.getPointY() + ")";
	}

	@Override
	public double toPointDistance(WayPoint p) {
		if (!(p instanceof PlanPoint)) {
			throw new WayPointRuntimeException("Incompatible waypoints.");
		}
		PlanPoint other = (PlanPoint) p;
		double deltaX = other.getPointX() - this.getPointX();
		double deltaY = other.getPointY() - this.getPointY();
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
	}
}
