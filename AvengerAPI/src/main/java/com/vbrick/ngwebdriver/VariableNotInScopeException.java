package com.vbrick.ngwebdriver;

import org.openqa.selenium.WebDriverException;

public class VariableNotInScopeException extends WebDriverException {
    public VariableNotInScopeException(String msg) {
        super(msg);
    }
}
