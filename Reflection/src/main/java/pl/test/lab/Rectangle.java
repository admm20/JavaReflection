package pl.test.lab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Rectangle {


    private double x = 5;
    public double y = 10;
    public int edges = 4;
    public int vertices = 4; // nie ma settera i gettera
    private double angle = 90;
    public double emptyValue;
    boolean testBool = true;

    public FigureType type = FigureType.RECTANGLE;

    public Date creationDate = new Date();

    public String getTestString() {
        System.out.println(testString);
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public String testString = "test string";


    public Rectangle(){
        // set date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = "03-05-2017";
        try {
            creationDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Cannot parse date");
        }
    }


    // Calculate area
    public double getArea(){
        System.out.println("Area = " + x*y);
        return (x * y);
    }

    // Calculate perimeter (private)
    private double getPerimeter(){
        System.out.println("Perimeter = " + (2*x + 2*y));
        return (2*x + 2*y);
    }


    public double getSumOfInternalAngles(){
        System.out.println("Sum of internal angles = " + angle*vertices);
        return (angle * vertices);
    }

    public void writeTypeOnConsole(){
        System.out.println("Rectangle");
    }

    public double getX() {
        System.out.println(x);
        return x;
    }

    public double setX(double x){
        return this.x = x;
    }


    public double getY() {
        System.out.println(y);
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getEdges() {
        System.out.println(edges);
        return edges;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }

    public FigureType getType() {
        System.out.println(type);
        return type;
    }

    public void setType(FigureType type) {
        this.type = type;
    }

    public Date getCreationDate() {
        System.out.println("Creation date: " + creationDate.toString());
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
