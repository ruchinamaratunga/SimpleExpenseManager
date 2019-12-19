package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DBHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class PersistenceMemoryTransaction implements TransactionDAO {
    DBHelper dbHelper ;

    public PersistenceMemoryTransaction(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.TransactionTable.COL_ACCOUNT_NO, accountNo);
        values.put(DBHelper.TransactionTable.COL_AMOUNT, amount);
        values.put(DBHelper.TransactionTable.COL_TYPE, expenseType.toString());
        values.put(DBHelper.TransactionTable.COL_DATE, String.valueOf(date));
        long newRowId = db.insert(DBHelper.TransactionTable.TABLE_NAME, null, values);

    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DBHelper.AccountTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List transactionList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String account_no = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TransactionTable.COL_ACCOUNT_NO));
            double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.TransactionTable.COL_AMOUNT));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TransactionTable.COL_DATE));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TransactionTable.COL_TYPE));
            Date newdate = null;
            try {
                newdate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Transaction transaction = new Transaction(newdate ,account_no,ExpenseType.valueOf(type),amount);
            transactionList.add(transaction);
        }
        cursor.close();

        return transactionList;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DBHelper.AccountTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List transactionList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String account_no = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TransactionTable.COL_ACCOUNT_NO));
            double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.TransactionTable.COL_AMOUNT));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TransactionTable.COL_DATE));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TransactionTable.COL_TYPE));
            Date newdate = null;
            try {
                newdate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Transaction transaction = new Transaction(newdate ,account_no,ExpenseType.valueOf(type),amount);
            transactionList.add(transaction);
        }
        cursor.close();

        return transactionList;
    }
}
