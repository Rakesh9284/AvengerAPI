package com.vbrick.ngwebdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;

public class ByAngularOptions extends ByAngular.BaseBy {

    public ByAngularOptions(String rootSelector, String options) {
        super(rootSelector);
        this.options = options;
    }

    private String options;

    protected Object getObject(SearchContext context, JavascriptExecutor javascriptExecutor) {
        return javascriptExecutor.executeScript(
                "var using = arguments[0] || document;\n" +
                        "var optionsDescriptor = '" + options + "';\n" +
                        "\n" +
                        ByAngular.functions.get("findByOptions")
                , context);
    }

    @Override
    public String toString() {
        return "options(" + options + ')';
    }
}