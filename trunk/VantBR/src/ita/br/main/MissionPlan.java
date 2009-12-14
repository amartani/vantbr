package ita.br.main;

public interface MissionPlan extends Cloneable {

	public abstract double getMissionDistance();
	public abstract MissionPlan clone();
	public abstract void addInitialPoint(WayPoint wp);
	public abstract void addWayPoint(WayPoint wp);

}