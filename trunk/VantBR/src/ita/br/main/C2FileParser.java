package ita.br.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ita.br.utils.FileToString;

public class C2FileParser {

	private C2 c2;
	private List<CombatSquad> combatsquads;
	private static final File SQUADFILE = new File("data/squads.csv");
	private static final File VNTFILE = new File("data/vnts.csv");
	private static final File[] ROTAFILES = { new File("data/FirstRota.csv"),
			new File("data/SecondRota.csv"), new File("data/ThirdRota.csv") };
	private static final int[] PAYLOADS = { 30, 80, 120, 300 };

	public C2FileParser(C2 c2) {
		this.c2 = c2;
		this.combatsquads = new ArrayList<CombatSquad>();
	}

	public void parseAll() {
		parseSquads();
		parseVNTs();
		parseRoutes();
	}

	private void parseSquads() {
		Collection<String> lines = FileToString.getStringsFromFile(SQUADFILE);
		for (String line : lines) {
			line = line.trim();
			if (line.isEmpty())
				continue;
			String[] squadspl = line.split(";");
			String name = squadspl[0].trim();
			name = name.substring(1, name.length() - 1);
			int soldiers = Integer.parseInt(squadspl[1].trim());
			String[] coords = squadspl[2].split(",");
			SpacialPoint waypoint = new SpacialPoint(Double
					.parseDouble(coords[0].trim()), Double
					.parseDouble(coords[1].trim()), Double
					.parseDouble(coords[2].trim()));
			Set<VNT> vnts = new HashSet<VNT>();
			Squad squad = new Squad(name, soldiers, vnts);
			CombatSquad combat = new CombatSquad(squad, waypoint);
			c2.addCombatSquad(combat);
			combatsquads.add(combat);
		}
	}

	private void parseVNTs() {
		Collection<String> lines = FileToString.getStringsFromFile(VNTFILE);
		for (String line : lines) {
			line = line.trim();
			if (line.isEmpty())
				continue;
			String[] linespl = line.split(",");
			String type = linespl[0].trim();
			String name = linespl[1].trim();
			// name = name.substring(1, name.length() - 1);
			int altitudeCapability = Integer.parseInt(linespl[2].trim());
			double endurance = Double.parseDouble(linespl[3].trim());
			int payLoad = Integer.parseInt(linespl[4].trim());
			int velocity = Integer.parseInt(linespl[5].trim());
			VNT vnt;

			if (type.equals("A")) {
				vnt = new VANT(name, altitudeCapability, endurance, payLoad,
						velocity);
			} else {
				vnt = new VTNT(name, endurance, payLoad, velocity);
			}
			// Adiciona UAV em todos os Squads
			for (CombatSquad combatsquad : combatsquads) {
				combatsquad.getSquad().addVNT(vnt);
			}
		}
	}

	private void parseRoutes() {
		for (File file : ROTAFILES) {
			Collection<String> lines = FileToString.getStringsFromFile(file);
			ArrayList<SpacialPoint> rota = new ArrayList<SpacialPoint>();
			ArrayList<PlanPoint> rotaTerrestre = new ArrayList<PlanPoint>();
			for (String line : lines) {
				String[] linespl = line.split(",");
				SpacialPoint waypoint = new SpacialPoint(Double
						.parseDouble(linespl[0].trim()), Double
						.parseDouble(linespl[1].trim()), Double
						.parseDouble(linespl[2].trim()));
				rota.add(waypoint);
				rotaTerrestre.add(waypoint.toPlanPoint());
			}
			for (int payload : PAYLOADS) {
				c2.addMissionPlan(new FlightPlan(rota, payload));
			}
			for (int payload : PAYLOADS) {
				c2.addMissionPlan(new TerrestrialPlan(rotaTerrestre, payload));
			}
		}
	}

}
