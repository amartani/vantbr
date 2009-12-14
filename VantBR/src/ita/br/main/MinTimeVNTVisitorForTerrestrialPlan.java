package ita.br.main;

public class MinTimeVNTVisitorForTerrestrialPlan implements MinTimeVNTVisitor {

	double minTime;
	VNT fasterVNT;
	TerrestrialPlan tp;
	
	public MinTimeVNTVisitorForTerrestrialPlan(TerrestrialPlan tp) {
		this.tp = tp;
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
		return;
	}

	@Override
	public void visit(VTNT vtnt) {
		double newTime = tp.getMissionDistance()/vtnt.getVelocity();
		if (newTime < minTime) {
			minTime = newTime;
			fasterVNT = vtnt;
		}
	}

}
