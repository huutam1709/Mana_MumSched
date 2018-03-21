package edu.mum.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import edu.mum.enums.MonthEnum;

public class BlockSimple implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	private MonthEnum month;
	private String courseName;
	/**
	 * @return the month
	 */
	public MonthEnum getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(MonthEnum Month) {
		month = Month;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String CourseName) {
		courseName = CourseName;
	}
	
}
