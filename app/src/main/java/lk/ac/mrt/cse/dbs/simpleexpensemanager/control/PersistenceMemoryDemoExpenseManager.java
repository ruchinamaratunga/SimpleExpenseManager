package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistenceMemoryAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistenceMemoryTransaction;

public class PersistenceMemoryDemoExpenseManager extends ExpenseManager{
    Context context;

    public PersistenceMemoryDemoExpenseManager(Context context) {
        this.context = context;
        setup();
    }

    @Override
    public void setup() {
        AccountDAO persistenceMemoryAccountDAO = new PersistenceMemoryAccountDAO(context);
        setAccountsDAO(persistenceMemoryAccountDAO);

        TransactionDAO persistenceMemoryTransaction = new PersistenceMemoryTransaction(context);
        setTransactionsDAO(persistenceMemoryTransaction);

    }
}

