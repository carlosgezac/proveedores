package com.proveedores.security.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "usuariotipo")
public class UsuarioTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_tipo")
    private Integer idUsuarioTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioTipo")
    //private Collection<Rol> roles;

    public UsuarioTipo(Integer idUsuarioTipo) {
        this.idUsuarioTipo = idUsuarioTipo;
    }

    public Integer getIdUsuarioTipo() {
        return idUsuarioTipo;
    }

    public void setIdUsuarioTipo(Integer idUsuarioTipo) {
        this.idUsuarioTipo = idUsuarioTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioTipo != null ? idUsuarioTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioTipo other = (UsuarioTipo) obj;
        if (!Objects.equals(this.idUsuarioTipo, other.idUsuarioTipo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proveedores.entity.security.Usuariotipo[ idUsuarioTipo=" + idUsuarioTipo + " ]";
    }

    public UsuarioTipo() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
