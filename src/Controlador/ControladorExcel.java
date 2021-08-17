/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
/**
 *
 * @author Auxiliar
 */
public class ControladorExcel {

    public static void exportarExcelSMS(JTable t, String fecha1, String fecha2) throws IOException {

        try {

            String ruta = "C:\\Prueba2\\ModuloEnvio_SMS " + fecha1 + " hasta " + fecha2 + ".xls";
            File archivoXLS = new File(ruta);
            if (!archivoXLS.exists()) {

                archivoXLS.createNewFile();
            }

            Workbook libro = new HSSFWorkbook();
            CellStyle cellStyle = libro.createCellStyle();
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            FileOutputStream archivo = new FileOutputStream(archivoXLS);
            org.apache.poi.ss.usermodel.Sheet hoja = libro.createSheet(fecha1 + "hasta " + fecha2);
            hoja.setColumnWidth(0, 4000);
            hoja.setColumnWidth(1, 4500);
            hoja.setColumnWidth(2, 12000);
            hoja.setColumnWidth(3, 4000);
            hoja.setColumnWidth(4, 12000);

            for (int f = 0; f < t.getRowCount(); f++) {
                Row fila = hoja.createRow(f);
                for (int c = 0; c < t.getColumnCount(); c++) {
                    Cell celda = fila.createCell(c);
                    if (f == 0) {
                        celda.setCellValue(t.getColumnName(c).toUpperCase());
                        celda.setCellStyle(cellStyle);
                    }
                }
            }
            int filaInicio = 1;
            for (int f = 0; f < t.getRowCount(); f++) {
                Row fila = hoja.createRow(filaInicio);
                filaInicio++;
                for (int c = 0; c < t.getColumnCount(); c++) {
                    Cell celda = fila.createCell(c);
                    if (t.getValueAt(f, c) instanceof Double) {
                        celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                        celda.setCellStyle(cellStyle);
                    } else if (t.getValueAt(f, c) instanceof Float) {
                        celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
                        celda.setCellStyle(cellStyle);
                    } else {
                        celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                        celda.setCellStyle(cellStyle);
                    }
                }
            }

            Row fila = hoja.createRow(filaInicio + 1);
            Cell celda = fila.createCell(0);
            int cont = filaInicio - 1;
            celda.setCellValue("Enviados: " + cont);
            celda.setCellStyle(cellStyle);

            libro.write(archivo);
            archivo.close();
            Desktop.getDesktop().open(archivoXLS);
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Archivo usado por otro proceso");
            throw e;
        }
        //}
    }

    public static void exportarExcelCorreo(JTable t, String fecha1, String fecha2) throws IOException {

        try {

            String ruta = "C:\\Prueba2\\ModuloEnvio_CORREO " + fecha1 + " hasta " + fecha2 + ".xls";
            File archivoXLS = new File(ruta);
            if (!archivoXLS.exists()) {

                archivoXLS.createNewFile();
            }

            Workbook libro = new HSSFWorkbook();
            CellStyle cellStyle = libro.createCellStyle();
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            FileOutputStream archivo = new FileOutputStream(archivoXLS);
            org.apache.poi.ss.usermodel.Sheet hoja = libro.createSheet(fecha1 + "hasta " + fecha2);
            hoja.setColumnWidth(0, 4000);
            hoja.setColumnWidth(1, 4500);
            hoja.setColumnWidth(2, 12000);
            hoja.setColumnWidth(3, 4000);
            hoja.setColumnWidth(4, 12000);

            hoja.setDisplayGridlines(false);
            for (int f = 0; f < t.getRowCount(); f++) {
                Row fila = hoja.createRow(f);
                for (int c = 0; c < t.getColumnCount(); c++) {
                    Cell celda = fila.createCell(c);
                    if (f == 0) {
                        celda.setCellValue(t.getColumnName(c).toUpperCase());
                        celda.setCellStyle(cellStyle);
                    }
                }
            }
            int filaInicio = 1;
            for (int f = 0; f < t.getRowCount(); f++) {
                Row fila = hoja.createRow(filaInicio);
                filaInicio++;
                for (int c = 0; c < t.getColumnCount(); c++) {
                    Cell celda = fila.createCell(c);
                    if (t.getValueAt(f, c) instanceof Double) {
                        celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                        celda.setCellStyle(cellStyle);
                    } else if (t.getValueAt(f, c) instanceof Float) {
                        celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
                        celda.setCellStyle(cellStyle);
                    } else {
                        celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                        celda.setCellStyle(cellStyle);
                    }
                }
            }

            Row fila = hoja.createRow(filaInicio + 1);
            Cell celda = fila.createCell(0);
            int cont = filaInicio - 1;
            celda.setCellValue("Enviados: " + cont);
            celda.setCellStyle(cellStyle);

            libro.write(archivo);
            archivo.close();
            Desktop.getDesktop().open(archivoXLS);
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Archivo usado por otro proceso " + e);
            throw e;
        }
        //}
    }
}
