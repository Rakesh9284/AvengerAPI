package com.vbrick.ngwebdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByAngularRepeaterCell extends ByAngular.BaseBy {

    private final String repeater;
    private boolean exact;
    private final int row;
    private final String column;

    public ByAngularRepeaterCell(String rootSelector, String repeater, boolean exact, int row, String column) {
        super(rootSelector);
        this.repeater = repeater;
        this.exact = exact;
        this.row = row;
        this.column = column;
    }

    protected Object getObject(SearchContext context, JavascriptExecutor javascriptExecutor) {
        return javascriptExecutor.executeScript(
                    "var using = arguments[0] || document;\n" +
                            "var rootSelector = '" + rootSelector + "';\n" +
                            "var repeater = '" + repeater.replace("'", "\\'") + "';\n" +
                            "var index = " + row + ";\n" +
                            "var binding = '" + column + "';\n" +
                            "var exact = " + exact + ";\n" +
                            "\n" +
                            ByAngular.functions.get("findRepeaterElement")
                    , context);
    }

    // meaningless
    @Override
    public List<WebElement> findElements(SearchContext searchContext) {
        throw new UnsupportedOperationException("This locator zooms in on a single cell, findElements() is meaningless");
    }

    @Override
    public String toString() {
        return (exact? "exactR":"r") + "epeater(" + repeater + ").row(" + row + ").column(" + column + ")";
    }

}
