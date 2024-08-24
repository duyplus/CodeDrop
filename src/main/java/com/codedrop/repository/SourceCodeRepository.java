package com.codedrop.repository;

import com.codedrop.model.SourceCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceCodeRepository extends JpaRepository<SourceCode, Integer>, JpaSpecificationExecutor<SourceCode> {

//    @Modifying
//    @Query(value = "exec getTopSourceCode", nativeQuery = true)
//    List<TopSourceCode> getTopSourceCode();

//    @Modifying
//    @Query("SELECT s FROM SourceCode s WHERE s.title LIKE '%'+?1+'%'")
//    List<SourceCode> searchByNameLike(String name);
}