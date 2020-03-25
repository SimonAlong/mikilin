package com.simonalong.mikilin.match;

import com.simonalong.mikilin.annotation.Matcher;
import com.simonalong.mikilin.match.matcher.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 属性匹配管理器
 *
 * @author zhouzhenyong
 * @since 2019/4/10 下午12:52
 */
@Getter
@Setter
@Accessors(chain = true)
public class FieldMatchManager {

    /**
     * 属性名字
     */
    private String name;

    /**
     * 匹配器列表
     */
    private List<Match> matchList = new ArrayList<>();

    /**
     * 拦截后的自定义描述
     */
    private String errMsg;

    /**
     * 属性核查禁用标示，对应{@link Matcher#disable()}
     */
    private Boolean disable;

    /**
     * 属性匹配匹配器
     *
     * @param object 待校验的属性的对象
     * @param value 待校验的数据，就是属性的值
     * @param context 核查上下文
     * @return true：匹配任何一个匹配器返回true，false：所有匹配器都没有匹配上
     */
    public Boolean match(Object object, Object value, MkContext context, Boolean whiteOrBlack) {
        List<String> errMsgList = new ArrayList<>();
        for (Match m : matchList) {
            if (null == m) {
                continue;
            }

            if (m.match(object, name, value)) {
                if (!whiteOrBlack) {
                    context.append(m.getBlackMsg());
                    addErrMsg(context, m.getBlackMsg());
                } else {
                    context.clear();
                }
                return true;
            } else {
                if(whiteOrBlack) {
                    errMsgList.add(m.getWhiteMsg());
                    addErrMsg(context, m.getWhiteMsg());
                }
            }
        }

        if (whiteOrBlack) {
            context.append(errMsgList);
        }

        return false;
    }

    private void addErrMsg(MkContext context, String sysErrMsg) {
        if (null != sysErrMsg) {
            if (!"".equals(errMsg)) {
                context.setLastErrMsg(errMsg);
            } else {
                context.setLastErrMsg(sysErrMsg);
            }
        }
    }

    /**
     * 判断是否有匹配器不空，如果有任何一个匹配器不空，则可以启动属性判决
     *
     * @return true：条件为空，false：条件不空
     */
    public Boolean isEmpty() {
        if (disable) {
            return true;
        }

        return matchList.stream().allMatch(Match::isEmpty);
    }

    @SuppressWarnings("all")
    public static FieldMatchManager buildFromValid(Field field, Matcher validCheck, MkContext context) {
        return new FieldMatchManager().setName(field.getName())
            .addMatcher(TypeMatch.build(validCheck.type()))
            .addMatcher(ValueMath.build(field, validCheck.value()))
            .addMatcher(MatcherFactory.build(ModelMatch.class, validCheck.model()))
            .addMatcher(MatcherFactory.build(EnumTypeMatch.class, validCheck.enumType()))
            .addMatcher(MatcherFactory.build(RangeMatch.class, validCheck.range()))
            .addMatcher(ConditionMatch.build(field, validCheck.condition()))
            .addMatcher(MatcherFactory.build(RegexMatch.class, validCheck.regex()))
            .addMatcher(JudgeMatch.build(validCheck.judge(), context))
            .setErrMsg(validCheck.errMsg())
            .setDisable(validCheck.disable());
    }

    private FieldMatchManager addMatcher(Match match) {
        if (null != match) {
            matchList.add(match);
        }
        return this;
    }
}
