package me.kzv.mvc.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyModelAndView {
    private Object view;
    private Map<String, Object> model = new HashMap<>();

    public MyModelAndView(String viewName) {
        this.view = viewName;
    }

    public Map<String, Object> getModel() {
        return Collections.unmodifiableMap(model);
    }

    public String getViewName() {
        return (this.view instanceof String ? (String) this.view : null);
    }
}
