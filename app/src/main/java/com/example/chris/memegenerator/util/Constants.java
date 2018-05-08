package com.example.chris.memegenerator.util;

/**
 * Created by chris on 12/2/2017.
 */

/**
 * for right this second, 7 day trial, you can use either of these bing subsription keys (5/7/18)
 * Key 1: e7e0b19756644009ac9adf50d767e5cb
 Key 2: ba6d878f282644ebbfd20f9eac5a042c
 */
public class Constants
{
   public static final String BASE_URL = "https://api.cognitive.microsoft.com/";
   public static final String PIXABAY_KEY = "7972684-7ee9b46c0655eecf8e847fa90";
   //public static final String BING_KEY1 = "e5d510902e0f4b8f84d5501982c45f5e";
   //public static final String BING_KEY2 = "cfefb6237b6145a3b41b40c7e065aa9c";
  public static final String BING_KEY1= "e7e0b19756644009ac9adf50d767e5cb";
   public static final String BING_KEY2= "ba6d878f282644ebbfd20f9eac5a042c";
   public static final String LANUAGE_PARSER_API_KEY = "69830c4d38b6259d7c9bd14adc09d2a1";
   public static boolean isGoogle;
   public static boolean iskeyword ;
   public static boolean istrending ;
   public static boolean isbing ;
   public static boolean istopTrending ;
   public static boolean isinterestTrending ;
   public static final String google = "google";
   public static final String keyword = "keyword";
   public static final String bing = "bing";
   public static final String trending = "trending";
   public static final String topTrending = "topTrending";
   public static final String interestTrending = "interestTrending";
   public static final String Searchmeme = "Searchmeme";
   public static final String ACTION_DOWNLOAD = "download";
   public static final String IMG_DOWNLOAD = "download_image";


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
        case Constants.interestTrending:
           isinterestTrending = true;
           break;
        case Constants.topTrending:
           istopTrending = true;
           break;
     }
   }
   public static void setallFALSE(){
      isGoogle=false;
      iskeyword = false;
      istrending = false;
      isbing = false;
      istopTrending = false;
      isinterestTrending = false;

   }
}
