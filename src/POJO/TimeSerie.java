package POJO;

//import java.sql.Date;

import java.util.Calendar;
//import java.util.Date;

public class TimeSerie {

	private Calendar validTime;
	private double msl;
	private double t;
	private double vis;
	private double wd;
	private double ws;
	private double r;
	private double tstm;
	private double tcc;
	private double lcc;
	private double mcc;
	private double hcc;
	private double gust;
	private double pit;
	private double pis;
	private double pcat;
	
	public String toString(){
		return "\nvalidTime: " + validTime + ", msl: " + msl + ", t:" + t + ", vis: " + vis + ", wd: " + wd +
				", ws: " + ws + ", r: " + r + ", tstm: " + tstm + ", tcc: " + tcc + ", lcc: " + lcc + 
				", gust: " + gust + ", pit: " + pit + ", pis: " +pis + ", pcat: " + pcat;
	}
	
	public String getClockTime(){
		String result = "";
		int hour = this.validTime.get(Calendar.HOUR_OF_DAY);
		
		if(hour < 10){
			result = "0";
		}
	
		result += hour+":00";
		return result;
	}
	
	public Calendar getValidTime() {
		return validTime;
	}
	public void setValidTime(Calendar validTime) {
		this.validTime = validTime;
	}
	public double getMsl() {
		return msl;
	}
	public void setMsl(double msl) {
		this.msl = msl;
	}
	public double getT() {
		return t;
	}
	public void setT(double t) {
		this.t = t;
	}
	public double getVis() {
		return vis;
	}
	public void setVis(double vis) {
		this.vis = vis;
	}
	public double getWd() {
		return wd;
	}
	public void setWd(double wd) {
		this.wd = wd;
	}
	public double getR() {
		return r;
	}
	public void setR(double r) {
		this.r = r;
	}
	public double getTstm() {
		return tstm;
	}
	public void setTstm(double tstm) {
		this.tstm = tstm;
	}
	public double getTcc() {
		return tcc;
	}
	public void setTcc(double tcc) {
		this.tcc = tcc;
	}
	public double getLcc() {
		return lcc;
	}
	public void setLcc(double lcc) {
		this.lcc = lcc;
	}
	public double getMcc() {
		return mcc;
	}
	public void setMcc(double mcc) {
		this.mcc = mcc;
	}
	public double getHcc() {
		return hcc;
	}
	public void setHcc(double hcc) {
		this.hcc = hcc;
	}
	public double getGust() {
		return gust;
	}
	public void setGust(double gust) {
		this.gust = gust;
	}
	public double getPit() {
		return pit;
	}
	public void setPit(double pit) {
		this.pit = pit;
	}
	public double getPis() {
		return pis;
	}
	public void setPis(double pis) {
		this.pis = pis;
	}
	public double getPcat() {
		return pcat;
	}
	public void setPcat(double pcat) {
		this.pcat = pcat;
	}
	public double getWs() {
		return ws;
	}
	public void setWs(double ws) {
		this.ws = ws;
	}
}
