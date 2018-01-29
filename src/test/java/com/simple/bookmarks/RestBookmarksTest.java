package com.simple.bookmarks;

/**
 * Created by jcarretero on 26/01/2018.
 */

import com.simple.bookmarks.controller.BookmarkController;
import com.simple.bookmarks.model.Bookmark;
import com.simple.bookmarks.service.BookmarkService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimplebookmarksApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
public class RestBookmarksTest {

    @LocalServerPort
    private int port;

    @Value("${local.management.port}")
    private int mgt;

    private MockMvc mockMvc;

    @InjectMocks
    private BookmarkController bookmarkController;

    @Mock
    private BookmarkService bookmarkService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(bookmarkController)
                .build();
    }

    private List<Bookmark> getBookmarkList(){
        Bookmark bookmark = new Bookmark("example", "EXAM", "http://www.google.com", "red", null, true);
        Bookmark bookmark1 = new Bookmark("example1", "EXAM1", "http://www.google.com", "red", null, true);
        return new ArrayList<>(Arrays.asList(bookmark, bookmark1));
    }

    @Test
    public void shouldReturn200WhenSendingRequestToController() throws Exception {
        List<Bookmark> bookmarkList = getBookmarkList();
        when(bookmarkService.getAllBookmarks()).thenReturn(bookmarkList);
        mockMvc.perform(get("/restBookmarks.json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("example")))
                .andExpect(jsonPath("$[0].initials", is("EXAM")))
                .andExpect(jsonPath("$[0].address", is("http://www.google.com")))
                .andExpect(jsonPath("$[1].name", is("example1")))
                .andExpect(jsonPath("$[1].initials", is("EXAM1")))
                .andExpect(jsonPath("$[1].color", is("red")));

        verify(bookmarkService, times(1)).getAllBookmarks();
        verifyNoMoreInteractions(bookmarkService);
    }

    @Test
    public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.mgt + "/info", Map.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}

