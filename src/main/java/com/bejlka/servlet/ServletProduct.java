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

@WebServlet("/products")
public class ServletProduct extends HttpServlet {

    private MapProduct mapProduct;

    @Override
    public void init() throws ServletException {
        mapProduct = new MapProduct();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (req.getParameter("id") != null) {
            String jsonString = mapper.writeValueAsString(mapProduct.getProductMap().get(Integer.parseInt(req.getParameter("id"))));
            ServletUtil.respJson(resp, jsonString);
            return;
        }
        String jsonString = mapper.writeValueAsString(mapProduct.getProductMap());
        ServletUtil.respJson(resp, jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(ServletUtil.getBody(req), Product.class);
        mapProduct.addProduct(product);
        String jsonString = objectMapper.writeValueAsString(mapProduct.getProductMap());
        ServletUtil.respJson(resp, jsonString);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        mapProduct.updateProduct(Integer.parseInt(req.getParameter("id")),
                objectMapper.readValue(ServletUtil.getBody(req), Product.class));
        String jsonString = objectMapper.writeValueAsString(mapProduct.getProductMap());
        ServletUtil.respJson(resp, jsonString);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mapProduct.deleteProduct(Integer.parseInt(req.getParameter("id")));
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(mapProduct.getProductMap());
        ServletUtil.respJson(resp, jsonString);
    }
}
