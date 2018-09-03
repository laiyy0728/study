package com.laiyy.design.demo16;

/**
 * @author laiyy
 * @date 2018/9/3 15:08
 * @description
 */
public class AndExpression implements Expression {

    private Expression expression1 = null;

    private Expression expression2 = null;

    public AndExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public boolean interpret(String context) {
        return expression1.interpret(context) && expression2.interpret(context);
    }
}
