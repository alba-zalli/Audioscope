/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package audioscope;

/**
 *
 * @author AmAbd4146
 */
public class Vector2 {

    // Attributes
    private int x; // X component of vector
    private int y; // Y component of vector

    /**
     * Main constructor for vector2 class - Creates a 2D vector to storing 2
     * values (x,y)
     *
     * @param x - X component of vector
     * @param y - Y component of vector
     */
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x component of vector
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y component of vector
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for x
     *
     * @param num - The new value
     */
    public void setX(int num) {
        x = num;
    }

    /**
     * Setter for y
     *
     * @param num - The new value
     */
    public void setY(int num) {
        y = num;
    }

    /**
     * Adds a given value specifically to the x component
     *
     * @param num - The value to add
     */
    public int addX(int num) {
        x = x + num;
        return x;
    }

    public void add(Vector2 otherVec) {
        x = x + otherVec.getX();
        y = y + otherVec.getY();
    }

    /**
     * Adds a given value specifically to the y component
     *
     * @param num - The value to add
     */
    public int addY(int num) {
        y = y + num;
        return y;
    }

    /**
     * Gets the magnitude of the Vector2 (The formula is the pythagorean
     * theorem)
     *
     * @return - Returns the vector magnitude as a float
     */
    public int magnitude() {
        // Magnitude is pythagorean theorem: c = square root of ( a^2  + b^2)
        return (int)(Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2))));
    }

}
