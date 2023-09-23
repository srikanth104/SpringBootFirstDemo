package dev.srikanth.productservice;


import dev.srikanth.productservice.inheritencedemo.jointable.Mentor;
import dev.srikanth.productservice.inheritencedemo.jointable.MentorRepository;
import dev.srikanth.productservice.inheritencedemo.jointable.User;
import dev.srikanth.productservice.inheritencedemo.jointable.UserRepository;
import dev.srikanth.productservice.model.Category;
import dev.srikanth.productservice.model.Product;
import dev.srikanth.productservice.repositories.CategoryRepository;
import dev.srikanth.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {


    private MentorRepository mMentorRepository;

    private UserRepository mUserRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceApplication(@Qualifier("jt_mr")MentorRepository mentorRepository,
                                     @Qualifier("jt_ur")UserRepository userRepository,
                                     ProductRepository productRepository,
                                     CategoryRepository categoryRepository) {
        this.mMentorRepository = mentorRepository;
        this.mUserRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        /* This is for demo purpose of Inheritance
        Mentor mentor = new Mentor();
        mentor.setName("Srikanth");
        mentor.setEmail("Srikanth@gmail.com");
        mentor.setAvgRating(4.5);
        mMentorRepository.save(mentor);

        User user = new User();
        user.setName("Aadya");
        user.setEmail("aadya@gmail.com");
        mUserRepository.save(user);

        List<User> users = mUserRepository.findAll();

        for(User user1 : users) {
            System.out.println(user1);
        } */

        Category category = new Category();
        category.setName("Apple");
        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setTitle("iPhone 15 Pro");
        product.setDescription("The best iPhone ever");
        product.setCategory(savedCategory);

        productRepository.save(product);




    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
