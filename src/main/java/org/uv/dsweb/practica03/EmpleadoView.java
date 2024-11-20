/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.uv.dsweb.practica03;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author ian
 */

@Named(value = "EmpleadoView")
@ViewScoped
public class EmpleadoView implements Serializable {

    private final EmpleadoDAO empleadoDao = new EmpleadoDAO();;
    
    private List<EmpleadoEntity> empleadoList;

    public List<EmpleadoEntity> getEmpleadoList() {
        empleadoList = empleadoDao.findAll();
        return empleadoList;
    }
    public void setEmpleadoList(List<EmpleadoEntity> empleadoList) {
        this.empleadoList = empleadoList;
    }
    
    @PostConstruct
    public void init(){
        empleadoList = empleadoDao.findAll();
    }
    
    public EmpleadoView() {
    }
    
    
}
