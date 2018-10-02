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

        Quiz_Question q1 = new Quiz_Question("You need to help your children maintain a healthy balanced diet. Which of these foods is high calcium?", "Fish", "Peanuts", "Yogurt", 3);
        addQuestion(q1);

        Quiz_Question q2 = new Quiz_Question("When do children start learning speech patterns?", "Between 1-2 years old", "At three months", "In the womb", 3);
        addQuestion(q2);

        Quiz_Question q3 = new Quiz_Question("Which of these foods, high in potassium?", "Peas", "Whole Grain Bread", "Bananas", 3);
        addQuestion(q3);

        Quiz_Question q4 = new Quiz_Question("Babies move around and wiggle so much because...", "They're exploring", "They're uncomfortable", "Their muscles twitch and flex", 1);
        addQuestion(q4);

        Quiz_Question q5 = new Quiz_Question("What street do Grover, Elmo, Kermit and Big Bird live on?", "23rd", "Sesame", "Mr. Roger's", 2);
        addQuestion(q5);

        Quiz_Question q6 = new Quiz_Question("True or False: You should make a child vomit if you suspect he or she is suffering from poison.", "It depends what type of poison", "False", "True", 2);
        addQuestion(q6);

        Quiz_Question q7 = new Quiz_Question("Fill in the blank: 'Hush little baby don’t say a word, Papa’s gonna buy you a _________", "Mockingbird", "Firebird", "Horse", 1);
        addQuestion(q7);

        Quiz_Question q8 = new Quiz_Question("Which is generally considered better - breastfeeding or bottle feeding?", "Breastfeeding", "Bottle feeding", "Neither is considered", 1);
        addQuestion(q8);

        Quiz_Question q9 = new Quiz_Question("Most babies get their first tooth when they are how old?", "3 months", "6 months", "1 year", 2);
        addQuestion(q9);

        Quiz_Question q10 = new Quiz_Question("What should your baby's first solid food be?", "Seafood", "Soft cooked veges and fruit", "Whatever the parents eat", 2);
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


