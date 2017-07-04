package com.github.q3527.srs.domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.github.q3527.srs.dao.ProfessorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class ProfessorCatalog{
	private ArrayList<Professor> professorCatalog;
	public ProfessorCatalog() {
		professorCatalog = new ArrayList<Professor>();
	}
	@Autowired
	private ProfessorDao professorDao;
	public void setProfessorDao(ProfessorDao professorDao) {
		this.professorDao = professorDao;
	}
	/*bean鍒涘缓瀹炰緥锛屾敞鍏ヤ緷璧栧叧绯讳箣鍚庢墽琛宨nit鏂规硶*/
	@PostConstruct
	public void init(){
		this.professorCatalog=load();
	}
	@PreDestroy
	public void preDestroy(){
		//鍦ㄦ鎵ц鎵归噺鏇存柊鏁版嵁搴�
		
	}
	/*鍔犺浇鏁版嵁搴撲腑鏁版嵁*/
	private ArrayList<Professor> load() {	
		ArrayList<Professor> list=professorDao.load();
		return list;
	}
	/*娣诲姞鏁欏笀*/
	public boolean addProfessor(Professor professor) { 
		boolean result=professorCatalog.add(professor);
		professorDao.insertProfessor(professor);
		return result;
	}
	/*鍒犻櫎鏁欏笀*/
	public boolean deleteProfessor(String ssn) {
		// TODO Auto-generated method stub
		int size=professorCatalog.size();
		for(int i=0;i<size;i++){
			if(professorCatalog.get(i).getSsn().equals(ssn)){
				professorCatalog.remove(professorCatalog.get(i));
				professorDao.deleteProfessor(ssn);
				break;
			}
		}
		return true;
	}
	/*淇敼鏁欏笀璧勬枡*/
	public boolean updateProfessor(Professor professor) {
		// TODO Auto-generated method stub
		int size=professorCatalog.size();
		for(int i=0;i<size;i++){
			if(professorCatalog.get(i).getSsn().equals(professor.getSsn())){
				professorCatalog.remove(professorCatalog.get(i));
				professorCatalog.add(professor);
				break;
			}
		}
		professorDao.updateProfessor(professor);
		return true;
	}
	public ArrayList<Professor> getProfessorCatalog() {
		return professorCatalog;
	}


	




	
}
