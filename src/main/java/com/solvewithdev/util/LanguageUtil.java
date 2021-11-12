package com.solvewithdev.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.solvewithdev.constants.Constants;

/**
 * 
 * @author Devesh
 *
 */

public class LanguageUtil {
	
	/*
	 * This method is being used to get the timezone map via CMD command
	 * changeTimeZoneCmd
	 */
	public static Map<String, String> getInstalledLanguageList(String languageCmd) throws IOException, InterruptedException {
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<String> allLines = new ArrayList<>();
		
		BufferedReader in = null;
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(languageCmd);
		String line = null;
		String line1 = null;
		String line2 = null;
		
		in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		
		while ((line = in.readLine()) != null) {
			if(!line.isEmpty()) {
				line1 = line.replace(Constants.LANGUAGE_KEY, "");
				line2 = line1.replace(Constants.ANTONYM_KEY, "");
				allLines.add(line2);
			}
		}
		for(int i=0;i<allLines.size();i=i+8) {
			map.put(allLines.get(i), allLines.get(i+1));
		}
		
		map.forEach((k,v) -> System.out.println("Key = " + k + "	|	Value = " + v));
		return map;
	}
}