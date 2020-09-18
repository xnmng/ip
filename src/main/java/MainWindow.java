import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        String intro = Ui.LINE + "     Hello! I'm Duke and I was designed by \n     Xuan Ming!" + Ui.LINE;
        DialogBox box = DialogBox.getDukeDialog(intro, dukeImage);
        dialogContainer.getChildren().add(box);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = Ui.LINE + userInput.getText() + Ui.LINE;
        String response = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (userInput.getText().equals("bye")) {
            System.exit(0);
        }
        userInput.clear();
    }
}