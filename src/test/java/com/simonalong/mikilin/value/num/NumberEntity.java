package com.simonalong.mikilin.value.num;

import com.simonalong.mikilin.annotation.Matcher;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhouzhenyong
 * @since 2019/10/26 下午9:14
 */
@Data
@Accessors(chain = true)
public class NumberEntity {

    @Matcher({"1", "2", "null"})
    private Integer age;
}
