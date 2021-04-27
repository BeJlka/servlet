package com.bejlka.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ServletUtil {
  public static String getBody(HttpServletRequest req) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();

    try (InputStream inputStream = req.getInputStream();
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line);
      }
    }

    return stringBuilder.toString();
  }
}
