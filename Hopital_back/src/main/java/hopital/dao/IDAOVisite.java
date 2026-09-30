package hopital.dao;

import java.util.List;

import hopital.model.Visite;

public interface IDAOVisite extends IDAO<Visite,Integer> {

	public List<Visite> findByPatientId(Integer idPatient);
	public List<Visite> findByMedecinIdAndDateVisiteBetween(Integer idMedecin,String debut,String fin);
	public double findSumByMedecinIdAndDateVisiteBetween(Integer idMedecin,String debut,String fin) ;
	public void updatePatientSetNull(Integer idPatient);
}
