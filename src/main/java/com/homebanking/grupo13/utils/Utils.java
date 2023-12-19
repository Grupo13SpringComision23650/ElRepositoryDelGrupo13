package com.homebanking.grupo13.utils;

public class Utils {

    public static String generateCbu(){
        return String.format("%022d",(long)(Math.random()*Long.MAX_VALUE));
    }
    public static Long generateDni(){
        return (long)(Math.random()*10000000);
    }
    public static String generateAlias(){
        final int count=6;
        final String[] words={"sergio","sebastian","mirta","santiago","gervasio","luciano",
                "cris","codo","java","avanzado","buenos","aires","2023","comision","23650",
                "spring","boot","backend","tpfinal","crud","api","rest","argentina","jakarta",
                "git","github","idea","mysql","jpa","tomcat","hibernate","json","junior","trainee",
                "portafolio","proyecto","diciembre","bootstrap","desarrollador","postman",
                "workbench","teamwork","curso","code","homebanking","grupo13","alias","aries",
                "taurus","libra","sagitario","picis","geminis","escorpio","acuario"};

        String[] alias=new String[count];
        for(int i=0;i<count;i++) {
            alias[i]= Utils.randomPick(words);
        }
        alias[count-1]=String.format("%d",(int)(Math.random()*1000));
        return String.join(".",alias).toUpperCase();
    }

    public static String randomPick(String[] list) {
        int randomPos=(int)(Math.random()*list.length);
        return list[randomPos];
    }
}
