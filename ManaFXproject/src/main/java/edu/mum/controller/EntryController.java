
package edu.mum.controller;
 
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.mum.domain.BlockSimple;
import edu.mum.domain.Entry;
import edu.mum.domain.Schedule;
import edu.mum.domain.UserLogin;
import edu.mum.enums.ScheduleStatusEnum;
import edu.mum.main.ViewManager;
import edu.mum.rest.service.EntryRestService;
import edu.mum.service.EntryService;
import edu.mum.service.ScheduleService;
import edu.mum.service.UserLoginService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
@Component
@Scope("prototype")
public class EntryController {
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
	
	 
	@FXML private Text actiontarget;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    
    @Autowired
	EntryService entryService;
    
    @Autowired
    UserLoginService userLoginService;
    
    @Autowired
    ScheduleService scheduleService;
    
    @Autowired
    private ApplicationContext context;

	
	@SuppressWarnings("unchecked")
	@FXML public void initialize() {
		
		//context = new ClassPathXmlApplicationContext("context/applicationContext.xml");
		//SentryService = (EntryService)context.getBean("EntryServiceImpl");
		
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
		//tblEntries.getItems().setAll(ls);
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
