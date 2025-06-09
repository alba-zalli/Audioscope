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
    private float x; // X component of vector
    private float y; // Y component of vector

    /**
     * Main constructor for vector2 class - Creates a 2D vector to storing 2
     * values (x,y)
     *
     * @param x - X component of vector
     * @param y - Y component of vector
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x component of vector
     *
     * @return
     */
    public float getX() {
        return x;
    }

    /**
     * Getter for y component of vector
     *
     * @return
     */
    public float getY() {
        return y;
    }

    /**
     * Setter for x
     *
     * @param num - The new value
     */
    public void setX(float num) {
        x = num;
    }

    /**
     * Setter for y
     *
     * @param num - The new value
     */
    public void setY(float num) {
        y += num;
    }

    /**
     * Adds a given value specifically to the x component
     *
     * @param num - The value to add
     */
    public void addX(float num) {
        x += num;
    }

    /**
     * Adds a given value specifically to the y component
     *
     * @param num - The value to add
     */
    public void addY(float num) {
        y = y + num;
    }

    public void add(Vector2 otherVec) {
        x += otherVec.getX();
        y += otherVec.getY();
    }

    /**
     * Gets the magnitude of the Vector2 (The formula is the pythagorean
     * theorem)
     *
     * @return - Returns the vector magnitude as a float
     */
    public float magnitude() {
        // Magnitude is pythagorean theorem: c = square root of ( a^2  + b^2)
        return (float) (Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2))));
    }
    /**
     * Converts vector2 Coordinates into a string formatted as: [x, y]
     * @return - The string representation of the vector2
     */
    public String toString(){
        return ("[" + x + ", " + y + "]");
            
    }

}