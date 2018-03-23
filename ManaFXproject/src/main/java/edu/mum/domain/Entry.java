package edu.mum.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

//@Entity
//@Table(name="entry")
public class Entry implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
//	@NotEmpty
//	@Size(max=100)
//	private String name;
//
//	
//	@NotNull
////	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate entryDate;
//	
////	private void String getEntryDateString() {
////		return this.e
////	}
//	
//	@Min(1)
//	private int mpp;
//	@Min(1)
//	private int fpp;
//	
//	@Min(1)
//	private int mppCPT;
//	private int mppOPT;
//	
//	//@Min(1)
//	private int fppCPT;
//	private int fppOPT;
//	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
////	public LocalDate getEntryDate() {
////		return entryDate;
////	}
////	public void setEntryDate(LocalDate entryDate) {
////		this.entryDate = entryDate;
////	}
//	public int getMpp() {
//		return mpp;
//	}
//	public void setMpp(int mpp) {
//		this.mpp = mpp;
//	}
//	public int getFpp() {
//		return fpp;
//	}
//	public void setFpp(int fpp) {
//		this.fpp = fpp;
//	}
//	public int getMppCPT() {
//		return mppCPT;
//	}
//	public void setMppCPT(int mppCPT) {
//		this.mppCPT = mppCPT;
//	}
//	public int getMppOPT() {
//		return mppOPT;
//	}
//	public void setMppOPT(int mppOPT) {
//		this.mppOPT = mppOPT;
//	}
//	public int getFppCPT() {
//		return fppCPT;
//	}
//	public void setFppCPT(int fppCPT) {
//		this.fppCPT = fppCPT;
//	}
//	public int getFppOPT() {
//		return fppOPT;
//	}
//	public void setFppOPT(int fppOPT) {
//		this.fppOPT = fppOPT;
//	}
}