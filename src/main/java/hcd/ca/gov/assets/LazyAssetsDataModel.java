/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.assets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author santosh
 */
public class LazyAssetsDataModel extends LazyDataModel<Assets> {
 private List<Assets> datasource;
 
     public LazyAssetsDataModel(){
         
     }
    
    public LazyAssetsDataModel(List<Assets> datasource) {
        this.datasource = datasource;
    }
 
    @Override
    public Assets getRowData(String rowKey) {
        for (Assets car : datasource) {
            if (car.getId().equals(rowKey)) {
                return car;
            }
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(Assets car) {
        return car.getId();
    }
 
    public List<Assets> load(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
        List<Assets> data = new ArrayList<>();

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}