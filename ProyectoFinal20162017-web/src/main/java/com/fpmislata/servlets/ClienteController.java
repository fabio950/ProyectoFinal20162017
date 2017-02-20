/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.servlets;

import com.fpmislata.domain.Categoria;
import com.fpmislata.domain.Cliente;
import com.fpmislata.domain.Direccion;
import com.fpmislata.domain.Producto;
import com.fpmislata.service.ClienteServiceLocal;
import com.fpmislata.service.ProductoServiceLocal;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ClienteController",
        loadOnStartup = 1,
        urlPatterns = {"/ListarClientes",
            "/AddCliente",
            "/DeleteCliente",
            "/UpdateCliente",
            "/ListarProductosPorCliente"})
public class ClienteController extends HttpServlet {

    @EJB
    private ProductoServiceLocal productoService;

    @EJB
    private ClienteServiceLocal clienteService;

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

        // Si la operacion es listar personas
        if (userPath.equals("/ListarClientes")) {
            listarClientes(request, response);
        } else if (userPath.equals("/AddCliente")) {
            altaCliente(request, response);
        } else if (userPath.equals("/DeleteCliente")) {
            eliminarCliente(request, response);
        } else if (userPath.equals("/UpdateCliente")) {
            modificarCliente(request, response);
        } else if (userPath.equals("/ListarProductosPorCliente")) {
            listarProductosPorCliente(request, response);
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

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) {
        try {
            List lista = clienteService.listClientes();
            ArrayList<Cliente> listCli = new ArrayList<>(lista);

            request.getSession().setAttribute("listaClientes", listCli);
            RequestDispatcher rd = request.getRequestDispatcher("/listarClientes.jsp");

            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void altaCliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String nif = request.getParameter("nif");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String poblacion = request.getParameter("poblacion");
            String provincia = request.getParameter("provincia");
            String cp = request.getParameter("cp");

            Cliente c = new Cliente();
            c.setNombre(nombre);
            c.setApellidos(apellidos);
            c.setNif(nif);
            c.setTelefono(telefono);

            Direccion d = new Direccion();
            d.setDireccion(direccion);
            d.setPoblacion(poblacion);
            d.setProvincia(provincia);
            d.setCodigopostal(cp);

            c.setDireccion(d);

            clienteService.addCliente(c);

            listarClientes(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            String accion = request.getParameter("accion");

            Cliente c = new Cliente();

            if (accion.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));

                c.setId(id);

                c = clienteService.findClienteById(c);

                request.setAttribute("c", c);
                request.getRequestDispatcher("/actualizarCliente.jsp").forward(request, response);
            } else if (accion.equals("actualizar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");
                String nif = request.getParameter("nif");
                String telefono = request.getParameter("telefono");
                String direccion = request.getParameter("direccion");
                String poblacion = request.getParameter("poblacion");
                String provincia = request.getParameter("provincia");
                String cp = request.getParameter("cp");

                c.setId(id);
                c = clienteService.findClienteById(c);

                c.setNombre(nombre);
                c.setApellidos(apellidos);
                c.setNif(nif);
                c.setTelefono(telefono);

                Direccion d = new Direccion();
                d.setDireccion(direccion);
                d.setPoblacion(poblacion);
                d.setProvincia(provincia);
                d.setCodigopostal(cp);

                c.setDireccion(d);
                
                clienteService.updateCliente(c);
                
                listarClientes(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Cliente c = new Cliente();
            c.setId(id);

            clienteService.deleteCliente(c);

            listarClientes(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarProductosPorCliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            Cliente c = new Cliente();
            c.setId(id);
            c = clienteService.findClienteById(c);
            
            ArrayList<Producto> listPro = new ArrayList<>(c.getProductos());
            
            request.getSession().setAttribute("listaProductos", listPro);
            RequestDispatcher rd = request.getRequestDispatcher("/listarProductos.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
