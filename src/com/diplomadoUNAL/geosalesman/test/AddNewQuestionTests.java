package com.diplomadoUNAL.geosalesman.test;

import android.provider.SyncStateContract.Helpers;
import android.test.ActivityInstrumentationTestCase2;
import android.test.RenamingDelegatingContext;
import android.widget.Button;
import android.widget.EditText;

import com.diplomadoUNAL.geosalesman.AddNewQuestion;
import com.diplomadoUNAL.geosalesman.R;
import com.diplomadoUNAL.geosalesman.database.SchemaHelper;
import com.jayway.android.robotium.solo.Solo;

public class AddNewQuestionTests extends
				ActivityInstrumentationTestCase2<AddNewQuestion> {

	public AddNewQuestionTests(Class<AddNewQuestion> name) {
		super(name);
	}

	public AddNewQuestionTests() {
		super(AddNewQuestion.class);
	}

	private Solo solo;

	EditText editTextQuestionTitle, editTextQuestionDescription,
					editTextQuestion, editTextMaximumValue,
					editTextMinimumValue;
	Button okButton, cancelButton;
	RenamingDelegatingContext renamingDelegatingContext;

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());		
	}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();
	}

	public AddNewQuestion setActivityTestContext() {

		final AddNewQuestion addNewQuestionActivity = getActivity();

		// Prepare Activity objects
		editTextQuestionTitle = (EditText) addNewQuestionActivity
						.findViewById(R.id.activity_add_new_question_editText_question_title);

		editTextQuestionDescription = (EditText) addNewQuestionActivity
						.findViewById(R.id.activity_add_new_question_editText_question_description);

		editTextQuestion = (EditText) addNewQuestionActivity
						.findViewById(R.id.activity_add_new_question_editText_question);

		editTextMaximumValue = (EditText) addNewQuestionActivity
						.findViewById(R.id.activity_add_new_question_editText_maximum_value);

		editTextMinimumValue = (EditText) addNewQuestionActivity
						.findViewById(R.id.activity_add_new_question_editText_minimum_value);

		okButton = (Button) addNewQuestionActivity
						.findViewById(R.id.activity_add_new_question_button_add_new_question_ok);

		cancelButton = (Button) addNewQuestionActivity
						.findViewById(R.id.activity_add_new_question_button_add_new_question_cancel);
		return addNewQuestionActivity;
	}

	public void testHappyPathAddScaleQuestion() {
		//Some weird chars
		String chars = "";
		
		// Type question title
		solo.clickOnEditText(0);
		solo.enterText((EditText) getActivity().findViewById(
						R.id.activity_add_new_question_editText_question_title),
						"Question title scale "+chars);
		// Type question description
		solo.clickOnEditText(1);
		solo.enterText((EditText) getActivity().findViewById(
						R.id.activity_add_new_question_editText_question_description),
						"Question description scale"+chars);
		// Type the question
		solo.clickOnEditText(2);
		solo.enterText((EditText) getActivity().findViewById(
						R.id.activity_add_new_question_editText_question),
						"Question scale"+chars);

		// Click the spinner and then the "Scale" question type
		solo.pressSpinnerItem(0, 4);

		solo.waitForText(getActivity().getResources().getString(R.string.activity_add_new_question_scale_selection_dialog_message));
		// Type minimum and maximum ranges
		solo.clickOnEditText(0);
		solo.searchText(getActivity().getResources().getString(R.string.activity_add_new_question_maximum_value_hint));
		solo.clickOnView(solo.getCurrentEditTexts().get(0));
		solo.enterText(0, "34");
		solo.clickOnView(solo.getCurrentEditTexts().get(0));
		solo.enterText(1, "55");
	
		// Click ok to close the dialog
		solo.clickOnButton(getActivity().getResources().getString(R.string.OK));
		
		// Click ok to get an ok message
		solo.clickOnButton(getActivity().getResources().getString(R.string.OK));
		//Wait for the ok toast message
		boolean flagOKDatabase=solo.waitForText(getActivity().getResources().getString(R.string.database_success_storing_data),1,120);
		assertEquals("Something wrong happened with the database", true, flagOKDatabase);
	}
/*
	@MediumTest
	public void testOnCreateBundle() {
		AddNewQuestionTests.fail("Not yet implemented"); // TODO
	}*/

}
