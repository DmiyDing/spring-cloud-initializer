package com.example.utils;

import net.sf.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    private static final Map<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();


    public static <S, T> T convertTo(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        T target = null;
        try {
            target = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        getCacheBeanCopier(source.getClass(), targetClass).copy(source, target, null);
        return target;
    }

    public static <S, T> List<T> convertTo(List<S> sources, Class<T> target) {
        if (CollectionUtils.isEmpty(sources)) {
            return null;
        }
        return sources.stream().map(s -> convertTo(s, target)).collect(Collectors.toList());
    }


    private static <S, T> BeanCopier getCacheBeanCopier(Class<S> source, Class<T> target) {
        String cacheKey = source.toString() + target.getName();
        if (!BEAN_COPIER_CACHE.containsKey(cacheKey)) {
            BEAN_COPIER_CACHE.putIfAbsent(cacheKey, BeanCopier.create(source, target, false));
        }
        return BEAN_COPIER_CACHE.get(cacheKey);
    }
}
