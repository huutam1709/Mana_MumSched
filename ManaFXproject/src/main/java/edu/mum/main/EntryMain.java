package edu.mum.main;


import java.awt.event.MouseEvent;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.BlockSimple;
import edu.mum.domain.Entry;
import edu.mum.domain.Schedule;
import edu.mum.enums.ScheduleStatusEnum;
import edu.mum.service.EntryService;
import edu.mum.service.ScheduleService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	
	@FXML protected ComboBox cmbStatus;
	
	 
	@Autowired
	EntryService entryService;
	
	@Autowired
	ScheduleService scheduleService;

	
	@SuppressWarnings("unchecked")
	@FXML public void initialize() {
		cmbStatus.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				Entry en = tblEntries.getSelectionModel().getSelectedItem();
				ScheduleStatusEnum currentStatus = ScheduleStatusEnum.valueOf((String) cmbStatus.getValue());
				Schedule sc = new Schedule();
				sc.setId(en.getId());
				sc.setStatus(currentStatus);
				scheduleService.update(sc);
			}
		});
		
		loadEntries();
//		tblEntries.setOnMouseClicked(e -> {
//			if( e.getClickCount() == 1 ) {
//				System.out.println("1 times");
//			}});
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
		
		//System.out.println("view scheduler click");
		Schedule sc = scheduleService.findOne(en.getId());
		System.out.println("id there: " + sc.getId());
		
		List<BlockSimple> ls = sc.getBlockSimple();
		tblBlocks.getItems().setAll(ls);
		
		setSelectedItemCombobox(sc.getStatus());
	}
	
	private void setSelectedItemCombobox(ScheduleStatusEnum status) {
		switch( status) {
		case PENDING:cmbStatus.getSelectionModel().select(0); break;
		case DRAFT:cmbStatus.getSelectionModel().select(1); break;
		case APPROVED:cmbStatus.getSelectionModel().select(2); break;
		}
		
	}
	
}
