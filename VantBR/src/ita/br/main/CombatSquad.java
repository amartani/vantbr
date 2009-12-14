package ita.br.main;

public class CombatSquad {

	private Squad squad;

	private WayPoint localization;

	public CombatSquad(Squad squad, WayPoint localization) {
		this.setLocalization(localization);
		this.setSquad(squad);
	}

	public double getMissionTime(FlightPlan fp) {
		FlightPlan toCheck = fp.clone();
		toCheck.getRota().add(0, this.getLocalization());
		toCheck.getRota().add(this.getLocalization());
		return this.getSquad().getMissionTime(toCheck);
	}

	private void setSquad(Squad squad) {
		this.squad = squad;
	}

	private void setLocalization(WayPoint localization) {
		this.localization = localization;
	}

	public Squad getSquad() {
		return squad;
	}

	public WayPoint getLocalization() {
		return localization;
	}
	
	public String toString() {
		return getSquad().toString() + " at " + getLocalization();
	}

}
