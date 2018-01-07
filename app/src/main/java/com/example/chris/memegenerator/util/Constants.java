package com.example.chris.memegenerator.util;

/**
 * Created by chris on 12/2/2017.
 */

public class Constants
{
   // public static final String BASE_URL = "http://api.wunderground.com/";
   public static final String BASE_URL = "https://www.googleapis.com/customsearch/";
   public static boolean isGoogle;
   public static boolean iskeyword ;
   public static boolean istrending ;
   public static boolean isbing ;
   public static final String google = "google";
   public static final String keyword = "keyword";
   public static final String bing = "bing";
   public static final String trending = "trending";

   public static void whichCall(String whichcall) {

     switch (whichcall){
        case Constants.google:
           isGoogle = true;
           break;
        case Constants.keyword:
           iskeyword = true;
           break;
        case Constants.bing:
           isbing = true;
           break;
        case Constants.trending:
           istrending = true;
           break;
     }
   }
   public static void setallFALSE(){
      isGoogle=false;
      iskeyword = false;
      istrending = false;
      isbing = false;

   }
}
