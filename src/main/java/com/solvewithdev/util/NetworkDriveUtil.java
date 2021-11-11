package com.solvewithdev.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import com.solvewithdev.entity.NetworkDrive;

public class NetworkDriveUtil {

	static Process process = null;
	static String networkResponse = null;
	public static final String NETWORK_DRIVE_BASE_CMD = "cmd.exe /c net use";
	
	public static Process mapNetworkDrive(NetworkDrive networkDrive) throws Exception {
		String MAP_CORNELLAD_NETWORK_DRIVE = String.format("%s %s %s %s", NETWORK_DRIVE_BASE_CMD, networkDrive.getNetworkDriveName(), networkDrive.getNetworkDrivePath(), "/persisten:yes");
		process = queryWindowsRegistrys(MAP_CORNELLAD_NETWORK_DRIVE);
		return process;
	}

	public static Process deleteNetworkDrive(String serverName) throws Exception {
		String DELETE_NETWORK_DRIVE = String.format("%s %s %s", NETWORK_DRIVE_BASE_CMD, serverName, "/delete");
		process = queryWindowsRegistrys(DELETE_NETWORK_DRIVE);
		return process;
	}

	public String listNetworkDrives() throws Exception {
		String responseMsg = queryWindowsRegistry(NETWORK_DRIVE_BASE_CMD);
		if (responseMsg != null) {
			responseMsg = "Network Drive List Successfully!";
		} else {
			responseMsg = "Something Wrong!";
		}
		return responseMsg;
	}

	private static String queryWindowsRegistry(String cmd) throws Exception {
		String responseMessage = null;
		final Process process = Runtime.getRuntime().exec(cmd);
		process.waitFor(10, TimeUnit.SECONDS);
		if (process.exitValue() != 0) {
			responseMessage = new BufferedReader(new InputStreamReader(process.getErrorStream())).readLine();
		} else {
			responseMessage = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine().trim();
		}
		return responseMessage;
	}
	
	private static Process queryWindowsRegistrys(String cmd) throws Exception {
		process = Runtime.getRuntime().exec(cmd);
		process.waitFor(10, TimeUnit.SECONDS);
		return process;
	}

}
