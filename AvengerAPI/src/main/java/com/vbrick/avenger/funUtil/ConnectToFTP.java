package com.vbrick.avenger.funUtil;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ConnectToFTP {

	
	public static List<String> connectToFTPGetFileList(String FTPUrl, String directorypath)

	{
		List<String> fileslist = new ArrayList<String>();

		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(FTPUrl.replace("/","").split(":")[1]);
			boolean login = ftpClient.login("admin", "admin");

			if (login) {
				System.out.println("Connection established...");
				
				//Changing to specified directory
				boolean workingDir = ftpClient.changeWorkingDirectory(directorypath);

				//Putting all file names in list
				if (workingDir) {

					FTPFile[] files = ftpClient.listFiles();
					for (FTPFile file : files) {
						if (file.getType() == FTPFile.FILE_TYPE) {
							System.out.println("File Name: "+ file.getName());
							fileslist.add(file.getName());
						}
					}

					boolean logout = ftpClient.logout();
					if (logout) {
						System.out.println("Connection close...");
					}
				} else {
					System.out.println("Connection fail...");
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		return fileslist;
		
	}
}