package com.solvewithdev.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.solvewithdev.constants.ConnectionStatus;
import com.solvewithdev.constants.Constants;
import com.solvewithdev.dto.NetworkDriveDto;
import com.solvewithdev.entity.NetworkDrive;
import com.solvewithdev.exception.ConnectionNotFoundException;
import com.solvewithdev.exception.RecordNotFoundException;
import com.solvewithdev.repository.NetworkDriveRepository;
import com.solvewithdev.util.NetworkDriveUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NetworkDriveDBServiceImpl implements NetworkDriveDBService{
	
	private final NetworkDriveRepository networkDriveRepository;
	static Process process = null;
	static String networkResponse = null;
	
	@Override
	public NetworkDriveDto addOrUpdateNetworkDrive(NetworkDriveDto networkDriveDto) throws Exception {
		NetworkDriveDto dto = null;
		process = NetworkDriveUtil.mapNetworkDriveDB(networkDriveDto);
		if (process.exitValue() == 0) {
			networkResponse = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine().trim();
			dto = DriverAdapter.convertToNetworkDriveDto(networkDriveRepository.save(DriverAdapter.convertToNetworkDriveEntity(networkDriveDto)));
		} else {
			networkResponse = new BufferedReader(new InputStreamReader(process.getErrorStream())).readLine();
			log.error(networkResponse);
		}
		return dto;
	}

	@Override
	public void deleteNetworkDrive(int networkDriveId, String networkDriveName)  throws Exception {
		process = NetworkDriveUtil.deleteNetworkDrive(networkDriveName);
		if (process.exitValue() == 0) {
			Optional<NetworkDrive> driveOptional = networkDriveRepository.findById(networkDriveId);
			if(driveOptional.isEmpty()) {
				throw new RecordNotFoundException("Unable to delete as no Drives found with id : "+ networkDriveId);
			}
			networkDriveRepository.delete(driveOptional.get());
			networkResponse = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine().trim();
		} else {
			networkResponse = new BufferedReader(new InputStreamReader(process.getErrorStream())).readLine();
			log.error(networkResponse);
		}
	}
	
	@Override
	public NetworkDriveDto updateNetworkDrive(NetworkDriveDto networkDriveDto) throws Exception {
		Optional<NetworkDrive> driveOptional = networkDriveRepository.findById(networkDriveDto.getNetworkDriveId());
		if(driveOptional.isEmpty()) {
			throw new RecordNotFoundException("Unable to update as no Drives found with name : "+ networkDriveDto.getNetworkDriveName());
		}
		return DriverAdapter.convertToNetworkDriveDto(networkDriveRepository.save(DriverAdapter.convertToNetworkDriveEntity(networkDriveDto)));
	}
	
	@Override
	public List<NetworkDriveDto> getMappedNetworkList() throws Exception {
		List<NetworkDriveDto> networkDriveDtos = new ArrayList<NetworkDriveDto>();

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
					
					NetworkDriveDto  model = NetworkDriveDto.builder()
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
	
	
	@Override
	public NetworkDriveDto connect(NetworkDriveDto networkDriveDto) throws Exception {
		int tmp = new Random().nextInt(11);
		log.info("Next Num: "+ tmp);
		if(tmp % 2 == 0) {
			networkDriveDto.setConnectionStatus(ConnectionStatus.CONNECTED);
			return updateNetworkDrive(networkDriveDto);
		}
		networkDriveDto.setConnectionStatus(ConnectionStatus.DISCONNECTED);
		updateNetworkDrive(networkDriveDto);
		throw new ConnectionNotFoundException("Unable to connect to drive: "+ networkDriveDto.getNetworkDriveName());
	}

	@Override
	public List<NetworkDriveDto> getAllNetworkDrivesFromDB() {
		List<NetworkDrive> networkDrive = networkDriveRepository.findAll();
		return networkDrive.stream()
				.map(record -> DriverAdapter.convertToNetworkDriveDto(record))
				.collect(Collectors.toList());
	}
}
