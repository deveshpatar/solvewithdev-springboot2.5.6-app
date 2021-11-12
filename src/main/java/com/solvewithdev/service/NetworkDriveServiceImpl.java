package com.solvewithdev.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.solvewithdev.constants.Constants;
import com.solvewithdev.dto.NetworkDriveModel;
import com.solvewithdev.util.NetworkDriveUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NetworkDriveServiceImpl implements NetworkDriveService{
	
	static Process process = null;
	static String networkResponse = null;
	
	@Override
	public String addOrUpdateNetworkDrive(NetworkDriveModel networkDriveModel) throws Exception {
		process = NetworkDriveUtil.mapNetworkDrive(networkDriveModel);
		if (process.exitValue() == 0) {
			networkResponse = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine().trim();
			log.info(networkResponse);
		} else {
			networkResponse = new BufferedReader(new InputStreamReader(process.getErrorStream())).readLine();
			log.error(networkResponse);
		}
		return networkResponse;
	}

	@Override
	public String deleteNetworkDrive(String networkDriveName)  throws Exception {
		process = NetworkDriveUtil.deleteNetworkDrive(networkDriveName);
		if (process.exitValue() == 0) {
			networkResponse = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine().trim();
			log.info(networkResponse);
		} else {
			networkResponse = new BufferedReader(new InputStreamReader(process.getErrorStream())).readLine();
			log.error(networkResponse);
		}
		return networkResponse;
	}
	
	@Override
	public List<NetworkDriveModel> getMappedNetworkList() throws Exception {
		List<NetworkDriveModel> networkDriveDtos = new ArrayList<NetworkDriveModel>();

		process = NetworkDriveUtil.queryWindowsRegistrys(Constants.NETWORK_DRIVE_BASE_CMD);
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String responseMessage = null;
		
		while ((responseMessage = in.readLine()) != null) {
			if (!responseMessage.isEmpty()) {
				
				if(responseMessage.toCharArray()[0] =='O' || responseMessage.toCharArray()[0] =='D' || responseMessage.toCharArray()[0] =='U') {
					String status = responseMessage.split(" ")[0];
					responseMessage = responseMessage.replaceFirst(status, "").trim();
					
					String drive = responseMessage.split(" ")[0]; 
					responseMessage = responseMessage.replaceFirst(drive, "").trim();
					
					String path = responseMessage.split(" ")[0];
					responseMessage = responseMessage.replaceFirst(Pattern.quote(path), "").trim();
					
					NetworkDriveModel  model = NetworkDriveModel.builder()
							.networkDriveName(drive)
							.networkDrivePath(path)
							.networkDriveStatus(status).networkDriveSource(responseMessage.trim())
							.build();
					networkDriveDtos.add(model);
				}
			}
		}
		return networkDriveDtos;
	}
}
