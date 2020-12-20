package com.proveedores.catalogos;

public enum TipoRolEnum {

    ADMINISTRADOR(1),
    INTERNO(2),
    PROVEEDOR(3);

    private final int tipo;

    private TipoRolEnum(int tipo) {
        this.tipo = tipo;
    }

    public int value() {
        return tipo;
    }

}
