package com.example.RunToU;

import org.json.JSONArray;

public class jsonData {
    private JSONArray jsonArray;

    public jsonData(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public JSONArray getJsonArrays() {
        return jsonArray;
    }

    public void setJsonArrays(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

}
