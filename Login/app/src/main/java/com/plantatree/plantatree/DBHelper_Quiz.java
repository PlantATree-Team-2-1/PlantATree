package com.plantatree.plantatree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper_Quiz extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PlantATree_Quiz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public DBHelper_Quiz(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                Quiz_Contract.QuestionsTable.QUIZ_TABLE + " ( " +
                Quiz_Contract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Quiz_Contract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                Quiz_Contract.QuestionsTable.COLUMN_CHOICE1 + " TEXT, " +
                Quiz_Contract.QuestionsTable.COLUMN_CHOICE2 + " TEXT, " +
                Quiz_Contract.QuestionsTable.COLUMN_CHOICE3 + " TEXT, " +
                Quiz_Contract.QuestionsTable.COLUMN_CORRECT_ANSWER + " INTEGER)";

        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Quiz_Contract.QuestionsTable.QUIZ_TABLE);
        onCreate(db);
    }

    public void fillQuestionsTable() {

        Quiz_Question q1 = new Quiz_Question("Trees may help reduce the effects of global warming by:",
                "Reduce CO2 in the atmosphere", "Provide shade from UV rays", "Increase oxygen in atmosphere", 1);
        addQuestion(q1);

        Quiz_Question q2 = new Quiz_Question("What two main substances do trees need to live?",
                "They absorb CO2 and H2O", "Trees absorb oxygen", "Trees only need sunlight", 1);
        addQuestion(q2);

        Quiz_Question q3 = new Quiz_Question("The amount of CO2 a tree may remove from the atmosphere to gain one pound is:",
                "About 3 pounds", "About 0.5 pounds", "About 1.5 pounds", 3);
        addQuestion(q3);

        Quiz_Question q4 = new Quiz_Question("What is one of the byproducts of photosynthesis?",
                "Shade", "Oxygen", "Shade", 2);
        addQuestion(q4);

        Quiz_Question q5 = new Quiz_Question("The water vapor given off by a tree becomes a critical part of Earth's ______________.",
                "Cloud cycle", "Hydrologic cycle", "Beauty", 2);
        addQuestion(q5);

        Quiz_Question q6 = new Quiz_Question("What element is used from the absorbed water to make the chemical energy for a tree?.",
                "Oxygen", "Carbon Dioxide", "Hydrogen", 3);
        addQuestion(q6);

        Quiz_Question q7 = new Quiz_Question("Trees are sometimes called ...?", "The Lungs of the World",
                "The Stomach of the World", "The Shady Part of the World", 1);
        addQuestion(q7);

        Quiz_Question q8 = new Quiz_Question("Approximately how much of the original forests have disappeared?",
                "3/4", "1/2", "1/3", 2);
        addQuestion(q8);

        Quiz_Question q9 = new Quiz_Question("The oldest living organism in North America happens to be a rugged tree. This tree is the?",
                "Bristlecone Pine", "Douglas Fir", "Giant Redwood", 1);
        addQuestion(q9);

        Quiz_Question q10 = new Quiz_Question("What is the science of dating through tree-ring growth called?",
                "Dendrochronology", "Eldology", "Lookingology", 1);
        addQuestion(q10);
    }

    public void addQuestion(Quiz_Question quizQuestion) {

        ContentValues cv = new ContentValues();
        cv.put(Quiz_Contract.QuestionsTable.COLUMN_QUESTION, quizQuestion.getQuestion());
        cv.put(Quiz_Contract.QuestionsTable.COLUMN_CHOICE1, quizQuestion.getOption1());
        cv.put(Quiz_Contract.QuestionsTable.COLUMN_CHOICE2, quizQuestion.getOption2());
        cv.put(Quiz_Contract.QuestionsTable.COLUMN_CHOICE3, quizQuestion.getOption3());
        cv.put(Quiz_Contract.QuestionsTable.COLUMN_CORRECT_ANSWER, quizQuestion.getAnswerNr());
        db.insert(Quiz_Contract.QuestionsTable.QUIZ_TABLE, null, cv);
    }

    public List<Quiz_Question> getAllQuestions() {

        List<Quiz_Question> quizQuestionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Quiz_Contract.QuestionsTable.QUIZ_TABLE, null);

        if (c.moveToFirst()) {

            do {

                Quiz_Question quizQuestion = new Quiz_Question();
                quizQuestion.setQuestion(c.getString(c.getColumnIndex(Quiz_Contract.QuestionsTable.COLUMN_QUESTION)));
                quizQuestion.setOption1(c.getString(c.getColumnIndex(Quiz_Contract.QuestionsTable.COLUMN_CHOICE1)));
                quizQuestion.setOption2(c.getString(c.getColumnIndex(Quiz_Contract.QuestionsTable.COLUMN_CHOICE2)));
                quizQuestion.setOption3(c.getString(c.getColumnIndex(Quiz_Contract.QuestionsTable.COLUMN_CHOICE3)));
                quizQuestion.setAnswerNr(c.getInt(c.getColumnIndex(Quiz_Contract.QuestionsTable.COLUMN_CORRECT_ANSWER)));
                quizQuestionList.add(quizQuestion);

            } while (c.moveToNext());
        }

        c.close();
        return quizQuestionList;
    }
}


