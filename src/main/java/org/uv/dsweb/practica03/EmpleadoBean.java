/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.uv.dsweb.practica03;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ian
 */

@ManagedBean
@Named(value = "EmpleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {
    
    private final String faltanDatos;
    private final String idIncorrecto;
    private final String empleadoCreado;
    private final String empleadoActualizado;
    private final String empleadoEliminado;
    private final String noHayEmpleados;
    private final String empleadoNoEncontrado;
    private final String warnTitle;
    private final String infoTitle;
    
    private EmpleadoEntity empleado = new EmpleadoEntity();
    private final EmpleadoDAO empleadoDao = new EmpleadoDAO();
    private List<EmpleadoEntity> empleadoList = new ArrayList<>();
    private long empleadoId;

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }
    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    public long getEmpleadoId() {
        return empleadoId;
    }
    public void setEmpleadoId(long EmpleadoId) {
        this.empleadoId = EmpleadoId;
    }

    public List<EmpleadoEntity> getEmpleadoList() {
        return empleadoList;
    }
    public void setEmpleadoList(List<EmpleadoEntity> empleadoList) {
        this.empleadoList = empleadoList;
    }
    
    
    
    public void guardarEmpleado(){
        if(empleado.getName().isEmpty() || empleado.getAddress().isEmpty() || empleado.getPhone().isEmpty()){
            addMessage(FacesMessage.SEVERITY_WARN, faltanDatos, warnTitle);
        }
        else{ 
            boolean response = empleadoDao.save(empleado);
            if(response){
                empleado = new EmpleadoEntity();
                addMessage(FacesMessage.SEVERITY_INFO, empleadoCreado, infoTitle);
            }
        }
    }
    
    public void eliminarEmpleado(){
        if(empleadoId<=0){
            addMessage(FacesMessage.SEVERITY_WARN, idIncorrecto, warnTitle);
        }
        else{
            boolean response = empleadoDao.delete(empleadoId);
            if(response){
                empleado = new EmpleadoEntity();
                addMessage(FacesMessage.SEVERITY_INFO, empleadoEliminado, infoTitle);
            }
        }
    }
    
    public void actualizarEmpleado(){
        if(empleado.getName().isEmpty() || empleado.getAddress().isEmpty() || empleado.getPhone().isEmpty()){
            addMessage(FacesMessage.SEVERITY_WARN, faltanDatos, warnTitle);
        }
        else if(empleadoId<=0){
            addMessage(FacesMessage.SEVERITY_WARN, idIncorrecto, warnTitle);
        }
        else{
            boolean response = empleadoDao.update(empleado, empleadoId);
            if(response){
                empleado = new EmpleadoEntity();
                addMessage(FacesMessage.SEVERITY_INFO, empleadoActualizado, infoTitle);
            }
        }
    }
    
    public void mostrarEmpleado(){
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
    
    public EmpleadoBean() {
        this.empleadoCreado = "El empleado ha sido creado";
        this.faltanDatos = "Los datos están incompletos";
        this.idIncorrecto = "El ID es incorrecto";
        this.empleadoActualizado = "El empleado ha sido actualizado";
        this.empleadoEliminado = "El empleado ha sido eliminado";
        this.noHayEmpleados = "No existen empleados existentes";
        this.warnTitle = "Datos incorrectos";
        this.infoTitle = "Tarea exitosa";
        this.empleadoNoEncontrado = "El empleado no existe";
    }
    
}
