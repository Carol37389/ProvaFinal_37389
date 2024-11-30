package com.fiec.provafinal.Models;

import jakarta.persistence.EntityManager;

public class SapatoRepositorio extends GenericRepositorioImpl<Sapato, String> {

    public SapatoRepositorio(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    Class<Sapato> getMyClass() {
        return Sapato.class;
    }
}
