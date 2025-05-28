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
    private float x;
    private float y;
    
    public Vector2(float x, float y){
        x = this.x;
        y = this.y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void setX(float num){
        x = num;
    }
    
    public void setY(float num){
        y = num;
    }
    
    public void addX(float num){
        x = x + num;
    }
    
    public void addY(float num){
        y = y + num;
    }
    
    public float magnitude(){
        // Magnitude is pythagoreom theorem: c = square root of ( a^2  + b^2)
        return (float) ( Math.sqrt( (Math.pow(x, 2) + Math.pow(y, 2)) ) );
    }
    
}
