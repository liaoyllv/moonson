package com.jf.moonson.starter.feign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 */
@Configurable
@AllArgsConstructor
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    private final ObjectMapper objectMapper;

    /**
     * 支持get传递对象，支持post传递对象
     */
    @Override
    public void apply(RequestTemplate template) {
        // 统一设置 DxAuthToken
        if (template.feignTarget().url().contains("/dsb/api/dx")) {
            template.header("DxAuthToken", "DOP_93e5d5e3af05431b8ce8c6de28978689");
        }

        // get-pojo贯穿
        if (template.method().equals(HttpMethod.GET.name()) && template.body() != null) {
            try {//template.requestBody().asBytes()
                JsonNode jsonNode = objectMapper.readTree(template.body());
                Map<String, Collection<String>> queries = new HashMap<>();
                //feign 不支持 GET 方法传 POJO, json body转query
                buildQuery(jsonNode, "", queries);
                template.queries(queries);
                template.body(Request.Body.empty());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //处理 get-pojo贯穿
    private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
        if (!jsonNode.isContainerNode()) { //叶子节点
            if (jsonNode.isNull()) {
                return;
            }
            Collection<String> values = queries.computeIfAbsent(path, k -> new ArrayList<>());
            values.add(jsonNode.asText());
            return;
        }
        if (jsonNode.isArray()) { //数组节点
            Iterator<JsonNode> it = jsonNode.elements();
            while (it.hasNext()) {
                buildQuery(it.next(), path, queries);
            }
        } else {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (StringUtils.hasText(path)) {
                    buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
                } else { //根节点
                    buildQuery(entry.getValue(), entry.getKey(), queries);
                }
            }
        }
    }
}