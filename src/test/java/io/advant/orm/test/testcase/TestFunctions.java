package io.advant.orm.test.testcase;

import io.advant.orm.DBFactory;
import io.advant.orm.DB;
import io.advant.orm.exception.ConnectionException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Marco Romagnolo
 */
public class TestFunctions {

    @Test
    public void testDBstream() throws ConnectionException {
        DB db = DBFactory.newInstance(Thread.currentThread().getContextClassLoader().getResourceAsStream("customconfig.properties"));
        Assert.assertFalse(db.isConnected());
        Assert.assertEquals(DBFactory.getInstance(), db);
        Assert.assertEquals(db.getConnection(), DBFactory.getInstance().getConnection());
        Assert.assertTrue(db.isConnected());
        db.disconnect();
        Assert.assertFalse(db.isConnected());
    }

    @Test
    public void testDBauto() throws ConnectionException {
        DB db = DBFactory.newInstance();
        Assert.assertFalse(db.isConnected());
        Assert.assertEquals(DBFactory.getInstance(), db);
        Assert.assertEquals(db.getConnection(), DBFactory.getInstance().getConnection());
        Assert.assertTrue(db.isConnected());
        db.disconnect();
        Assert.assertFalse(db.isConnected());
    }

}
