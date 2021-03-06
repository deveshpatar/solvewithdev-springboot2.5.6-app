package com.solvewithdev.dto;

import java.sql.Timestamp;

import com.solvewithdev.constants.ConnectionStatus;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class NetworkDriveDto {
	
	private int networkDriveId;
	private String networkDriveName;
	private String networkDrivePath;
	private String networkDriveUsername;
	private String networkDrivePassword;
	private String networkDriveStatus;
	private String networkDriveSource;
	private Timestamp createdAt;
	private ConnectionStatus connectionStatus;
	
}
