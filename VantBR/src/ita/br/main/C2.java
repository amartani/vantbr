package ita.br.main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class C2 {

	private Set<CombatSquad> combatSquads;

	private List<MissionPlan> missionPlans;

	private static final DecimalFormat minutesFormat = new DecimalFormat(
			"0.00 min");

	public C2() {
		combatSquads = new HashSet<CombatSquad>();
		missionPlans = new ArrayList<MissionPlan>();
	}

	public Boolean addCombatSquad(CombatSquad squad) {
		return combatSquads.add(squad);
	}

	public Boolean addMissionPlan(MissionPlan plan) {
		return missionPlans.add(plan);
	}

	public Set<CombatSquad> getCombatSquads() {
		return combatSquads;
	}

	public List<MissionPlan> getMissionPlans() {
		return missionPlans;
	}

	public void printAllMissionTimes() {
		int i = 1;
		for (MissionPlan mp : missionPlans) {
			System.out.println("Plan " + i);
			i++;
			for (CombatSquad csquad : combatSquads) {
				MinTimeVNTVisitor visitor = csquad.getMinTimeVNTVisitor(mp);
				double time = visitor.getMinTime();
				VNT vnt = visitor.getFasterVNT();
				if (vnt != null) {
					System.out.println(csquad.getSquad().getName() + ": "
							+ minutesFormat.format(time) + " (" + vnt + ")");
				} else {
					System.out.println(csquad.getSquad().getName()
							+ ": Cannot realize mission");
				}
			}
		}
	}

	public void printBestMissionTimes() {
		int i = 1;
		for (MissionPlan mp : missionPlans) {
			System.out.println("Plan " + i);
			i++;
			double besttime = Double.MAX_VALUE;
			VNT bestvnt = null;
			CombatSquad bestsquad = null;
			for (CombatSquad csquad : combatSquads) {
				MinTimeVNTVisitor visitor = csquad.getMinTimeVNTVisitor(mp);
				double time = visitor.getMinTime();
				VNT vnt = visitor.getFasterVNT();
				if (vnt != null && time < besttime) {
					besttime = time;
					bestsquad = csquad;
					bestvnt = vnt;
				}
			}
			if (bestsquad != null) {
				System.out
						.println(bestsquad.getSquad().getName() + ": "
								+ minutesFormat.format(besttime) + " ("
								+ bestvnt + ")");
			} else {
				System.out.println("Cannot realize mission");
			}
		}
	}

}
