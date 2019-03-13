package com.github.fac30ff.visitor.classic;

public class DoubleDispatchVisitor {
}

interface ExpressionVisitor {
    void visit(DoubleExpression e);
    void visit(AdditionExpression e);
}

abstract class Expression {
    public abstract void accept(ExpressionVisitor visitor);
}

class ExpressionCalculator implements ExpressionVisitor {

    public double result;

    public void visit(DoubleExpression e) {
        result = e.value;
    }

    public void visit(AdditionExpression e) {
        e.left.accept(this);
        double a = result;
        e.right.accept(this);
        double b = result;
        result = a + b;
    }
}

class ExpressionPrinter implements ExpressionVisitor {

    private StringBuilder stringBuilder = new StringBuilder();

    public void visit(DoubleExpression e) {
        stringBuilder.append(e.value);
    }

    public void visit(AdditionExpression e) {
        stringBuilder.append("(");
        e.left.accept(this);
        stringBuilder.append("+");
        e.right.accept(this);
        stringBuilder.append(")");
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}

class DoubleExpression extends Expression {
    public double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}

class AdditionExpression extends Expression {
    public Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
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
        ExpressionPrinter ep = new ExpressionPrinter();
        ep.visit(e);
        System.out.println(ep);
        ExpressionCalculator ec = new ExpressionCalculator();
        ec.visit(e);
        System.out.println(ep + " = " + ec.result);
    }
}
