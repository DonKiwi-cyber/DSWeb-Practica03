/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dsweb.practica03;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ian
 */

@Entity
@Table(name = "empleado")
public class EmpleadoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
            generator = "empleado_id_seq")
    @SequenceGenerator(name = "empleado_id_seq",
            sequenceName = "empleado_id_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name="id")
    private long id;
    
    @Column(name="nombre")
    private String name;
    
    @Column(name="direccion")
    private String address;
    
    @Column(name="telefono")
    private String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
