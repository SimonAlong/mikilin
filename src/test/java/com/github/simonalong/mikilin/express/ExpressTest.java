package com.github.simonalong.mikilin.express;

import com.github.simonalong.mikilin.util.Maps;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhouzhenyong
 * @since 2019/4/14 上午11:26
 */
public class ExpressTest {

    @Test
    public void testScript(){
        ExpressParser express = new ExpressParser(Maps.of("start", 1, "end", 100));
        express.addBinding(Maps.of("o", 10));
//        Assert.assertFalse(express.parse("1==2"));
        Assert.assertTrue(express.parse("import static java.lang.Math.*",
            "min(12,32) > 10"));
    }
}