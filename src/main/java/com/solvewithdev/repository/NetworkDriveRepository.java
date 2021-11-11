package com.solvewithdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solvewithdev.entity.NetworkDrive;

@Repository
public interface NetworkDriveRepository extends JpaRepository<NetworkDrive, Integer> {

}
