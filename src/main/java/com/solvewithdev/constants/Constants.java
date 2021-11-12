package com.solvewithdev.constants;

public class Constants {
	
	public static final String NETWORK_DRIVE_BASE_CMD = "cmd.exe /c net use";
	
	static String driveName = "V:";
	static String drivePath = "\\\\DESKTOP-Q0FUS1Q\\Songs";
	String username = "CEPHEID.PRI\\aditya.dasari";
	String password = "Cepheid2021!!";

	public static final String MAP_CORNELLAD_NETWORK_DRIVE = "cmd.exe /c net use Y: \\\\DPAIO-DELL\\Songs /persisten:yes";
	public static final String MAP_NON_CORNELLAD_NETWORK_DRIVE = "cmd.exe /c net use R: \\\\CEPSNYLPENG4511\\Home Cepheid2021!! /user:CEPHEID.PRI\\aditya.dasari /persisten:yes";
	public static final String DELETE_NETWORK_DRIVE = "cmd.exe /c net use "+driveName+" /delete";
	
	public static final String generalSettingsTimeZone = "India Standard Time";
	public static final String CHANGE_TIMEZONE_CMD = "cmd.exe /c tzutil /s \"%s\"";
	public static final String CHANGE_TIMEZONE_CMD1 = "cmd.exe /c tzutil /s \"" + generalSettingsTimeZone + "\"";
	public static final String TIMEZONE_LIST_ALL_CMD = "cmd.exe /c tzutil /l ";
	
	public static final String LANGUAGE_KEY = "LanguageTag     : ";
	public static final String ANTONYM_KEY = "Autonym         : ";
	public static final String INSTALLED_LANGUAGE_LIST_PS = "powershell.exe Get-WinUserLanguageList";
	
	public static final String STATUS_CONNECTED = "Connected";
	public static final String STATUS_DISCONNECTED = "Disconnected";
	

}
