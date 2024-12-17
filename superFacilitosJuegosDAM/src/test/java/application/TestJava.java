package application;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import app.Launcher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)

public class TestJava {
    private Button button;

	@Start
	public void Start(Stage stage) throws IOException {
	
		FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/LogIn.fxml"));
	       
	 	Pane root = loader.load();
          
        Scene scene = new Scene(root);
        
        stage.setScene(scene);     
        stage.show();    
        stage.toFront();
	
	}
	
	@Test
	
	public void testTexto() {
		
		
		FxRobot robot = new FxRobot();

        robot.clickOn("#buttonEnterLogIn");
       
        robot.write("prueba pasada");
        
	}
	
	@Test
	
	public void testCambioVentana() {
		
		FxRobot robot = new FxRobot();
		robot.clickOn("#buttonRegister");
		    
		robot.sleep(2000);  

		FxAssert.verifyThat(robot.window("Registro"), WindowMatchers.isShowing());
	}
	
	   
	

}
