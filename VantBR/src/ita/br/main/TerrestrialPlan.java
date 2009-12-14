package ita.br.main;

import java.util.ArrayList;
import java.util.List;

public class TerrestrialPlan implements MissionPlan, Cloneable {

	private List<PlanPoint> rota;

	private int payLoad;

	public TerrestrialPlan(List<PlanPoint> rota, int payLoad) {
		this.setRota(rota);
		this.setPayLoad(payLoad);
	}

	public List<PlanPoint> getRota() {
		return rota;
	}

	public int getPayLoad() {
		return payLoad;
	}

	public void addWayPoint(PlanPoint wp) {
		this.getRota().add(wp);
	}

	public void addInitialPoint(PlanPoint wp) {
		this.getRota().add(0, wp);
	}

	public void addWayPoint(SpacialPoint wp) {
		this.getRota().add(wp.toPlanPoint());
	}

	public void addInitialPoint(SpacialPoint wp) {
		this.getRota().add(0, wp.toPlanPoint());
	}

	public void addWayPoint(WayPoint wp) {
		if (wp instanceof SpacialPoint) {
			addWayPoint((SpacialPoint) wp);
		} else if (wp instanceof PlanPoint) {
			addWayPoint((PlanPoint) wp);
		} else {
			throw new WayPointRuntimeException("Incompatible waypoint.");
		}
	}

	public void addInitialPoint(WayPoint wp) {
		if (wp instanceof SpacialPoint) {
			addInitialPoint((SpacialPoint) wp);
		} else if (wp instanceof PlanPoint) {
			addInitialPoint((PlanPoint) wp);
		} else {
			throw new WayPointRuntimeException("Incompatible waypoint.");
		}
	}

	private void setRota(List<PlanPoint> rota) {
		this.rota = rota;
	}

	private void setPayLoad(int payLoad) {
		this.payLoad = payLoad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ita.br.main.MissionPlan#getMissionDistance()
	 */
	public double getMissionDistance() {
		double result = 0;
		for (int i = 1; i < this.getRota().size(); i++) {
			result += this.getRota().get(i).toPointDistance(
					this.getRota().get(i - 1));
		}
		return result;
	}

	public MissionPlan clone() {
		List<PlanPoint> newRota = this.getNewRota();
		return new TerrestrialPlan(newRota, this.getPayLoad());
	}

	private List<PlanPoint> getNewRota() {
		List<PlanPoint> result = new ArrayList<PlanPoint>();

		for (PlanPoint wp : this.getRota()) {
			result.add(wp.clone());
		}
		return result;
	}

	public PlanPoint getFirstWayPoint() {
		return rota.get(0);
	}

	public PlanPoint getLastWayPoint() {
		return rota.get(rota.size() - 1);
	}

}
