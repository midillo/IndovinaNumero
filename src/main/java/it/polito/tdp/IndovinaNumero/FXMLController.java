/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	@FXML // fx:id="layoutTentativo"
    private HBox layoutTentativo; // Value injected by FXMLLoader
	
    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativoUtente"
    private TextField txtTentativoUtente; // Value injected by FXMLLoader

    @FXML // fx:id="btmProva"
    private Button btmProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	//gestione inizio partita
    	this.segreto = (int) (Math.random()*NMAX)+1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	
    	//gestione interfaccia
    	this.txtTentativi.setText(Integer.toString(TMAX));
    	this.layoutTentativo.setDisable(false);
    
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//lettura input dell'utente
    	String ts = txtTentativoUtente.getText();
    	
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	}catch(NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero.");
    		return;
    	}
    
    	this.txtTentativoUtente.setText("");
    	this.tentativiFatti ++;
    	this.txtTentativi.setText(Integer.toString(TMAX - this.tentativiFatti));
    	
    	if(tentativo == this.segreto) {
    		//indovinato
    		txtRisultato.setText("Hai vinto con " + this.tentativiFatti + " tentativi!");
    		this.inGioco = false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	
    	if(this.tentativiFatti == TMAX) {
    		txtRisultato.setText("Hai perso. Il segreto era: " + this.segreto);
    		this.layoutTentativo.setDisable(true);
    		this.inGioco = false;
    		return;
    	}
    	//non ha indovinato
    	if(tentativo<this.segreto) {
    		txtRisultato.setText("Tentativo troppo basso.");
    	}else {
    		txtRisultato.setText("Tentativo troppo alto.");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btmProva != null : "fx:id=\"btmProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
 
}
