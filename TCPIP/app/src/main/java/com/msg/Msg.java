package com.msg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Msg implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private HashMap<String,Msg> maps; //서버는 hashmap에 저장되어 있음 
	private  ArrayList<String> ips;
	private String id;
	private String msg;
	

	public Msg() {
	}
	

	public Msg(String id, String msg) {
		this.id = id;
		this.msg = msg;
	}
	


	


	public Msg(ArrayList<String> ips, String id, String msg) {
		this.ips = ips;
		this.id = id;
		this.msg = msg;
	}


	public Msg(HashMap<String, Msg> maps, ArrayList<String> ips, String id, String msg) {
		this.maps = maps;
		this.ips = ips;
		this.id = id;
		this.msg = msg;
	}


	public HashMap<String, Msg> getMaps() {
		return maps;
	}

	public void setMaps(HashMap<String, Msg> maps) {
		this.maps = maps;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public ArrayList<String> getIps() {
		return ips;
	}


	public void setIps(ArrayList<String> ips) {
		this.ips = ips;
	}
	
	
}
