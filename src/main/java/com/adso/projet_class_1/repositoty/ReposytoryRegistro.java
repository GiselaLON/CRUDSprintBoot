package com.adso.projet_class_1.repositoty;

import com.adso.projet_class_1.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReposytoryRegistro extends JpaRepository<Registro, Long> {


}
