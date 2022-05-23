package amazOrgan.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilities {
	
	//LEER UN ENTERO POR CONSOLA
    public static int readIntFromKeyboard(String question)
    {
        System.out.println(question);
        int numero;

        while (true)
        {
            try
            {
                String stringLeido = null;
                BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
                stringLeido = consola.readLine();
                numero = Integer.parseInt(stringLeido);
                return numero;

            } catch (IOException ioe)
            {
                System.out.println("Error. Vuelva a introducir un número");
            } catch (NumberFormatException nfe)
            {
                System.out.println("No ha introducido un número. Introduzca un número entero.");
            }
        }
    }
    
  //LEER UN STRING POR CONSOLA
    public static String readStringFromKeyboard(String question)
    {
        System.out.println(question);

        while (true)
        {
            try
            {
                String stringLeido = null;
                BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
                stringLeido = consola.readLine();

                return stringLeido;

            } catch (IOException ioe)
            {
                System.out.println("Error. Introduzca una nueva cadena de texto.");
            }
        }
    }
    
    //LEER ENTERO POR CONSOLA DENTRO DE UN RANGO
    public static int readIntFromKeyboardInRange(String question, int begin, int end)
    {
        System.out.println(question);
        int numero;
        if (begin > end)
        {
            int temp = begin;
            begin = end;
            end = begin;
        }
        while (true)
        {
            try
            {
                String stringLeido = null;
                BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
                stringLeido = consola.readLine();
                numero = Integer.parseInt(stringLeido);
                if (numero >= begin && numero <= end)
                {
                    return numero;
                } else
                {
                    System.out.println("El número debe estar entre " + begin + " y " + end + ".");
                }
            } catch (IOException ioe)
            {
                System.out.println("Error. Vuelva a introducir un número.");
            } catch (NumberFormatException nfe)
            {
                System.out.println("No ha introducido un número. Introduzca un número entero.");
            }
        }
    }

    
  //LEER UN FLOAT POR CONSOLA
    public static float readFloatFromKeyboard(String question)
    {
        System.out.println(question);
        float numero;

        while (true)
        {
            try
            {
                String stringLeido = null;
                BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
                stringLeido = consola.readLine();
                numero = Float.parseFloat(stringLeido);
                return numero;

            } catch (IOException ioe)
            {
                System.out.println("Ha habido un error. Vuelva a introducir un número");
            } catch (NumberFormatException nfe)
            {
                System.out.println("No ha introducido un número. Introduzca un número entero.");
            }
        }
    }
}
