package com.dasoops.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.List;


/**
 * @title CompletionUtil
 * @classPath cn.qiaoml.utils.CompletionUtil
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/25
 * @version 1.0.0
 * @description 补全     工具类
 */
public class CompletionUtil {

    /**
     * 集合数据补全
     *
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @param dateField     日期字段
     * @param dateList      日期集合
     * @param dataList1     数据集合1
     * @param defaultValue1 数据集合1默认值
     */
    public static <T1> void listDataCompletion(Date beginTime, Date endTime, DateField dateField, List<Date> dateList,
                                               List<T1> dataList1, T1 defaultValue1
                                              ) {
        CompletionUtil.listDataCompletion(beginTime, endTime, dateField, dateList, dataList1, defaultValue1, null, null, null, null, null, null, null, null);
    }


    /**
     * 集合数据补全
     *
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @param dateField     日期字段
     * @param dateList      日期集合
     * @param dataList1     数据集合1
     * @param defaultValue1 数据集合1默认值
     * @param dataList2     数据集合2
     * @param defaultValue2 数据集合2默认值
     */
    public static <T1, T2> void listDataCompletion(Date beginTime, Date endTime, DateField dateField, List<Date> dateList,
                                                   List<T1> dataList1, T1 defaultValue1,
                                                   List<T2> dataList2, T2 defaultValue2
                                                  ) {
        CompletionUtil.listDataCompletion(beginTime, endTime, dateField, dateList, dataList1, defaultValue1, dataList2, defaultValue2, null, null, null, null, null, null);
    }


    /**
     * 集合数据补全
     *
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @param dateField     日期字段
     * @param dateList      日期集合
     * @param dataList1     数据集合1
     * @param defaultValue1 数据集合1默认值
     * @param dataList2     数据集合2
     * @param defaultValue2 数据集合2默认值
     * @param dataList3     数据集合3
     * @param defaultValue3 数据集合3默认值
     */
    public static <T1, T2, T3> void listDataCompletion(Date beginTime, Date endTime, DateField dateField, List<Date> dateList,
                                                       List<T1> dataList1, T1 defaultValue1,
                                                       List<T2> dataList2, T2 defaultValue2,
                                                       List<T3> dataList3, T3 defaultValue3
                                                      ) {
        CompletionUtil.listDataCompletion(beginTime, endTime, dateField, dateList, dataList1, defaultValue1, dataList2, defaultValue2, dataList3, defaultValue3, null, null, null, null);
    }


    /**
     * 集合数据完成
     * 集合数据补全
     *
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @param dateField     日期字段
     * @param dateList      日期集合
     * @param dataList1     数据集合1
     * @param defaultValue1 数据集合1默认值
     * @param dataList2     数据集合2
     * @param defaultValue2 数据集合2默认值
     * @param dataList3     数据集合3
     * @param defaultValue3 数据集合3默认值
     * @param dataList4     数据集合4
     * @param defaultValue4 数据集合4默认值
     */
    public static <T1, T2, T3, T4> void listDataCompletion(Date beginTime, Date endTime, DateField dateField, List<Date> dateList,
                                                           List<T1> dataList1, T1 defaultValue1,
                                                           List<T2> dataList2, T2 defaultValue2,
                                                           List<T3> dataList3, T3 defaultValue3,
                                                           List<T4> dataList4, T4 defaultValue4
                                                          ) {
        CompletionUtil.listDataCompletion(beginTime, endTime, dateField, dateList, dataList1, defaultValue1, dataList2, defaultValue2, dataList3, defaultValue3, dataList4, defaultValue4, null, null);
    }


    /**
     * 集合数据补全
     *
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @param dateField     日期字段
     * @param dateList      日期集合
     * @param dataList1     数据集合1
     * @param defaultValue1 数据集合1默认值
     * @param dataList2     数据集合2
     * @param defaultValue2 数据集合2默认值
     * @param dataList3     数据集合3
     * @param defaultValue3 数据集合3默认值
     * @param dataList4     数据集合4
     * @param defaultValue4 数据集合4默认值
     * @param dataList5     数据集合5
     * @param defaultValue5 数据集合5默认值
     */
    public static <T1, T2, T3, T4, T5> void listDataCompletion(Date beginTime, Date endTime, DateField dateField, List<Date> dateList,
                                                               List<T1> dataList1, T1 defaultValue1,
                                                               List<T2> dataList2, T2 defaultValue2,
                                                               List<T3> dataList3, T3 defaultValue3,
                                                               List<T4> dataList4, T4 defaultValue4,
                                                               List<T5> dataList5, T5 defaultValue5
                                                              ) {
        CompletionUtil.listDataCompletion(beginTime, endTime, dateField, 1, dateList, dataList1, defaultValue1, dataList2, defaultValue2, dataList3, defaultValue3, dataList4, defaultValue4, dataList5, defaultValue5);
    }


    /**
     * 集合数据补全
     *
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @param dateField     日期字段
     * @param dateStep      日期步进
     * @param dateList      日期集合
     * @param dataList1     数据集合1
     * @param defaultValue1 数据集合1默认值
     * @param dataList2     数据集合2
     * @param defaultValue2 数据集合2默认值
     * @param dataList3     数据集合3
     * @param defaultValue3 数据集合3默认值
     * @param dataList4     数据集合4
     * @param defaultValue4 数据集合4默认值
     * @param dataList5     数据集合5
     * @param defaultValue5 数据集合5默认值
     */
    public static <T1, T2, T3, T4, T5> void listDataCompletion(Date beginTime, Date endTime, DateField dateField, Integer dateStep, List<Date> dateList,
                                                               List<T1> dataList1, T1 defaultValue1,
                                                               List<T2> dataList2, T2 defaultValue2,
                                                               List<T3> dataList3, T3 defaultValue3,
                                                               List<T4> dataList4, T4 defaultValue4,
                                                               List<T5> dataList5, T5 defaultValue5
                                                              ) {

        int dateListSize = dateList.size();

        if (beginTime == null) {
            throw new CompletionException("起始时间不可为空");
        }

        if (endTime == null) {
            throw new CompletionException("结束时间不可为空");
        }

        beginTime = DateUtil.truncate(beginTime, dateField);
        endTime = DateUtil.truncate(endTime, dateField);

        //参数检查
        if (beginTime.after(endTime)) {
            throw new CompletionException("起始时间大于结束时间");
        }
        if (dataList1 == null) {
            throw new CompletionException("至少需要一组数据");
        }
        if (dataList1.size() != dateListSize) {
            throw new CompletionException("数据集合长度需与时间集合长度一致");
        }
        if (dataList2 != null && dataList2.size() != dateListSize) {
            throw new CompletionException("数据集合长度需与时间集合长度一致");
        }
        if (dataList3 != null && dataList3.size() != dateListSize) {
            throw new CompletionException("数据集合长度需与时间集合长度一致");
        }
        if (dataList4 != null && dataList4.size() != dateListSize) {
            throw new CompletionException("数据集合长度需与时间集合长度一致");
        }
        if (dataList5 != null && dataList5.size() != dateListSize) {
            throw new CompletionException("数据集合长度需与时间集合长度一致");
        }

        //步进间隔
        if (dateStep == null) {
            dateStep = 1;
        }
        //根据范围日期数判断是否需要补全
        List<DateTime> rangeDateList = DateUtil.rangeToList(beginTime, endTime, dateField, dateStep);
        int rangeDateListSize = rangeDateList.size();
        if (rangeDateListSize == dateListSize) {
            return;
        }

        //todo 缺少日期步进检测,有空一定

        //判断是否同一天
        for (int i = 0; i < rangeDateList.size(); i++) {
            DateTime rangeDate = rangeDateList.get(i);
            if (dateList.size() != i) {
                Date date = dateList.get(i);
                if (CompletionUtil.isSameDate(rangeDate, date, dateField)) {
                    continue;
                }
            }

            dateList.add(i, rangeDate.toJdkDate());
            dataList1.add(i, defaultValue1);
            if (dataList2 != null) {
                dataList2.add(i, defaultValue2);
            }
            if (dataList3 != null) {
                dataList3.add(i, defaultValue3);
            }
            if (dataList4 != null) {
                dataList4.add(i, defaultValue4);
            }
            if (dataList5 != null) {
                dataList5.add(i, defaultValue5);
            }

        }
    }

    private static boolean isSameDate(Date date1, Date date2, DateField dateField) {
        switch (dateField) {
            //调用写好的轮子效率更高
            case DAY_OF_YEAR:
            case DAY_OF_WEEK:
            case DAY_OF_WEEK_IN_MONTH:
                return DateUtil.isSameDay(date1, date2);
            case WEEK_OF_YEAR:
            case WEEK_OF_MONTH:
                return DateUtil.isSameWeek(date1, date2, true);
            case MONTH:
                return DateUtil.isSameMonth(date1, date2);
            //没有轮子简单粗暴判断起始时间和结束时间
            default:
                DateTime beginTime1 = DateUtil.truncate(date1, dateField);
                DateTime beginTime2 = DateUtil.truncate(date2, dateField);
                if (!beginTime1.equals(beginTime2)) {
                    return false;
                }
                DateTime endTime1 = DateUtil.ceiling(date1, dateField);
                DateTime endTime2 = DateUtil.ceiling(date2, dateField);
                return endTime1.equals(endTime2);
        }
    }

    private static class CompletionException extends RuntimeException {
        public CompletionException(String message) {
            super(message);
        }
    }

}
