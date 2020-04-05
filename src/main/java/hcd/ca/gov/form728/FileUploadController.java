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
@Named("fileUploadController")
@SessionScoped
public class FileUploadController implements Serializable {
    
    public void upload(FileUploadEvent event) {
        try {
            List<ItGoodsLineitem> excelLineItemList = new ArrayList<>();
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
                ItGoodsLineitem newLineItem = new ItGoodsLineitem();
 
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
 
                    int columnIndex = nextCell.getColumnIndex();
 
                    switch (columnIndex) {
                    case 0:
                        Date dateReceived = nextCell.getDateCellValue();
                        newLineItem.setDateReceived(dateReceived);
                        break;                    
                    case 1:
                        String itemDesc = nextCell.getStringCellValue();
                        newLineItem.setItemDesc(itemDesc);
                        break;
                    case 2:
                        String serialNum = nextCell.getStringCellValue();
                        newLineItem.setSerialNum(serialNum);
                        break;                    
                    /*
                    case 3:
                        BigInteger decalNo = BigInteger.valueOf((long) nextCell.getNumericCellValue());
                        newLineItem.setDecalNo(decalNo.toString());
                        break;
                    */
                    case 3:
                        String decalNo =  nextCell.getStringCellValue();
                        newLineItem.setDecalNo(decalNo);
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
    
    private void addLineItems(List<ItGoodsLineitem> excelLineItemList) {
        try{
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ItGoodsController itGoodsController = (ItGoodsController) 
                    facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itGoodsController");
                    //facesContext.getExternalContext().getSessionMap().get("itGoodsLineitemController");
            itGoodsController.setLineItemList(excelLineItemList);
            //itGoodsController.create();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
