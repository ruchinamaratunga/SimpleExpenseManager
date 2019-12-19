package lk.ac.mrt.cse.dbs.simpleexpensemanager.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
//import android.support.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "SEM.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static class AccountTable implements BaseColumns {
        public static final String TABLE_NAME = "Account";
        public static final String COL_BALANCE = "balance";
        public static final String COL_ACCOUNT_NO = "account_no";
        public static final String COL_BANK = "bank_name";
        public static final String COL_ACCOUNT_HOLDER = "account_holder";

    }

    public static class TransactionTable implements BaseColumns {
        public static final String TABLE_NAME = "Transactions";
        public static final String COL_ACCOUNT_NO = "account_no";
        public static final String COL_TYPE = "type";
        public static final String COL_AMOUNT = "amount";
        public static final String COL_DATE = "date";

    }

    private static final String SQL_CREATE_ACCOUNT =
            "CREATE TABLE " + AccountTable.TABLE_NAME + " (" +
                    AccountTable.COL_ACCOUNT_NO + " TEXT PRIMARY KEY," +
                    AccountTable.COL_BALANCE + " REAL," +
                    AccountTable.COL_BANK + " TEXT," +
                    AccountTable.COL_ACCOUNT_HOLDER + " TEXT)";

    private static final String SQL_CREATE_TRANSACTIONS =
            "CREATE TABLE " + TransactionTable.TABLE_NAME + " (" +
                    TransactionTable._ID + " INTEGER PRIMARY KEY," +
                    TransactionTable.COL_TYPE + " TEXT," +
                    TransactionTable.COL_DATE + " TEXT," +
                    TransactionTable.COL_AMOUNT + " INTEGER," +
                    TransactionTable.COL_ACCOUNT_NO + " TEXT)";

    private static final String SQL_DELETE_ACCOUNT = "DROP TABLE IF EXISTS " + AccountTable.TABLE_NAME;
    private static final String SQL_DELETE_TRANSACTIONS = "DROP TABLE IF EXISTS " + TransactionTable.TABLE_NAME;



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACCOUNT);
        db.execSQL(SQL_CREATE_TRANSACTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ACCOUNT);
        db.execSQL(SQL_DELETE_TRANSACTIONS);
        onCreate(db);
    }
}
