package com.solvewithdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solvewithdev.dto.NetworkDriveDto;
import com.solvewithdev.entity.UserLogin;
import com.solvewithdev.service.NetworkDriveDBService;
import com.solvewithdev.util.UserLoginUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NetworkDriveDBController {

	private final NetworkDriveDBService networkDriveService;
	private static final String BASE_RQUEST_URL = "/v1/networkDBDrive";

	@GetMapping(BASE_RQUEST_URL)
	public List<NetworkDriveDto> getAllNetworkDrives() throws Exception {
		return networkDriveService.getMappedNetworkList();
	}

	@PostMapping(BASE_RQUEST_URL)
	public NetworkDriveDto addNetworkDrive(@RequestBody NetworkDriveDto networkDriveDto) throws Exception {
		return networkDriveService.addOrUpdateNetworkDrive(networkDriveDto);
	}

	@PutMapping(BASE_RQUEST_URL)
	public NetworkDriveDto updateNetworkDrive(@RequestBody NetworkDriveDto networkDriveDto) throws Exception {
		return networkDriveService.addOrUpdateNetworkDrive(networkDriveDto);
	}

	@DeleteMapping(BASE_RQUEST_URL)
	public void deleteNetworkDrive(@RequestParam int networkDriveId, @RequestParam String networkDriveName)
			throws Exception {
		networkDriveService.deleteNetworkDrive(networkDriveId, networkDriveName);
	}

	// Login
	@Autowired
	private UserLoginUtil loginUtil;

	@PostMapping("/authenticateUser")
	public boolean authenticateUser(@RequestBody UserLogin userLogin) {
		return loginUtil.authenticate(userLogin);
	}

}
