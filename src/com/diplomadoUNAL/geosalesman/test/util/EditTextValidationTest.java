package com.diplomadoUNAL.geosalesman.test.util;

import java.util.HashMap;
import java.util.Iterator;

import junit.framework.TestCase;

import android.annotation.SuppressLint;
import com.diplomadoUNAL.geosalesman.util.EditTextValidation;

@SuppressLint("UseSparseArrays")
public class EditTextValidationTest extends TestCase {

	public EditTextValidationTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAlphabeticValidation() {
		
		HashMap<String,Boolean> toValidate = new HashMap<String, Boolean>();
		toValidate.put("asl뻕 kjfad fiojo놽  adfadf 픂Easfadf",true);
		toValidate.put("adfkj뻦j뻢?lj뻢fdadfadf", true);
		toValidate.put("asl뻕1! kjfad fiojo눩e @ adfadf EEasfadf", false);
		toValidate.put("1뻢lj뻢fdadfadf", false);
		
		String errorMessage = "alphabetic validation error";
		HashMap<Integer, String> validationTest = new HashMap<Integer, String>();
		validationTest.put(EditTextValidation.ALPHABETHIC_VALIDATION,
						errorMessage);
		
		Iterator<String> toValidateIterator = toValidate.keySet().iterator();
		while(toValidateIterator.hasNext()){
			String stringToValidate= toValidateIterator.next();
			String result = EditTextValidation.editTextValidation(
							stringToValidate, validationTest);
			assertEquals("Error for " + errorMessage+": "+stringToValidate, (toValidate.get(stringToValidate)).booleanValue(),
							result == null ? true : false);
		}
	}

	public void testNumberValidation() {
		
		HashMap<String,Boolean> toValidate = new HashMap<String, Boolean>();
		toValidate.put("1",true);
		toValidate.put("0987656784345678", true);
		toValidate.put("813740989069861348'071", false);
		toValidate.put("870138974 341897431824", false);
		
		String errorMessage = "number validation error";
		HashMap<Integer, String> validationTest = new HashMap<Integer, String>();
		validationTest.put(EditTextValidation.NUMBER_VALIDATION,
						errorMessage);
		
		Iterator<String> toValidateIterator = toValidate.keySet().iterator();
		while(toValidateIterator.hasNext()){
			String stringToValidate= toValidateIterator.next();
			String result = EditTextValidation.editTextValidation(
							stringToValidate, validationTest);
			assertEquals("Error for " + errorMessage+": "+stringToValidate, (toValidate.get(stringToValidate)).booleanValue(),
							result == null ? true : false);
		}
	}

	public void testNumberWithSpacesValidation() {
		
		HashMap<String,Boolean> toValidate = new HashMap<String, Boolean>();
		toValidate.put("1",true);
		toValidate.put("0987 65678 4345678", true);
		toValidate.put(" 0987 65678 4345678", true);	
		toValidate.put("a870138974 341897 431824", false);
		toValidate.put("870138974 341897e431824a", false);
		toValidate.put("81374098 9069861348'071", false);
		
		String errorMessage = "number with spaces validation error";
		HashMap<Integer, String> validationTest = new HashMap<Integer, String>();
		validationTest.put(EditTextValidation.NUMBER_WITH_SPACES_VALIDATION,
						errorMessage);
		
		Iterator<String> toValidateIterator = toValidate.keySet().iterator();
		while(toValidateIterator.hasNext()){
			String stringToValidate= toValidateIterator.next();
			String result = EditTextValidation.editTextValidation(
							stringToValidate, validationTest);
			assertEquals("Error for " + errorMessage+": "+stringToValidate, (toValidate.get(stringToValidate)).booleanValue(),
							result == null ? true : false);
		}
	}

	public void testNoPipeValidation() {
		
		HashMap<String,Boolean> toValidate = new HashMap<String, Boolean>();
		toValidate.put("1",true);
		toValidate.put("0987 65678 4345678", true);
		toValidate.put(" 0987 65678 4345678", true);	
		toValidate.put("<cxj좋dfhalduhFGkhldauf432524", true);
		toValidate.put("870138974 341897e431824a", true);
		toValidate.put("81374098 9069861348'071", true);
		toValidate.put("|",false);
		toValidate.put("0987 |65678 4345678", false);
		toValidate.put(" |0987 65678 4345678", false);	
		toValidate.put("<cxj좋dfha|lduhFGkhldauf432524", false);
		toValidate.put("870138974 34189|7e431824a", false);
		toValidate.put("81374098 |9069861348'071", false);
		
		String errorMessage = "number with spaces validation error";
		HashMap<Integer, String> validationTest = new HashMap<Integer, String>();
		validationTest.put(EditTextValidation.NO_PIPE_CHAR_VALIDATION,
						errorMessage);
		
		Iterator<String> toValidateIterator = toValidate.keySet().iterator();
		while(toValidateIterator.hasNext()){
			String stringToValidate= toValidateIterator.next();
			String result = EditTextValidation.editTextValidation(
							stringToValidate, validationTest);
			assertEquals("Error for " + errorMessage+": "+stringToValidate, (toValidate.get(stringToValidate)).booleanValue(),
							result == null ? true : false);
		}
	}

	public void testCharacterValidation() {
		
		HashMap<String,Boolean> toValidate = new HashMap<String, Boolean>();
		toValidate.put("1",true);
		toValidate.put("0987 65678 4345678", true);
		toValidate.put(" 0987 65678X4345678", true);	
		toValidate.put("<cxj좋dfhalduhFGkhldauf432524", true);
		toValidate.put("870138974 341897e431824a", true);
		toValidate.put("0987 |*65678 4345678", false);
		toValidate.put("81374098 9069861348'071", false);
		toValidate.put("|",true);
		toValidate.put(" |0987 6+5678 4345678", false);	
		toValidate.put("<cxj좋dfha|lduhFGkh'ldauf432524", false);
		toValidate.put("*870138974 34189|7e431824a", false);
		toValidate.put("81374098 |9069861348'071+", false);
		
		String errorMessage = "character validation error";
		HashMap<Integer, String> validationTest = new HashMap<Integer, String>();
		validationTest.put(EditTextValidation.CHARACTER_VALIDATION,
						errorMessage);
		
		Iterator<String> toValidateIterator = toValidate.keySet().iterator();
		while(toValidateIterator.hasNext()){
			String stringToValidate= toValidateIterator.next();
			String result = EditTextValidation.editTextValidation(
							stringToValidate, validationTest);
			assertEquals("Error for " + errorMessage+": "+stringToValidate, (toValidate.get(stringToValidate)).booleanValue(),
							result == null ? true : false);
		}
	}
}
