package com.github.q3527.srs.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.q3527.srs.dao.provider.ProfessorDynaSqlProvider;
import com.github.q3527.srs.domain.Professor;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public interface ProfessorDao {
	/**
	 * 璐熻矗瀵筽rofessor琛ㄨ繘琛屾搷浣�
	 * @return
	 */
	@Select("select * from professor ")
	@Results({
		@Result(id=true,column="ssn",property="ssn"),
		@Result(column="name",property="name"),
		@Result(column="title",property="title"),
		@Result(column="department",property="department")/*,
		@Result(column="ssn",property="teaches",
		many=@Many(select="com.srs.dao.SectionDao.selectByProfessorSsn",
		fetchType=FetchType.EAGER))*/
	})
	ArrayList<Professor> load();

	@Select("select * from professor where ssn=#{ssn}")
	@Results({
		@Result(id=true,column="ssn",property="ssn"),
		@Result(column="name",property="name"),
		@Result(column="title",property="title"),
		@Result(column="department",property="department")
	})
	Professor selectProfessorBySsn(@Param("ssn") String ssn);

	@Delete("delete from professor where ssn=#{ssn}")
	void deleteProfessor(String ssn);
	
	@Insert("insert into professor (ssn,department,title,name) values(#{ssn},#{department},#{title},#{name})")
	void insertProfessor(Professor professor);
	@UpdateProvider(type=ProfessorDynaSqlProvider.class,method="update")
	void updateProfessor(Professor professor);

}
