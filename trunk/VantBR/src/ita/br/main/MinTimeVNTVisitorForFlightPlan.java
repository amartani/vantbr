package ita.br.main;

public class MinTimeVNTVisitorForFlightPlan implements MinTimeVNTVisitor {

	double minTime;
	VNT fasterVNT;
	FlightPlan fp;

	public MinTimeVNTVisitorForFlightPlan(FlightPlan fp) {
		this.fp = fp;
		minTime = Double.MAX_VALUE;
		fasterVNT = null;
	}

	@Override
	public VNT getFasterVNT() {
		return fasterVNT;
	}

	@Override
	public double getMinTime() {
		return minTime;
	}

	@Override
	public void visit(VANT vant) {
		if (vant.getAltitudeCapability() < fp.getMaxAltitude()) {
			return;
		}

		double newTime = fp.getMissionDistance() / vant.getVelocity();
		if (newTime < minTime) {
			minTime = newTime;
			fasterVNT = vant;
		}
	}

	@Override
	public void visit(VTNT vtnt) {
		return;
	}

}
