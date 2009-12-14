package ita.br.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class FileToString {

	private static Collection<String> strings;

	public static Collection<String> getStringsFromFile(File file) {
		fillStrings(file);
		return strings;
	}

	private static void fillStrings(File file) {
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			strings = new ArrayList<String>();
			String strResult;
			while ((strResult = br.readLine()) != null) {
				strings.add(strResult);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
