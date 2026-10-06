package hopital.dao;

import java.util.List;

import hopital.context.Singleton;
import hopital.model.Visite;
import jakarta.persistence.EntityManager;

public class DAOVisite implements IDAOVisite{

	@Override
	public List<Visite> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			List<Visite>visites = em.createQuery("FROM Visite").getResultList();
		em.close();
		return visites;
	}

	@Override
	public Visite findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			Visite visite = em.find(Visite.class, id);
		em.close();
		return visite;
	}

	@Override
	public Visite save(Visite visite) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				visite=em.merge(visite);
			em.getTransaction().commit();
		em.close();
		return visite;
	}

	@Override
	public void delete(Visite visite) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				visite=em.merge(visite);
				em.remove(visite);
			em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				Visite visite= em.find(Visite.class, id);
				em.remove(visite);
			em.getTransaction().commit();
		em.close();
	}
	@Override
	public List<Visite> findByMedecinIdAndDateVisiteBetween(Integer idMedecin, String debut, String fin) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Visite>visites = em.createQuery("SELECT v FROM Visite v where v.medecin.id=:idMedecin and v.dateVisite between :debut and :fin")
				.setParameter("idMedecin", idMedecin)
				.setParameter("debut", debut)
				.setParameter("fin", fin)
				.getResultList();
	em.close();
	return visites;
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

	@Override
	public List<Visite> findByPatientId(Integer idPatient) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Visite>visites = em.createQuery("SELECT v FROM Visite v where v.patient.id=:idPatient")
				.setParameter("idMedecin", idPatient)
				.getResultList();
	em.close();
	return visites;
	}

}
