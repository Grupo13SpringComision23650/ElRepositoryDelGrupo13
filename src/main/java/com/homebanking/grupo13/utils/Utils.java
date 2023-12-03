package com.homebanking.grupo13.utils;

import java.time.Year;

public class Utils {


  // todo Mensajes para Postman se podrian agregar de todas los metodos
  public static final String USER_EXISTS_CODE = "001";
  public static final String USER_EXISTS_MESSAGE = "Este usuario ya existe!";

  public static final String USER_CREATION_SUCCESS_CODE = "002";
  public static final String USER_CREATION_SUCCESS_MESSAGE  = "Nuevo usuario creado exitosamente!";

  public static final String USER_LIST_CODE = "003";
  public static final String USER_LIST_MESSAGE  = "Listado de Usuarios!";



  public static  String generateAccountNumber(){// todo seria el CBU lo hice de 10 digitos? esta bien?
    /**
     * todo 2023 + randomSixDigits serian 6 digitos ramdon + 2023
     */
    Year currentYear = Year.now();
    int min = 100000;
    int max = 999999;

    /**
     * todo generamos un numero randon de 6 cifras para la cuenta entre min y max
     */
    int randNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
    /**
     * todo convertimos currentYear y RandNumber en String , luego concatenamos ambos
     */
    String year = String.valueOf(currentYear);
    String randomNumber = String.valueOf(randNumber);

    StringBuilder accountNumber = new StringBuilder();
    return accountNumber.append(year).append(randomNumber).toString();

  }

}
