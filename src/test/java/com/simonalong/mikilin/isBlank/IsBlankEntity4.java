package com.simonalong.mikilin.isBlank;

import com.simonalong.mikilin.annotation.Matcher;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shizi
 * @since 2020/6/18 5:10 PM
 */
@Data
@Accessors(chain = true)
public class IsBlankEntity4 {

    @Matcher(isBlank = "false", accept = false)
    private String name;
    private Integer age;
}
