package hopital.dao;

import java.util.List;

import hopital.model.Visite;

public class DAOVisite implements IDAOVisite{

	@Override
	public List<Visite> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visite findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visite save(Visite obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Visite obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Visite> findByPatientId(Integer idPatient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visite> findByMedecinIdAndDateVisiteBetween(Integer idMedecin, String debut, String fin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double findSumByMedecinIdAndDateVisiteBetween(Integer idMedecin, String debut, String fin) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updatePatientSetNull(Integer idPatient) {
		// TODO Auto-generated method stub
		
	}

}
