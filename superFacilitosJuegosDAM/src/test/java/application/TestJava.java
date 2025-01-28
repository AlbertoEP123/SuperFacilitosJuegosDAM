package application;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import app.Launcher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)

public class TestJava {

    @Start
    public void start(Stage stage) throws IOException {
        Launcher.init();
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/LogIn.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void testTexto(FxRobot robot) {
        robot.clickOn("#buttonEnterLogIn");
        robot.write("prueba pasada");
    }

    @Test
    public void testCambioRegister(FxRobot robot) {
        robot.clickOn("#buttonRegister");
        FxAssert.verifyThat(robot.window("Registro"), WindowMatchers.isShowing());
    }
    @Test
    public void testContraseñasNoCoinciden(FxRobot robot) {
        robot.clickOn("#buttonRegister");

        robot.clickOn("#nombreId").write("Carlos");
        robot.clickOn("#ApellidosId").write("Gómez");
        robot.clickOn("#FechaNacId").write("15/10/2000");

        robot.clickOn("#nicknameId").write("carlosg");
        robot.clickOn("#emailId").write("carlos@gmail.com");
        robot.clickOn("#confirmarEmailId").write("carlos@gmail.com");
        robot.clickOn("#contraseñaId").write("123");
        robot.clickOn("#confirmarContraseñaId").write("122");

        robot.clickOn("#botonRegistro");

        robot.lookup(".dialog-pane").tryQuery().ifPresent(dialogPane -> {
            DialogPane pane = (DialogPane) dialogPane;
            FxAssert.verifyThat(pane.getContentText(), LabeledMatchers.hasText("Las contraseñas no coinciden."));

            robot.clickOn(pane.lookupButton(ButtonType.OK));
        });
    
    }
    @Test
    public void testRegistroExitoso(FxRobot robot) {
        robot.clickOn("#buttonRegister");

        robot.clickOn("#nombreId").write("Juan");
        robot.clickOn("#ApellidosId").write("Pérez");
        robot.clickOn("#FechaNacId").write("15/10/2000");
        robot.clickOn("#nicknameId").write("juan90");
        robot.clickOn("#emailId").write("juan@gmail.com");
        robot.clickOn("#confirmarEmailId").write("juan@gmail.com");
        robot.clickOn("#contraseñaId").write("password123");
        robot.clickOn("#confirmarContraseñaId").write("password123");

        robot.clickOn("#botonRegistro");
        robot.lookup(".dialog-pane").tryQuery().ifPresent(dialogPane -> {
            DialogPane pane = (DialogPane) dialogPane;
            FxAssert.verifyThat(pane.getContentText(), LabeledMatchers.hasText("Se ha registrado el usuario Juan"));

            robot.clickOn(pane.lookupButton(ButtonType.OK));
        });
       
    }



    @Test
    public void testLoginTrue(FxRobot robot) {
        robot.clickOn("#texFieldUsername").write("alvarorb");
        robot.clickOn("#passwordField").write("1234");
        robot.clickOn("#buttonEnterLogIn");

        robot.lookup(".dialog-pane").tryQuery().ifPresent(dialogPane -> {
            DialogPane pane = (DialogPane) dialogPane;
            FxAssert.verifyThat(pane.getContentText(), LabeledMatchers.hasText("Te has logueado alvarorb"));

            robot.clickOn(pane.lookupButton(ButtonType.OK));
        });
    }
}
