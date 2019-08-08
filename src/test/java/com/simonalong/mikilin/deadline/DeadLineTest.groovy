package com.simonalong.mikilin.deadline

import com.simonalong.mikilin.Checks
import org.junit.Assert
import spock.lang.Specification

/**
 * @author zhouzhenyong
 * @since 2019/8/3 上午10:42
 */
class DeadLineTest extends Specification {

    def "嵌套死循环"() {
        given:
        DeadBEntity bEntity = new DeadBEntity().setName(name)
        DeadAEntity aEntity = new DeadAEntity().setDeadBEntity(bEntity)

        expect:
        def act = Checks.check(aEntity)
        Assert.assertEquals(result, act)
        if (!act) {
            println Checks.errMsg
        }

        where:
        name | result
        "a"  | true
        "b"  | true
        "c"  | false
    }

}