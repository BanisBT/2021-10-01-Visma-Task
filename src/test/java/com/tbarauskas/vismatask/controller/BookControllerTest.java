package com.tbarauskas.vismatask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tbarauskas.vismatask.dto.BookRequestDTO;
import com.tbarauskas.vismatask.dto.BookResponseDTO;
import com.tbarauskas.vismatask.entity.Book;
import com.tbarauskas.vismatask.entity.User;
import com.tbarauskas.vismatask.model.ErrorHandler;
import com.tbarauskas.vismatask.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private final LocalDateTime now = LocalDateTime.now();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreateBook() throws Exception {
        BookRequestDTO book = new BookRequestDTO("Ziedu valdovas", "Dzonas Tolkinas", "fantastika",
                "lietuviu", LocalDate.of(1954, 1, 1), "IMS-22-234-67643", false);

        MvcResult mvcResult = mockMvc.perform(post("/books/create")
                .content(objectMapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        BookResponseDTO bookDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookResponseDTO.class);

        Book bookFromDb = bookRepository.getBookById(bookDTO.getId()).orElse(null);

        assert bookFromDb != null;
        assertEquals(book.getAuthor(), bookFromDb.getAuthor());
    }

    @Test
    void testTakeBook() {
        User user = new User(1L, "Tomas", 2, now, now);
        LocalDate date = now.plusMonths(1).toLocalDate();

    }

    @Test
    void testGetBookById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/books/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();

        BookResponseDTO book = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookResponseDTO.class);

        assertEquals(1L, book.getId());
        assertEquals("Gyvuliu ukis", book.getTittle());
    }

    @Test
    void testGetBookByIdIfNotExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/books/{id}", 9L))
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorHandler error = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorHandler.class);

        assertEquals(HttpStatus.NOT_FOUND.value(), error.getStatus());
        assertEquals("Book with id - 9 was not found", error.getMessage());
    }

    @Test
    void getBooksByAuthor() {
    }

    @Test
    void deleteBook() {
    }
}
