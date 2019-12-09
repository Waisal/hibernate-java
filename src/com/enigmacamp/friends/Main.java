package com.enigmacamp.friends;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import com.enigmacamp.friends.db.entities.Category;
import com.enigmacamp.friends.db.entities.Gender;
import com.enigmacamp.friends.db.entities.Person;
import com.enigmacamp.friends.db.entities.Post;
import com.enigmacamp.friends.db.entities.PostHasCategory;
import com.enigmacamp.friends.db.repositories.CategoryRepository;
import com.enigmacamp.friends.db.repositories.GenderRepository;
import com.enigmacamp.friends.db.repositories.PersonRepository;
import com.enigmacamp.friends.db.repositories.PostRepository;

public class Main {

	public static void main(String[] args) {
		hibernateSample();
	}

//	@SuppressWarnings("unchecked")
	private static void hibernateSample() {
		System.out.println("PROJECT: friends-orm-hibernate");
        GenderRepository genderRepo = new GenderRepository();
        PersonRepository personRepo = new PersonRepository();
        PostRepository postRepo = new PostRepository();
        CategoryRepository categoryRepo = new CategoryRepository();
        
        List<Gender> genders = genderRepo.findAll(
        	genderRepo.getCriteriaBuilder().equal(genderRepo.getEntity().get("name"), "MALE")
        );
        
        Gender gender = genderRepo.find(3);
        
        List<Person> persons = personRepo.findAll();
        Person person = personRepo.find(1);
        
        System.out.println("find: " + gender);
        System.out.println("findAll: " + genders);
        
        
        System.out.println("find: " + person);
        System.out.println("findAll: " + persons);
        Person obj1 = personRepo.find(1);
        Post obj = postRepo.find(9);
//        obj.setTitle("drama liburan");
//        obj.setContent("kukejar kau walau kau tak berlari");
//        obj.setPerson_id(obj1);
//        postRepo.save(obj);
        System.out.println("========================");
        for (Post post : postRepo.findAllByDate()) {
			System.out.println(post);
		}
        System.out.println("=========================");
        
        System.out.println(postRepo.findByTitle("mu vs persija"));
        
//        List<Post> posts = postRepo.findAll();
//        System.out.println("Find All Post :");
//        for (Post post : posts) {
//			System.out.println(post);
//		}
        
        System.out.println("Category : ");
        for (Category cat : categoryRepo.findAll()) {
			System.out.println(cat);
		}
        
//        Category cate = categoryRepo.find(3);
//        cate.setName("olah-raga");
//        categoryRepo.delete(cate);
//        System.out.println(cate);
        
//        Category cats = categoryRepo.findAll();
//        PostHasCategory phc = new PostHasCategory();
//        phc.setCategory(cats);
//        phc.setPost(posts);
//        System.out.println(phc);
        
//        Category cate = new Category();
//        cate.setId(4);
//        cate.setName("travle");
//        categoryRepo.save(cate);
        
//        System.out.println("Find All category :");
//        for (Category cat : cats) {
//			System.out.println(cat);
//		}
        
//
        System.out.println("find all by category and name person");
        for (Post pr1 : postRepo.findAllByCategoryAndPname(person)) {
			System.out.println(pr1);
		}
        
        System.out.println("find all by name person");
        for (Post pr1 : postRepo.findAllByPersonName("maiya")) {
			System.out.println(pr1);
		}
        
        System.out.println("find all by category");
        for (Post pr1 : postRepo.findAllByCategory()) {
			System.out.println(pr1);
		}
	}
}
