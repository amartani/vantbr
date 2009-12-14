package ita.br.main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class C2 {

	private Set<CombatSquad> combatSquads;

	private List<FlightPlan> flightPlans;
	
	private static final DecimalFormat minutesFormat = new DecimalFormat("0.00 min");

	public C2() {
		combatSquads = new HashSet<CombatSquad>();
		flightPlans = new ArrayList<FlightPlan>();
	}
	
	public Boolean addCombatSquad(CombatSquad squad) {
		return combatSquads.add(squad);
	}
	
	public Boolean addFlightPlan(FlightPlan plan) {
		return flightPlans.add(plan);
	}

	public Set<CombatSquad> getCombatSquads() {
		return combatSquads;
	}

	public List<FlightPlan> getFlightPlans() {
		return flightPlans;
	}
	
	public void printAllMissionTimes() {
		int i = 1;
		for (FlightPlan fp: flightPlans) {
			System.out.println("Plano " + i);
			i++;
			for (CombatSquad csquad: combatSquads) {
				MinTimeVNTVisitor visitor = csquad.getMinTimeVNTVisitor(fp); 
				double time = visitor.getMinTime();
				VNT vnt = visitor.getFasterVNT();
				System.out.println(csquad.getSquad().getName() + ": " + minutesFormat.format(time) + " (" + vnt + ")");
			}
		}
	}
	
	public void printBestMissionTimes() {
		int i = 1;
		for (FlightPlan fp: flightPlans) {
			System.out.println("Plano " + i);
			i++;
			double besttime = Double.MAX_VALUE;
			VNT bestvnt = null;
			CombatSquad bestsquad = null;
			for (CombatSquad csquad: combatSquads) {
				MinTimeVNTVisitor visitor = csquad.getMinTimeVNTVisitor(fp); 
				double time = visitor.getMinTime();
				VNT vnt = visitor.getFasterVNT();
				if (time < besttime) {
					besttime = time;
					bestsquad = csquad;
					bestvnt = vnt;
				}
			}
			if (bestsquad != null) {
				System.out.println(bestsquad.getSquad().getName() + ": " + minutesFormat.format(besttime) + " (" + bestvnt + ")");
			}
		}
	}

}
