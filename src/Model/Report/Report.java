/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Report;

import Helper.PDFHelper;
import Model.Products.Products;
import Model.Feedback.Feedback;
import Model.Orders.Orders;
import Model.OrderItems.OrderItems;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Desktop;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GJH
 */
public class Report {
//https://www.baeldung.com/java-pdf-creation
    DecimalFormat df = new DecimalFormat("###.##");
    String absolutePath = new File("").getAbsolutePath()+"\\";
    PDFHelper help = new PDFHelper();
    Paragraph hr = new Paragraph(" ");
    Font headerFont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    LocalDate now = LocalDate.now();
    public Report() {
    }
    public String getThisPath() {
        return absolutePath;
    }
    public String generateOrderItemReport() {
        Document document = new Document();
        String fileName = "Order_" + now + ".pdf";
        Double totalAmount = 0.0;
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            document.add(new Chunk("Order Report", headerFont));
            document.add(hr);

            List<String> fromFile = new OrderItems().getReader().getFromFile();
            System.out.println(fromFile);
            PdfPTable table = new PdfPTable(fromFile.get(0).split(",").length);
            ArrayList<String> newFromFile = new ArrayList<String>();
            for (int j = 0; j < fromFile.size(); j++) {
                String[] split = fromFile.get(j).split(",");
                if (!Boolean.parseBoolean(split[6])) {
                    ArrayList<String> list = new ArrayList<String>();
                    if (j == 0) {
                        list.add(split[0]);
                        list.add(split[1]);
                        list.add(split[2]);
                        list.add(split[3]);
                        list.add(split[4]);
                        list.add(split[5]);
                        list.add("amount");
                        String result =  String.join(",", list);
                        newFromFile.add(result);
                    } else {
                        Products product = new Products().where("id", split[1]);
                        Double amount = Integer.parseInt(split[2]) * product.getPrice();
                        totalAmount = totalAmount + amount;
                        list.add(split[0]);
                        list.add(product.getName());
                        list.add(split[2]);
                        list.add(split[3]);
                        list.add(split[4]);
                        list.add(split[5]);
                        list.add(df.format(amount));
                        String result =  String.join(",", list);
                        newFromFile.add(result);
  
                    }
                }
            }
            ArrayList<String> rowTotalAmount = new ArrayList<>();
            rowTotalAmount.add("Total Amount");
            rowTotalAmount.add("");
            rowTotalAmount.add("");
            rowTotalAmount.add("");
            rowTotalAmount.add("");
            rowTotalAmount.add("");
            rowTotalAmount.add(df.format(totalAmount));
            String result =  String.join(",", rowTotalAmount);
            newFromFile.add(result); 
                  System.out.println(newFromFile);
      
            help.addTableHeader(table, newFromFile);
            help.addRows(table, newFromFile);
            document.add(table);

            
            document.close();
            writer.close();
            Desktop.getDesktop().open(new File(fileName));
            
            return absolutePath + fileName;
        } catch (IOException | DocumentException e) {
            System.out.println(e.getMessage());
            return "Error";
        }
    }
    public String generateProductReport() {
        Document document = new Document();
        String fileName = "Product_" + now + ".pdf";
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            document.add(new Chunk("Product Report", headerFont));
            document.add(hr);

            List<String> fromFile = new Products().getReader().getFromFile();
            PdfPTable table = new PdfPTable(fromFile.get(0).split(",").length -1);
            ArrayList<String> newFromFile = new ArrayList<String>();
            for (int j = 0; j < fromFile.size(); j++) {
                String[] split = fromFile.get(j).split(",");
                if (!Boolean.parseBoolean(split[7])) {
                    ArrayList<String> list = new ArrayList<String>();
                    if (j == 0) {
                        list.add(split[0]);
                        list.add(split[1]);
                        list.add(split[2]);
                        list.add(split[3]);
                        list.add(split[4]);
                        list.add(split[5]);
                        list.add(split[6]);
                        String result =  String.join(",", list);
                        newFromFile.add(result);
                    } else {
                        list.add(split[0]);
                        list.add(split[1]);
                        list.add(split[2]);
                        list.add(split[3]);
                        list.add(split[4]);
                        list.add(split[5]);
                        list.add(split[6]);
                        String result =  String.join(",", list);
                        newFromFile.add(result);
  
                    }
                }
            }
            
            help.addTableHeader(table, newFromFile);
            help.addRows(table, newFromFile);
            document.add(table);

            document.close();
            writer.close();
            Desktop.getDesktop().open(new File(fileName));

            return fileName;
        } catch (IOException | DocumentException e) {
            System.out.println(e.getMessage());
            return "Error";
        }
    }
    public String generateReceipt(Orders order) {
        Document document = new Document();
        String name = order.getUser().getName();
        Double totalAmount = order.getTotalAmount();
        int id = order.getID();
        ArrayList<OrderItems> orderItems = new OrderItems().getAllByOrder(Integer.toString(id));
        String fileName = "Order_" + id + ".pdf";

        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            document.add(new Chunk("Order " + id, headerFont));
            document.add(hr);
            document.add(new Chunk("Customer : " + name, headerFont));
            document.add(hr);
            document.add(new Chunk("Total Amount :" + totalAmount, headerFont));
            document.add(hr);

            PdfPTable table = new PdfPTable(3);
            ArrayList<String> oiList = new ArrayList<String>();
            ArrayList<String> headerList = new ArrayList<>();
            headerList.add("Product");
            headerList.add("Price");
            headerList.add("Quantity");
            String headerString =  String.join(",", headerList);
            oiList.add(headerString); 
            
            for (int j = 0; j < orderItems.size(); j++) {
                OrderItems oi = orderItems.get(j);
                    ArrayList<String> list = new ArrayList<String>();
                        list.add(oi.getProduct().getName());
                        list.add(Double.toString(oi.getProduct().getPrice()));
                        list.add(Integer.toString(oi.getQuantity()));
                        String result =  String.join(",", list);
                        oiList.add(result);                    
            }
            help.addTableHeader(table, oiList);
            help.addRows(table, oiList);
            document.add(table);

            document.close();
            writer.close();
            Desktop.getDesktop().open(new File(fileName));
            
        return  absolutePath + fileName;
        } catch (IOException | DocumentException e) {
            System.out.println(e.getMessage());
            return "Error";
        }
    }
}
