package com.teemor.core.utils;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * JSONUtil is to encapsulate Jackson method, and make it easier to call with simple parameters
 *
 * @author guoqiang
 */
@Slf4j
public final class JsonUtil {

    private JsonUtil() {
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    private static final ObjectMapper STRICT_OBJECT_MAPPER = new ObjectMapper()
            .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    private static final ObjectMapper OBJECT_MAPPER_INCLUDE_NULL = new ObjectMapper()
            .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.ALWAYS);

    /**
     * Parses the given JSON string with the specified class and then returns a new instance of that class. If the given
     * JSON string is blank, {@code null} is returned.
     *
     * @param jsonString the JSON string
     * @param clazz      the class
     * @param <T>        the type of the class
     * @return a new instance of the class, or {@code null} if failed
     */
    public static <T> T fromJson(final String jsonString, final Class<T> clazz) {
        if (StrUtil.isNotBlank(jsonString)) {
            try {
                return OBJECT_MAPPER.readValue(jsonString, clazz);
            } catch (final IOException ex) {
                log.warn("Exception when de-serializing " + clazz + " with " + jsonString, ex);
            }
        }

        return null;
    }

    /**
     * Parses the given JSON string with the specified class and then returns a new instance of that class. If the given
     * JSON string is blank, {@code null} is returned.
     *
     * @param jsonString the JSON string
     * @param clazz      the class
     * @param <T>        the type of the class
     * @return a new instance of the class, or {@code null} if failed
     */
    public static <T> T fromJsonIncludeNull(final String jsonString, final Class<T> clazz) {
        if (StrUtil.isNotBlank(jsonString)) {
            try {
                return OBJECT_MAPPER_INCLUDE_NULL.readValue(jsonString, clazz);
            } catch (final IOException ex) {
                log.warn("Exception when de-serializing " + clazz + " with " + jsonString, ex);
            }
        }

        return null;
    }

    /**
     * Parses the given JSON string with the specified type reference and then returns a new instance of that type. If
     * the given JSON string is blank, {@code null} is returned. If you want to parse JSON to a parameterized type more
     * than two layer like List&lt;String&gt; or List&lt;Map&lt;String,SomeType&gt;&gt;, use this method.  Example :
     * List&lt;String&gt; should be put as new TypeToken&lt;List&lt;String&gt;&gt;(){}
     *
     * @param jsonString    the JSON string
     * @param typeReference the type reference
     * @param <T>           the type
     * @return a new instance of the class, or {@code null} if failed
     */
    public static <T> T fromJson(final String jsonString, final TypeReference<T> typeReference) {
        if (StrUtil.isNotBlank(jsonString)) {
            try {
                return OBJECT_MAPPER.readValue(jsonString, typeReference);
            } catch (final IOException ex) {
                log.warn("Exception when de-serializing " + jsonString, ex);
            }
        }

        return null;
    }

    /**
     * Parses the given JSON string with the specified type reference and then returns a new instance of that type. If
     * the given JSON string is blank, {@code null} is returned. If you want to parse JSON to a parameterized type more
     * than two layer like List&lt;String&gt; or List&lt;Map&lt;String,SomeType&gt;&gt;, use this method.  Example :
     * List&lt;String&gt; should be put as new TypeToken&lt;List&lt;String&gt;&gt;(){}
     *
     * @param jsonString    the JSON string
     * @param typeReference the type reference
     * @param <T>           the type
     * @return a new instance of the class, or {@code null} if failed
     */
    public static <T> T fromJsonIncludeNull(final String jsonString, final TypeReference<T> typeReference) {
        if (StrUtil.isNotBlank(jsonString)) {
            try {
                OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
                return OBJECT_MAPPER.readValue(jsonString, typeReference);
            } catch (final IOException ex) {
                log.warn("Exception when de-serializing " + jsonString, ex);
            }
        }

        return null;
    }

    /**
     * Parses the given object and returns a JSON string representing this object. If the given object is {@code null},
     * it returns an empty string.
     *
     * @param object the object
     * @return a JSON string representing this object, or {@code null} if null
     */
    public static String toJson(final Object object) {
        if (object != null) {
            try {
                return OBJECT_MAPPER.writeValueAsString(object);
            } catch (final JsonProcessingException ex) {
                log.warn("Exception when serializing " + object, ex);
            }
        }

        return "";
    }

    /**
     * Parses the given object and returns a JSON string representing this object. If the given object is {@code null},
     * it returns an empty string.
     *
     * @param object the object
     * @return a JSON string representing this object, or {@code null} if null
     */
    public static String toJsonIncludeDefault(final Object object) {
        if (object != null) {
            try {
                OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
                return OBJECT_MAPPER.writeValueAsString(object);
            } catch (final JsonProcessingException ex) {
                log.warn("Exception when serializing " + object, ex);
            }
        }

        return "";
    }

    /**
     * Parses the given object and returns a JSON string representing this object. If the given object is {@code null},
     * it returns an empty string.
     *
     * @param object the object
     * @return a JSON string representing this object, or {@code null} if null
     */
    public static String toJsonIncludeNull(final Object object) {
        if (object != null) {
            try {
                return OBJECT_MAPPER_INCLUDE_NULL.writeValueAsString(object);
            } catch (final JsonProcessingException ex) {
                log.warn("Exception when serializing " + object, ex);
            }
        }

        return "";
    }

    /**
     * Parses the given JSON string with the specified class and then returns a new instance of that class. If the given
     * JSON string is blank, {@code null} is returned.
     *
     * @param jsonString the JSON string
     * @param clazz      the class
     * @param <T>        the type of the class
     * @return a new instance of the class, or {@code null} if failed
     */
    public static <T> T fromJsonStrict(final String jsonString, final Class<T> clazz) {
        if (StrUtil.isNotBlank(jsonString)) {
            try {
                return STRICT_OBJECT_MAPPER.readValue(jsonString, clazz);
            } catch (final Exception ex) {
                log.warn("Exception when de-serializing " + clazz + " with " + jsonString, ex);
            }
        }

        return null;
    }


    /**
     * serializing obj to byte
     *
     * @param obj
     * @return byte[]
     * @author wood
     */
    public static byte[] toJsonByte(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(obj);
        } catch (Exception e) {
            log.warn("Exception when serializing " + obj + "to byte", e);
        }
        return null;
    }

}
