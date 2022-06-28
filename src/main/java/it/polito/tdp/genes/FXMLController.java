package it.polito.tdp.genes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStatistiche;

    @FXML
    private Button btnRicerca;

    @FXML
    private ComboBox<String> boxLocalizzazione;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRicerca(ActionEvent event) {
    	String l = boxLocalizzazione.getValue();
        txtResult.appendText("\n"+this.model.cercaCammino(l));
    }

    @FXML
    void doStatistiche(ActionEvent event) {
    String l = boxLocalizzazione.getValue();
    txtResult.appendText("\n"+this.model.getCompConn(l));
    }

    @FXML
    void initialize() {
        assert btnStatistiche != null : "fx:id=\"btnStatistiche\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxLocalizzazione != null : "fx:id=\"boxLocalizzazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.model.creaGrafo();
		txtResult.appendText("Archi: "+this.model.nArchi()+"\n");
		txtResult.appendText("Vertici: "+this.model.nVertici()+"\n");
		boxLocalizzazione.getItems().clear();
		boxLocalizzazione.getItems().addAll(this.model.getLocalizzazioni());
	}
}
