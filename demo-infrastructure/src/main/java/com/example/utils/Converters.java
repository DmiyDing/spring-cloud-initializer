package com.example.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ding
 */
public class Converters {


    /**
     * 转换bean
     *
     * @param source    要转换的bean
     * @param destClass 结果bean class类型
     * @return
     */
    public static <S, D> D convert(S source, Class<D> destClass) {
        if (source == null) {
            return null;
        }
        D dest;
        try {
            dest = destClass.newInstance();
            BeanUtils.copyProperties(source, dest, destClass);
        } catch (Exception e) {
            throw new ClassCastException("bean 转换异常.sourceBean=" + source + ", destBeanClass=" + destClass);
        }
        return dest;
    }

    /**
     * source bean List convert to dest bean list
     *
     * @param sourceList 要转换bean的集合
     * @param destClass  目标bean class类型
     * @return
     */
    public static <S, D> List<D> convertList(List<S> sourceList, Class<D> destClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Lists.newArrayList();
        }
        return sourceList.parallelStream().map(s -> convert(s, destClass)).collect(Collectors.toList());
    }

    /**
     * 分页拷贝
     *
     * @param sourcePage
     * @param destClass
     * @param <S>
     * @param <D>
     * @return
     */
        public static <S, D> IPage<D> convertPageQuery(IPage<S> sourcePage, Class<D> destClass) {
        IPage<D> iPage = new Page<>();
        if (sourcePage == null || sourcePage.getRecords().isEmpty()) {
            return iPage;
        }
        BeanUtils.copyProperties(sourcePage, iPage);
        iPage.setRecords(sourcePage.getRecords().parallelStream().map(s -> convert(s, destClass)).collect(Collectors.toList()));
        return iPage;
    }


    private Converters() {
        throw new IllegalStateException("Utility class");
    }
}
