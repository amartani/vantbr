package ita.br.main;

import java.util.ArrayList;
import java.util.List;

public class FlightPlan implements MissionPlan, Cloneable {

	private List<SpacialPoint> rota;

	private int payLoad;

	private double maxAltitude = 0;

	public FlightPlan(List<SpacialPoint> rota, int payLoad) {
		this.setRota(rota);
		this.setPayLoad(payLoad);
	}

	private void setMaxAltitude(double maxAltitude) {
		this.maxAltitude = maxAltitude;
	}

	public List<SpacialPoint> getRota() {
		return rota;
	}

	public int getPayLoad() {
		return payLoad;
	}

	public double getMaxAltitude() {
		return maxAltitude;
	}

	public void addWayPoint(SpacialPoint wp) {
		this.getRota().add(wp);
		this.setMaxAltitude(Math.max(this.getMaxAltitude(), wp.getPointZ()));
	}

	public void addInitialPoint(SpacialPoint wp) {
		this.getRota().add(0, wp);
		this.setMaxAltitude(Math.max(this.getMaxAltitude(), wp.getPointZ()));
	}

	public void addWayPoint(WayPoint wp) {
		if (wp instanceof SpacialPoint) {
			addWayPoint((SpacialPoint) wp);
		} else {
			throw new WayPointRuntimeException("Incompatible waypoint.");
		}
	}

	public void addInitialPoint(WayPoint wp) {
		if (wp instanceof SpacialPoint) {
			addInitialPoint((SpacialPoint) wp);
		} else {
			throw new WayPointRuntimeException("Incompatible waypoint.");
		}
	}

	private void setRota(List<SpacialPoint> rota) {
		this.rota = rota;
		this.findMaxAltitude();
	}

	private void findMaxAltitude() {
		this.setMaxAltitude(0);
		for (int i = 0; i < rota.size(); i++) {
			SpacialPoint wp = rota.get(i);
			this
					.setMaxAltitude(Math.max(this.getMaxAltitude(), wp
							.getPointZ()));
		}
	}

	private void setPayLoad(int payLoad) {
		this.payLoad = payLoad;
	}

	public double getMissionDistance() {
		double result = 0;
		for (int i = 1; i < this.getRota().size(); i++) {
			result += this.getRota().get(i).toPointDistance(
					this.getRota().get(i - 1));
		}
		return result;
	}

	public FlightPlan clone() {
		List<SpacialPoint> newRota = this.getNewRota();
		return new FlightPlan(newRota, this.getPayLoad());
	}

	private List<SpacialPoint> getNewRota() {
		List<SpacialPoint> result = new ArrayList<SpacialPoint>();

		for (SpacialPoint wp : this.getRota()) {
			result.add(wp.clone());
		}
		return result;
	}

	public SpacialPoint getFirstWayPoint() {
		return rota.get(0);
	}

	public SpacialPoint getLastWayPoint() {
		return rota.get(rota.size() - 1);
	}
}
