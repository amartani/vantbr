package ita.br.main;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class C2Test {
	
	private C2 c2;
	private C2FileParser parser;
	
	@Before
	public void setUp() {
		c2 = new C2();
		parser = new C2FileParser(c2);
		parser.parseAll();
	}
	
	@After
	public void tearDown() {
		System.out.println();
	}
	
	@Test
	public void testAll() {
		System.out.println("Tempo de cada pelotao:");
		c2.printAllMissionTimes();
	}
	
	@Test
	public void testBest() {
		System.out.println("Menor tempo para cada missao:");
		c2.printBestMissionTimes();
	}
	
}
