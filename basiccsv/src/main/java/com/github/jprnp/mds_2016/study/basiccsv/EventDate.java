package com.github.jprnp.mds_2016.study.basiccsv;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventDate {
	static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private int id;
	private Calendar dtIni;
	private Calendar dtFim;
	private String descr;
	
	public EventDate(int id, Calendar dtIni, Calendar dtFim, String descr) {
		this.id = id;
		this.dtIni = dtIni;
		this.dtFim = dtFim;
		this.descr = descr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDtIni() {
		return dtIni;
	}

	public void setDtIni(Calendar dtIni) {
		this.dtIni = dtIni;
	}

	public Calendar getDtFim() {
		return dtFim;
	}

	public void setDtFim(Calendar dtFim) {
		this.dtFim = dtFim;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Override
	public String toString() {
		return "EventDate [ID=" + id + ", Data de Inicio=" + sdf.format(dtIni.getTime()) + 
				", Data do Fim=" + sdf.format(dtFim.getTime()) + ", Descrição=" + descr + "]";
	}	
	
}