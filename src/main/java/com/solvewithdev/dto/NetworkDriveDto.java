package com.solvewithdev.dto;

import java.sql.Timestamp;

import com.solvewithdev.constants.ConnectionStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NetworkDriveDto {
	
	private int networkDriveId;
	private String networkDriveName;
	private String networkDrivePath;
	private String networkDriveUsername;
	private String networkDrivePassword;
	private Timestamp createdAt;
	private ConnectionStatus connectionStatus;
	
}
