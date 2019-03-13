package com.github.fac30ff.visitor.reflective;

public class ReflectiveVisitor {
}

class ExpressionPrinter {
    public static void print(Expression expressions, StringBuilder sb) {
        if (expressions.getClass() == DoubleExpression.class) {
            sb.append(((DoubleExpression) expressions).value);
        } else if (expressions.getClass() == AdditionExpression.class) {
            AdditionExpression ae = (AdditionExpression) expressions;
            sb.append("(");
            print(ae.left, sb);
            sb.append("+");
            print(ae.right, sb);
            sb.append(")");
        }
    }
}

abstract class Expression {
}

class DoubleExpression extends Expression {
    public double value;

    public DoubleExpression(double value) {
        this.value = value;
    }
}

class AdditionExpression extends Expression {
    public Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}

class Demo {
    public static void main(String[] args) {
        AdditionExpression e = new AdditionExpression(
                new DoubleExpression(1),
                new AdditionExpression(
                        new DoubleExpression(2),
                        new DoubleExpression(3)
                )
        );
        StringBuilder sb = new StringBuilder();
        ExpressionPrinter ep = new ExpressionPrinter();
        ep.print(e, sb);
        System.out.println(sb);
    }
}
