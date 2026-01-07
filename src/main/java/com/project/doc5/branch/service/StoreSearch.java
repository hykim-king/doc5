package com.project.doc5.branch.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StoreSearch {

	final Logger log = LogManager.getLogger(getClass());
	
	public static final String STORE_DATA = ".\\data\\starbucks.json"; //스토어 저장 경로

	private double mylon;
	private double mylat;
	private int killo;
	
	public StoreSearch() {
		super();
		log.debug("┌──────────────────────────┐");
		log.debug("│storesearch()             │");
		log.debug("└──────────────────────────┘");	
	}

	public StoreSearch(double mylon, double mylat, int killo) {
		super();
		this.mylon = mylon;
		this.mylat = mylat;
		this.killo = killo;
	
	}

	public double getMylon() {
		return mylon;
	}

	public void setMylon(double mylon) {
		this.mylon = mylon;
	}

	public double getMylat() {
		return mylat;
	}

	public void setMylat(double mylat) {
		this.mylat = mylat;
	}

	public int getKillo() {
		return killo;
	}

	public void setKillo(int killo) {
		this.killo = killo;
	}

	public Logger getLog() {
		return log;
	}

	public static String getStoreData() {
		return STORE_DATA;
	}

	@Override
	public String toString() {
		return "StoreSearch [log=" + log + ", mylon=" + mylon + ", mylat=" + mylat + ", killo=" + killo + "]";
	}
	
	
	

	
	
	
	
}
