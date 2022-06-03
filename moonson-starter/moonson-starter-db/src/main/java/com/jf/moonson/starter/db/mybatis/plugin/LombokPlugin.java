package com.jf.moonson.starter.db.mybatis.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.*;

/**
 * ref: https://github.com/softwareloop/mybatis-generator-lombok-plugin/blob/master/src/main/java/com/softwareloop/mybatis/generator/plugins/LombokPlugin.java
 */
public class LombokPlugin extends PluginAdapter {

    private final Collection<LombokAnnotation> lombokAnnotations;

    public LombokPlugin() {
        this.lombokAnnotations = new LinkedHashSet<>(LombokAnnotation.values().length);
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 拦截普通字段
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addLombokAnnotations(topLevelClass);
        return true;
    }

    /**
     * 拦截主键字段
     */
    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addLombokAnnotations(topLevelClass);
        return true;
    }

    /**
     * 拦截blob类型字段
     */
    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        addLombokAnnotations(topLevelClass);
        return true;
    }

    /**
     * 不生成getter方法
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * 不生成setter方法
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        lombokAnnotations.add(LombokAnnotation.DATA);
        for (String annotationName : properties.stringPropertyNames()) {
            /// 先处理lombok注解
            if (annotationName.contains(".")) {
                continue;
            }
            /// lombok注解是否生效
            String value = properties.getProperty(annotationName);
            if (!Boolean.parseBoolean(value)) {
                continue;
            }
            LombokAnnotation lombokAnnotation = LombokAnnotation.getValueOf(annotationName);
            if (lombokAnnotation == null) {
                continue;
            }
            String optionsPrefix = annotationName + ".";
            /// 再处理lombok注解的属性
            for (String propertyName : properties.stringPropertyNames()) {
                if (!propertyName.startsWith(optionsPrefix)) {
                    continue;
                }
                String propertyValue = properties.getProperty(propertyName);
                lombokAnnotation.appendOptions(propertyName, propertyValue);
            }
            lombokAnnotations.add(lombokAnnotation);
            lombokAnnotations.addAll(LombokAnnotation.getDependencies(lombokAnnotation));
        }
    }

    @Override
    public boolean clientGenerated(Interface face, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        /// face.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
        /// face.addAnnotation("@Mapper");
        return true;
    }

    private void addLombokAnnotations(TopLevelClass topLevelClass) {
        for (LombokAnnotation annotation : lombokAnnotations) {
            topLevelClass.addImportedType(annotation.javaType);
            topLevelClass.addAnnotation(annotation.asAnnotation());
        }
    }

    private enum LombokAnnotation {
        /**
         *
         */
        DATA("data", "@Data", "lombok.Data"),
        BUILDER("builder", "@Builder", "lombok.Builder"),
        ALL_ARGS_CONSTRUCTOR("allArgsConstructor", "@AllArgsConstructor", "lombok.AllArgsConstructor"),
        NO_ARGS_CONSTRUCTOR("noArgsConstructor", "@NoArgsConstructor", "lombok.NoArgsConstructor"),
        ACCESSORS("accessors","@Accessors", "lombok.experimental.Accessors"),
        TO_STRING("toString", "@ToString", "lombok.ToString"),
        SUPER_BUILDER("superBuilder", "@SuperBuilder", "lombok.experimental.SuperBuilder");

        private final String propName;
        private final String name;
        private final FullyQualifiedJavaType javaType;
        private final List<String> options;

        LombokAnnotation(String propName, String name, String className) {
            this.propName = propName;
            this.name = name;
            this.javaType = new FullyQualifiedJavaType(className);
            this.options = new ArrayList<>();
        }

        private static LombokAnnotation getValueOf(String paramName) {
            for (LombokAnnotation annotation : LombokAnnotation.values()) {
                if (String.CASE_INSENSITIVE_ORDER.compare(paramName, annotation.propName) == 0) {
                    return annotation;
                }
            }
            return null;
        }

        private static Collection<LombokAnnotation> getDependencies(LombokAnnotation annotation) {
            if (annotation == ALL_ARGS_CONSTRUCTOR) {
                return Collections.singleton(NO_ARGS_CONSTRUCTOR);
            } else {
                return Collections.emptyList();
            }
        }

        public void appendOptions(String key, String value) {
            String keyPart = key.substring(key.indexOf(".") + 1);
            String valuePart = value.contains(",") ? String.format("{%s}", value) : value;
            this.options.add(String.format("%s=%s", keyPart, quote(valuePart)));
        }

        /**
         * A trivial quoting. Because Lombok annotation options type is almost String or boolean.
         */
        private static String quote(String value) {
            if (Boolean.TRUE.toString().equals(value) || Boolean.FALSE.toString().equals(value)) {
                /// case of boolean, not passed as an array.
                return value;
            }
            return value.replaceAll("[\\w]+", "\"$0\"");
        }

        private String asAnnotation() {
            if (options.isEmpty()) {
                return name;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append("(");
            boolean first = true;
            for (String option : options) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                sb.append(option);
            }
            sb.append(")");
            return sb.toString();
        }
    }

}
