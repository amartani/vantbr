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
	private static final File UAVFILE = new File("data/uavs.csv");
	private static final File[] ROTAFILES = { 
			new File("data/FirstRota.csv"),
			new File("data/SecondRota.csv"),
			new File("data/ThirdRota.csv") };
	
	public C2FileParser(C2 c2) {
		this.c2 = c2;
		this.combatsquads = new ArrayList<CombatSquad>();
	}

	public void parseAll() {
		parseSquads();
		parseUAVs();
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
			SpacialPoint waypoint = new SpacialPoint(Double.parseDouble(coords[0]
					.trim()), Double.parseDouble(coords[1].trim()), Double
					.parseDouble(coords[2].trim()));
			Set<VANT> uavs = new HashSet<VANT>();
			Squad squad = new Squad(name, soldiers, uavs);
			CombatSquad combat = new CombatSquad(squad, waypoint);
			c2.addCombatSquad(combat);
			combatsquads.add(combat);
		}
	}

	private void parseUAVs() {
		Collection<String> lines = FileToString.getStringsFromFile(UAVFILE);
		for (String line : lines) {
			line = line.trim();
			if (line.isEmpty())
				continue;
			String[] linespl = line.split(",");
			String name = linespl[0].trim();
			name = name.substring(1, name.length() - 1);
			int altitudeCapability = Integer.parseInt(linespl[1].trim());
			double endurance = Double.parseDouble(linespl[2].trim());
			int payLoad = Integer.parseInt(linespl[3].trim());
			int velocity = Integer.parseInt(linespl[4].trim());

			VANT uav = new VANT(altitudeCapability, endurance, payLoad, velocity);
			// Adiciona UAV em todos os Squads
			for (CombatSquad combatsquad : combatsquads) {
				combatsquad.getSquad().addVNT(uav);
			}
		}
	}

	private void parseRoutes() {
		for (File file: ROTAFILES) {
			Collection<String> lines = FileToString.getStringsFromFile(file);
			ArrayList<SpacialPoint> rota = new ArrayList<SpacialPoint>();
			for (String line: lines) {
				String[] linespl = line.split(",");
				SpacialPoint waypoint = new SpacialPoint(
						Double.parseDouble(linespl[0].trim()),
						Double.parseDouble(linespl[1].trim()),
						Double.parseDouble(linespl[2].trim()));
				rota.add(waypoint);
			}
			c2.addFlightPlan(new FlightPlan(rota, 0));
		}
	}

}
