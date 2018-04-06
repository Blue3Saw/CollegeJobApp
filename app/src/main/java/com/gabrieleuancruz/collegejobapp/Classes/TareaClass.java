package com.gabrieleuancruz.collegejobapp.Classes;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Gabriel Euan Cruz on 05/04/2018.
 */

public class TareaClass {
    private int Codigo;
    private String Titulo;
    private String Descripcion;
    public Date Fecha;
    public Time Hora;
    public String Empleador;
    public String Clasificación;
    public byte[] Imagen;

    /*public TareaClass(int codigo, String titulo, String descripcion, Date fecha, Time hora, String empleador, String clasificación, byte[] imagen) {
        Codigo = codigo;
        Titulo = titulo;
        Descripcion = descripcion;
        Fecha = fecha;
        Hora = hora;
        Empleador = empleador;
        Clasificación = clasificación;
        Imagen = imagen;
    }*/

    public int getCodigo() { return Codigo; }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Time getHora() {
        return Hora;
    }

    public void setHora(Time hora) {
        Hora = hora;
    }

    public String getEmpleador() {
        return Empleador;
    }

    public void setEmpleador(String empleador) {
        Empleador = empleador;
    }

    public String getClasificación() {
        return Clasificación;
    }

    public void setClasificación(String clasificación) {
        Clasificación = clasificación;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] imagen) {
        Imagen = imagen;
    }
}
