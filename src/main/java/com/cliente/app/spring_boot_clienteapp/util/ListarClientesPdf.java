package com.cliente.app.spring_boot_clienteapp.util;

import com.cliente.app.spring_boot_clienteapp.models.entity.Cliente;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("/views/clientes/listar")
public class ListarClientesPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked")
        List<Cliente> listadoClientes = (List<Cliente>) model.get("clientes");

        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(-20,-20,40,20);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        PdfPCell celda = null;

        Font fuenteTitulo = FontFactory.getFont("Helvetica",16,Color.BLACK);
        celda = new PdfPCell(new Phrase("LISTADO GENERAL DE CLIENTES",fuenteTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(62,73,232));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(20);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(25);

        PdfPTable tablaClientes = new PdfPTable(6);

        listadoClientes.forEach(cliente -> {
            tablaClientes.addCell(cliente.getId().toString());
            tablaClientes.addCell(cliente.getNombres());
            tablaClientes.addCell(cliente.getApellidos());
            tablaClientes.addCell(cliente.getTelefono());
            tablaClientes.addCell(cliente.getEmail());
            tablaClientes.addCell(cliente.getCiudad().getCiudad());
        });
        document.add(tablaTitulo);
        document.add(tablaClientes);
    }
}
