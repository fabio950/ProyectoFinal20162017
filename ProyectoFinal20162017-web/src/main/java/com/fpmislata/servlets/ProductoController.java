/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.servlets;

import com.fpmislata.domain.Categoria;
import com.fpmislata.domain.Cliente;
import com.fpmislata.domain.Producto;
import com.fpmislata.service.CategoriaServiceLocal;
import com.fpmislata.service.ClienteServiceLocal;
import com.fpmislata.service.ProductoServiceLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
@WebServlet(name = "ProductoController",
        loadOnStartup = 1,
        urlPatterns = {"/ListarProductos",
            "/AddProducto",
            "/DeleteProducto",
            "/UpdateProducto",
            "/ListarClientesPorProducto"})
public class ProductoController extends HttpServlet {

    @EJB
    private ClienteServiceLocal clienteService;

    @EJB
    private CategoriaServiceLocal categoriaService;

    @EJB
    private ProductoServiceLocal productoService;

    List lista;
    ArrayList<Producto> listPro;

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

        if (userPath.equals("/ListarProductos")) {
            listarProductos(request, response);
        } else if (userPath.equals("/AddProducto")) {
            altaProducto(request, response);
        } else if (userPath.equals("/DeleteProducto")) {
            eliminarProducto(request, response);
        } else if (userPath.equals("/UpdateProducto")) {
            modificarProducto(request, response);
        } else if (userPath.equals("/ListarClientesPorProducto")) {
            listarClientesPorProducto(request, response);
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

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) {
        try {
            lista = productoService.listProductos();
            listPro = new ArrayList<>(lista);

            List listarCat = categoriaService.listCategorias();
            ArrayList<Categoria> listCat = new ArrayList<>(listarCat);

            List lista2 = clienteService.listClientes();
            ArrayList<Cliente> listCli = new ArrayList<>(lista2);
            
            request.getSession().setAttribute("listaCategorias", listCat);
            request.getSession().setAttribute("listaProductos", listPro);
            request.getSession().setAttribute("listaClientes", listCli);
            RequestDispatcher rd = request.getRequestDispatcher("/listarProductos.jsp");

            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void altaProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nombre = request.getParameter("nombre");
            Float precio = Float.parseFloat(request.getParameter("precio"));
            int idCat = Integer.parseInt(request.getParameter("categoria"));
            String[] clientes = request.getParameterValues("clientes");
            
            Producto p = new Producto();
            p.setNombre(nombre);
            p.setPrecio(precio);

            Categoria c = new Categoria();
            c.setId(idCat);
            c = categoriaService.findCategoriaById(c);

            p.setCategoria(c);

            productoService.addProducto(p);
            
            if(clientes != null) {
                Cliente cli;
                for(String idCli : clientes) {
                    cli = new Cliente();
                    int idCliente = Integer.parseInt(idCli);
                    cli.setId(idCliente);
                    cli = clienteService.findClienteById(cli);
                    cli.getProductos().add(p);
                    p.getClientes().add(cli);
                    
                    clienteService.updateCliente(cli);
                }
            }

            lista = productoService.listProductos();
            listPro = new ArrayList<>(lista);

            request.getSession().setAttribute("listaProductos", listPro);
            RequestDispatcher rd = request.getRequestDispatcher("/listarProductos.jsp");

            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //No funciona bien del todo por la relacion del ManyToMany :(
    private void modificarProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            String accion = request.getParameter("accion");
            
            Producto p = new Producto();
            
            if(accion.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                
                p.setId(id);
                
                p = productoService.findProductoById(p);
                
                request.setAttribute("p", p);
                request.getRequestDispatcher("/actualizarProducto.jsp").forward(request, response);
            } else if (accion.equals("actualizar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                Float precio = Float.parseFloat(request.getParameter("precio"));
                int idCat = Integer.parseInt(request.getParameter("categoria"));
                
                p.setId(id);
                
                p = productoService.findProductoById(p);
                
                p.setNombre(nombre);
                p.setPrecio(precio);
                
                Categoria cat = new Categoria();
                cat.setId(idCat);
                cat = categoriaService.findCategoriaById(cat);
                Categoria catAnt = p.getCategoria();
                p.getCategoria().getProductos().remove(p);
                p.setCategoria(cat);
                cat.getProductos().add(p);
                
                productoService.updateProducto(p);
                categoriaService.updateCategoria(catAnt);
                categoriaService.updateCategoria(cat);
                
                listarProductos(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            Producto p = new Producto();
            p.setId(id);
            
            p = productoService.findProductoById(p);
            
            Categoria cat = p.getCategoria();
            p.getCategoria().getProductos().remove(p);
            
            Set listaC = p.getClientes();
            ArrayList<Cliente> clientes = new ArrayList<>(listaC);
            
            for(Cliente c : clientes) {
                c.getProductos().remove(p);
                clienteService.updateCliente(c);
            }
            
            productoService.deleteProducto(p);
            
            categoriaService.updateCategoria(cat);
            
            listarProductos(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void listarClientesPorProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            Producto p = new Producto();
            p.setId(id);
            p = productoService.findProductoById(p);
            
            ArrayList<Cliente> listCli = new ArrayList<>(p.getClientes());
            
            request.getSession().setAttribute("listaClientes", listCli);
            RequestDispatcher rd = request.getRequestDispatcher("/listarClientes.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}