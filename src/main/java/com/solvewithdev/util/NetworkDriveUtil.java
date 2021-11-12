package com.solvewithdev.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import com.solvewithdev.dto.NetworkDriveDto;
import com.solvewithdev.dto.NetworkDriveModel;

public class NetworkDriveUtil {

	static Process process = null;
	static String networkResponse = null;
	public static final String NETWORK_DRIVE_BASE_CMD = "cmd.exe /c net use";
	
	public static Process mapNetworkDrive(NetworkDriveModel networkDriveModel) throws Exception {
		String MAP_CORNELLAD_NETWORK_DRIVE = String.format("%s %s %s %s", NETWORK_DRIVE_BASE_CMD, networkDriveModel.getNetworkDriveName(), networkDriveModel.getNetworkDrivePath(), "/persisten:yes");
		process = queryWindowsRegistrys(MAP_CORNELLAD_NETWORK_DRIVE);
		return process;
	}
	
	public static Process mapNetworkDriveDB(NetworkDriveDto networkDriveDto) throws Exception {
		String MAP_CORNELLAD_NETWORK_DRIVE = String.format("%s %s %s %s", NETWORK_DRIVE_BASE_CMD, networkDriveDto.getNetworkDriveName(), networkDriveDto.getNetworkDrivePath(), "/persisten:yes");
		process = queryWindowsRegistrys(MAP_CORNELLAD_NETWORK_DRIVE);
		return process;
	}

	public static Process deleteNetworkDrive(String serverName) throws Exception {
		String DELETE_NETWORK_DRIVE = String.format("%s %s %s", NETWORK_DRIVE_BASE_CMD, serverName, "/delete");
		process = queryWindowsRegistrys(DELETE_NETWORK_DRIVE);
		return process;
	}

	public static Process queryWindowsRegistrys(String cmd) throws Exception {
		process = Runtime.getRuntime().exec(cmd);
		process.waitFor(10, TimeUnit.SECONDS);
		return process;
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
	
}
