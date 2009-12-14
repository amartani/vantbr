package ita.br.main;

public class CombatSquad {

	private Squad squad;

	private SpacialPoint localization;

	public CombatSquad(Squad squad, SpacialPoint localization) {
		this.setLocalization(localization);
		this.setSquad(squad);
	}

	public double getMissionTime(MissionPlan mp) {
		MissionPlan toCheck = mp.clone();
		toCheck.addInitialPoint(this.getLocalization());
		toCheck.addWayPoint(this.getLocalization());
		
		VNTVisitorFactory vfactory = new VNTVisitorFactory(); 
		MinTimeVNTVisitor visitor = vfactory.createMinTimeVNTVisitor(mp);
		for (VNT vnt: getSquad().getVNTs()) {
			vnt.accept(visitor);
		}
		return visitor.getMinTime();
		
	}

	private void setSquad(Squad squad) {
		this.squad = squad;
	}

	private void setLocalization(SpacialPoint localization) {
		this.localization = localization;
	}

	public Squad getSquad() {
		return squad;
	}

	public SpacialPoint getLocalization() {
		return localization;
	}
	
	public String toString() {
		return getSquad().toString() + " at " + getLocalization();
	}

}
