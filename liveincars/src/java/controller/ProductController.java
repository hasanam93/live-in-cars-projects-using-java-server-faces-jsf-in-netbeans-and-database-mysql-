/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import javax.faces.bean.ManagedBean;
import entity.*;
import model.*;
import java.util.*;
@ManagedBean(name="proContrl")
/**
 *
 * @author Student
 */
public class ProductController {
 private List<Products> lst=new ArrayList<Products>();   

    public List<Products> getLst() {
        product_dao dao=new product_dao();
        return dao.getAll();
        
    }

    public void setLst(List<Products> lst) {
        this.lst = lst;
    }
 
private String keyword;
public String getKeyword()
{
    return keyword;
}
public void setKeyword(String keyword)
{
    this.keyword=keyword;
}
public String search()
{
    product_dao dao=new product_dao();
    this.lst=dao.search(keyword);
    return "index";
}
}
