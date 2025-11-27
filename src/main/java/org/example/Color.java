package org.example;

public class Color {
    public static void main(String[] args) {
        Line [] lines = new Line[2];
        lines[0] = new Line (new Point(0,0),new Point(1,1));
        lines[1] = new ColorLine (new Point(5,10),new Point(10,20), "Blue");
        for (Line line : lines) {
            line.printLine();
        }
    }
}
class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
         }

         public void printLine() {
             System.out.println(this);
         }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
class ColorLine extends Line {
    private String color;

    public ColorLine(Point start, Point end,  String color) {
        super(start, end);
        this.color = color;
    }

    @Override
    public String toString() {
        return "ColorLine{" +
                "color='" + color + '\'' +
                "," + super.toString().substring(5);
    }
}

