package com.jf.moonson.starter.web.config;

import com.jf.moonson.common.enums.code2enum.BaseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * 枚举自动映射
 */
@Slf4j
@Configuration
public class CodeEnumWebMvcAutoConfiguration implements WebMvcConfigurer {

    public CodeEnumWebMvcAutoConfiguration() {
        log.info("Initializing CodeEnumWebMvcAutoConfiguration");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new BaseCodeEnumConverterFactory());
    }

    private static class BaseCodeEnumConverterFactory implements ConverterFactory<String, BaseCodeEnum> {

        @Override
        public <T extends BaseCodeEnum> Converter<String, T> getConverter(Class<T> targetType) {
            return new BaseCodeEnumConverter<>(targetType);
        }
    }

    private static class BaseCodeEnumConverter<T extends BaseCodeEnum> implements Converter<String, T> {
        private final T[] enumTypes;

        BaseCodeEnumConverter(Class<T> enumType) {
            this.enumTypes = enumType.getEnumConstants();
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (StringUtils.isBlank(source)) {
                return null;
            }
            return Arrays.stream(enumTypes).filter(e -> Integer.valueOf(source).equals(e.getCode())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Unknown type " + source));
        }
    }
}
