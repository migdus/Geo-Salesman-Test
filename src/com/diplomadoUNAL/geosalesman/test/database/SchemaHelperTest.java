package com.diplomadoUNAL.geosalesman.test.database;

import java.util.ArrayList;
import java.util.HashMap;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.test.AndroidTestCase;

import com.diplomadoUNAL.geosalesman.database.QuestionTable;
import com.diplomadoUNAL.geosalesman.database.SchemaHelper;

public class SchemaHelperTest extends AndroidTestCase {

	SchemaHelper schemaHelper;

	public SchemaHelperTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		schemaHelper = new SchemaHelper(getContext());
		schemaHelper.onUpgrade(schemaHelper.getWritableDatabase(), 1, 1);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		schemaHelper = new SchemaHelper(getContext());
		schemaHelper.onUpgrade(schemaHelper.getWritableDatabase(), 1, 1);
	}

	public void testOnCreateSQLiteDatabase() {
		SQLiteException e = null;
		try {
			SQLiteDatabase dbe = SQLiteDatabase.openDatabase(
							schemaHelper.getDatabaseName(), null, 0);
			dbe.close();
		} catch (SQLiteException f) {
			e = f;
		}
		assertNull(e);
	}

	public void testOnUpgradeSQLiteDatabaseIntInt() {
		fail("Not yet implemented"); // TODO
	}

	public void testAddClient() {

		ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> temp = new HashMap<String, String>();

		temp.put("clientName", "The Simpsons");
		temp.put("phoneNumber", "1234567890");
		temp.put("address", "742 Evergreen Terrace");
		temp.put("contactName", "Simpson, Homer");
		temp.put("isThisExampleOK", Boolean.TRUE.toString());
		elements.add(temp);

		temp = new HashMap<String, String>();
		temp.put("clientName", "The Addams Family");
		temp.put("phoneNumber", "000");
		temp.put("address", "0001 Cemetery Lane");
		temp.put("contactName", "Gomez Addams");
		temp.put("isThisExampleOK", Boolean.TRUE.toString());
		elements.add(temp);

		temp = new HashMap<String, String>();
		temp.put("clientName", "The Jetsons");
		temp.put("phoneNumber", "VENUS-1234");
		temp.put("address", "Sky Pad apartments, Orbit City");
		temp.put("contactName", "George Jetson");
		temp.put("isThisExampleOK", Boolean.FALSE.toString());
		elements.add(temp);

		for (int i = 0; i < elements.size(); i++) {
			temp = elements.get(i);

			if (!temp.containsKey("isThisExampleOK"))
				fail("The key: \"isThisExampleOK\" was not found when inserting the following values:"
								+ temp.get("clientName")
								+ temp.get("phoneNumber")
								+ temp.get("address")
								+ temp.get("contactName") + " to the database");
			int result = -1;
			try {
				result = (int) schemaHelper.addClient(temp.get("clientName"),
								Integer.parseInt(temp.get("phoneNumber")),
								temp.get("address"), temp.get("contactName"));
			} catch (NumberFormatException e) {
				if (!Boolean.parseBoolean(temp.get("isThisExampleOK")))
					assertFalse(false);
			}
			String message = "Error when inserting: " + temp.get("clientName")
							+ temp.get("phoneNumber") + temp.get("address")
							+ temp.get("contactName") + " to the database";

			boolean flagSomeInserted = result > -1 ? true : false;

			if (Boolean.parseBoolean(temp.get("isThisExampleOK")))
				assertTrue(message, flagSomeInserted);
			else if (!Boolean.parseBoolean(temp.get("isThisExampleOK")))
				assertFalse(message, flagSomeInserted);
		}
	}

	public void testAddQuestion() {
		ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> temp = new HashMap<String, String>();

		temp.put("questionTitle", "Número de empleados");
		temp.put("question", "¿Cuántos empleados tiene la compañía?");
		temp.put("questionDescription",
						"Determinar el número de empleados de la compañía.");
		temp.put("questionType", "Open");
		temp.put("answerOptions", "");
		temp.put("isThisExampleOK", Boolean.TRUE.toString());
		elements.add(temp);

		temp = new HashMap<String, String>();
		temp.put("questionTitle", "Opiniones del servicio");
		temp.put("question", "¿Qué opina sobre nuestro servicio?");
		temp.put("questionDescription",
						"Describir la opinión del servicio al cliente.");
		temp.put("questionType", "Open");
		temp.put("answerOptions", "");
		temp.put("isThisExampleOK", Boolean.TRUE.toString());
		elements.add(temp);

		temp = new HashMap<String, String>();
		temp.put("questionTitle", "Apreciación del servicio");
		temp.put("question", "¿Le gustó el servicio?");
		temp.put("questionDescription", "Esta es una descripción más");
		temp.put("questionType", "Yes/No");
		temp.put("answerOptions", "Yes | No");
		temp.put("isThisExampleOK", Boolean.TRUE.toString());
		elements.add(temp);

		temp = new HashMap<String, String>();

		for (int i = 0; i < elements.size(); i++) {
			temp = elements.get(i);

			if (!temp.containsKey("isThisExampleOK"))
				fail("The key: \"isThisExampleOK\" was not found when inserting the following values:"
								+ temp.get("clientName")
								+ temp.get("phoneNumber")
								+ temp.get("address")
								+ temp.get("contactName") + " to the database");
			int result = -1;

			result = (int) schemaHelper
							.addQuestion(temp.get("questionTitle"),
											temp.get("question"),
											temp.get("questionDescription"),
											temp.get("questionType"),
											temp.get("answerOptions"));

			String message = "Error when inserting: "
							+ temp.get("questionTitle") + temp.get("question")
							+ temp.get("questionDescription")
							+ temp.get("questionType")
							+ temp.get("answerOptions") + " to the database";

			boolean flagSomeInserted = result > -1 ? true : false;

			if (Boolean.parseBoolean(temp.get("isThisExampleOK")))
				assertTrue(message, flagSomeInserted);
			else if (!Boolean.parseBoolean(temp.get("isThisExampleOK")))
				assertFalse(message, flagSomeInserted);
		}

	}

	public void testGetQuestion() {
		fail("Not yet implemented"); // TODO
	}

	public void testUpdateQuestion() {
		// add a question
		HashMap<String, String> temp = new HashMap<String, String>();

		temp.put("questionTitle", "Test Update");
		temp.put("question", "¿Actualizará este método?");
		temp.put("questionDescription",
						"Determinar si el método actualiza o no.");
		temp.put("questionType", "Open");
		temp.put("answerOptions", "");
		int id = (int) schemaHelper.addQuestion(temp.get("questionTitle"),
						temp.get("question"), temp.get("questionDescription"),
						temp.get("questionType"), temp.get("answerOptions"));
		assertTrue(id > -1);

		// update fields
		int rowsAffected = 0;
		// Test each one of the fields
		String updatedField = "Test Update ACTUALIZADO";
		// 1
		rowsAffected = schemaHelper.updateQuestion(Integer.toString(id),
						updatedField, "¿Actualizará este método?",
						"¿Actualizará este método?", "Open", "");
		assertTrue(rowsAffected > 0);
		// Check if it is ok
		temp = schemaHelper.getQuestion(id);
		assertTrue(temp.get(QuestionTable.QUESTION_TITLE).equals(updatedField) ? true
						: false);
		// 2
		rowsAffected = schemaHelper.updateQuestion(Integer.toString(id),
						"Test Update", updatedField,
						"¿Actualizará este método?", "Open", "");
		assertTrue(rowsAffected > 0);
		// Check if it is ok
		temp = schemaHelper.getQuestion(id);
		assertTrue(temp.get(QuestionTable.QUESTION_DESCRIPTION).equals(
						updatedField) ? true : false);
		// 3
		rowsAffected = schemaHelper.updateQuestion(Integer.toString(id),
						"Test Update", "¿Actualizará este método?",
						updatedField, "Open", "");
		assertTrue(rowsAffected > 0);
		// Check if it is ok
		temp = schemaHelper.getQuestion(id);
		assertTrue(temp.get(QuestionTable.QUESTION).equals(updatedField) ? true
						: false);
		// 4
		rowsAffected = schemaHelper.updateQuestion(Integer.toString(id),
						"Test Update", "¿Actualizará este método?",
						"¿Actualizará este método?", updatedField, "");
		assertTrue(rowsAffected > 0);
		// Check if it is ok
		temp = schemaHelper.getQuestion(id);
		assertTrue(temp.get(QuestionTable.QUESTION_TYPE).equals(updatedField) ? true
						: false);

		// 5
		rowsAffected = schemaHelper.updateQuestion(Integer.toString(id),
						"Test Update", "¿Actualizará este método?",
						"¿Actualizará este método?", "Open", updatedField);
		assertTrue(rowsAffected > 0);
		// Check if it is ok
		temp = schemaHelper.getQuestion(id);
		assertTrue(temp.get(QuestionTable.ANSWER_OPTIONS).equals(updatedField) ? true
						: false);
	}

	public void testAddReportTemplate() {
		fail("Not yet implemented"); // TODO
	}

	public void testAddReport() {
		fail("Not yet implemented"); // TODO
	}

	public void testGetQuestionsTitlesAndDescriptions() {
		fail("Not yet implemented"); // TODO
	}

}
