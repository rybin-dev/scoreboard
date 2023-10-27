package com.rybindev.scoreboard.util;

import lombok.experimental.UtilityClass;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;
@UtilityClass
public class TestUtils {

    public static Field getField(Class<?> clazz, String fieldName) {
        return ReflectionUtils.findFields(
                        clazz,
                        field -> field.getName().equals(fieldName),
                        ReflectionUtils.HierarchyTraversalMode.TOP_DOWN)
                .get(0);
    }
}
