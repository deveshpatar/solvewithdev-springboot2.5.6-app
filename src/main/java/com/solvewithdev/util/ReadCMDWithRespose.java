package com.solvewithdev.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadCMDWithRespose {
	
	static String driveName = "X:";
	static String drivePath = "\\\\DESKTOP-Q0FUS1Q\\Songs";
	String username = "CEPHEID.PRI\\aditya.dasari";
	String password = "Cepheid2021!!";

	public static final String MAP_CORNELLAD_NETWORK_DRIVE = "cmd.exe /c net use "+driveName+" "+drivePath+" /persisten:yes";

	public static void main(String[] args) throws IOException {
		execCmdWithResponse1(MAP_CORNELLAD_NETWORK_DRIVE);
		
		
	}
	
	public static String execCmdWithResponse1(String cmd) {
	    String result = null;
	    try (InputStream inputStream = Runtime.getRuntime().exec(cmd).getInputStream();
	            Scanner s = new Scanner(inputStream).useDelimiter("\\A")) {
	        result = s.hasNext() ? s.next() : null;
	        System.out.println(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	public static String execCmdWithResponse2(String cmd) throws IOException {
		String result = null;
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(cmd);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		// Read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			result = s;
			System.out.println(s);
		}

		// Read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			result = s;
			System.out.println(s);
		}
		return result;
	}
}
