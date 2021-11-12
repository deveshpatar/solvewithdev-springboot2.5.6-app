package com.solvewithdev.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Devesh
 *
 */

public class WindowsTimeZoneAPI {

	public static final String NETWORK_DRIVE_BASE_CMD = "cmd.exe /c net use";
	
	public static void main(String[] args) {
		
	}
	/*
	 * This method is being used to change the windows system timezone based on user
	 * selection via CMD command timeZoneListCmd
	 */
	public static boolean changeWindowsSystemTimeZone(String changeTimeZoneCmd)
			throws IOException, InterruptedException {
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(changeTimeZoneCmd);
		int exitCode = pr.waitFor();
		return exitCode == 0;
	}

	/*
	 * This method is being used to get the timezone map via CMD command
	 * changeTimeZoneCmd
	 */
	public static String getTimeZoneValueByKey(String timeZoneListCmd, String timeZoneKey) throws IOException, InterruptedException {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<String> allLines = new ArrayList<>();
		List<String> timeZoneValueList = new ArrayList<>();
		
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(timeZoneListCmd);
		BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = null;
		
		while ((line = in.readLine()) != null) {			
			if(!line.isEmpty()) {
				allLines.add(line);
			}
		}
		int i;
		String timeZoneValue = null;
		for(i=0;i<allLines.size();i=i+2) {
			map.put(allLines.get(i), allLines.get(i+1));
			timeZoneValueList.add(allLines.get(i));
			
		}
		
		System.out.println(timeZoneValueList);
		
		if (timeZoneValueList.contains(timeZoneKey)) {
		    System.out.println("Account found");
		} else {
		    System.out.println("Account not found");
		}
		
		System.out.println(timeZoneKey);
		for (String name: map.keySet())         //iteration over keys  
		{  
		//returns the value to which specified key is mapped  
		timeZoneValue=  map.get(timeZoneKey);   
		   
		}   
		System.out.println("Value: " + timeZoneValue);
		
		//map.forEach((k,v) -> System.out.println("Key = " + k + "	|	Value = " + v));
		return timeZoneValue;
	}
	
	/*
	 * This method is being used to get the timezone map via CMD command
	 * timeZoneListCmd
	 */
	public static Map<String, String> getTimeZoneMap(String timeZoneListCmd) throws IOException, InterruptedException {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<String> allLines = new ArrayList<>();
		
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(timeZoneListCmd);

		BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = null;
		while ((line = in.readLine()) != null) {			
			if(!line.isEmpty()) {
				allLines.add(line);
			}
		}
		
		for(int i=0;i<allLines.size();i=i+2) {
			map.put(allLines.get(i+1), allLines.get(i));
		}
		
		map.forEach((k,v) -> System.out.println("Key = " + k + "	|	Value = " + v));
		return map;
	}
	
	/*
	 * This method is being used to get the timezone map via CMD command
	 * changeTimeZoneCmd
	 */
	public static List<String> getTimeZoneList(String timeZoneListCmd) throws IOException, InterruptedException {
		List<String> allLines = new ArrayList<>();
		List<String> timeZoneList = new ArrayList<>();
		
		BufferedReader in = null;
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(timeZoneListCmd);

		in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = null;
		while ((line = in.readLine()) != null) {			
			if(!line.isEmpty()) {
				allLines.add(line);
			}
		}
		
		for(int i=0;i<allLines.size();i=i+2) {
			timeZoneList.add(allLines.get(i));
		}
		
		System.out.println(timeZoneList);
		return timeZoneList;
	}
}