package com.simonalong.mikilin.range;

import com.simonalong.mikilin.annotation.FieldBlackMatcher;
import com.simonalong.mikilin.annotation.FieldWhiteMatcher;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhouzhenyong
 * @since 2019/4/14 上午11:44
 */
@Data
@Accessors(chain = true)
public class RangeEntity3 {

    @FieldWhiteMatcher(range = "[0.00,3.00]")
    private Float height;

    @FieldBlackMatcher(range = "[10,10000]")
    private Double money;

}