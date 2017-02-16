/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.servlets;

import com.fpmislata.domain.Categoria;
import com.fpmislata.domain.Producto;
import com.fpmislata.service.CategoriaServiceLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet(name = "CategoriaController",
        loadOnStartup = 1,
        urlPatterns = {"/ListarCategorias",
            "/AddCategoria",
            "/DeleteCategoria",
            "/UpdateCategoria",
            "/ListarProductosPorCategoria"})
public class CategoriaController extends HttpServlet {

    @EJB
    private CategoriaServiceLocal categoriaService;

    List lista;
    ArrayList<Categoria> listCat;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();

        if (userPath.equals("/ListarCategorias")) {
            listarCategorias(request, response);
        } else if (userPath.equals("/AddCategoria")) {
            altaCategoria(request, response);
            // Si la operacion es Eliminar Categoria
        } else if (userPath.equals("/DeleteCategoria")) {
            eliminarCategoria(request, response);
            // Si la operacion es Modificar Categoria
        } else if (userPath.equals("/UpdateCategoria")) {
            modificarCategoria(request, response);
        } else if (userPath.equals("/ListarProductosPorCategoria")) {
            listarProductosPorCategoria(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response) {
        try {
            lista = categoriaService.listCategorias();
            listCat = new ArrayList<>(lista);

            request.getSession().setAttribute("listaCategorias", listCat);
            RequestDispatcher rd = request.getRequestDispatcher("/listarCategorias.jsp");

            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void altaCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nombre = request.getParameter("nombre");

            Categoria c = new Categoria();
            c.setNombre(nombre);

            categoriaService.addCategoria(c);

            lista = categoriaService.listCategorias();
            listCat = new ArrayList<>(lista);

            request.getSession().setAttribute("listaCategorias", listCat);
            RequestDispatcher rd = request.getRequestDispatcher("/listarCategorias.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Categoria c = new Categoria();
            c.setId(id);

            categoriaService.deleteCategoria(c);

            lista = categoriaService.listCategorias();
            listCat = new ArrayList<>(lista);

            request.getSession().setAttribute("listaCategorias", listCat);
            RequestDispatcher rd = request.getRequestDispatcher("/listarCategorias.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modificarCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            String accion = request.getParameter("accion");
            
            Categoria c = new Categoria();
            
            if (accion.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                
                c.setId(id);
                c = categoriaService.findCategoriaById(c);
                
                request.setAttribute("c", c);
                request.getRequestDispatcher("/actualizarCategoria.jsp").forward(request, response);
            } else if (accion.equals("actualizar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");

                c.setId(id);
                c = categoriaService.findCategoriaById(c);
                c.setNombre(nombre);
                
                categoriaService.updateCategoria(c);

                lista = categoriaService.listCategorias();
                listCat = new ArrayList<>(lista);

                request.getSession().setAttribute("listaCategorias", listCat);
                RequestDispatcher rd = request.getRequestDispatcher("/listarCategorias.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarProductosPorCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            Categoria c = new Categoria();
            c.setId(id);
            c = categoriaService.findCategoriaById(c);
            
            ArrayList<Producto> listPro = new ArrayList<>(c.getProductos());
            
            request.getSession().setAttribute("listaProductos", listPro);
            RequestDispatcher rd = request.getRequestDispatcher("/listarProductos.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
