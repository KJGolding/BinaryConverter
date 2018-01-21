package BinaryConverter;

/**
 * @Author Name: Kimberly Golding
 * @Date: Jun 17, 2017
 * @Description: Binary Converter to convert base-10 integers input by user to
 * base-2 binary numbers
 */

//Imports
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Begin Class BinaryConverter
public class BinaryConverter extends Application {

    //Class-wide Declarations
    private TextField tfInt = new TextField();
    private int userInt;
    private TextArea taBinary = new TextArea();
    private Button btConvert = new Button("Convert");
    private ArrayList<String> binary = new ArrayList<>();
    private Button btClear = new Button("Clear");
    private Button btExit = new Button("Exit");

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create border pane
        BorderPane b = new BorderPane();
        b.setPadding(new Insets(10, 10, 10, 10));

        //Place nodes in the border pane
        b.setTop(getTop());
        b.setLeft(getLeft());
        b.setCenter(getRight());
        b.setBottom(getBottom());

        // Create and register handler for exit button
        btExit.setOnAction(exit -> System.exit(0));

        //Create and register handler for reset button
        btClear.setOnAction(clear -> {
            tfInt.clear();
            taBinary.clear();
            tfInt.requestFocus();
        });

        //Create and register handler for convert button
        btConvert.setOnAction(go -> {
            
            //Clear text area and ArrayList contents for new Binary Conversion
            taBinary.clear();
            binary.clear();

            //pop up alert to notify user if field empty
            if (tfInt.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Required Field Empty");
                alert.setContentText("You must enter a number in the "
                        + "\"Enter Number: \" field.\nPlease try again.");
                tfInt.clear();
                tfInt.requestFocus();
                alert.showAndWait();

            //pop up alert to nofity user if field is not a number
            } else if (!tfInt.getText().trim().isEmpty()) {
                try { //parse user entry to Int
                    userInt = parseInt(tfInt.getText().trim());
                    //Call recursive method and pass user input
                    toBinary(userInt);
                    //Call method to convert ArrayList to String
                    results();
                } catch (NumberFormatException nfe) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Data Entry Error.");
                    alert.setContentText("You must enter a whole number in the "
                            + "\"Enter Number: \" field.\nNo letters, symbols, "
                            + "or decimal numbers permitted.\nPlease try again.");
                    tfInt.clear();
                    taBinary.clear();
                    tfInt.requestFocus();
                    alert.showAndWait();
                }
            }
        }); //end btConvert handler

        //Create a scene and place it in the stage
        Scene scene = new Scene(b, 550, 500);
        primaryStage.setTitle("Binary Converter");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * Method to create VBox for Top Region of Border Pane
     *
     * @return top
     */
    private VBox getTop() {

        //Create VBox
        VBox top = new VBox();
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(10, 10, 10, 10));

        //Create text & set characteristics
        Text txt1 = new Text("Binary Converter");
        txt1.setFont(Font.font("SansSerif", FontWeight.BOLD,
                FontPosture.ITALIC, 32));
        Text txt2 = new Text("Please enter an integer in the Number box and "
                + "click Convert");
        txt2.setFont(Font.font("SansSerif", FontWeight.BOLD,
                FontPosture.ITALIC, 16));

        //Add text to VBox
        top.getChildren().addAll(txt1, txt2);

        return top;
    }

    /**
     * Method to create VBox for Left Region of Border Pane
     *
     * @return left
     */
    private VBox getLeft() {

        //Create VBox to Display Image & Nodes from getLnodes() method
        VBox left = new VBox(10);
        left.setPadding(new Insets(10, 10, 10, 10));
        left.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
        left.setAlignment(Pos.TOP_CENTER);
        left.setMaxWidth(250);

        //Create an image and show image via ImageView
        Image image = new Image("/images/BinaryNums.jpg");
        ImageView iView = new ImageView(image);
        iView.setFitHeight(300);
        iView.setFitWidth(225);

        //Add nodes to VBox
        left.getChildren().add(getLnodes());
        left.getChildren().add(iView);

        return left;
    }

    /**
     * Method to create HBox with nodes for use in getLeft VBox
     *
     * @return nodes
     */
    private HBox getLnodes() {

        //Create HBox for nodes to display in left region
        HBox nodes = new HBox();

        //Create Label & TextField
        Label lblInt = new Label("Enter Number: ");
        tfInt.setMaxWidth(100);

        //Add nodes to HBox to display in left region
        nodes.getChildren().addAll(lblInt, tfInt);

        return nodes;
    }

    /**
     * Method to create HBox to hold Text Area to display binary conversion
     *
     * @return right
     */
    private HBox getRight() {

        //Create HBox for Text Area
        HBox right = new HBox();
        right.setPadding(new Insets(10, 10, 10, 10));
        right.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
        right.setAlignment(Pos.TOP_CENTER);
        right.setMaxWidth(265);

        //Set Text Area features
        taBinary.setEditable(false);
        taBinary.setWrapText(true);
        taBinary.setPrefColumnCount(20);
        taBinary.setPrefRowCount(20);

        //Add Text Area to HBox
        right.getChildren().add(taBinary);

        return right;
    }

    /**
     * Method to create HBox for Bottom Region of Border Pane
     *
     * @return bottom
     */
    private HBox getBottom() {

        //Create HBox to Display Buttons
        HBox bottom = new HBox(10);
        bottom.setPadding(new Insets(10, 10, 10, 10));
        bottom.setAlignment(Pos.CENTER);

        //Add nodes to HBox
        bottom.getChildren().addAll(btConvert, btClear, btExit);

        return bottom;
    }

    /**
     * Recursive method to determine binary value of user entered number
     *
     * @param num
     * @return num
     */
    public int toBinary(int num) {

        //Exit recursion when num gets to 0
        if (num == 0) {
            return num;
        } else {
            //Find remainder
            int u = (num - 2 * (num / 2));
            //Convert remainder to a string
            String r = Integer.toString(u);
            //Add remainder ArrayList
            binary.add(r);
            //Output result
            taBinary.appendText(num + "/2 =\t" + num / 2 + " Remainder " + r + "\n");
            //Recursive call to method
            return toBinary(num / 2);
        }
    }

    /**
     * Method to convert ArrayList to & output String value of Binary Conversion
     */
    public void results() {
        //Declaration
        String list = "";

        //Reverse order of ArrayList
        Collections.reverse(binary);

        //Convert elements of ArrayList to String
        for (String s : binary) {
            list += s + "";
        }

        //Output binary value of user input
        taBinary.appendText("---------------------------------------------\n"
                + "Results: " + list);
    }

} //End Class BinaryConverter
