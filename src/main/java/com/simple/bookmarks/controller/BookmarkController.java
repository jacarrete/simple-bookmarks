package com.simple.bookmarks.controller;

import com.simple.bookmarks.SimplebookmarksApplication;
import com.simple.bookmarks.model.Bookmark;
import com.simple.bookmarks.service.BookmarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private static final Logger log = LoggerFactory.getLogger(SimplebookmarksApplication.class);

    @Autowired
    private BookmarkService bookmarkService;

    @RequestMapping(value = "/getAllBookmarks", method = RequestMethod.GET, headers = "Accept=application/json")
    public String getAllBookmarks(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
        model.addAttribute("bookmark", new Bookmark());
        model.addAttribute("listOfBookmarks", bookmarkService.getAllBookmarks());
        model.addAttribute("username", username);
        return "bookmarkDetails";
    }

    @RequestMapping(value = "/bookmarkList", method = RequestMethod.GET, headers = "Accept=application/json")
    public String bookmarkList(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
        log.info("Username: {}", username);
        model.addAttribute("listOfBookmarks", bookmarkService.getAllBookmarks());
        model.addAttribute("username", username);
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
    public String addBookmark(@ModelAttribute("bookmark") Bookmark bookmark, HttpServletRequest servletRequest) {
        MultipartFile file = ((StandardMultipartHttpServletRequest) servletRequest).getFile("image");
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
    public String updateBookmark(@PathVariable("id") int id,Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
        model.addAttribute("bookmark", bookmarkService.getBookmark(id));
        model.addAttribute("listOfBookmarks", bookmarkService.getAllBookmarks());
        model.addAttribute("username", username);
        return "bookmarkDetails";
    }

    @RequestMapping(value = "/deleteBookmark/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String deleteBookmark(@PathVariable("id") int id) {
        bookmarkService.deleteBookmark(id);
        return "redirect:/getAllBookmarks";
    }

    @RequestMapping(value = "/restBookmarks", method = RequestMethod.GET, produces = "application/json")
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