package com.diplomadoUNAL.geosalesman.test;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.diplomadoUNAL.geosalesman.AddNewQuestion;
import com.diplomadoUNAL.geosalesman.R;
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
	Intent intent;

	protected void setUp() throws Exception {
		super.setUp();
		intent = new Intent();
		intent.putExtra(AddNewQuestion.ADD_NEW_QUESTION_ACTIVITY_MODE,
						AddNewQuestion.ACTIVITY_MODE_ADD_NEW);
		setActivityIntent(intent);
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();
	}

	public void testAddScaleQuestion() {
		ArrayList<HashMap<String, String>> testData = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("question title", "Cantidad solicitada");
		hashMap.put("question description",
						"Determinar el nœmero de productos del pedido");
		hashMap.put("question", "ÀCu‡ntos productos desea el cliente?");
		hashMap.put("MinRange", "12");
		hashMap.put("MaxRange", "24");
		hashMap.put("IsThisOK", Boolean.TRUE.toString());
		testData.add(hashMap);

		hashMap = new HashMap<String, String>();
		hashMap.put("question title", "Nœmero de personas");
		hashMap.put("question description",
						"cu‡nta gente hay antes de hablar con el que toma las decisiones");
		hashMap.put("question", "ÀCu‡ntas barreras hay antes del jefe?");
		hashMap.put("MinRange", "0");
		hashMap.put("MaxRange", "35");
		hashMap.put("IsThisOK", Boolean.TRUE.toString());
		testData.add(hashMap);

		/*
		 * hashMap.put("question title", ""); hashMap.put("question description", ); hashMap.put("question", );
		 * hashMap.put("MinRange",); hashMap.put("MaxRange", ); hashMap.put("IsThisOK", ); testData.add(hashMap);
		 * 
		 * hashMap.put("question title", ); hashMap.put("question description", ); hashMap.put("question", );
		 * hashMap.put("MinRange",); hashMap.put("MaxRange", ); hashMap.put("IsThisOK", ); testData.add(hashMap);
		 * 
		 * hashMap.put("question title", ); hashMap.put("question description", ); hashMap.put("question", );
		 * hashMap.put("MinRange",); hashMap.put("MaxRange", ); hashMap.put("IsThisOK", ); testData.add(hashMap);
		 */
		boolean flag1rst = true;
		for (int i = 0; i < testData.size(); i++) {
			if (flag1rst == true)
				flag1rst = false;
			else {
				this.launchActivityWithIntent("com.diplomadoUNAL.geosalesman",
								AddNewQuestion.class, intent);
				solo = new Solo(getInstrumentation(), getActivity());
			}
			HashMap<String, String> testValues = testData.get(i);

			// Type question title
			solo.enterText(0, testValues.get("question title"));
			// Type question description
			solo.enterText(1, testValues.get("question description"));

			// Type the question
			solo.enterText(2, testValues.get("question"));

			// Click the spinner and then the "Scale" question type
			solo.pressSpinnerItem(0, 4);

			// Type minimum and maximum ranges
			solo.waitForText(getActivity()
							.getResources()
							.getString(R.string.activity_add_new_question_maximum_value_hint));

			solo.enterText(0, testValues.get("MinRange"));
			solo.enterText(1, testValues.get("MaxRange"));

			// Click ok to close the dialog
			solo.clickOnButton(getActivity().getResources().getString(
							R.string.ok));

			// Click ok to get an ok message
			solo.clickOnButton(getActivity().getResources().getString(
							R.string.ok));

			// Wait for the ok toast message
			boolean flagOKDatabase = solo.waitForText(getActivity()
							.getResources()
							.getString(R.string.database_success_storing_data));
			assertEquals("Something wrong happened with the database", true,
							flagOKDatabase);
			// Finish opened activities
			solo.finishOpenedActivities();

		}

	}

}
