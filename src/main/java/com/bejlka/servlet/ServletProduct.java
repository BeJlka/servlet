package com.bejlka.servlet;

import com.bejlka.service.MapProduct;
import com.bejlka.model.Product;
import com.bejlka.util.ServletUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product")
public class ServletProduct extends HttpServlet {

  private MapProduct mapProduct;

  @Override
  public void init() throws ServletException {
    mapProduct = MapProduct.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("Enter doGet");

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    ObjectMapper mapper = new ObjectMapper();
    if (req.getParameter("id") != null) {
      String jsonString = mapper.writeValueAsString(mapProduct.getProductMap().get(Integer.parseInt(req.getParameter("id"))));
      resp.getWriter().print(jsonString);
      return;
    }
    System.out.println(mapProduct.getProductMap());
    String jsonString = mapper.writeValueAsString(mapProduct.getProductMap());
    resp.getWriter().print(jsonString);

    System.out.println("Exit doGet");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("Enter doPost");

    req.setCharacterEncoding("UTF-8");
    ObjectMapper objectMapper = new ObjectMapper();
    Product product = objectMapper.readValue(ServletUtil.getBody(req), Product.class);
    mapProduct.addProduct(product);
    String jsonString = objectMapper.writeValueAsString(mapProduct.getProductMap());
    System.out.println(mapProduct.getProductMap());

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().write(jsonString);

    System.out.println("Exit doPost");
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("Enter doPut");

    ObjectMapper objectMapper = new ObjectMapper();
    mapProduct.updateProduct(Integer.parseInt(req.getParameter("id")),
        objectMapper.readValue(ServletUtil.getBody(req), Product.class));

    String jsonString = objectMapper.writeValueAsString(mapProduct.getProductMap());
    System.out.println(mapProduct.getProductMap());

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().write(jsonString);

    System.out.println("Exit doPut");
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("Enter doDelete");

    mapProduct.deleteProduct(Integer.parseInt(req.getParameter("id")));
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString = objectMapper.writeValueAsString(mapProduct.getProductMap());
    System.out.println(mapProduct.getProductMap());

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().write(jsonString);

    System.out.println("Exit doDelete");
  }
}
