package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping("/")
    public String home(Model model){
       model.addAttribute("authors", authorRepository.findAll());
       model.addAttribute("books", bookRepository.findAll());
       return "index";
    }

    @GetMapping("/addauthor")
    public String authorForm(Model model){
        model.addAttribute("author", new Author());
        return "authorform";
    }

    @PostMapping("/process_author")
    public String processAuthorForm(@Valid Author author, BindingResult result){
        if (result.hasErrors()){
            return "authorform";
        }
        authorRepository.save(author);

        return "redirect:/authorlist";
    }

    @RequestMapping("/authorlist")
    public String authorList(Model model){
        model.addAttribute("authors", authorRepository.findAll());

        return "authorlist";
    }

    @GetMapping("/addbook")
    public String bookForm(Model model){
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("book", new Book());
        return "bookform";
    }

    @PostMapping("/process_book")
    public String processBookForm(@Valid Book book, BindingResult result){
        if (result.hasErrors()){
            return "bookform";
        }
        System.out.println("HomeController:process_book: save book: " + book.getTitle());
        bookRepository.save(book);

        return "redirect:/booklist";
    }

    @RequestMapping("/booklist")
    public String bookList(Model model){
        model.addAttribute("books", bookRepository.findAll());

        return "booklist";
    }

    @RequestMapping("/detail/{id}")
    public String showAuthor(@PathVariable("id") long id, Model model){
        model.addAttribute("author", authorRepository.findById(id).get());
        return "showauthor";
    }

    @RequestMapping("/update/{id}")
    public String updateAuthor(@PathVariable("id") long id, Model model){
        model.addAttribute("author", authorRepository.findById(id).get());
        return "authorform";
    }

    @RequestMapping("/delete/{id}")
    public String delAuthor(@PathVariable("id") long id){
        System.out.println("HomeController: Delete Author with id: " + id);
        try {
            long count = bookRepository.count();
//            Iterable<Customer> findAllByLastNameContainingIgnoreCase(String lastName);
            //Iterable<Book> findByAuthor(Author author);
            Author author = authorRepository.findByAuthorID(id);
            ArrayList<Book> results1 = (ArrayList<Book>)bookRepository.findByAuthor(author);
            if (results1.size()>0){
                System.out.println("Results1: Cannot Delete this author as it is in use. Author ID: " + id);
                return "redirect:/";
            }
            else {
                System.out.println("Results1: Number of Books found with this author : " + results1.size());
            }

//            ArrayList<Book> results = (ArrayList<Book>) bookRepository.findAll();
//            for (int i=0; i<results.size(); i++){
//                if (id == results.get(i).getAuthor().getAuthorID()){
//                    System.out.println("Cannot Delete this author as it is in use. Author ID: " + id);
//                    return "redirect:/";
//                }
//            }
//            if (results.size() == 0) {
//                authorRepository.deleteById(id);
//            } else {
//                System.out.println("HomeController: delAuthor: " + "Cannot Delete Author as it is currently assigned to a book");
//            }
            authorRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println("HomeController:delAuthor: " + e.getMessage());
        }
//        return "index";
        return "redirect:/";
    }

    @RequestMapping("/detail_book/{id}")
    public String showBook(@PathVariable("id") long id, Model model){
        model.addAttribute("book", bookRepository.findById(id).get());
        return "showbook";
    }

    @RequestMapping("/update_book/{id}")
    public String updateBook(@PathVariable("id") long id, Model model){
//        check book
        model.addAttribute("book", bookRepository.findById(id).get());
        model.addAttribute("authors",authorRepository.findAll());
        return "bookform";
    }

    @RequestMapping("/delete_book/{id}")
    public String delBook(@PathVariable("id") long id, Model model){
        System.out.println("HomeController: Delete Book with id: " + id);
//        bookRepository.delete(bookRepository.findById(id));

        try {
            bookRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println("HomeController:delete_book: " + e.getMessage());
        }

        if (bookRepository.existsById(id)){
            System.out.println("HomeController: delete_book: Book exists with id: " + id);
        }
        else {
            System.out.println("HomeController: delete_book: Book does Not exist with id: " + id);
        }
        return "redirect:/";
//        return "index";
    }
}
