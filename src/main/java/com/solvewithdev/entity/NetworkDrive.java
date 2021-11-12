package com.solvewithdev.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.solvewithdev.constants.ConnectionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NETWORK_DRIVE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NetworkDrive {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NETWORK_DRIVE_ID")
	private int networkDriveId;

	@Column(name = "NETWORK_DRIVE_NAME")
	private String networkDriveName;

	@Column(name = "NETWORK_DRIVE_PATH")
	private String networkDrivePath;

	@Column(name = "NETWORK_DRIVE_USERNAME")
	private String networkDriveUsername;

	@Column(name = "NETWORK_DRIVE_PASSWORD")
	private String networkDrivePassword;
	
	private String networkDriveStatus;
	
	private String networkDriveSource;
	
	@Column(name = "CREATED_AT")
	private Timestamp createdAt;
	
	@Column(name = "CONNECTION_STATUS")
	private ConnectionStatus connectionStatus;
		
	@PrePersist
	private void prepersist() {
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}
}
