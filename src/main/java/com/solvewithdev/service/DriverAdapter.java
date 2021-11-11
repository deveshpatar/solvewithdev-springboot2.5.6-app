package com.solvewithdev.service;

import com.solvewithdev.constants.ConnectionStatus;
import com.solvewithdev.dto.NetworkDriveDto;
import com.solvewithdev.entity.NetworkDrive;

public class DriverAdapter {
	
	public static NetworkDriveDto convertToNetworkDriveDto(NetworkDrive networkDrive) {
		return NetworkDriveDto.builder()
				.networkDriveId(networkDrive.getNetworkDriveId())
				.networkDriveName(networkDrive.getNetworkDriveName())
				.networkDrivePath(networkDrive.getNetworkDrivePath())
				.networkDriveUsername(networkDrive.getNetworkDriveUsername())
				.networkDrivePassword(networkDrive.getNetworkDrivePassword())
				.connectionStatus(networkDrive.getConnectionStatus())
				.createdAt(networkDrive.getCreatedAt())
				.build();
	}
	
	public static NetworkDrive convertToNetworkDriveEntity(NetworkDriveDto networkDriveDto) {
		return NetworkDrive.builder()
				.networkDriveId(networkDriveDto.getNetworkDriveId())
				.networkDriveName(networkDriveDto.getNetworkDriveName())
				.networkDrivePath(networkDriveDto.getNetworkDrivePath())
				.networkDriveUsername(networkDriveDto.getNetworkDriveUsername())
				.networkDrivePassword(networkDriveDto.getNetworkDrivePassword())
				.connectionStatus(networkDriveDto.getConnectionStatus()!=null?networkDriveDto.getConnectionStatus():ConnectionStatus.CONNECTED)
				.build();
	}
}
