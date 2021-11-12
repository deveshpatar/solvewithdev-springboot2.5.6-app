package com.solvewithdev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solvewithdev.dto.NetworkDriveModel;
import com.solvewithdev.service.NetworkDriveService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NetworkDriveController {
	
	private final NetworkDriveService networkDriveService;
	private static final String BASE_RQUEST_URL = "/v1/networkDrive";
	
	@GetMapping(BASE_RQUEST_URL)
	public List<NetworkDriveModel> getAllNetworkDrives() throws Exception {
		return networkDriveService.getMappedNetworkList();
	}
	
	@PostMapping(BASE_RQUEST_URL)
	public String addOrUpdateNetworkDrive(@RequestBody NetworkDriveModel networkDriveModel) throws Exception {
		return networkDriveService.addOrUpdateNetworkDrive(networkDriveModel);
	}
		
	@DeleteMapping(BASE_RQUEST_URL)
	public String deleteNetworkDrive(@RequestParam String networkDriveName) throws Exception {
		return networkDriveService.deleteNetworkDrive(networkDriveName);
	}

}
