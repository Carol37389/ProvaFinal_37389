package com.fiec.provafinal;


import com.fiec.provafinal.Models.Sapato;
import com.fiec.provafinal.Models.SapatoRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/sapato")
public class SapatoServlet extends HttpServlet {

    private SapatoRepositorio sapatoRepositorio;

    public SapatoServlet(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("minhaUnidadeDePersistencia");
        EntityManager em = emf.createEntityManager();
        this.sapatoRepositorio = new SapatoRepositorio(em);
    }

    // Create  /produtos
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String preco = request.getParameter("preco");
        String imagem = request.getParameter("imagem");
        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        String tamanho = request.getParameter("tamanho");
        Sapato p = Sapato.builder()
                .nome(nome)
                .preco(Double.valueOf(preco))
                .imagem(imagem)
                .Marca(marca)
                .tamanho(Integer.valueOf(tamanho))
                .build();
        this.sapatoRepositorio.criar(p);
        response.setContentType("text/html");
        response.getWriter().println("Sapato Salvo");

    }
    // Read  /Sapato
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Sapato> sapatos = this.sapatoRepositorio.ler();
        response.setContentType("text/html");
        System.out.println(sapatos);
        response.setContentType("text/html");
        response.getWriter().println(sapatos.stream().map(Sapato::toString).collect(Collectors.toList()));

    }
    // Update   /Sapato/:id
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = getId(request);
        String preco = request.getParameter("preco");
        String imagem = request.getParameter("imagem");
        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        String tamanho = request.getParameter("tamanho");
        Sapato p = Sapato.builder()
                .nome(nome)
                .preco(Double.valueOf(preco))
                .imagem(imagem)
                .Marca(marca)
                .tamanho(Integer.valueOf(tamanho))
                .build();
        this.sapatoRepositorio.atualiza(p, id);
        response.setContentType("text/html");
        response.getWriter().println("Sapato Atualizado");

    }
    // Delete  /Sapato/:id
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = getId(request);
        this.sapatoRepositorio.deleta(id);
        response.setContentType("text/html");
        response.getWriter().println("Sapato Deletado");
    }

    private static String getId(HttpServletRequest req){
        String path = req.getPathInfo();
        String[] paths = path.split("/");
        String id = paths[paths.length - 1];
        return id;
    }

}