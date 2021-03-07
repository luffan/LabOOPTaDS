package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Point startPoint;
    protected Color stroke;
    protected Color fill;

    public Shape(Point startPoint) {
        this.startPoint = startPoint;
    }

    public abstract void settings(Point startPoint, Point endPoint);

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setStroke(Color stroke) {
        this.stroke = stroke;
    }

    public void setFill(Color fill) {
        this.fill = fill;
    }

    protected abstract void draw(GraphicsContext graphicsContext);
}

class Square extends Shape {
    protected double width;

    public Square() {
        super(new Point(0, 0));
    }

    public Square(double x1, double y1, double width) {
        super(new Point(x1, y1));
        this.width = width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void settings(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        width = Math.abs(startPoint.getX() - endPoint.getX());
    }

    @Override
    protected void draw(GraphicsContext graphicsContext) {
        graphicsContext.strokeRect(startPoint.getX(), startPoint.getY(), width, width);
    }
}

class Rectangle extends Square {
    protected double height;

    public Rectangle() {
        super();
    }

    @Override
    public void settings(Point startPoint, Point endPoint) {
        super.settings(startPoint, endPoint);
        height = Math.abs(startPoint.getY() - endPoint.getY());
    }

    public Rectangle(double x1, double y1, double width, double height) {
        super(x1, y1, width);
        this.height = height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    protected void draw(GraphicsContext graphicsContext) {
        graphicsContext.strokeRect(startPoint.getX(), startPoint.getY(), width, height);
    }
}

class Parallelogram extends Rectangle {
    protected double angel = 45.0f;


    public Parallelogram() {
        super();
    }

    public Parallelogram(double x1, double y1, double width, double height, double angel) {
        super(x1, y1, width, height);
        this.angel = angel;
    }

    public void setAngel(double angel) {
        this.angel = angel;
    }

    @Override
    protected void draw(GraphicsContext graphicsContext) {
        graphicsContext.beginPath();
        double offset = height / Math.tan(angel * Math.PI / 180);
        graphicsContext.moveTo(startPoint.getX() + offset, startPoint.getY());
        graphicsContext.lineTo(startPoint.getX() + width, startPoint.getY());
        graphicsContext.lineTo(startPoint.getX() + width - offset,startPoint.getY() + height);
        graphicsContext.lineTo(startPoint.getX(), startPoint.getY() + height);
        graphicsContext.lineTo(startPoint.getX() + offset, startPoint.getY());
        graphicsContext.fill();
        graphicsContext.closePath();

    }
}

class Circle extends Shape {
    protected double radius1;

    public Circle() {
        super(new Point(0, 0));
    }

    public Circle(double x1, double y1, double radius1) {
        super(new Point(x1, y1));
        this.radius1 = radius1;
    }

    @Override
    public void settings(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.radius1 = Math.abs(startPoint.getY() - endPoint.getY());
    }

    @Override
    protected void draw(GraphicsContext graphicsContext) {
        graphicsContext.strokeOval(startPoint.getX(), startPoint.getY(), radius1, radius1);
    }
}

class Ellipse extends Circle {
    protected double radius2;

    public Ellipse() {
        super();
    }

    @Override
    public void settings(Point startPoint, Point endPoint) {
        super.settings(startPoint, endPoint);
        radius2 = Math.abs(startPoint.getX() - endPoint.getX());
    }

    public Ellipse(double x1, double y1, double radius1, double radius2) {
        super(x1, y1, radius1);
        this.radius2 = radius2;
    }

    @Override
    protected void draw(GraphicsContext graphicsContext) {
        graphicsContext.strokeOval(startPoint.getX(), startPoint.getY(), radius2, radius1);
    }
}

class Line extends Shape {
    protected Point endPoint;

    public Line() {
        super(new Point(0, 0));
        endPoint = new Point(0, 0);
    }

    public Line(double x1, double y1, double x2, double y2) {
        super(new Point(x1, y1));
        endPoint = new Point(x2, y2);
    }


    @Override
    public void settings(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    protected void draw(GraphicsContext graphicsContext) {
        graphicsContext.strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
    }
}

