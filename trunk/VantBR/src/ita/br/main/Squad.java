package ita.br.main;

import java.util.Iterator;
import java.util.Set;

public class Squad {

	private int soldiers;

	private Set<UAV> uavs;

	private String name;

	private static final int MAXTIME = Integer.MAX_VALUE;

	public Squad(String name, int soldiers, Set<UAV> uavs) {
		super();
		this.setName(name);
		this.setSoldiers(soldiers);
		this.setUavs(uavs);
	}

	public int getSoldiers() {
		return soldiers;
	}

	public Set<UAV> getUavs() {
		return uavs;
	}
	
	public Boolean addUAV(UAV uav) {
		return uavs.add(uav);
	}

	private void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}

	private void setUavs(Set<UAV> uavs) {
		this.uavs = uavs;
	}

	public double getMissionTime(FlightPlan fp) {
		double result = MAXTIME;
		Iterator<UAV> it = this.getUavs().iterator();
		while (it.hasNext()) {
			UAV toWork = it.next();
			double time;
			if ((time = toWork.getTimeToMission(fp)) != -1) {
				result = Math.min(result, time);
			}
		}
		return result;
	}

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
		return getName() + ": " + getSoldiers() + " soldiers, " +
			getUavs().size() + " UAVs";
	}

}
