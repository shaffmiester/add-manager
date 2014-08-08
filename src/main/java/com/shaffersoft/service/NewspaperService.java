package com.shaffersoft.service;

import com.shaffersoft.model.Advertisement;
import com.shaffersoft.model.Newspaper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * Created by joelshaffer on 8/7/14.
 */
public class NewspaperService {

    private EntityManagerFactory emf;

    public NewspaperService(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Newspaper addNewspaper(String name, String state) {
        Newspaper newspaper = new Newspaper();
        newspaper.setName(name);
        newspaper.setState(state);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(newspaper);
        em.getTransaction().commit();
        em.close();
        return newspaper;
    }

    public List<Newspaper> getAllNewspapers(){
        EntityManager em = emf.createEntityManager();
        List<Newspaper> newspapers = em.createQuery("from Newspaper").getResultList();
        em.close();
        return newspapers;
    }

    public Advertisement addAvertisementToNewspaper(Advertisement advertisement, Newspaper newspaper){
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        advertisement.addNewspaper(newspaper);
        em.persist(advertisement);
        em.persist(newspaper);
        em.getTransaction().commit();
        em.close();
        return advertisement;

    }

    public Advertisement addAdvertisement(String name, String text) {
        Advertisement advertisement = new Advertisement();
        advertisement.setName(name);
        advertisement.setText(text);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(advertisement);
        em.getTransaction().commit();
        em.close();
        return advertisement;
    }

    public List<Advertisement> getAllAdvertisements() {
        EntityManager em = emf.createEntityManager();
        List<Advertisement> advertisements = em.createQuery("from Advertisement").getResultList();
        em.close();
        return advertisements;
    }

    public Advertisement getAdvertisementById(Long id) {
        EntityManager em = emf.createEntityManager();
        Advertisement foundAdd = em.find(Advertisement.class, id);
        em.close();
        return foundAdd;
    }

    public void associateAdvertisementWithNewspapers(Long id, List<Long> newspaperIds) {
        Advertisement advertisement = this.getAdvertisementById(id);
        List<Newspaper> newspapers = getNewspapersByIds(newspaperIds);
        advertisement.getNewspapers().addAll(newspapers);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(advertisement);
        em.getTransaction().commit();
        em.close();


    }

    public List<Newspaper> getNewspapersByIds(List<Long> ids) {
        EntityManager em = emf.createEntityManager();
        List<Newspaper> newspapers = em.createQuery("from Newspaper where id in (:ids)").setParameter("ids", ids).getResultList();
        em.close();
        return newspapers;
    }
}
