/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.form728;

import hcd.ca.gov.form728.util.JsfUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author Saras
 */
@Named("fileUploadController2")
@SessionScoped
public class FileUploadController2 implements Serializable {
    
    public void upload(FileUploadEvent event) {
        try {
            List<NonItGoodsLineitem> excelLineItemList = new ArrayList<>();
            UploadedFile uploadedFile = event.getFile();
            String fileName = uploadedFile.getFileName();
            String contentType = uploadedFile.getContentType();
            //byte[] contents = uploadedFile.getContents(); 
            System.out.println("File name: "+fileName+" and content type: "+contentType);
            InputStream inputStream = uploadedFile.getInputstream();
            Workbook workbook = new XSSFWorkbook(inputStream);
 
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            
            int count = 0;
             
            rowIterator.next(); // skip the header row
             
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                NonItGoodsLineitem newLineItem = new NonItGoodsLineitem();
 
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
 
                    int columnIndex = nextCell.getColumnIndex();
 
                    switch (columnIndex) {
                    case 0:
                        Date dateReceived = nextCell.getDateCellValue();
                        newLineItem.setDateReceived(dateReceived);
                        break;                    
                    case 1:
                        int itemNo = (int) nextCell.getNumericCellValue();
                        newLineItem.setLineItemNo(itemNo);
                        break;
                    case 2:
                        String itemDesc = nextCell.getStringCellValue();
                        newLineItem.setItemDesc(itemDesc);
                        break;                    
                    case 3:
                        BigInteger modelNo = BigInteger.valueOf((long) nextCell.getNumericCellValue());
                        newLineItem.setModelNo(modelNo.toString());
                        break;                    
                    case 4:
                        BigInteger decalNo = BigInteger.valueOf((long) nextCell.getNumericCellValue());
                        newLineItem.setDecalNo(decalNo.toString());
                        break;                    
                    } 
                }
                excelLineItemList.add(newLineItem);
            } 
            workbook.close();
            if(inputStream!=null) inputStream.close();
            if(!excelLineItemList.isEmpty()) {
                System.out.println(excelLineItemList.size()+" line items fetched from Excel sheet");
                JsfUtil.addSuccessMessage(excelLineItemList.size()+" line items fetched from Excel sheet");
                addLineItems(excelLineItemList);
            }
            else System.out.println("Rows could not get fetched from Excel sheet! Check file and data inside!");
        }
        catch (IOException ex1) {
            System.out.println("Error reading file");
            ex1.printStackTrace();
            JsfUtil.addErrorMessage(ex1, "Error reading Excel file, check server logs for more details");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JsfUtil.addErrorMessage(ex, "Exception, check server logs for more details");
        }
        
    }
    
    private void addLineItems(List<NonItGoodsLineitem> excelLineItemList) {
        try{
            FacesContext facesContext = FacesContext.getCurrentInstance();
            NonItGoodsController nonItGoodsController = (NonItGoodsController) 
                    facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nonItGoodsController");
                    //facesContext.getExternalContext().getSessionMap().get("itGoodsLineitemController");
            nonItGoodsController.setLineItemList(excelLineItemList);
            //itGoodsController.create();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
