package project;

import com.sun.net.httpserver.*;

import project.CSVHandler;

import java.io.*;
import java.net.*;
import java.util.*;

public class RequestHandler implements HttpHandler {

  public void handle(HttpExchange httpExchange) throws IOException {
    String response = "Request Received";
    String method = httpExchange.getRequestMethod();
    try {
      if (method.equals("GET")) {
        response = handleGet(httpExchange);
      } else if (method.equals("POST")) {
        //response = handlePost(httpExchange);
      } else if (method.equals("PUT")) {
        //response = handlePut(httpExchange);
      } else if (method.equals("DELETE")) {
        //response = handleDelete(httpExchange);
      } else {
        throw new Exception("Not Valid Request Method");
      }
    } catch (Exception e) {
      System.out.println("An erroneous request");
      response = e.toString();
      e.printStackTrace();
    }

    // Sending back response to the client
    httpExchange.sendResponseHeaders(200, response.length());
    OutputStream outStream = httpExchange.getResponseBody();
    outStream.write(response.getBytes());
    outStream.close();
  }

  private String handleGet(HttpExchange httpExchange) throws IOException {
    String response = "Invalid GET request";
    URI uri = httpExchange.getRequestURI();

    try {
      CSVHandler csvHandler = new CSVHandler();
      //List<String> list = csvHandler.readRecipes();
      //response = String.join("þ ", list);

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error getting recipes");
    }

    // String query = uri.getRawQuery();
    // if (query != null) {
    // String value = query.substring(query.indexOf("=") + 1);
    // String year = data.get(value); // Retrieve data from hashmap
    // if (year != null) {
    // response = year;
    // System.out.println("Queried for " + value + " and found " + year);
    // } else {
    // response = "No data found for " + value;
    // }
    // }
    return response;
  }

  // private String handlePost(HttpExchange httpExchange) throws IOException {
  // InputStream inStream = httpExchange.getRequestBody();
  // Scanner scanner = new Scanner(inStream);
  // String postData = scanner.nextLine();
  // String language = postData.substring(
  // 0,
  // postData.indexOf(",")), year = postData.substring(postData.indexOf(",") + 1);

  // // Store data in hashmap
  // data.put(language, year);

  // String response = "Posted entry {" + language + ", " + year + "}";
  // System.out.println(response);
  // scanner.close();

  // return response;
  // }

  // private String handlePut(HttpExchange httpExchange) throws IOException {
  // String response;
  // InputStream inStream = httpExchange.getRequestBody();
  // Scanner scanner = new Scanner(inStream);
  // String postData = scanner.nextLine();
  // String language = postData.substring(
  // 0,
  // postData.indexOf(",")), year = postData.substring(postData.indexOf(",") + 1);

  // // If the data already exists
  // if (data.containsKey(language)) {
  // String previousYear = data.get(language);
  // data.put(language, year);
  // response = "Updated entry {" + language + ", " + year + "} (previos year: " +
  // previousYear + ")";
  // }
  // // If the data does not exist
  // else {
  // data.put(language, year);
  // response = "Added entry {" + language + ", " + year + "}";
  // }

  // System.out.println(response);
  // scanner.close();
  // return response;
  // }

  // private String handleDelete(HttpExchange httpExchange) throws IOException {
  // String response = "Invalid DELETE request";
  // URI uri = httpExchange.getRequestURI();
  // String query = uri.getRawQuery();
  // if (query != null) {
  // String value = query.substring(query.indexOf("=") + 1);
  // String year = data.get(value); // Retrieve data from hashmap
  // if (year != null && data.containsValue(year)) {
  // String previousYear = year;
  // String previousValue = value;
  // data.remove(value, year);
  // response = "Deleted entry {" + previousValue + ", " + previousYear + "}";
  // } else {
  // response = "No data found for " + value;
  // }
  // }
  // return response;
  // }
}