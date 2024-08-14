package com.cliente.app.spring_boot_clienteapp.util;

import com.cliente.app.spring_boot_clienteapp.models.entity.Cliente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.util.List;
import java.util.Map;

@Component("/views/clientes/listar.xlsx")
public class ListarClientesExcel extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition","attachment; filename=\"listado-clientes.xlsx\"");
        Sheet hoja = workbook.createSheet("Clientes");

        Row filaTitulo = hoja.createRow(0);
        Cell celda = filaTitulo.createCell(0);
        celda.setCellValue("LISTADO GENERAL DE CLIENTES");

        Row filaData = hoja.createRow(2);
        String[] columnas = {"ID", "NOMBRES","APELLIDOS","TELEFONO","CORREO","CIUDAD"};
        for (int  i = 0;  i < columnas.length ;  i++) {
            celda = filaData.createCell(i);
            celda.setCellValue(columnas[i]);
        }
        List<Cliente> listaC = (List<Cliente>) model.get("clientes");

        int numFila =3;
        for (Cliente cliente : listaC){
            filaData = hoja.createRow(numFila);

            filaData.createCell(0).setCellValue(cliente.getId());
            filaData.createCell(1).setCellValue(cliente.getNombres());
            filaData.createCell(2).setCellValue(cliente.getApellidos());
            filaData.createCell(3).setCellValue(cliente.getTelefono());
            filaData.createCell(4).setCellValue(cliente.getEmail());
            filaData.createCell(5).setCellValue(cliente.getCiudad().getCiudad());

            numFila ++;
        }


    }
}
