package com.proveedores.security.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "roltipo")
public class RolTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_rol")
    private Integer idTipoRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolTipo")
    private Collection<Rol> rolCollection;

    public RolTipo() {
    }

    public RolTipo(Integer idTipoRol) {
        this.idTipoRol = idTipoRol;
    }

    public RolTipo(Integer idTipoRol, String tipo) {
        this.idTipoRol = idTipoRol;
        this.tipo = tipo;
    }

    public Integer getIdTipoRol() {
        return idTipoRol;
    }

    public void setIdTipoRol(Integer idTipoRol) {
        this.idTipoRol = idTipoRol;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<Rol> getRolCollection() {
        return rolCollection;
    }

    public void setRolCollection(Collection<Rol> rolCollection) {
        this.rolCollection = rolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoRol != null ? idTipoRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolTipo)) {
            return false;
        }
        RolTipo other = (RolTipo) object;
        if ((this.idTipoRol == null && other.idTipoRol != null) || (this.idTipoRol != null && !this.idTipoRol.equals(other.idTipoRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proveedores.entity.Roltipo[ idTipoRol=" + idTipoRol + " ]";
    }

}
