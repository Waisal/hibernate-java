package com.enigmacamp.friends.db.repositories;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.enigmacamp.friends.db.entities.Person;
import com.enigmacamp.friends.db.entities.Post;
public class PostRepository extends Repository<Post> {

	public PostRepository() {
		this.entity = this.query.from(Post.class);
	}
	
	public List<Post> findAll(Predicate... predicates) {
		CriteriaQuery<Post> cq = this.query.select(this.entity);
		cq.where(predicates);

		return this.executeQuery(cq).getResultList();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Post> findAllByPersonName(String name) {
		Criteria c = session.createCriteria(Post.class);
		c.createAlias("person", "pr", Criteria.INNER_JOIN, Restrictions.eq("pr.name", name));
		return c.list();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Post> findAllByCategory() {
		Criteria c = session.createCriteria(Post.class);
		c.createAlias("category", "cat", Criteria.INNER_JOIN, Restrictions.eq("cat.name", "berita"));
		return c.list();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Post> findAllByCategoryAndPname(Person person) {
		Criteria c = session.createCriteria(Post.class);
		c.createAlias("category", "cat", Criteria.INNER_JOIN, Restrictions.eq("cat.name", "berita")).createAlias("person", "pr", Criteria.INNER_JOIN, Restrictions.eq("pr.name", person.getName()));
		return c.list();
	}
	
	public Post find(Integer id) {
		return (Post) session.get(Post.class, id);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Post findByTitle(String title) {
		Post post = null;
		Criteria c = session.createCriteria(Post.class);  
		c.add(Restrictions.eq("title", title));
		List<Post> list= c.list();  
		for (Post pos : list) {
			post = pos;
		}
		
		return post;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Post> findAllByDate() {
//		Criteria c = session.createCriteria(Post.class);
//		c.addOrder(Order.desc("post_date"));
		
		CriteriaQuery<Post> cq = this.query.select(this.entity);
		cq.orderBy(criteriaBuilder.desc(this.entity.get("post_date")));
		return this.executeQuery(cq).getResultList();
//		  c.list();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public List<Post> findAllByToday(Predicate... predicates) {
//		Criteria c = session.createCriteria(Post.class); 
//		String date = LocalDate.now().toString();
//		c.add(Expression.like("post_date", date, MatchMode.ANYWHERE));
//		List<Post> list= c.list();  
		
		Query qw = session.createQuery("from Post where date(post_date) = curdate()");
		List<Post> results = qw.list(); 
		return results;
	}
	
	public Post save(Post post) {
		Transaction trx = this.session.beginTransaction();
		
		Integer id = (Integer) this.session.save(post);
		post = this.find(id);
		
		trx.commit();
	
		return post;
	}
	
	public Post delete(Post post) {
		Transaction trx = this.session.beginTransaction();
		
		this.session.delete(post);
		post = this.find(post.getId());
		
		trx.commit();
	
		return post;
	}
}
