package org.docto.dao;




import org.docto.entites.Docteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DocteurRepository extends JpaRepository<Docteur,Long> {
	@Query("select d from Docteur d where d.nom like :x and d.specialite like :s")
	public Page<Docteur> chercher(@Param("x")String mc,@Param("s")String ms,Pageable pageable);

	public Docteur getUserByLoginAndPass(String login,String pass);
}
