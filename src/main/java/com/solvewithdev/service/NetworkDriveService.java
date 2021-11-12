package com.solvewithdev.service;

import java.util.List;

import com.solvewithdev.dto.NetworkDriveModel;

public interface NetworkDriveService {
	
	List<NetworkDriveModel> getMappedNetworkList() throws Exception;
	
	String addOrUpdateNetworkDrive(NetworkDriveModel networkDriveModel)  throws Exception;

	String deleteNetworkDrive(String networkDriveName)  throws Exception;

}
