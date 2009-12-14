package ita.br.main;

public interface MinTimeVNTVisitor extends VNTVisitor {
	public VNT getFasterVNT();

	public double getMinTime();
}
