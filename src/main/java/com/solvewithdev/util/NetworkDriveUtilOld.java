package com.solvewithdev.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import com.solvewithdev.entity.NetworkDrive;

public class NetworkDriveUtilOld {

	Process process = null;
	String networkResponse = null;
	public static final String NETWORK_DRIVE_BASE_CMD = "cmd.exe /c net use";
	
	public String mapNetworkDrive(NetworkDrive networkDrive) throws Exception {

		String MAP_CORNELLAD_NETWORK_DRIVE = String.format("%s %s %s %s", NETWORK_DRIVE_BASE_CMD, networkDrive.getNetworkDriveName(), networkDrive.getNetworkDrivePath(), "/persisten:yes");
		process = queryWindowsRegistrys(MAP_CORNELLAD_NETWORK_DRIVE);
		
		if (process.exitValue() == 0) {
			networkResponse = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine().trim();	
		} else {
			networkResponse = new BufferedReader(new InputStreamReader(process.getErrorStream())).readLine();
		}
		return networkResponse;
	}

	public String deleteNetworkDrive(NetworkDrive networkDrive) throws Exception {

		String MAP_CORNELLAD_NETWORK_DRIVE = NETWORK_DRIVE_BASE_CMD + networkDrive.getNetworkDriveName() + " "
				+ networkDrive.getNetworkDrivePath() + " /persisten:yes";

		String networkResponse = queryWindowsRegistry(MAP_CORNELLAD_NETWORK_DRIVE);
		if (networkResponse != null) {
			networkResponse = "Network Drive Mapped Successfully!";
		} else {
			networkResponse = "Something Wrong!";
		}
		return networkResponse;
	}

	public String deleteNetworkDrive(String serverName) throws Exception {
		String DELETE_MAPPED_NETWORK_DRIVE = NETWORK_DRIVE_BASE_CMD + serverName + ": /delete";

		String responseMsg = queryWindowsRegistry(DELETE_MAPPED_NETWORK_DRIVE);
		if (responseMsg != null) {
			responseMsg = "Network Drive Deleted Successfully!";
		} else {
			responseMsg = "Something Wrong!";
		}
		return responseMsg;
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

	private String queryWindowsRegistry(String cmd) throws Exception {
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
	
	private Process queryWindowsRegistrys(String cmd) throws Exception {
		process = Runtime.getRuntime().exec(cmd);
		process.waitFor(10, TimeUnit.SECONDS);
		return process;
	}

}
