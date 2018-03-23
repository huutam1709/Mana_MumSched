package edu.mum.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.mum.enums.ScheduleStatusEnum;

public class Schedule implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Enumerated
    //@Column(columnDefinition = "smallint")
	private ScheduleStatusEnum status;
	
	//@JsonIgnore
	@OneToOne
	private Entry entry;
	
	//@JsonIgnore
//	@OneToMany(mappedBy="schedule", cascade=CascadeType.ALL)
	//private List<Block> blockList;
	
	public String getCourseName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ScheduleStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatusEnum status) {
		this.status = status;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	
	private List<BlockSimple> blockSimple;

	/**
	 * @return the blockSimple
	 */
	public List<BlockSimple> getBlockSimple() {
		return blockSimple;
	}

//	public List<Block> getBlockList() {
//		return blockList;
//	}
//
//	public void setBlockList(List<Block> blockList) {
//		this.blockList = blockList;
//	}
	
	
}
