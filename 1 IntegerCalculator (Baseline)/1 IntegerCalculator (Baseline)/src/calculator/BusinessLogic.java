
package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javafx.scene.control.TextField;

/**
 * <p> Title: BusinessLogic Class. </p>
 * 
 * <p> Description: The code responsible for performing the calculator business logic functions. 
 * This method deals with CalculatorValues and performs actions on them.  The class expects data
 * from the User Interface to arrive as Strings and returns Strings to it.  This class calls the
 * CalculatorValue class to do computations and this class knows nothing about the actual 
 * representation of CalculatorValues, that is the responsibility of the CalculatorValue class and
 * the classes it calls.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author B.Pradeep Kumar
 * 	Based on the work of Lynn Robert Carter
 * 
 * @version 4.00	2014-10-18 The JavaFX-based GUI implementation of a long integer calculator 
 * 
 */

public class BusinessLogic {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major calculator values 
	
	private CalculatorValue operand1 = new CalculatorValue(0);
	private CalculatorValue operand2 = new CalculatorValue(0);
	private CalculatorValue result = new CalculatorValue(0);
	private CalculatorValue error1 = new CalculatorValue(0);
	private CalculatorValue error2 = new CalculatorValue(0);
	private String operand1ErrorMessage = "";
	private String error1ErrorMessage = "";
	private boolean operand1Defined = false;
	private String operand2ErrorMessage = "";
	private String error2ErrorMessage = "";
	private boolean operand2Defined = false;
	private String resultErrorMessage = "";
	private boolean error1Defined = false;
	private boolean error2Defined = false;
	static TextField resultUnit = new TextField("");
	
	private static int addSubTable[][] = {
			{  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,   -1,  -1,  -1,  -1,  -1,  -1,  -1},
			{  1,  2,  2,  2,  2,  2,  2,  2,  2,  2,   -1,  -1,  -1,  -1,  -1,  -1,  -1},
			{  1,  2,  3,  3,  3,  3,  3,  3,  3,  3,   -1,  -1,  -1,  -1,  -1,  -1,  -1},
			{  1,  2,  3,  4,  4,  4,  4,  4,  4,  4,   -1,  -1,  -1,  -1,  -1,  -1,  -1},
			{  1,  2,  3,  4,  5,  5,  5,  5,  5,  5,   -1,  -1,  -1,  -1,  -1,  -1,  -1},
			{  1,  2,  3,  4,  5,  6,  6,  6,  6,  6,   -1,  -1,  -1,  -1,  -1,  -1,  -1},
			{  1,  2,  3,  4,  5,  6,  7,  7,  7,  7,   -1,  -1,  -1,  -1,  -1,  -1,  -1},
			{  1,  2,  3,  4,  5,  6,  7,  8,  8,  8,   -1,  -1,  -1,  -1,  -1,  -1,  -1},
			{  1,  2,  3,  4,  5,  6,  7,  8,  9,  9,   -1,  -1,  -1,  -1,  -1,  -1,  -1},
			{  1,  2,  3,  4,  5,  6,  7,  8,  9, 10,   -1,  -1,  -1,  -1,  -1,  -1,  -1},
			{ -1, -1, -1, -1, -1, -1, -1,  -1, -1, -1,  11,  11,  11,  11,  11,  11,  11},
			{ -1, -1, -1, -1, -1, -1, -1,  -1, -1, -1,  11,  12,  12,  12,  12,  12,  12},
			{ -1, -1, -1, -1, -1, -1, -1,  -1, -1, -1,  11,  12,  13,  13,  13,  13,  13},
			{ -1, -1, -1, -1, -1, -1, -1,  -1, -1, -1,  11,  12,  13,  14,  14,  14,  14},
			{ -1, -1, -1, -1, -1, -1, -1,  -1, -1, -1,  11,  12,  13,  14,  15,  15,  15},
			{ -1, -1, -1, -1, -1, -1, -1,  -1, -1, -1,  11,  12,  13,  14,  15,  16,  16},
			{ -1, -1, -1, -1, -1, -1, -1,  -1, -1, -1,  11,  12,  13,  14,  15,  16,  17},		
	};
	
	private static double normalizeTable[][] = {
			{        1,        1,        1,        1,        1,        1,        1,        1,        1,        1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{     1000,        1,        1,        1,        1,        1,        1,        1,        1,        1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{      1E6,      1E3,        1,        1,        1,        1,        1,        1,        1,        1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{      1E7,      1E4,       10,        1,        1,        1,        1,        1,        1,        1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{      1E9,      1E6,      1E3,      1E2,        1,        1,        1,        1,        1,        1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{     1E12,      1E9,      1E6,      1E5,      1E3,        1,        1,        1,        1,        1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{   2.54E7,   2.54E4,   2.54E1,   2.54E0,  2.54E-2,  2.54E-5,        1,        1,        1,        1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{   3.05E8,   3.05E5,   3.05E2,   3.05E1,  3.05E-1,  3.05E-4,       12,        1,        1,        1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{   9.14E8,   9.14E5,   9.14E2,   9.14E1,  9.14E-1,  9.14E-4,       36,        3,        1,        1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{  1.61E12,   1.61E9,   1.61E6,   1.61E5,   1.61E3,   1.61E0,   6.34E4,   5.28E3,   1.76E3,        1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,        -1,       -1,       -1},
			{       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,      1E3,        1,        1,        1,         1,        1,        1},
			{       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,      1E6,      1E3,        1,        1,         1,        1,        1},
			{       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,      1E9,      1E6,      1E3,        1,         1,        1,        1},
			{       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,     6E10,      6E7,      1E4,       60,         1,        1,        1},
			{       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,   3.6E12,    3.6E9,    3.6E6,    3.6E3,        60,        1,        1},
			{       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,       -1,  8.64E13,  8.64E10,   8.64E7,   8.64E4,   1.44E+3,       24,        1}
	};
	
	static String unitTitle[] = {"","nm","\u00B5m","mm","cm","m","km","inch","foot","yard","mile","ns","\u00B5s","ms","s","min",
			"hr","day"};
	static String mpyTitle[] = {"","nm^2","\u00B5m^2","mm^2","cm^2","m^2","km^2","inch^2","foot^2","yard^2","mile^2","ns^2","\u00B5s^2","ms^2","s^2","min^2",
			"hr^2","day^2"};
	static String squareRootTitle[] = {"","nm^1/2","\u00B5m^1/2","mm^1/2","cm^1/2","m^1/2","km^1/2","inch^1/2","foot^1/2","yard^1/2","mile^1/2","ns^1/2","\u00B5s^1/2","ms^1/2","s^1/2","min^1/2",
			"hr^1/2","day^1/2"};
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/
	
	/**********
	 * This method initializes all of the elements of the business logic aspect of the calculator.
	 * There is no special computational initialization required, so the initialization of the
	 * attributes above are all that are needed.
	 */
	public BusinessLogic() {
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into operand1, any associated error message
	 * into operand1ErrorMessage, and sets flags accordingly.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setOperand1(String value) {
		operand1Defined = false;							// Assume the operand will not be defined
		if (value.length() <= 0) {						// See if the input is empty. If so no error
			operand1ErrorMessage = "";					// message, but the operand is not defined.
			return true;									// Return saying there was no error.
		}
		operand1 = new CalculatorValue(value);			// If there was input text, try to convert it
		operand1ErrorMessage = operand1.getErrorMessage();	// into a CalculatorValue and see if it
		if (operand1ErrorMessage.length() > 0) 			// worked. If there is a non-empty error 
			return false;								// message, signal there was a problem.
		operand1Defined = true;							// Otherwise, set the defined flag and
		return true;										// signal that the set worked
	}
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into error1, any associated error message
	 * into error1ErrorMessage, and sets flags accordingly.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean seterror1(String value) {
		error1Defined = false;							// Assume the error term will not be defined
		error1=new CalculatorValue(value,0);
		error1ErrorMessage=error1.getErrorMessage();
		if(error1ErrorMessage.length()>0)
			return false;
		error1Defined=true;
		return true;
	}
	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into operand2, any associated error message
	 * into operand1ErrorMessage, and sets flags accordingly.
	 * 
	 * The logic of this method is the same as that for operand1 above.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setOperand2(String value) {			// The logic of this method is exactly the
		operand2Defined = false;							// same as that for operand1, above.
		if (value.length() <= 0) {
			operand2ErrorMessage = "";
			return true;
		}
		operand2 = new CalculatorValue(value);
		operand2ErrorMessage = operand2.getErrorMessage();
		if (operand2ErrorMessage.length() > 0)
			return false;
		operand2Defined = true;
		return true;
	}
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into error2, any associated error message
	 * into error2ErrorMessage, and sets flags accordingly.
	 * 
	 * The logic of this method is the same as that for error1 above.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean seterror2(String value) {			// The logic of this method is exactly the
		error2Defined = false;							// same as that for error term1, above.
		error2 = new CalculatorValue(value,0);
		error2ErrorMessage=error2.getErrorMessage();
		if(error2ErrorMessage.length()>0)
			return false;
		error2Defined = true;
		return true;
		}
		
	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into result, any associated error message
	 * into resuyltErrorMessage, and sets flags accordingly.
	 * 
	 * The logic of this method is similar to that for operand1 above. (There is no defined flag.)
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setResult(String value) {				// The logic of this method is similar to
		if (value.length() <= 0) {						// that for operand1, above.
			operand2ErrorMessage = "";
			return true;
		}
		result = new CalculatorValue(value);
		resultErrorMessage = operand2.getErrorMessage();
		if (operand2ErrorMessage.length() > 0)
			return false;
		return true;
	}
	/**********
	 * This public setter sets the String explaining the current error in operand1.
	 * 
	 * @return
	 */
	public void setOperand1ErrorMessage(String m) {
		operand1ErrorMessage = m;
		return;
	}
	public void seterror1ErrorMessage(String m) {
		error1ErrorMessage = m;
		return;
	}
	/**********
	 * This public getter fetches the String explaining the current error in operand1, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return and error message or an empty String
	 */
	public String getOperand1ErrorMessage() {
		return operand1ErrorMessage;
	}
	public String geterror1ErrorMessage() {
		return error1ErrorMessage;
	}
	
	/**********
	 * This public setter sets the String explaining the current error into operand1.
	 * 
	 * @return
	 */
	public void setOperand2ErrorMessage(String m) {
		operand2ErrorMessage = m;
		return;
	}
	public void seterror2ErrorMessage(String m) {
		error2ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in operand2, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return and error message or an empty String
	 */
	public String getOperand2ErrorMessage() {
		return operand2ErrorMessage;
	}
	public String geterror2ErrorMessage() {
		return error2ErrorMessage;
	}
	/**********
	 * This public setter sets the String explaining the current error in the result.
	 * 
	 * @return
	 */
	public void setResultErrorMessage(String m) {
		resultErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in the result, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return and error message or an empty String
	 */
	public String getResultErrorMessage() {
		return resultErrorMessage;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand1. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getOperand1Defined() {
		return operand1Defined;
	}
	public boolean geterror1Defined()
	{
		return error1Defined;
	}
	public boolean geterror2Defined()
	{
		return error2Defined;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand2. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getOperand2Defined() {
		return operand2Defined;
	}

	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/**********
	 * This toString method invokes the toString method of the result type (CalculatorValue is this 
	 * case) to convert the value from its hidden internal representation into a String, which can be
	 * manipulated directly by the BusinessLogic and the UserInterface classes.
	 */
	public String toString() {
		return result.toString();
	}
	
	/**********
	 * This public toString method is used to display all the values of the BusinessLogic class in a
	 * textual representation for debugging purposes.
	 * 
	 * @return a String representation of the class
	 */
	public String debugToString() {
		String r = "\n******************\n*\n* Business Logic\n*\n******************\n";
		r += "operand1 = " + operand1.toString() + "\n";
		r += "     operand1ErrorMessage = " + operand1ErrorMessage+ "\n";
		r += "     operand1Defined = " + operand1Defined+ "\n";
		r += "operand2 = " + operand2.toString() + "\n";
		r += "     operand2ErrorMessage = " + operand2ErrorMessage+ "\n";
		r += "     operand2Defined = " + operand2Defined+ "\n";
		r += "result = " + result.toString() + "\n";
		r += "     resultErrorMessage = " + resultErrorMessage+ "\n";
		r += "*******************\n\n";
		return r;
	}
	
	/**********************************************************************************************

	Business Logic Operations (e.g. addition)
	
	**********************************************************************************************/
	
	/**********
	 * This public method computes the sum of the two operands using the CalculatorValue class method 
	 * for addition. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String addition(String unit1,String unit2) {
		result = new CalculatorValue(operand1);
		int unitCode1=Integer.parseInt(unit1)-1;
		int unitCode2=Integer.parseInt(unit2)-1;
		int resultUnitCode = 0;
		if(unitCode1 ==-1 && unitCode2 != -1)
		{
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
		if(unitCode2 ==-1 && unitCode1 != -1)
		{
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
		if (unitCode1 == -1 && unitCode2 == -1) 
		{
			resultUnitCode = 0;	
		}
		else
		{
			resultUnitCode = addSubTable[unitCode1][unitCode2];
			System.out.println(resultUnitCode+"\n");
		}
		
		// Check to see if these operands are compatible
		if (resultUnitCode < 0) {
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
		
		// Use the unit codes from the normalizeTable to determine the left and the right normalizing factor
		double normalize1 = normalizeTable[unitCode1][unitCode2];
		double normalize2 = normalizeTable[unitCode2][unitCode1];
		resultUnit.setText(unitTitle[resultUnitCode]);
		String r=result.add(operand2,normalize1,normalize2,resultUnitCode);
		resultErrorMessage = result.getErrorMessage();
		return r+","+unitTitle[resultUnitCode];
	}
	public static String getSignificant(double value) {
		MathContext mc = new MathContext(1, RoundingMode.DOWN);
		BigDecimal bigDecimal = new BigDecimal(value, mc);
		return bigDecimal.toPlainString();
		}
	/**********
	 * This public method computes the sum of the two error terms using the CalculatorValue class method 
	 * for addition. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the error terms are defined and valid. It replaces the left error term with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String additionerr()
	{
		result = new CalculatorValue(error1);
		String r=result.add(error2);
		resultErrorMessage = result.getErrorMessage();
		String w=getSignificant(Double.parseDouble(r));
		return w;
	}
	/**********
	 * This public method computes the difference of the two operands using the CalculatorValue class method 
	 * for subtraction. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String subtraction(String unit1,String unit2) {
		result = new CalculatorValue(operand1);
		int unitCode1 = Integer.parseInt(unit1)-1;
		int unitCode2 = Integer.parseInt(unit2)-1;
		int resultUnitCode = 0;
		if(unitCode1 ==-1 && unitCode2 != -1)
		{
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
		if(unitCode2 ==-1 && unitCode1 != -1)
		{
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
		if (unitCode1 == -1 && unitCode2 == -1) 
		{
			resultUnitCode = 0;	
		}
		else
		{
			resultUnitCode = addSubTable[unitCode1][unitCode2];
			
		}
		
		// Check to see if these operands are compatible
		if (resultUnitCode < 0) {
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
		
		// Use the unit codes from the normalizeTable to determine the left and the right normalizing factor
		double normalize1 = normalizeTable[unitCode1][unitCode2];
		double normalize2 = normalizeTable[unitCode2][unitCode1];
		
			
		// Display the resulting unit as text for the GUI
		resultUnit.setText(unitTitle[resultUnitCode]);
		
		String r=result.sub(operand2, normalize1, normalize2, resultUnitCode);
		resultErrorMessage = result.getErrorMessage();
		return r+","+unitTitle[resultUnitCode];
	}
	public String subtractionerr()
	{
		result=new CalculatorValue(error1);
		String r=result.sub(error2);
		resultErrorMessage=result.getErrorMessage();
		String w=getSignificant(Double.parseDouble(r));
//		System.out.println(w);
		return w;
	}
	/**********
	 * This public method computes the product of the two operands using the CalculatorValue class method 
	 * for multiplication. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String multiplication(String unit1, String unit2) {
		result = new CalculatorValue(operand1);
		if(unit1.equals("0") && unit2.equals("0")) {
			String r=result.mpy(error1,operand2,error2,1.0,1.0,1);
			return r;
		}
		// Extract the unit codes from each operand so we can compute the unit code for the result using the addSubTable
		int unitCode1 = Integer.parseInt(unit1)-1;
		int unitCode2 = Integer.parseInt(unit2)-1;

			// Compute the result unit code using the addSubTable and the unit codes from the two operands

			
			
			int resultUnitCode = 0;
			if(unitCode1 ==-1 && unitCode2 != -1)
			{
				resultUnit.setText("*** Error: These units are not compatible with this operation");
				return "null";
			}
			if(unitCode2 ==-1 && unitCode1 != -1)
			{
				resultUnit.setText("*** Error: These units are not compatible with this operation");
				return "null";
			}
			if (unitCode1 == -1 && unitCode2 == -1) 
			{
				resultUnitCode = 0;	
			}
			else
			{
				resultUnitCode = addSubTable[unitCode1][unitCode2];
				
			}
			
			// Check to see if these operands are compatible
			if (resultUnitCode < 0) 
			{
				resultUnit.setText("*** Error: These units are not compatible with this operation");
				return "null";
			}
			
			// Use the unit codes from the normalizeTable to determine the left and the right normalizing factor
			double normalize1 = normalizeTable[unitCode1][unitCode2];
			double normalize2 = normalizeTable[unitCode2][unitCode1];
			
			
			System.out.println(normalize1+"  is unit1");
			System.out.println(normalize2+"  is unit2");
			// Display the resulting unit as text for the GUI
			resultUnit.setText(mpyTitle[resultUnitCode]);
		
		String r=result.mpy(error1,operand2,error2,normalize1,normalize2,resultUnitCode);
		
		resultErrorMessage = result.getErrorMessage();
		return r+","+mpyTitle[resultUnitCode];
	}
	/**********
	 * This public method computes the product of the two error terms using the CalculatorValue class method 
	 * for multiplication. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the error terms are defined and valid. It replaces the left error term with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String multiplicationerr(String unit1,String unit2) {
		result = new CalculatorValue(operand1);
		if(unit1.equals("0") && unit2.equals("0")) {
			String r=result.mpyerr(error1,operand2,error2,1.0,1.0,1);
			return r;
		}
		// Extract the unit codes from each operand so we can compute the unit code for the result using the addSubTable
		int unitCode1 = Integer.parseInt(unit1)-1;
		int unitCode2 = Integer.parseInt(unit2)-1;
		
		int resultUnitCode = 0;
		if(unitCode1 ==-1 && unitCode2 != -1)
		{
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
		if(unitCode2 ==-1 && unitCode1 != -1)
		{
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "";
		}
		if (unitCode1 == -1 && unitCode2 == -1) 
		{
			resultUnitCode = 0;	
		}
		else
		{
			resultUnitCode = addSubTable[unitCode1][unitCode2];
			
		}
		
		// Check to see if these operands are compatible
		if (resultUnitCode < 0) 
		{
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
		
		// Use the unit codes from the normalizeTable to determine the left and the right normalizing factor
		double normalize1 = normalizeTable[unitCode1][unitCode2];
		double normalize2 = normalizeTable[unitCode2][unitCode1];
		
		
		
		// Display the resulting unit as text for the GUI
		resultUnit.setText(mpyTitle[resultUnitCode]);
		String r=result.mpyerr(error1,operand2,error2,normalize1, normalize2, resultUnitCode);
		resultErrorMessage = result.getErrorMessage();
		String w=getSignificant(Double.parseDouble(r));
//		System.out.println(w);
		return w+","+mpyTitle[resultUnitCode];
	}
	/**********
	 * This public method computes the division of the two operands using the CalculatorValue class method 
	 * for division. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String division(String unit1,String unit2) {
		UNumber temp= new UNumber(operand2.measuredValue);
		result = new CalculatorValue(operand1);
		if(unit1.equals("0") && unit2.equals("0")) {
			if(temp.getDouble()==0) {
				return "";
			}
			else{
				String r=result.div(error1,operand2,error2,1,1,1);
			
			return r;
			}
		}
		if(unit2.equals("0")) {
			String r=result.div(error1,operand2,error2,1,1,1);
			return r;
		}
	
		
		if(temp.getDouble()==0) {
			return "";
		}
		int unitCode1 = Integer.parseInt(unit1)-1;
		int unitCode2 = Integer.parseInt(unit2)-1;
		int resultUnitCode = 0;
		if(unitCode1 ==-1 && unitCode2 != -1)
		{
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
		else if (unitCode1 > 11) {
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
			
		else
		{
			if(unitCode2!=-1) resultUnitCode = addSubTable[unitCode1][unitCode2];
			else resultUnitCode = addSubTable[unitCode1][0];
		}
		double normalize1;
		double normalize2;
		if(unitCode2!=-1) {
			normalize1 = normalizeTable[unitCode1][unitCode2];
			normalize2 = normalizeTable[unitCode2][unitCode1];
		}
		else {
			normalize1 = normalizeTable[unitCode1][0];
			normalize2 = normalizeTable[0][unitCode1];
		}
		if(resultUnitCode == -1 && unitCode1 < 11)
		{
			resultUnit.setText(unitTitle[unitCode1] +"/"+unitTitle[unitCode2]);
			
		}
		else if(resultUnitCode == -1 && unitCode2 == -1) {
			resultUnit.setText(unitTitle[unitCode1]);
		}
		else resultUnit.setText("");
		String r=result.div(error1,operand2,error2,normalize1, normalize2, resultUnitCode);
		resultErrorMessage = result.getErrorMessage();
		if(resultUnitCode == -1 && unitCode1 < 11)
		{
			return r.toString()+","+unitTitle[unitCode1+1] +"/"+unitTitle[unitCode2+1];
		}
		return r.toString();
	}
	/**********
	 * This public method computes the division of the two error terms using the CalculatorValue class method 
	 * for division. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the error terms are defined and valid. It replaces the left error term with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String divisionerr(String unit1,String unit2) {
		result=new CalculatorValue(operand1);
		UNumber temp = new UNumber(operand2.measuredValue);
		
		if(temp.getDouble()==0) {
			return "";
		}
		if(unit1.equals("0") && unit2.equals("0")) {
			String r=result.diverr(error1,operand2,error2,1,1,1);
			return r;
		}
		
		int unitCode1 = Integer.parseInt(unit1)-1;
		int unitCode2 = Integer.parseInt(unit2)-1;
		
		int resultUnitCode = 0;
		if(unitCode1 ==-1 && unitCode2 != -1)
		{
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
		else if (unitCode1 > 11) {
			resultUnit.setText("*** Error: These units are not compatible with this operation");
			return "null";
		}
			
		else
		{
			if(unitCode2!=-1) resultUnitCode = addSubTable[unitCode1][unitCode2];
			else resultUnitCode = addSubTable[unitCode1][0];
		}
		
		double normalize1;
		double normalize2;
		if(unitCode2!=-1) {
			normalize1 = normalizeTable[unitCode1][unitCode2];
			normalize2 = normalizeTable[unitCode2][unitCode1];
		}
		else {
			normalize1 = normalizeTable[unitCode1][0];
			normalize2 = normalizeTable[0][unitCode1];
		}
		
		if(resultUnitCode == -1 && unitCode1 < 11)
		{
			resultUnit.setText(unitTitle[unitCode1] +"/"+unitTitle[unitCode2]);
			
		}
		else if(resultUnitCode == -1 && unitCode2 == -1) {
			resultUnit.setText(unitTitle[unitCode1]);
		}
		else resultUnit.setText("");
		
		String r=result.diverr(error1,operand2,error2,normalize1,normalize2,resultUnitCode);
		resultErrorMessage=result.getErrorMessage();
		String w=getSignificant(Double.parseDouble(r));
//		System.out.println(w);
		return w;
	}
	/**********
	 * This public method computes the square root of the first operand using the CalculatorValue class method 
	 * for square root. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. 
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String squareroot()
	{
		result= new CalculatorValue(operand1);
		if(operand1.measuredValue.isPositive())
		{
			String r=result.square();
			resultErrorMessage=result.getErrorMessage();
			return r;
		}
		else
	{
			result.square();
			resultErrorMessage=result.getErrorMessage();
			return "";
		}
	}
	public String squarerooterror()
	{
		result=new CalculatorValue(operand1);
		String r=result.squareerror(operand1, error1);
		resultErrorMessage=result.getErrorMessage();
		return r;
	}
	
}
