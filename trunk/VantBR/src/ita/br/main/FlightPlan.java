package ita.br.main;

import java.util.ArrayList;
import java.util.List;

public class FlightPlan implements Cloneable {

	private List<WayPoint> rota;

	private int payLoad;

	private double maxAltitude = 0;

	public FlightPlan(List<WayPoint> rota, int payLoad) {
		this.setRota(rota);
		this.setPayLoad(payLoad);
	}

	private void setMaxAltitude(double maxAltitude) {
		this.maxAltitude = maxAltitude;
	}

	public List<WayPoint> getRota() {
		return rota;
	}

	public int getPayLoad() {
		return payLoad;
	}

	public double getMaxAltitude() {
		return maxAltitude;
	}

	public void addWayPoint(WayPoint wp) {
		this.getRota().add(wp);
		this.setMaxAltitude(Math.max(this.getMaxAltitude(), wp.getPointZ()));
	}

	private void setRota(List<WayPoint> rota) {
		this.rota = rota;
		this.findMaxAltitude();
	}

	private void findMaxAltitude() {
		this.setMaxAltitude(0);
		for (int i = 0; i < rota.size(); i++) {
			WayPoint wp = rota.get(i);
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
			result += this.getRota().get(i).getHorizontalDistanceFrom(
					this.getRota().get(i - 1));
		}
		return result;
	}

	public FlightPlan clone() {
		List<WayPoint> newRota = this.getNewRota();
		return new FlightPlan(newRota, this.getPayLoad());
	}

	private List<WayPoint> getNewRota() {
		List<WayPoint> result = new ArrayList<WayPoint>();

		for (WayPoint wp : this.getRota()) {
			result.add(wp.clone());
		}
		return result;
	}
	
	public WayPoint getFirstWayPoint() {
		return rota.get(0);
	}
	
	public WayPoint getLastWayPoint() {
		return rota.get(rota.size()-1);
	}

}
