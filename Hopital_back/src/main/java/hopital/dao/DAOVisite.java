package hopital.dao;

import java.time.LocalDate;
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
	public List<Visite> findByMedecinIdAndDateVisiteBetween(Integer idMedecin, LocalDate debut, LocalDate fin) {
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
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		double total = em.createQuery("SELECT SUM(v.prix) FROM Visite v where v.medecin.id=:idMedecin and v.dateVisite between :debut and :fin",Double.class)
				.setParameter("idMedecin", idMedecin)
				.setParameter("debut", LocalDate.parse(debut))
				.setParameter("fin", LocalDate.parse(fin))
				.getSingleResult();
		return total;
	}

	@Override
	public void updatePatientSetNull(Integer idPatient) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			em.createQuery("UPDATE Visite v set v.patient=null where v.patient.id=:id").setParameter("id", idPatient).executeUpdate();
		em.getTransaction().commit();
		em.close();
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
