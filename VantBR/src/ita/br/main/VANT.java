package ita.br.main;

public class VANT implements VNT {

	private int payLoad;
	private double endurance;
	private int velocity;
	private int altitudeCapability;
	private String name;

	public VANT(String name, int altitudeCapability, double endurance, int payLoad, int velocity) {
		super();
		this.setName(name);
		this.setAltitudeCapability(altitudeCapability);
		this.setEndurance(endurance);
		this.setPayLoad(payLoad);
		this.setVelocity(velocity);
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
	
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(VNTVisitor visitor) {
		visitor.visit(this);
	}
	
	public String toString() {
		return name;
	}

}
