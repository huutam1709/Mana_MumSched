package edu.mum.main;


import java.awt.event.MouseEvent;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.BlockSimple;
import edu.mum.domain.Entry;
import edu.mum.domain.Schedule;
import edu.mum.service.EntryService;
import edu.mum.service.ScheduleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class EntryMain {
	@FXML protected TableView<Entry> tblEntries;
	@FXML protected TableColumn<Entry, String> id;
	@FXML protected TableColumn<Entry, String> name;
	
	@FXML protected TableColumn<Entry, String> entryDate;
	@FXML protected TableColumn<Entry, String> fpp;
	@FXML protected TableColumn<Entry, String> fppCPT;
	@FXML protected TableColumn<Entry, String> fppOPT;
	@FXML protected TableColumn<Entry, String> mpp;
	@FXML protected TableColumn<Entry, String> mppCPT;
	@FXML protected TableColumn<Entry, String> mppOPT;
	
	@FXML protected TableView<BlockSimple> tblBlocks;
	@FXML protected TableColumn<BlockSimple, String> month;
	@FXML protected TableColumn<BlockSimple, String> courseName;
	
	 
	@Autowired
	EntryService entryService;
	
	@Autowired
	ScheduleService scheduleService;

	
	@FXML public void initialize() {
		loadEntries();
		tblEntries.setOnMouseClicked(e -> {
			if( e.getClickCount() == 1 ) {
				System.out.println("1 times");
			}});
	}
	
	private void loadEntries() {
		id.setCellValueFactory(new PropertyValueFactory<Entry, String>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Entry, String>("name"));
		fpp.setCellValueFactory(new PropertyValueFactory<Entry, String>("fpp"));
		fppCPT.setCellValueFactory(new PropertyValueFactory<Entry, String>("fppCPT"));
		fppOPT.setCellValueFactory(new PropertyValueFactory<Entry, String>("fppOPT"));
		mpp.setCellValueFactory(new PropertyValueFactory<Entry, String>("mpp"));
		mppCPT.setCellValueFactory(new PropertyValueFactory<Entry, String>("mppCPT"));
		mppOPT.setCellValueFactory(new PropertyValueFactory<Entry, String>("mppOPT"));
		entryDate.setCellValueFactory(new PropertyValueFactory<Entry, String>("entryDate"));

		List<Entry> ls = entryService.findAll();
		tblEntries.getItems().setAll(ls);
	}
	
	@FXML protected void ViewSchedules(ActionEvent event) {
		month.setCellValueFactory(new PropertyValueFactory<BlockSimple, String>("month"));
		courseName.setCellValueFactory(new PropertyValueFactory<BlockSimple, String>("courseName"));
		
		Entry en = tblEntries.getSelectionModel().getSelectedItem();
		
		System.out.println("view scheduler click");
		Schedule sc = scheduleService.findOne(en.getId());
		System.out.println("id there: " + sc.getId());
		
		
		List<BlockSimple> ls = sc.getBlockSimple();
		tblBlocks.getItems().setAll(ls);
		
	}
}
