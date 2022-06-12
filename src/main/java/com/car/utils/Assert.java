package com.car.utils;

import com.car.entity.enums.ExceptionEnum;
import com.car.entity.exception.BaseException;

import java.util.Collection;

/**
 * assert util
 *
 * @author Gandalf
 * @since 2022-06-11 18:01
 */
public class Assert {

   public static void notBlank(String s, ExceptionEnum exceptionEnum){
       if (s == null || "".equals(s)) {
           throw BaseException.of(exceptionEnum);
       }
   }

   public static void isTrue(boolean b, ExceptionEnum exceptionEnum){
       if (!b) {
           throw BaseException.of(exceptionEnum);
       }
   }

    public static void notNull(Object obj, ExceptionEnum exceptionEnum) {
        if (obj == null) {
            throw BaseException.of(exceptionEnum);
        }
    }

    public static void empty(Collection collection, ExceptionEnum exceptionEnum) {
        if (collection != null && !collection.isEmpty()) {
            throw BaseException.of(exceptionEnum);
        }
    }
}
