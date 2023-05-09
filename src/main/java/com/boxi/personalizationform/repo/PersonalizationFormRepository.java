package com.boxi.personalizationform.repo;


import com.boxi.personalizationform.entity.PersonalizationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalizationFormRepository extends JpaRepository<PersonalizationForm, Long>, JpaSpecificationExecutor<PersonalizationForm> {

    PersonalizationForm findByUserIdAndPermissionId(Long userid, Long permissionId);

    Boolean existsByUserIdAndPermissionId(Long userid, Long permissionId);
}
