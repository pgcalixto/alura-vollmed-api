package med.voll.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.entity.MedicoEntity;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    Page<MedicoEntity> findAllByAtivoTrue(Pageable paginacao);

}
