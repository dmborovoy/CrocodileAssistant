package com.borovoi.dmitrii.crocodileassistant;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import java.util.List;

/**
 * Created by dimas on 11/2/2015.
 */
public class DBHelperTest extends AndroidTestCase {

    private static final String LOG_TAG = "myLogs";

    private DBHelper db;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new DBHelper(context);
    }

    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    public void testAddEntry(){
        Word random = db.getRandom(true, null);
        assertNotNull(random);
        Log.i(LOG_TAG, random.toString());
    }

    public void testAll(){
        List<Word> list = db.getAllWordsRandom();
        assertNotNull(list);
        assertEquals(9, list.size());
    }
}
