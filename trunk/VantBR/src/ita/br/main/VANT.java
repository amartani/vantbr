package ita.br.main;

public class VANT implements VNT {

	private int payLoad;

	private double endurance;

	private int velocity;

	private int altitudeCapability;

	public VANT(int altitudeCapability, double endurance, int payLoad, int velocity) {
		super();
		this.setAltitudeCapability(altitudeCapability);
		this.setEndurance(endurance);
		this.setPayLoad(payLoad);
		this.setVelocity(velocity);
	}

	public double getTimeToMission(FlightPlan fp) {
		if (fp.getPayLoad() < this.getPayLoad()
				&& fp.getMaxAltitude() < this.getAltitudeCapability()) {
			double distance = fp.getMissionDistance();
			return distance / velocity;
		}
		return -1;
	}

	public int getPayLoad() {
		return payLoad;
	}

	public double getEndurance() {
		return endurance;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getAltitudeCapability() {
		return altitudeCapability;
	}

	private void setPayLoad(int payLoad) {
		this.payLoad = payLoad;
	}

	private void setEndurance(double endurance) {
		this.endurance = endurance;
	}

	private void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	private void setAltitudeCapability(int altitudeCapability) {
		this.altitudeCapability = altitudeCapability;
	}

	@Override
	public void accept(VNTVisitor visitor) {
		visitor.visit(this);
	}

}
