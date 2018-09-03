package com.laiyy.design.demo16;

/**
 * @author laiyy
 * @date 2018/9/3 15:07
 * @description
 */
public class TerminalExpression implements Expression{

    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if (context.contains(data)) {
            return true;
        }
        return false;
    }
}
