package com.doo.study.dytransit.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dooyoungki on 1/1/16.
 */
public class AttributeSet {

    @SerializedName("provider_attributes")
    private Map<String, Map<String, String>> attributes = new HashMap<>();

    public Map<String, Map<String, String>> getAttributes() {
        return attributes;
    }

    public void setAtttributes(Map<String, Map<String, String>> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        final StringBuilder formatted = new StringBuilder();
        formatted.append("Provider Attributes: ");
        for(final String key : attributes.keySet()){
            formatted.append("\n\t-name: ").append(key)
                    .append("\n\t-attributes: ").append(attributes.get(key));

        }
        return formatted.toString();
    }
}
