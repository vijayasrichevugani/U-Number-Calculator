
package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author B.Pradeep Kumar
 * 	Based on the work of Lynn Robert Carter
 * 
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator
 * 
 */

public class UserInterface extends Application{
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	public final static double WINDOW_WIDTH = 900;
	public final static double WINDOW_HEIGHT = 400;
	
	

	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;
	
	// These are the application values required by the user interface
	private Label label_IntegerCalculator = new Label("Double Calculator");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();
	private Label label_Result = new Label("Result");
	private TextField text_Result = new TextField();
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Button button_sqroot=new Button("\u221A"); 				// The square root symbol : \u221A
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand2 = new Label("");
	private Label label_errResult = new Label("");
	private Label label_error1=new Label("errorterm1");				// ErrorTerm1 label is added
	private TextField text_error1=new TextField();					// Text Field for errorterm1 is added
	private Label label_errerror1=new Label("");						
	private Label label_error2=new Label("errorterm2");				// ErrorTerm2 label is added
	private TextField text_error2=new TextField();					// Text Field for errorterm2 is added
	private Label label_errerror2=new Label("");
	private Label label_Resulterr=new Label("Resulterr");			// Resulterr label is added
	private TextField text_Resulterr=new TextField();				// Text Field for Resulterr is added
	private Label label_errResulterr=new Label("");
	public TextField Result_Units = new TextField("");				// Text Feild for Result_Units is added
	public Button btnSelectAUnit1 = new Button("Select a Unit");	// Unit Buttons for both operands are added
	public Button btnSelectAUnit2 = new Button("Select a Unit");
	public TextField Units_Result = new TextField();				// TextFeild for Units_Result is added
	public Label label_UnitsError = new Label("");
	public Label label_unit1 = new Label("Select Unit 1");
	public Label label_unit2 = new Label("Select Unit 2");
	public Label label_unit3 = new Label("UNITS");
	private String unit1_index;
	private String unit2_index;
	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used.
	
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();
	
	TitledPane t11, t12, t13; //panes for accordion1
	ObservableList<Button> array1A = FXCollections.observableArrayList();
	ObservableList<Button> array1B = FXCollections.observableArrayList();
	ObservableList<Button> array1C = FXCollections.observableArrayList();

	TitledPane t21, t22, t23; //panes for accordion2
	ObservableList<Button> array2A = FXCollections.observableArrayList();
	ObservableList<Button> array2B = FXCollections.observableArrayList();
	ObservableList<Button> array2C = FXCollections.observableArrayList();
	
	TextField outputSelectedUnitCode1 = new TextField("0");
	TextField outputSelectedUnitCode2 = new TextField("0");
	TextField outputSelectedUnitCode3 = new TextField("0");
	
	//operand1 pop-up window button definitions
	Button btnUnit000 = createButton1 ("- no unit selected - ", "0");
	Button btnUnit001 = createButton1 ("nm", "1");
	Button btnUnit002 = createButton1 ("\u00B5m", "2");
	Button btnUnit003 = createButton1 ("mm", "3");
	Button btnUnit004 = createButton1 ("cm", "4");
	Button btnUnit005 = createButton1 ("m", "5");
	Button btnUnit006 = createButton1 ("km", "6");

	Button btnUnit007 = createButton1 ("inch", "7");
	Button btnUnit008 = createButton1 ("foot", "8");
	Button btnUnit009 = createButton1 ("yard", "9");
	Button btnUnit010 = createButton1 ("mile", "10");

	Button btnUnit011 = createButton1 ("ns", "11");
	Button btnUnit012 = createButton1 ("\u00B5s", "12");
	Button btnUnit013 = createButton1 ("ms", "13");
	Button btnUnit014 = createButton1 ("s", "14");
	Button btnUnit015 = createButton1 ("min", "15");
	Button btnUnit016 = createButton1 ("hr", "16");
	Button btnUnit017 = createButton1 ("day", "17");

	//operand2 pop-up window button definitions
	Button btnUnit018 = createButton2 ("- no unit selected - ", "0");
	Button btnUnit019 = createButton2 ("nm", "1");
	Button btnUnit020 = createButton2 ("\u00B5m", "2");
	Button btnUnit021 = createButton2 ("mm", "3");
	Button btnUnit022 = createButton2 ("cm", "4");
	Button btnUnit023 = createButton2 ("m", "5");
	Button btnUnit024 = createButton2 ("km", "6");

	Button btnUnit025 = createButton2 ("inch", "7");
	Button btnUnit026 = createButton2 ("foot", "8");
	Button btnUnit027 = createButton2 ("yard", "9");
	Button btnUnit028 = createButton2 ("mile", "10");

	Button btnUnit029 = createButton2 ("ns", "11");
	Button btnUnit030 = createButton2 ("\u00B5s", "12");
	Button btnUnit031 = createButton2 ("ms", "13");
	Button btnUnit032 = createButton2 ("s", "14");
	Button btnUnit033 = createButton2 ("min", "15");
	Button btnUnit034 = createButton2 ("hr", "16");
	Button btnUnit035 = createButton2 ("day", "17");
		
	Group rootPopUp1;
	Group rootPopUp2;
	
	Scene scenePopUp1;
	Scene scenePopUp2;
	
	static Stage stagePopUp1;
	static Stage stagePopUp2;
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
		buttonSpace = Calculator.WINDOW_WIDTH / 6;		
		setupLabelUI(label_IntegerCalculator, "Arial", 24, WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 70);
		
		setupLabelUI(label_error1, "Arial", 18, WINDOW_WIDTH-50, Pos.BASELINE_LEFT, 450, 70);
		
		setupLabelUI(label_unit1, "Arial", 18, WINDOW_WIDTH-50, Pos.BASELINE_LEFT, 740, 70);
		label_unit1.setTextFill(Color.BLUE);

		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, WINDOW_WIDTH-490, Pos.BASELINE_LEFT, 10, 100, true);
		
		setupTextUI(text_error1, "Arial", 18, 260, Pos.BASELINE_LEFT, WINDOW_WIDTH-460, 100, true);
		
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		
		text_error1.textProperty().addListener((observable, oldValue, newValue) -> {seterror1(); });
		// Move focus to the second operand when done
		text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 100, 45);
		label_errOperand1.setTextFill(Color.RED);
		
		// Establish an error message for the first operand Error term just above it with, right aligned
		setupLabelUI(label_errerror1, "Arial", 18, WINDOW_WIDTH-50, Pos.BASELINE_LEFT, 350, 45);
		label_errerror1.setTextFill(Color.RED);
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 165);
		
		setupLabelUI(label_error2,"Arial", 18, WINDOW_WIDTH-50, Pos.BASELINE_LEFT, 450,165);
		
		setupLabelUI(label_unit2,"Arial", 18, WINDOW_WIDTH-50, Pos.BASELINE_LEFT, 740,165);
		label_unit2.setTextFill(Color.BLUE);

		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18,WINDOW_WIDTH-490, Pos.BASELINE_LEFT, 10, 190, true);
		
		setupTextUI(text_error2, "Arial", 18, 260, Pos.BASELINE_LEFT, 440, 190, true);
		
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> { setOperand2(); });
		
		text_error2.textProperty().addListener((observable, oldValue, newValue) -> {seterror2(); });

		// Move the focus to the result when done
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		text_error2.setOnAction((event) -> { text_Resulterr.requestFocus(); });
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 160, 135);
		label_errOperand2.setTextFill(Color.RED);
		
		// Establish an error message for the second operand Error term just above it with, right aligned
		setupLabelUI(label_errerror2, "Arial", 18, WINDOW_WIDTH-50, Pos.BASELINE_LEFT, 350, 135);
		label_errerror2.setTextFill(Color.RED);
		
		// Label the result just above it, left aligned
		setupLabelUI(label_Result, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 250);
		
		setupLabelUI(label_Resulterr, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 490, 250);
		
		setupLabelUI(label_unit3, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 760, 250);
		
		// Establish the result output field.  It is not editable, so the text can be selected
		// and copied, but it cannot be altered by the user.
		setupTextUI(text_Result, "Arial",  18,WINDOW_WIDTH-490, Pos.BASELINE_LEFT, 10, 280, false);
		
		setupTextUI(text_Resulterr,"Arial", 18, 260, Pos.BASELINE_LEFT, 440,  280, true);
		
		setupTextUI(Units_Result,"Arial", 18, 100, Pos.BASELINE_LEFT, 740,  280, true);
		
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 460);
		button_Add.setOnAction((event) -> { addOperands(); });
		
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 460);
		button_Sub.setOnAction((event) -> { subOperands(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 460);
		button_Mpy.setOnAction((event) -> { mpyOperands(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 460);
		button_Div.setOnAction((event) -> { divOperands(); });
		
		setupButtonUI(button_sqroot, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 460);
		button_sqroot.setOnAction((event)->{rootOperands();});
		
		setupLabelUI(label_UnitsError, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 620, 250);
		label_UnitsError.setTextFill(Color.RED);
		
		//Here we add all labels that are created. The functionality of these labels is performed only when they are added to Root.getchildren()
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1,
				label_errOperand1, label_error1,text_error1,label_errerror1,label_Operand2, text_Operand2, 
				label_errOperand2, label_error2,text_error2,label_errerror2,label_Result,
				text_Result, label_errResult, label_Resulterr, text_Resulterr, label_errResulterr,
				button_Add,button_Sub, button_Mpy, button_Div,button_sqroot,btnSelectAUnit1,
				btnSelectAUnit2,Units_Result,label_UnitsError,label_unit1,label_unit2,label_unit3);

		//Units that are related to one group of operand1 are added to array   
		array1A = FXCollections.observableArrayList(
				btnUnit000, btnUnit001, btnUnit002, btnUnit003, btnUnit004,
				btnUnit005, btnUnit006);
		
		//Units that are related to one group of operand1  are added to each array
		array1B = FXCollections.observableArrayList(
				btnUnit007, btnUnit008, btnUnit009, btnUnit010);
		
		//Units that are related to one group of operand1 are added to each array
		array1C = FXCollections.observableArrayList(
				btnUnit011, btnUnit012, btnUnit013, btnUnit014,
				btnUnit015, btnUnit016, btnUnit017);
		
		//Units that are related to one group  of operand2 are added to each array
		array2A = FXCollections.observableArrayList(
				btnUnit018, btnUnit019, btnUnit020, btnUnit021, btnUnit022,
				btnUnit023, btnUnit024);
		
		//Units that are related to one group  of operand2 are added to each array
		array2B = FXCollections.observableArrayList(
				btnUnit025, btnUnit026, btnUnit027, btnUnit028);
		
		//Units that are related to one group  of operand2 are added to each array
		array2C = FXCollections.observableArrayList(
				btnUnit029, btnUnit030, btnUnit031, btnUnit032,
				btnUnit033, btnUnit034, btnUnit035);
		
		rootPopUp1=new Group();
		rootPopUp2=new Group();
		
		//Unit Titles of operand1 are added to list and is given name
		t11 = new TitledPane("Distance Metric", new ListView<>(array1A));
		t12 = new TitledPane("Distances Imperial", new ListView<>(array1B));
		t13 = new TitledPane("Time", new ListView<>(array1C));
		
		//Unit Titles of operand2 are added to list and is given name
		t21 = new TitledPane("Distance Metric", new ListView<>(array2A));
		t22 = new TitledPane("Distances Imperial", new ListView<>(array2B));
		t23 = new TitledPane("Time", new ListView<>(array2C));
		
		//adding all titlepanes to Accordion for operand1
		Accordion accordion1 = new Accordion();
		accordion1.getPanes().addAll(t11,t12,t13);
		accordion1.setMinWidth(300);
		accordion1.setMaxHeight(400);
		
		//adding all titlepanes to Accordion for operand1
		Accordion accordion2 = new Accordion();
		accordion2.getPanes().addAll(t21,t22,t23);
		accordion2.setMinWidth(300);
		accordion2.setMaxHeight(400);
		
		//Adding this accordions to getchildren
		rootPopUp1.getChildren().addAll(accordion1);
		rootPopUp2.getChildren().addAll(accordion2);
		
		scenePopUp1 = new Scene(rootPopUp1, 300, 400);
		stagePopUp1 = new Stage();
		stagePopUp1.setScene(scenePopUp1);
		stagePopUp1.setTitle("Select a unit");
		
		scenePopUp2 = new Scene(rootPopUp2, 300, 400);
		stagePopUp2 = new Stage();
		stagePopUp2.setScene(scenePopUp2);
		stagePopUp2.setTitle("Select a unit");
		
		setupButtonUI(btnSelectAUnit1, "Arial", 14, 70, Pos.BASELINE_LEFT, 740, 105);
		btnSelectAUnit1.setOnAction(e->{stagePopUp1.showAndWait();});
		btnSelectAUnit1.setMinWidth(28);
		
		setupButtonUI(btnSelectAUnit2, "Arial", 14, 70, Pos.BASELINE_LEFT, 740, 195);
		btnSelectAUnit2.setOnAction(e->{stagePopUp2.showAndWait();});
		btnSelectAUnit2.setMinWidth(28);
	}

	//This method takes input units given by user and also set the unit1_index
	public  Button createButton1 (String buttonText, String txtIndex) {
		Button button = new Button(buttonText);
		button.setOnAction(eve->{
			btnSelectAUnit1.setText(button.getText());
			outputSelectedUnitCode1.setText(txtIndex);
			label_UnitsError.setText("");
			text_Result.setText("");
			text_Resulterr.setText("");
			Units_Result.setText("");
			unit1_index = txtIndex;			
			stagePopUp1.close();
		});

		return button;
	}

	//This method takes input units given by user and also set the unit2_index
	public  Button createButton2 (String buttonText, String txtIndex) {
		Button button = new Button(buttonText);
		button.setOnAction(eve->{
			btnSelectAUnit2.setText(button.getText());
			outputSelectedUnitCode2.setText(txtIndex); 
			label_UnitsError.setText("");
			text_Result.setText("");
			text_Resulterr.setText("");
			Units_Result.setText("");
			unit2_index = txtIndex;
			stagePopUp2.close();
			}
				);

		return button;
	}

	
	public void start(Stage theStage) throws Exception
	{
	}
	
//		
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void setOperand1() {
		text_Result.setText("");								// Any change of an operand probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.setOperand1(text_Operand1.getText())) {	// Set the operand and see if there was an error
			label_errOperand1.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			label_errOperand1.setText(perform.getOperand1ErrorMessage());	// the error message that was generated
	}

	/**********
	 * Private local method to set the value of the first operand error term given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void seterror1() {
		text_Result.setText("");								// Any change of an error term1 probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.seterror1(text_error1.getText())) {	// Set the error term and see if there was an error
			label_errerror1.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		
		else 												// If there's a problem with the error term, display
			label_errerror1.setText(perform.geterror1ErrorMessage());
				// the error message that was generated
	}
	/**********
	 * Private local method to set the value of the second operand error term given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void seterror2() {
		text_Resulterr.setText("");								// Any change of an error term probably invalidates
		label_Resulterr.setText("Resulterr");						// the result, so we clear the old result.
		label_errResulterr.setText("");
		if (perform.seterror2(text_error2.getText())) {	// Set the error term and see if there was an error
			label_errerror2.setText("");					// If no error, clear this operands error
			if (text_error1.getText().length() == 0)		// If the other error term is empty, clear its error
				label_errerror1.setText("");				// as well.
		}
		else 												// If there's a problem with the error term, display
			label_errerror2.setText(perform.geterror2ErrorMessage());
				// the error message that was generated
	}
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		label_Result.setText("Result");				
		label_errResult.setText("");
		if (perform.setOperand2(text_Operand2.getText())) {
			label_errOperand2.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
		}
		else
			label_errOperand2.setText(perform.getOperand2ErrorMessage());
	}
	
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		String errortermMessage1 = perform.geterror1ErrorMessage();
		String errortermMessage2 = perform.geterror2ErrorMessage();
		if (errorMessage1.length() > 0 || errortermMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);// there's an error message, so display it.
			label_errerror1.setText(errortermMessage1);
			if (errorMessage2.length() > 0 || errortermMessage2.length() > 0 ) {					// Check the second and display it if there is
				label_errOperand2.setText(errorMessage2);		// and error with the second as well.
				label_errerror2.setText(errortermMessage2);
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0 || errortermMessage2.length() > 0) {					// No error with the first, so check the second
			label_errOperand2.setText(errorMessage2);			// operand. If non-empty string, display the error
			label_errerror2.setText(errortermMessage2);	
			return true;											// message and return true... the second has an error
		}
		return false;
	}
	
	private boolean rootoperandIssues()
	{
		String errorMessage1=perform.getOperand1ErrorMessage();
		String errortermMessage1 = perform.geterror1ErrorMessage();
		if (errorMessage1.length() > 0 || errortermMessage1.length() > 0 ) {
			label_errOperand1.setText(errorMessage1);
			label_errerror1.setText(errortermMessage1);
			return true;
		}
		return false;
	}
	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	private void addOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.addition(unit1_index,unit2_index);						// Call the business logic add method
		String res1 = perform.additionerr();
		
		if(theAnswer.equals("null") || res1.equals("null")) {
			label_UnitsError.setText("INCOMPATIBLE UNITS");
			return;
		}
		
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {// Check the returned String to see if it is okay
			if(!theAnswer.contains(","))
			{
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("Sum");
			}
			if(!res1.contains(","))
			{
			text_Resulterr.setText(res1);							// If okay, display it in the result field and
			label_Resulterr.setText("sumerr");
		}
		}
		else {														// Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
		String[] res = theAnswer.split(",");
		text_Result.setText(res[0]);	
		label_Result.setText("Sum");
		Units_Result.setText(res[1]);
	}


	/**********
	 * This is the subtract routine
	 * 
	 */
		private void subOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the subtraction and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.subtraction(unit1_index,unit2_index);
		String theAnswererr = perform.additionerr();// Call the business logic subtraction method
		label_errResult.setText("");									// Reset any result error messages from before
		
		if(theAnswer.equals("null") || theAnswererr.equals("null")) {
			label_UnitsError.setText("INCOMPATIBLE UNITS");
			return;
		}
		
		if (theAnswer.length() > 0) {// Check the returned String to see if it is okay
			if(!theAnswer.contains(",")) {
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("Subtraction");								// change the title of the field to "Subtraction"
		}
		}
		else {														// Some error occurred while doing the subtraction.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
		String[] res = theAnswer.split(",");
		text_Result.setText(res[0]);	
		label_Result.setText("subtraction");
		Units_Result.setText(res[1]);
		if (theAnswererr.length() > 0) {								// Check the returned String to see if it is okay
			if(!theAnswererr.contains(",")) {
			text_Resulterr.setText(theAnswererr);							// If okay, display it in the result field and
			label_Resulterr.setText("differr");								// change the title of the field to "differr"
		}
		}
		else {														// Some error occurred while doing the subtraction.
			text_Resulterr.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResulterr.setText(perform.getResultErrorMessage());	// Display the error message.
		}
		
		}
	/*
	private void subOperands(){
		label_Result.setText("Subtraction not yet implemented!");		// Replace this line with the code
		text_Result.setText("");										// required to do subtraction.
	}
	*/

	/**********
	 * This is the multiply routine
	 * 
	 */
		private void mpyOperands(){
			// Check to see if both operands are defined and valid
			if (binaryOperandIssues()) 									// If there are issues with the operands, return
				return;													// without doing the computation
			
			// If the operands are defined and valid, request the business logic method to do the multiplication and return the
			// result as a String. If there is a problem with the actual computation, an empty string is returned
			
			String theAnswer = perform.multiplication(unit1_index,unit2_index);// Call the business logic multiplication method
			String theAnswererr = perform.multiplicationerr(unit1_index,unit2_index);
			label_errResult.setText("");									// Reset any result error messages from before
			label_errResulterr.setText("");
			if(theAnswer.equals("null")|| theAnswererr.equals("null")) {
				label_UnitsError.setText("INCOMPATIBLE UNITS");
				return;
			}
			if (theAnswer.length() > 0) {// Check the returned String to see if it is okay
				if(!theAnswer.contains(",")) {
				text_Result.setText(theAnswer);							// If okay, display it in the result field and
				label_Result.setText("Multiplication");								// change the title of the field to "Multiplication"
			}
			}
			else {														// Some error occurred while doing the multiplication.
				text_Result.setText("");									// Do not display a result if there is an error.				
				label_Result.setText("Result");							// Reset the result label if there is an error.
				label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
			}
			String[] res = theAnswer.split(",");
			text_Result.setText(res[0]);	
			label_Result.setText("multiplication");
			Units_Result.setText(res[1]);
			if (theAnswererr.length() > 0) {								// Check the returned String to see if it is okay
				if(!theAnswererr.contains(",")) {
				text_Resulterr.setText(theAnswererr);							// If okay, display it in the result field and
				label_Resulterr.setText("Multiplicationerr");								// change the title of the field to "Multiplicationerr"
			}
			}
			else {														// Some error occurred while doing the Multiplication.
				text_Resulterr.setText("");									// Do not display a result if there is an error.				
				label_Resulterr.setText("Result");							// Reset the result label if there is an error.
				label_errResulterr.setText(perform.getResultErrorMessage());	// Display the error message.
			}
			String[] res1 = theAnswererr.split(",");
			text_Resulterr.setText(res1[0]);	
			label_Resulterr.setText("multiplicationerr");
			
		}
	/*
	private void mpyOperands(){
		label_Result.setText("Multiplication not yet implemented!");	// Replace this line with the code
		text_Result.setText("");										// required to do multiplication.
	}
	*/

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
			private void divOperands(){
				// Check to see if both operands are defined and valid
				if (binaryOperandIssues()) 									// If there are issues with the operands, return
					return;													// without doing the computation
				
				// If the operands are defined and valid, request the business logic method to do the Division and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.division(unit1_index,unit2_index);						// Call the business logic Division method
				String theAnswererr = perform.divisionerr(unit1_index,unit2_index);
				label_errResult.setText("");									// Reset any result error messages from before
				if(theAnswer.equals("null")||theAnswererr.equals("null")) {
					label_UnitsError.setText("INCOMPATIBLE UNITS");
					return;
				}
				String[] res = null;
				if (theAnswer.length() > 0) {// Check the returned String to see if it is okay
					res = theAnswer.split(",");
					if(res.length==1) {
					text_Result.setText(res[0]);							// If okay, display it in the result field and
					label_Result.setText("Division");								// change the title of the field to "Division"
				}
				}
				else {														// Some error occurred while doing the Division.
					text_Result.setText("");									// Do not display a result if there is an error.				
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
				}
				text_Result.setText(res[0]);	
				label_Result.setText("Division");
				Units_Result.setText(res[1]);
				
				if (theAnswererr.length() > 0) {								// Check the returned String to see if it is okay
					res = theAnswererr.split(",");
					if(res.length==1) {
					text_Resulterr.setText(res[0]);							// If okay, display it in the result field and
					label_Resulterr.setText("Divisionerr");								// change the title of the field to "Divisionerr"
				}
				}
				else {														// Some error occurred while doing the Division.
					text_Resulterr.setText("");									// Do not display a result if there is an error.				
					label_Resulterr.setText("Result");							// Reset the result label if there is an error.
					label_errResulterr.setText(perform.getResultErrorMessage());	// Display the error message.
				}
				text_Result.setText(res[0]);	
				label_Result.setText("Division");
				Units_Result.setText(res[1]);
			}
	/*
	private void divOperands(){
		label_Result.setText("Division not yet implemented!");		// Replace this line with the code
		text_Result.setText("");										// required to do division.
	}
	*/
			private void rootOperands()
			{
				// Check to see if both operands are defined and valid
				if (rootoperandIssues()) 									// If there are issues with the operands, return
					return;													// without doing the computation
				
				// If the operands are defined and valid, request the business logic method to do the square root and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.squareroot();						// Call the business logic square root method
				String theAnswererr=perform.squarerooterror();
				label_errResult.setText("");									// Reset any result error messages from before
				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					text_Result.setText(theAnswer);							// If okay, display it in the result field and
					label_Result.setText("square root");// change the title of the field to "square root"
					Units_Result.setText(btnSelectAUnit1.getText()+"^ 1/2");
				}
				else {														// Some error occurred while doing the square root.
					text_Result.setText("");									// Do not display a result if there is an error.				
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
				}
				if(theAnswererr.length()>0)
				{
					text_Resulterr.setText(theAnswererr);
					label_Resulterr.setText("squarerooterr");
					Units_Result.setText(btnSelectAUnit1.getText()+"^1/2");
				}
		}
			}

