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
				double time = csquad.getMissionTime(fp);
				System.out.println(csquad.getSquad().getName() + ": " + minutesFormat.format(time));
			}
		}
	}
	
	public void printBestMissionTimes() {
		int i = 1;
		for (FlightPlan fp: flightPlans) {
			System.out.println("Plano " + i);
			i++;
			double besttime = Double.MAX_VALUE;
			CombatSquad bestsquad = null;
			for (CombatSquad csquad: combatSquads) {
				double time = csquad.getMissionTime(fp);
				if (time < besttime) {
					besttime = time;
					bestsquad = csquad;
				}
			}
			if (bestsquad != null) {
				System.out.println(bestsquad.getSquad().getName() + ": " + minutesFormat.format(besttime));
			}
		}
	}

}
