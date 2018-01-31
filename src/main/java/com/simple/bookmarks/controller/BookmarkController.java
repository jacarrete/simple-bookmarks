package com.simple.bookmarks.controller;

import com.simple.bookmarks.model.Bookmark;
import com.simple.bookmarks.service.BookmarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jcarretero on 23/01/2018.
 */
@Controller
public class BookmarkController {

    private static final Logger log = LoggerFactory.getLogger(BookmarkController.class);

    @Autowired
    private BookmarkService bookmarkService;

    @RequestMapping(value = "/getAllBookmarks", method = RequestMethod.GET, headers = "Accept=application/json")
    public String getAllBookmarks(Model model) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
        model.addAttribute("bookmark", new Bookmark());
        model.addAttribute("listOfBookmarks", bookmarkService.getAllBookmarks());
        model.addAttribute("loggedUser", loggedUser);
        return "bookmarkDetails";
    }

    @RequestMapping(value = "/bookmarkList", method = RequestMethod.GET, headers = "Accept=application/json")
    public String bookmarkList(Model model) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
        log.info("LoggedUser: {}", loggedUser);
        model.addAttribute("listOfBookmarks", bookmarkService.getAllBookmarks());
        model.addAttribute("loggedUser", loggedUser);
        return "bookmarkList";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
    public String goToHomePage() {
        return "redirect:/bookmarkList";
    }

    @RequestMapping(value = "/getBookmark/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Bookmark getBookmarkById(@PathVariable int id) {
        return bookmarkService.getBookmark(id);
    }

    @RequestMapping(value = "/addBookmark", method = RequestMethod.POST, headers = "Accept=application/json")
    public String addBookmark(@ModelAttribute("bookmark") Bookmark bookmark, BindingResult result, Model model, HttpServletRequest servletRequest) {
        MultipartFile file = ((StandardMultipartHttpServletRequest) servletRequest).getFile("image");
        boolean error = false;
        Bookmark bookmarkDB = bookmarkService.getBookmarkByName(bookmark.getName());
        if (StringUtils.isEmpty(bookmark.getName())) {
            result.rejectValue("name", "error.empty.name");
            error = true;
        } else if (bookmarkDB != null && bookmarkDB.getId() != bookmark.getId()) {
            result.rejectValue("name", "error.name");
            error = true;
        }
        if (StringUtils.isEmpty(bookmark.getAddress())) {
            result.rejectValue("address", "error.empty.address");
            error = true;
        }
        if(error) {
            model.addAttribute("bookmark", bookmark);
            model.addAttribute("listOfBookmarks", bookmarkService.getAllBookmarks());
            model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
            return "bookmarkDetails";
        }
        if (file != null) {
            bookmark.setImageName(file.getOriginalFilename());
        }
        if(bookmark.getId()==0) {
            bookmarkService.addBookmark(bookmark);
        }
        else {
            bookmarkService.updateBookmark(bookmark);
        }
        transferMultipartFile(servletRequest);
        return "redirect:/getAllBookmarks";
    }

    @RequestMapping(value = "/updateBookmark/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String updateBookmark(@PathVariable("id") int id, Model model) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
        model.addAttribute("bookmark", bookmarkService.getBookmark(id));
        model.addAttribute("listOfBookmarks", bookmarkService.getAllBookmarks());
        model.addAttribute("loggedUser", loggedUser);
        return "bookmarkDetails";
    }

    @RequestMapping(value = "/deleteBookmark/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String deleteBookmark(@PathVariable("id") int id) {
        bookmarkService.deleteBookmark(id);
        return "redirect:/getAllBookmarks";
    }

    @RequestMapping(value = "/restBookmarks", method = RequestMethod.GET, produces={"application/json", "application/xml"})
    @ResponseBody
    public List<Bookmark> restBookmarks() {
        return bookmarkService.getAllBookmarks();
    }

    private void transferMultipartFile(HttpServletRequest servletRequest) {
        MultipartFile file = ((StandardMultipartHttpServletRequest) servletRequest).getFile("image");
        if (!"".equals(file.getOriginalFilename())) {
            String fileName = file.getOriginalFilename();
            File imageFile = new File(servletRequest.getServletContext().getRealPath("/images"), fileName);
            try {
                file.transferTo(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}