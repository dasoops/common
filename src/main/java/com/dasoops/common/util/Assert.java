package com.dasoops.common.util;

import cn.hutool.core.util.ObjUtil;
import com.dasoops.common.util.entity.AssertReslover;
import com.dasoops.common.util.entity.DefaultAssertReslover;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @title Assert
 * @classPath com.dasoops.core.util.Assert
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/01
 * @version 1.0.0
 * @description 断言工具类
 */
public class Assert {

    private final AssertReslover reslover;
    private static Assert instance;

    protected Assert(AssertReslover reslover) {
        this.reslover = reslover;
    }

    public static Assert getInstance() {
        if (instance == null) {
            instance = new Assert(new DefaultAssertReslover());
        }
        return instance;
    }

    /**
     * 如果为空
     *
     * @param obj obj
     */
    public static <T> void ifNull(T obj, NoneFunction function) {
        if (ObjUtil.isNull(obj) || ObjUtil.isEmpty(obj)) {
            function.invoke();
        }
    }

    /**
     * 如果为空
     *
     * @param obj obj
     */
    public static <T, R> R ifNullOrElse(T obj, Function<T, R> function, Function<T, R> orElseFunction) {
        if (ObjUtil.isNull(obj) || ObjUtil.isEmpty(obj)) {
            function.apply(obj);
        }
        return orElseFunction.apply(obj);
    }

    /**
     * 对象非空时执行
     *
     * @param obj obj
     */
    public static <T> void ifNotNull(T obj, NoneFunction function) {
        if (!ObjUtil.isNull(obj) && !ObjUtil.isEmpty(obj)) {
            function.invoke();
        }
    }

    /**
     * 对象非空时执行
     *
     * @param obj obj
     */
    public static <T, R> R ifNotNull(T obj, Supplier<R> function) {
        if (!ObjUtil.isNull(obj) && !ObjUtil.isEmpty(obj)) {
            return function.get();
        }
        return null;
    }

    /**
     * 对象非空时执行
     *
     * @param obj obj
     */
    public static <T, R> R ifNotNull(T obj, Function<T, R> function) {
        if (!ObjUtil.isNull(obj) && !ObjUtil.isEmpty(obj)) {
            return function.apply(obj);
        }
        return null;
    }

    /**
     * 对象非空时执行function,为空时执行orElseFunction
     *
     * @param obj obj
     */
    public static <T, R> R ifNotNullOrElse(T obj, Function<T, R> function, Function<T, R> orElseFunction) {
        if (!ObjUtil.isNull(obj) && !ObjUtil.isEmpty(obj)) {
            return function.apply(obj);
        }
        return orElseFunction.apply(obj);
    }

    /**
     * 对象非空时执行function,为空时执行orElseFunction
     *
     * @param obj obj
     */
    public static <T> void ifNotNullOrElse(T obj, NoneFunction function, NoneFunction orElseFunction) {
        if (!ObjUtil.isNull(obj) && !ObjUtil.isEmpty(obj)) {
            function.invoke();
        }
        orElseFunction.invoke();
    }

    /**
     * 结果为真
     *
     * @param bool     boolean
     * @param function 函数
     */
    public static void ifTrue(Boolean bool, NoneFunction function) {
        if (bool) {
            function.invoke();
        }
    }

    /**
     * 结果为真
     *
     * @param bool     boolean
     * @param function 函数
     */
    public static void ifTrueOrElse(Boolean bool, NoneFunction function, NoneFunction orElseFunction) {
        if (bool) {
            function.invoke();
        } else {
            orElseFunction.invoke();
        }
    }

    /**
     * 结果为假
     *
     * @param bool     boolean
     * @param function 函数
     */
    public static void ifFalse(Boolean bool, NoneFunction function) {
        if (!bool) {
            function.invoke();
        }
    }

    /**
     * 对象必须全部为空
     *
     * @param objs objs
     */
    public boolean allMustNull(Object... objs) {
        if (ObjUtil.isNull(objs) || ObjUtil.isEmpty(objs)) {
            return true;
        }
        for (Object obj : objs) {
            if (!ObjUtil.isNull(obj) && !ObjUtil.isEmpty(obj)) {
                reslover.allMustNull();
                return false;
            }
        }
        return true;
    }

    /**
     * 对象必须全部非空
     *
     * @param obj obj
     */
    public boolean allMustNotNull(Object... obj) {
        if (ObjUtil.isNull(obj) || ObjUtil.isEmpty(obj)) {
            reslover.allMustNotNull();
            return false;
        }
        for (Object o : obj) {
            if (ObjUtil.isNull(o) || ObjUtil.isEmpty(o)) {
                reslover.allMustNotNull();
                return false;
            }
        }
        return true;
    }

    /**
     * 如果全空
     * 全空返回true
     *
     * @param obj obj
     */
    public static boolean ifAllNull(NoneFunction function, Object... obj) {
        if (!ObjUtil.isNull(obj) && !ObjUtil.isEmpty(obj)) {
            function.invoke();
            return true;
        }
        for (Object o : obj) {
            if (!ObjUtil.isNull(o) && !ObjUtil.isEmpty(o)) {
                function.invoke();
                return true;
            }
        }
        return false;
    }

    /**
     * 如果有空
     * 有空返回true
     *
     * @param obj obj
     */
    public static boolean ifHasAnyNull(NoneFunction function, Object... obj) {
        if (ObjUtil.isNull(obj) || ObjUtil.isEmpty(obj)) {
            function.invoke();
            return true;
        }
        for (Object o : obj) {
            if (ObjUtil.isNull(o) || ObjUtil.isEmpty(o)) {
                function.invoke();
                return true;
            }
        }
        return false;
    }

    /**
     * 结果为真
     *
     * @param bool boolean
     */
    public void isTrue(Boolean bool) {
        if (bool) {
            reslover.isTrue();
        }
    }

    /**
     * 结果为假
     *
     * @param bool boolean
     */
    public void isFalse(Boolean bool) {
        if (!bool) {
            reslover.isFalse();
        }
    }

    /**
     * 执行结果必须成功
     *
     * @param bool 保龄球
     */
    public void dbExecuteMustSuccess(Boolean bool) {
        if (!bool) {
            reslover.dbExecuteMustSuccess();
        }
    }

    /**
     * 数据库不可执行返回not null
     *
     * @param obj obj
     */
    public void dbExecuteReturnMustNotNull(Object obj) {
        if (ObjUtil.isNull(obj)) {
            reslover.dbExecuteReturnMustNotNull();
        }
    }

    /**
     * sql执行返回记录数不可为空
     *
     * @param count 记录数
     */
    public void dbExecuteResNotZero(Integer count) {
        if (count <= 0) {
            reslover.dbExecuteResNotZero();
        }
    }

    /**
     * @title noneFunction
     * @classPath com.dasoops.dasserver.core.util.CqAssert.getInstance()noneFunction
     * @author DasoopsNicole@Gmail.com
     * @date 2022/11/01
     * @version 1.0.0
     * @description 无参无返回值函数
     */
    public interface NoneFunction {
        /**
         * 调用
         */
        void invoke();
    }

}
