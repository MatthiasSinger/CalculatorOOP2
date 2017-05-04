package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class CalculatorController
{
	 	@FXML
	    private Text output;

	    private CalculatorModel model = new CalculatorModel();

	    @FXML
	    private void processNumpad(ActionEvent event) 
	    {
	        String value = ((Button)event.getSource()).getText();
	        output.setText(output.getText() + value);
	    }

	    @FXML
	    private void processEqual(ActionEvent event) 
	    {
	    	String ergebnis = Double.toString(model.calculate(output.getText()));
	    	output.setText(ergebnis);
	    }
	    
	    @FXML
	    private void deleteAll(ActionEvent event)
	    {
	    	output.setText("");
	    }
}
