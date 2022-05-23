/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author estudiante
 */
public class DimensionException extends Exception
{
    private int min, max;
    
    public DimensionException(int min, int max)
    {
        super();
        this.min = min;
        this.max = max;
    }

    @Override
    public String getMessage() {
        return ("La dimensión debe de estar entre " + min + " y " + max);
    }
    
    
}
