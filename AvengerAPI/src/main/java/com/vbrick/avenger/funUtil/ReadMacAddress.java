package com.vbrick.avenger.funUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ReadMacAddress{
	
	private String macaddress;
	private String ipaddress;

   public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

  public void  get_MacAddress()
   {
	   InetAddress ip;
	            try {
                    
                	ip = InetAddress.getLocalHost();
                                System.out.println("Current IP address : " + ip.getHostAddress());

                                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                                setIpaddress(ip.getHostAddress());

                                byte[] mac = network.getHardwareAddress();

                              System.out.println("Current MAC address : ");

                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < mac.length; i++) {
                                                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));                          
                                }
                                System.out.println("after replacement"+sb.toString().replaceAll("-",""));
                                setMacaddress(sb.toString().replaceAll("-",""));
                } catch (UnknownHostException e) {
                                e.printStackTrace();

                } catch (SocketException e){

                                e.printStackTrace();
                }
            
   }
  
  public static void main(String[] args) {
	
	   ReadMacAddress mac = new ReadMacAddress();
	   mac.get_MacAddress();
	   System.out.println("Ip Address"+mac.getIpaddress());
	   System.out.println("Mac Address"+mac.getMacaddress());
	   
	   
	
   }
   
   

}
