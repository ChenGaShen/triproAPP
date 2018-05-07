
package com.menglin.tripro.util;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PhoneUtils
{
  public static String getBelonging(String phone)
    throws Exception
  {
    Gson gson = new Gson();
    String httpUrl = "http://apis.juhe.cn/mobile/get?phone=" + phone + "&key=7b4029cf1c329d784b45e8caacab5d5d";
    JsonResult jsonResult = getJsonResult(httpUrl);
    String resultcode = jsonResult.getResultcode();
    if ("200".equals(resultcode)) {
      ResultPhone result = (ResultPhone)gson.fromJson(gson.toJson(jsonResult.getResult()), ResultPhone.class);
      return result.getProvince() + result.getCity();
    }
    return "";
  }

  public static String getIPBelonging(String ip)
    throws Exception
  {
    Gson gson = new Gson();
    String httpUrl = "http://apis.juhe.cn/ip/ip2addr?ip=" + ip + "&key=c5ef44787671404a11dc322d1d520744";
    JsonResult jsonResult = getJsonResult(httpUrl);
    String resultcode = jsonResult.getResultcode();
    if ("200".equals(resultcode)) {
      ResultIP result = (ResultIP)gson.fromJson(gson.toJson(jsonResult.getResult()), ResultIP.class);
      return result.getArea() + result.getLocation();
    }
    return "";
  }

  private static JsonResult getJsonResult(String httpUrl)
    throws Exception
  {
    BufferedReader reader = null;
    String result = null;
    StringBuffer sbf = new StringBuffer();

    URL url = new URL(httpUrl);
    HttpURLConnection connection = (HttpURLConnection)url
      .openConnection();
    connection.connect();
    InputStream is = connection.getInputStream();
    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
    String strRead = null;
    while ((strRead = reader.readLine()) != null) {
      sbf.append(strRead);
    }
    reader.close();
    result = sbf.toString();

    Gson gson = new Gson();
    return (JsonResult)gson.fromJson(result, JsonResult.class);
  }
  
  public static String getBelonging1(String phone)
  throws Exception
{
  Gson gson = new Gson();
  String httpUrl = "http://apis.juhe.cn/mobile/get?phone=" + phone + "&key=7b4029cf1c329d784b45e8caacab5d5d";
  JsonResult jsonResult = getJsonResult(httpUrl);
  String resultcode = jsonResult.getResultcode();
  if ("200".equals(resultcode)) {
    ResultPhone result = (ResultPhone)gson.fromJson(gson.toJson(jsonResult.getResult()), ResultPhone.class);
    return result.getProvince();
  }
  return "";
}
}