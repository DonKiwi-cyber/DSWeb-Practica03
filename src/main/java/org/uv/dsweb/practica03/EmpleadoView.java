/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.uv.dsweb.practica03;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ian
 */

@Named(value = "EmpleadoView")
@ViewScoped
public class EmpleadoView implements Serializable {
    
    private final String idIncorrecto;
    private final String noHayEmpleados;
    private final String empleadoNoEncontrado;
    private final String warnTitle;

    private EmpleadoDAO empleadoDao;
    private List<EmpleadoEntity> empleadoList;

    public EmpleadoDAO getEmpleadoDao() {
        return empleadoDao;
    }
    public void setEmpleadoDao(EmpleadoDAO empleadoDao) {
        this.empleadoDao = empleadoDao;
    }

    public List<EmpleadoEntity> getEmpleadoList() {
        return empleadoList;
    }
    public void setEmpleadoList(List<EmpleadoEntity> empleadoList) {
        this.empleadoList = empleadoList;
    }
    
    @PostConstruct
    public void init(){
        empleadoDao = new EmpleadoDAO();
        empleadoList = new ArrayList<>();
    }
    
    public void mostrarEmpleado(long empleadoId){
        if(empleadoId<=0){
            addMessage(FacesMessage.SEVERITY_WARN, idIncorrecto, warnTitle);
        }
        else{
            EmpleadoEntity emp = empleadoDao.findByID(empleadoId);
            if(emp == null){
                addMessage(FacesMessage.SEVERITY_WARN, empleadoNoEncontrado, warnTitle);
            }
            else{
                empleadoList.clear();
                System.out.println(empleadoList.size());
                empleadoList.add(emp);
            }
        }
    }
    
    public void mostrarTodo(){
        empleadoList = empleadoDao.findAll();
        if(empleadoList==null){
            addMessage(FacesMessage.SEVERITY_WARN, noHayEmpleados, warnTitle);
        }
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public EmpleadoView() {
        this.idIncorrecto = "El ID es incorrecto";
        this.empleadoNoEncontrado = "El empleado no existe";
        this.noHayEmpleados = "No existen empleados existentes";
        this.warnTitle = "Datos incorrectos";
    }
    
    
}
