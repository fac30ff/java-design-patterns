package com.github.fac30ff.visitor.acyclic;

public class AcyclicVisitor {
}

interface Visitor {
    //marker interface
}

interface ExpressionVisitor extends Visitor {
    void visit(Expression obj);
}

interface DoubleExpressionVisitor extends Visitor {
    void visit(DoubleExpression obj);
}

interface AdditionExpressionVisitor extends Visitor {
    void visit(AdditionExpression obj);
}

abstract class Expression {
    public void accept(Visitor visitor) {
        if (visitor instanceof ExpressionVisitor) {
            ((ExpressionVisitor)visitor).visit(this);
        }
    }
}

class ExpressionPrinter implements DoubleExpressionVisitor, AdditionExpressionVisitor {

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

    @Override
    public void accept(Visitor visitor) {
        if (visitor instanceof DoubleExpressionVisitor) {
            ((DoubleExpressionVisitor)visitor).visit(this);
        }
    }
}

class AdditionExpression extends Expression {
    public Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor instanceof AdditionExpressionVisitor) {
            ((AdditionExpressionVisitor)visitor).visit(this);
        }
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
    }
}
