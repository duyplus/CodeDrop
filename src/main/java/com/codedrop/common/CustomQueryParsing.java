package com.codedrop.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomQueryParsing {

    private final ObjectMapper objectMapper;

    public CustomQueryParsing(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ParsedQuery safeParseQuery(String query) throws InvalidQueryFormatException {
        try {
            return parseQuery(query);
        } catch (IOException e) {
            throw new InvalidQueryFormatException("Invalid query format", e);
        }
    }

    private ParsedQuery parseQuery(String query) throws IOException {
        Map<String, String> conditions = new HashMap<>();
        int page = 0;
        int size = 10;
        JsonNode queryNode = objectMapper.readTree(query);
        JsonNode conditionNode = queryNode.get("condition");
        if (conditionNode != null && conditionNode.isObject()) {
            conditionNode.fields().forEachRemaining(entry -> {
                JsonNode valueNode = entry.getValue();
                if (valueNode.isTextual()) {
                    conditions.put(entry.getKey(), valueNode.asText());
                } else if (valueNode.isNumber()) {
                    conditions.put(entry.getKey(), valueNode.toString());
                }
            });
        }
        JsonNode paginationNode = queryNode.get("pagination");
        if (paginationNode != null) {
            page = paginationNode.has("page") ? paginationNode.get("page").asInt() : 0;
            size = paginationNode.has("pageSize") ? paginationNode.get("pageSize").asInt() : 10;
        }
        return new ParsedQuery(conditions, page, size);
    }

    @Getter
    public static class ParsedQuery {
        private final Map<String, String> conditions;
        private final int page;
        private final int size;

        public ParsedQuery(Map<String, String> conditions, int page, int size) {
            this.conditions = conditions;
            this.page = page;
            this.size = size;
        }
    }

    public static class InvalidQueryFormatException extends Exception {
        public InvalidQueryFormatException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
