package com.solvewithdev.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class NetworkDriveModel {
	
	private String networkDriveName;
	private String networkDrivePath;
	private String networkDriveUsername;
	private String networkDrivePassword;
	private String networkDriveStatus;
	private String networkDriveSource;
	
}
