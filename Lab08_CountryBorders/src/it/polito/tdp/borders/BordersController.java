/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		if(txtAnno.getText().equals("") || !txtAnno.getText().matches("[\\d]+")){
			this.txtResult.setText("Inserire un numero.");
			return;
		}
		this.txtResult.clear();
		int anno=Integer.parseInt(txtAnno.getText());
		if(anno<1816 || anno>2006){
			this.txtResult.setText("Errore: inserire un anno valido compreso tra il 1816 e il 2006.");
			return;
		}
		model.createGraph(anno);
		this.txtResult.setText("Numero di componenti connesse nel grafo: "+model.getNumberOfConnectedComponents()+"\n");
		this.txtResult.appendText("Elenco stati con numero di stati confinanti: \n");
		for(String s : model.getCountryCounts())
			this.txtResult.appendText(s+"\n");
		return;
		
	}
	
	public void setModel(Model model){
		this.model=model;
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
	}
}
