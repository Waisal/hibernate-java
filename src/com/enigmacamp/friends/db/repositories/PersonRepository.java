package com.enigmacamp.friends.db.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.enigmacamp.friends.db.entities.Person;
import com.enigmacamp.friends.db.entities.Post;
import com.enigmacamp.friends.enums.Gender;

public class PersonRepository extends Repository<Person> {
	public PersonRepository() {
		this.entity = this.query.from(Person.class);
	}

	public List<Person> findAll(Predicate... predicates) {
		CriteriaQuery<Person> cq = this.query.select(this.entity);
		cq.where(predicates);

		return this.executeQuery(cq).getResultList();
	}

	public Person find(Integer id) {
		return (Person) session.get(Person.class, id);
	}

	
}
