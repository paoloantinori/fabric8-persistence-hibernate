/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.pires.example.dal.impl.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

/**
 * Abstract data access object.
 * 
 * @param <T>
 *          the entity type to be managed.
 */
public class AbstractDao<T> {

  protected Class<T> entityClass;
  private EntityManager em;
  private EntityManagerFactory emf;

  public AbstractDao(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public EntityManager getEntityManager() {
    return this.em;
  }

  public void setEntityManager(EntityManager em) {
    this.em = em;
  }

  public EntityManagerFactory getEntityManagerFactory() {
    return this.emf;
  }

  public void setEntityManagerFactory(EntityManagerFactory emf) {
    this.emf = emf;
    this.em = emf.createEntityManager();
  }

  /**
   * Retrieves the meta-model for a certain entity.
   * 
   * @return the meta-model of a certain entity.
   */
  protected EntityType<T> getMetaModel() {
    return em.getMetamodel().entity(entityClass);
  }

  public void persist(T entity) {
    this.em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
  }

  public T merge(T entity) {
    em.getTransaction().begin();
    T result = em.merge(entity);
    em.getTransaction().commit();
    return result;
  }

  public void remove(Object entityId) {
    T entity = find(entityId);
    if (entity != null){
      em.getTransaction().begin();
      em.remove(entity);
      em.getTransaction().commit();
    }
      
  }

  public T find(Object id) {
    this.em = emf.createEntityManager();
    return em.find(entityClass, id);
  }

  public List<T> findAll() {
    this.em = emf.createEntityManager();
    CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(
        entityClass);
    cq.select(cq.from(entityClass));
    return em.createQuery(cq).getResultList();
  }

  public int count() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> cq = cb.createQuery(Long.class);
    Root<T> root = cq.from(entityClass);
    cq.select(cb.count(root));
    return em.createQuery(cq).getSingleResult().intValue();
  }

}