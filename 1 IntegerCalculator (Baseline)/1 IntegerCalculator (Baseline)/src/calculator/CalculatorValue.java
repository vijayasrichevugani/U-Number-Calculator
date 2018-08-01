package calculator;

import java.util.Scanner;


/**
 * <p> Title: CalculatorValue Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Perni Pavani
 * 	Based on the work of Lynn Robert Carter
 * 
 * @version 4.00	2017-10-18 Long integer implementation of the CalculatorValue class 
 * 
 */
public class CalculatorValue {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major values that define a calculator value
	UNumber measuredValue;
	UNumber error;
	int x = 0;
	boolean p=true;
	String errorMessage = "";
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
		measuredValue=new UNumber(0);
		errorMessage="";
	}

	/*****
	 * This constructor creates a calculator value based on a long integer. For future calculators, it
	 * is best to avoid using this constructor.
	 */
	public CalculatorValue(double v) {
		measuredValue = new UNumber(v);
		errorMessage="";
	}

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = new UNumber(v.measuredValue);
		errorMessage = v.errorMessage;
	}
	
	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the 
	 * routine returns the value with the error message value set to empty or the string 
	 * of an error message.
	 */
	public CalculatorValue(String s) {
		String q = MeasuredValueRecognizer.checkMeasureValue(s);
		measuredValue = new UNumber(0);
		if (s.length() == 0) {								// If there is nothing there,
			errorMessage = "***Error*** Input is empty";		// signal an error	
			return;												
		}
		// If the first character is a plus sign, ignore it.
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		if (s.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-'){					// See if the first character is '-'
			start++;											// if so, skip it
			negative = true;									// but do not ignore it
		}
		
		// See if the user-entered string can be converted into an integer value
		Scanner tempScanner = new Scanner(s.substring(start));// Create scanner for the digits
		if(q.length()!=0)
		{
			errorMessage=MeasuredValueRecognizer.measuredValueErrorMessage;
			tempScanner.close();
			return;
		}
		
		if (!tempScanner.hasNextDouble()) {					// See if the next token is a valid
			errorMessage = "***Error*** Invalid value"; 		// integer value.  If not, signal there
			tempScanner.close();								// return a zero
			return;												
		}
		
		// Convert the user-entered string to a integer value and see if something else is following it
		measuredValue = new UNumber(tempScanner.nextDouble());				// Convert the value and check to see
		if (tempScanner.hasNext()) {							// that there is nothing else is 
			errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
			tempScanner.close();								// is an error.  Therefore we must
			measuredValue = new UNumber(0);								// return a zero value.
			return;													
		}
		tempScanner.close();
		errorMessage = "";
		if (negative)										// Return the proper value based
			measuredValue .mpy(new UNumber(-1));					// on the state of the flag that
	}
	public CalculatorValue(String s,int w) {
		String q = ErrorTermRecognizer.checkErrorTerm(s);
		measuredValue = new UNumber(0);
		if (s.length() == 0) {								// If there is nothing there,
			errorMessage = "***Error*** Input is empty";		// signal an error	
			return;												
		}
		// If the first character is a plus sign, ignore it.
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		if (s.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-'){					// See if the first character is '-'
			start++;											// if so, skip it
			negative = true;									// but do not ignore it
		}
		
		// See if the user-entered string can be converted into an integer value
		Scanner tempScanner = new Scanner(s.substring(start));// Create scanner for the digits
		if(q.length()!=0)
		{
			errorMessage=ErrorTermRecognizer.errorTermErrorMessage;
			tempScanner.close();
			return;
		}
		
		if (!tempScanner.hasNextDouble()) {					// See if the next token is a valid
			errorMessage = "***Error*** Invalid value"; 		// integer value.  If not, signal there
			tempScanner.close();								// return a zero
			return;												
		}
		
		// Convert the user-entered string to a integer value and see if something else is following it
		measuredValue = new UNumber(tempScanner.nextDouble());				// Convert the value and check to see
		if (tempScanner.hasNext()) {							// that there is nothing else is 
			errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
			tempScanner.close();								// is an error.  Therefore we must
			measuredValue = new UNumber(0);								// return a zero value.
			return;													
		}
		tempScanner.close();
		errorMessage = "";
		if (negative)										// Return the proper value based
			measuredValue .mpy(new UNumber(-1));					// on the state of the flag that
	}
	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 */
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/*****
	 * Set the current value of a calculator value to a specific long integer
	 */
	public void setValue(double v){
		measuredValue = new UNumber(v);
		errorMessage="";
	}
	
	/*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}
	
	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
	}
	
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/*****
	 * This is the default toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String toString() {
		if(x == 0)
		{
			return measuredValue + "";
		}
		else
		{
			return "";
		}
	}
	
	/*****
	 * This is the debug toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String debugToString() {
		return "measuredValue = " + measuredValue + "\nerrorMessage = " + errorMessage + "\n";
	}

	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	

	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is addition and we do not yet support units, we don't recognize any errors.
	 * @return 
	 */
	public String add(CalculatorValue v,double nm1,double nm2,int uc) {

		UNumber temp1=new UNumber(measuredValue); 	//Operand1 is assigned to temp1
		temp1.mpy(new UNumber(nm1));				//multiply temp1 with normalizedvalue1 i.e., nm1
		UNumber temp2=new UNumber(v.measuredValue); //Operand2 is assigned to temp2
		temp2.mpy(new UNumber(nm2));				//multiply temp2 with normalizedvalue2 i.e., nm2
		temp1.add(temp2);							//temp1 and tepm2 is added and result is stored in temp1 
		return temp1.toString();					// temp1 is returned as a string 
	}

	public String add(CalculatorValue v) {
		UNumber temp1=new UNumber(measuredValue);	//Operand1 is assigned to temp1
		UNumber temp2=new UNumber(v.measuredValue); //Operand2 is assigned to temp2
		temp1.add(temp2);							//temp1 and tepm2 is added and result is stored in temp1 
		return temp1.toString();					// temp1 is returned as a string 
	}

	public String sub(CalculatorValue v) {
		UNumber temp1=new UNumber(measuredValue);	//Operand1 is assigned to temp1
		UNumber temp2=new UNumber(v.measuredValue);	//Operand2 is assigned to temp2
		temp1.sub(temp2);							//temp1 and tepm2 is subtracted and result is stored in temp1 
		return temp1.toString();					// temp1 is returned as a string
		
	}
	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is subtraction and we do not yet support units, we don't recognize any errors.
	 * @return 
	 */
	public String sub(CalculatorValue v,double nm1,double nm2,int uc) {
		UNumber temp1=new UNumber(measuredValue);	//Operand1 is assigned to temp1
		temp1.mpy(new UNumber(nm1));				//multiply temp1 with normalizedvalue1 i.e., nm1
		UNumber temp2=new UNumber(v.measuredValue);	//Operand2 is assigned to temp2
		temp2.mpy(new UNumber(nm2));				//multiply temp2 with normalizedvalue2 i.e., nm2
		temp1.sub(temp2);							//temp1 and tepm2 is subtracted and result is stored in temp1
		return temp1.toString();					// temp1 is returned as a string
		
	}
	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is multiplication and we do not yet support units, we don't recognize any errors.
	 * @return 
	 */
	public String mpy(CalculatorValue error1,CalculatorValue v,CalculatorValue error2,double nm1,double nm2,int uc) {
		UNumber temp1=new UNumber(measuredValue);	//Operand1 is assigned to temp1
		temp1.mpy(new UNumber(nm1));				//multiply temp1 with normalizedvalue1 i.e., nm1
		UNumber temp2=new UNumber(v.measuredValue);	//Operand2 is assigned to temp2
		temp2.mpy(new UNumber(nm2));				//multiply temp1 with normalizedvalue2 i.e., nm2
		temp1.mpy(temp2);							//temp1 and tepm2 is subtracted and result is stored in temp1
		return temp1.toString();					// temp1 is returned as a string
	}
	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is multiplication of error terms of both operands and we do not yet support units, we don't recognize any errors.
	 * @return 
	 */
	public String mpyerr(CalculatorValue error1,CalculatorValue v,CalculatorValue error2,double nm1,double nm2,int uc) {
		UNumber temp1=new UNumber(error1.measuredValue);//errOperand1 is assigned to temp1	
		UNumber temp2=new UNumber(v.measuredValue);		//Operand2 is assigned to temp2
		UNumber temp3=new UNumber(error2.measuredValue);//errOperand2 is assigned to temp3
		UNumber temp01=new UNumber(measuredValue);		//Operand1 is assigned to temp01
		temp01.mpy(new UNumber(nm1));					//multiply temp01 with normalizedvalue1 i.e., nm1
		temp2.mpy(new UNumber(nm2));					//multiply temp2 with normalizedvalue2 i.e., nm2
		temp1.mpy(new UNumber(nm1));					//multiply temp1 with normalizedvalue1 i.e., nm1
		temp2.mpy(new UNumber(nm2));					//multiply temp2 with normalizedvalue2 i.e., nm2
		temp01.abs();									//absolute value of temp01 is calculated and stored in temp01
		temp1.div(temp01);								//temp1 is divided with temp01
		temp2.abs();									//temp2 absolute value is calculated
		temp3.div(temp2);								//temp3 is divided by temp2
		temp01.mpy(temp2);								//temp01 and temp2 is multiplied
		temp1.add(temp3);								//temp1 is added with temp3
		temp1.mpy(temp01);								//temp1 is multiplied with temp01
		errorMessage="";								//errmessage is set to empty
		return temp1.toString();						//temp1 is returned
	}
	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is division and we do not yet support units, we don't recognize any errors.
	 * @return 
	 */
	public String div(CalculatorValue error1,CalculatorValue v,CalculatorValue error2,double nm1, double nm2, int uc) {
		
		//this condition to verify the Division By Zero Error
		if(v.measuredValue.isZero())
		{
			x=1;
			measuredValue=new UNumber(0);
			setErrorMessage("Error: Division not possible");
		}
		else
		{
			UNumber temp1=new UNumber(measuredValue);	//Operand1 is assigned to temp1
			temp1.mpy(new UNumber(nm1));				//multiply temp1 with normalizedvalue1 i.e., nm1
			UNumber temp2= new UNumber(v.measuredValue);//Operand2 is assigned to temp2
			temp2.mpy(new UNumber(nm2));				//multiply temp2 with normalizedvalue2 i.e., nm2
			temp1.div(temp2);							//temp1 and tepm2 is divided and result is stored in temp1
			errorMessage="";							//errMessage is set to empty
			return measuredValue.toString();			//measuredvalue is returned
		}
		return "";
		}
	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is division of error terms of both operands and we do not yet support units, we don't recognize any errors.
	 * @return 
	 */
	public String diverr(CalculatorValue error1,CalculatorValue v,CalculatorValue error2,double nm1, double nm2,int uc) {
		
		//this condition to verify the Division By Zero Error
		if(v.measuredValue.isZero())
		{
			x = 1;
			errorMessage = "Division not possible";
		}
		else
		{
			UNumber temp01=new UNumber(measuredValue);	//Operand1 is assigned to temp01
			temp01.mpy(new UNumber(nm1));				//multiply temp01 with normalizedvalue1 i.e., nm1
			UNumber temp1= new UNumber(error1.measuredValue);//errOperand1 is assigned to temp1
			temp1.mpy(new UNumber(nm1));				//multiply temp1 with normalizedvalue1 i.e., nm1
			UNumber temp2=new UNumber(v.measuredValue);	//Operand2 is assigned to temp2
			temp2.mpy(new UNumber(nm2));				//multiply temp2 with normalizedvalue2 i.e., nm2
			UNumber temp3=new UNumber(error2.measuredValue);//errOperand2 is assigned to temp3
			temp2.mpy(new UNumber(nm2));				//multiply temp2 with normalizedvalue2 i.e., nm2
			temp01.abs();								//absolute value of temp01 is calculated and stored in temp01
			temp1.div(temp01);							//temp1 is divided with temp01
			temp2.abs();								//temp2 absolute value is calculated
			temp3.div(temp2);							//temp3 is divided by temp2	
			temp01.div(temp2);							//temp01 and temp2 are divided
			temp1.add(temp3);							//temp1 is added with temp3
			temp01.abs();								//absolute value of temp01 is calculated and stored in temp01
			temp1.mpy(temp01);							//temp1 is multiplied with temp01
			errorMessage="";							//errmessage is set to empty
			return temp1.toString();					//temp1 is returned	
		}
		return "";
	}
	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is square root and we do not yet support units, we don't recognize any errors.
	 * @return 
	 */
	public String square()
	{
		UNumber root=UNumber.squareRoot(measuredValue);	//Operand1 is assigned to root
		errorMessage="";								//errmessage is set to empty
		return root.toString();							//root is returned
	}
	public String squareerror(CalculatorValue operand1, CalculatorValue error1)
	{
		UNumber temp1=new UNumber(operand1.measuredValue);//Operand1 is assigned to temp1
		UNumber temp2=new UNumber(error1.measuredValue); //errOperand1 is assigned to temp2
		temp2.div(temp1);								//temp2 is divided by temp1
		UNumber ans=UNumber.squareRoot(temp1);			//Sqrt root of temp1 is calculated
		temp2.mpy(new UNumber(0.5));					//temp2 is multiplied by 0.5					
		temp2.mpy(ans);									//temp2 is multiplied by ans
		return temp2.toString();						//temp2 is returned
	}
	}

