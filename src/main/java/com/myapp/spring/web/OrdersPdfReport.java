package com.myapp.spring.web;
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.myapp.spring.model.OrderEntity;
 
public class OrdersPdfReport {
 
    public static ByteArrayInputStream ordersReport(List<OrderEntity> orders) throws DocumentException {
 
        Document doc = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(60);
        table.setWidths(new int[] { 1, 2, 3, 4,5 });
 
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA);
        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Id", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
 
        hcell = new PdfPCell(new Phrase("TimeOrderPlaced", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
 
        hcell = new PdfPCell(new Phrase("OrderNumber", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
 
        hcell = new PdfPCell(new Phrase("LastUpdate", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
 
        hcell = new PdfPCell(new Phrase("OrderStatus", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
 
        orders.forEach(o -> {
            PdfPCell cell;
            cell = new PdfPCell(new Phrase(String.valueOf(o.getId()), headFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
           
            cell = new PdfPCell(new Phrase(String.valueOf(o.getTimeOrderPlaced()), headFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
           
            cell = new PdfPCell(new Phrase(String.valueOf(o.getOrderNumber()), headFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
           
            cell = new PdfPCell(new Phrase(String.valueOf(o.getLastUpdate()), headFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
           
            cell = new PdfPCell(new Phrase(String.valueOf(o.getStatus()), headFont));
            cell.setPaddingRight(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        });
 
        PdfWriter.getInstance(doc, out);
        doc.open();
        doc.add(table);
        doc.close();
        return new ByteArrayInputStream(out.toByteArray());
 
    }
}