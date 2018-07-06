/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ali Al-Jobouri
 */
public class Rect {

    double x, y, width, height;

    public Rect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    Rect intersection(Rect rect2) {
        double newX = Math.max(this.x, rect2.x);
        double newY = Math.max(this.y, rect2.y);

        double newWidth = Math.min(this.x + this.width, rect2.x + rect2.width) - newX;
        double newHeight = Math.min(this.y + this.height, rect2.y + rect2.height) - newY;

        if (newWidth <= 0d || newHeight <= 0d) return null;

        return new Rect(newX, newY, newWidth, newHeight);
    }

    double area() {
        return this.width * this.height;
    }

}