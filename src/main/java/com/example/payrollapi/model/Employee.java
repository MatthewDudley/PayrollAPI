package com.example.payrollapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {


    // Setup id, empname, emptitle fields that are mapped to the Payroll.Employee table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String empname;

    @NotBlank
    private String emptitle;

    // Constructors
    public Employee() {
    }

    public Employee(Long id, String empname, String emptitle) {
        this.id = id;
        this.empname = empname;
        this.emptitle = emptitle;
    }

    // GETTERS and SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmptitle() {
        return emptitle;
    }

    public void setEmptitle(String emptitle) {
        this.emptitle = emptitle;
    }
}
