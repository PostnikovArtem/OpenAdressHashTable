/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openadresshashtable;

/**
 *
 * @author Admin
 */
public class OpenAddressHashTable_main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable(1000);
        
        table.put("number", 1232);
    }
    
}
