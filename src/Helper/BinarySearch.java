/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.List;

/**
 *
 * @author GJH
 */
public class BinarySearch {
    public String[] bsearch(List<String> fromFile, int first, int last, int id, int searchByColumn) {
            if (last >= first) {
                int mid = first + (last - first)/2;
                String[] split = fromFile.get(mid).split(",");
                if (Integer.parseInt(split[searchByColumn]) == id){  
                    return split;  
                } 
                if (Integer.parseInt(split[searchByColumn]) > id){  
                    return bsearch(fromFile, first, mid-1, id, searchByColumn);//search in left subarray  
                }else{  
                    return bsearch(fromFile, mid+1, last, id, searchByColumn);//search in right subarray  
                }  
            }
            return null;
    }
}
