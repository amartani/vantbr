package ita.br.main;

public class VNTVisitorFactory {
	public VNTVisitorFactory() {
		super();
	}

	public MinTimeVNTVisitor createMinTimeVNTVisitor(FlightPlan fp) {
		return new MinTimeVNTVisitorForFlightPlan(fp);
	}

	public MinTimeVNTVisitor createMinTimeVNTVisitor(TerrestrialPlan tp) {
		return new MinTimeVNTVisitorForTerrestrialPlan(tp);
	}

	public MinTimeVNTVisitor createMinTimeVNTVisitor(MissionPlan mp) {
		if (mp instanceof FlightPlan) {
			return createMinTimeVNTVisitor((FlightPlan) mp);
		} else if (mp instanceof TerrestrialPlan) {
			return createMinTimeVNTVisitor((TerrestrialPlan) mp);
		}
		return null;
	}
}
