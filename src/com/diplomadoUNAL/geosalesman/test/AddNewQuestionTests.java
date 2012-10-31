package com.diplomadoUNAL.geosalesman.test;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.test.ActivityUnitTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

import com.diplomadoUNAL.geosalesman.AddNewQuestion;
import com.diplomadoUNAL.geosalesman.R;
import com.diplomadoUNAL.geosalesman.database.SchemaHelper;

public class AddNewQuestionTests extends ActivityUnitTestCase<AddNewQuestion> {

	public AddNewQuestionTests(Class<AddNewQuestion> name) {
		super(name);
	}

	public AddNewQuestionTests() {
		super(AddNewQuestion.class);
	}

	EditText editTextQuestionTitle, editTextQuestionDescription,
					editTextQuestion, editTextMaximumValue,
					editTextMinimumValue;
	Button okButton, cancelButton;
	RenamingDelegatingContext renamingDelegatingContext;

	protected void setUp() throws Exception {
		super.setUp();

		// Setup and start delegating Context
		renamingDelegatingContext = new RenamingDelegatingContext(
						getInstrumentation().getTargetContext(), "TEST.");

		renamingDelegatingContext.makeExistingFilesAndDbsAccessible();

		// Clear sharedPreferences
		Editor sharedPreferencesEditor = renamingDelegatingContext
						.getSharedPreferences(AddNewQuestion.PREFS_NAME, 0)
						.edit();
		sharedPreferencesEditor.clear();
		sharedPreferencesEditor.commit();

		// Clear database
		SchemaHelper schemaHelper = new SchemaHelper(renamingDelegatingContext);
		schemaHelper.onUpgrade(schemaHelper.getWritableDatabase(), 1, 1);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public AddNewQuestion setActivityTestContext() {

		setActivityContext(renamingDelegatingContext);
		startActivity(new Intent(), null, null);
		final AddNewQuestion addNewQuestionActivity = getActivity();

		// Prepare Activity objects
		editTextQuestionTitle = (EditText) addNewQuestionActivity
						.findViewById(R.id.editText_question_title);

		editTextQuestionDescription = (EditText) addNewQuestionActivity
						.findViewById(R.id.editText_question_description);

		editTextQuestion = (EditText) addNewQuestionActivity
						.findViewById(R.id.editText_question);

		/*
		 * Cannot be tested because they are in a dialog editTextMaximumValue = (EditText) addNewQuestionActivity
		 * .findViewById(R.id.editText_maximum_value);
		 * 
		 * editTextMinimumValue = (EditText) addNewQuestionActivity .findViewById(R.id.editText_minimum_value);
		 */
		okButton = (Button) addNewQuestionActivity
						.findViewById(R.id.button_add_new_question_ok);

		cancelButton = (Button) addNewQuestionActivity
						.findViewById(R.id.button_add_new_question_cancel);
		return addNewQuestionActivity;
	}

	@SmallTest
	public void testViewsCreated() {
		AddNewQuestion addNewQuestionActivity = setActivityTestContext();

		assertNotNull(addNewQuestionActivity);
		assertNotNull(editTextQuestionTitle);
		assertNotNull(editTextQuestionDescription);
		assertNotNull(editTextMaximumValue);
		assertNotNull(editTextMinimumValue);
		assertNotNull(okButton);
		assertNotNull(cancelButton);

	}

	public final void testFieldsShouldStartEmpty() {
		setActivityTestContext();
		assertEquals("", editTextQuestionTitle.getText().toString());
		assertEquals("", editTextQuestionDescription.getText().toString());
		assertEquals("", editTextMaximumValue.getText().toString());
		assertEquals("", editTextMinimumValue.getText().toString());
	}

	@MediumTest
	public void testOnCreateBundle() {
		AddNewQuestionTests.fail("Not yet implemented"); // TODO
	}

}
