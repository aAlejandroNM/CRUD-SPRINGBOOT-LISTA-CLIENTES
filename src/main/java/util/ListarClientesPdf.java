package util;

import models.entity.Cliente;
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

        /* FUENTES TAMANOS COLORES */
        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA,16,Color.BLACK);
        Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,Color.BLACK);
        Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER,10,Color.BLACK);

        celda = new PdfPCell(new Phrase("LISTADO GENERAL DE CLIENTES",fuenteTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(62,73,232));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(20);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(25);

        PdfPTable tablaClientes = new PdfPTable(6);
        tablaClientes.setWidths(new float[]{0.8f,2f,2f,1.5f,3.5f,1.5f});

        celda = new PdfPCell(new Phrase("ID",fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("NOMBRES",fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("APELLIDOS",fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("TELEFONO",fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("EMAIL",fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("CIUDAD",fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaClientes.addCell(celda);

        for (Cliente cliente : listadoClientes){
            celda = new PdfPCell(new Phrase(cliente.getId().toString(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

            celda = new PdfPCell(new Phrase(cliente.getNombres(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

            celda = new PdfPCell(new Phrase(cliente.getApellidos(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

            celda = new PdfPCell(new Phrase(cliente.getTelefono(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

            celda = new PdfPCell(new Phrase(cliente.getEmail(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);

            celda = new PdfPCell(new Phrase(cliente.getCiudad().getCiudad(),fuenteDataCeldas));
            celda.setPadding(5);
            tablaClientes.addCell(celda);
        }
        /*
        listadoClientes.forEach(cliente -> {
            tablaClientes.addCell(cliente.getId().toString());
            tablaClientes.addCell(cliente.getNombres());
            tablaClientes.addCell(cliente.getApellidos());
            tablaClientes.addCell(cliente.getTelefono());
            tablaClientes.addCell(cliente.getEmail());
            tablaClientes.addCell(cliente.getCiudad().getCiudad());
        });
        Agregar tablas al documento*/

        document.add(tablaTitulo);
        document.add(tablaClientes);
    }
}
