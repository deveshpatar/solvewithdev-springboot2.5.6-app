package com.solvewithdev.service;

import java.util.List;

import com.solvewithdev.dto.NetworkDriveDto;

public interface NetworkDriveDBService {
	
	List<NetworkDriveDto> getMappedNetworkList() throws Exception;
	
	NetworkDriveDto addOrUpdateNetworkDrive(NetworkDriveDto networkDriveDto)  throws Exception;
	
	NetworkDriveDto updateNetworkDrive(NetworkDriveDto networkDriveDto)  throws Exception;
	
	void deleteNetworkDrive(int driveId, String networkDriveName)  throws Exception;

	NetworkDriveDto connect(NetworkDriveDto driveDto)  throws Exception;
	
	List<NetworkDriveDto> getAllNetworkDrivesFromDB();

}
