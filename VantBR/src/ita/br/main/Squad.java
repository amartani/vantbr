package ita.br.main;

import java.util.Set;

public class Squad {

	private int soldiers;

	private Set<VNT> vnts;

	private String name;

	// private static final int MAXTIME = Integer.MAX_VALUE;

	public Squad(String name, int soldiers, Set<VNT> vnts) {
		super();
		this.setName(name);
		this.setSoldiers(soldiers);
		this.setVNTs(vnts);
	}

	public int getSoldiers() {
		return soldiers;
	}

	public Set<VNT> getVNTs() {
		return vnts;
	}

	public Boolean addVNT(VNT vnt) {
		return vnts.add(vnt);
	}

	private void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}

	private void setVNTs(Set<VNT> vnts) {
		this.vnts = vnts;
	}

	// public double getMissionTime(FlightPlan fp) {
	// double result = MAXTIME;
	// Iterator<VNT> it = this.getUavs().iterator();
	// while (it.hasNext()) {
	// VNT toWork = it.next();
	// double time;
	// if ((time = toWork.getTimeToMission(fp)) != -1) {
	// result = Math.min(result, time);
	// }
	// }
	// return result;
	// }

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getName();
	}

	public String description() {
		return getName() + ": " + getSoldiers() + " soldiers, "
				+ getVNTs().size() + " UAVs";
	}

}
