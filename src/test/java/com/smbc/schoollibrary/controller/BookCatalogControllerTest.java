package com.smbc.schoollibrary.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smbc.schoollibrary.constants.StatusBookEnum;
import com.smbc.schoollibrary.constants.ApiConstant.ResponseMessage;
import com.smbc.schoollibrary.controllers.BookCatalogController;
import com.smbc.schoollibrary.dto.ListBookPojo;
import com.smbc.schoollibrary.dto.SMBCResponseDto;
import com.smbc.schoollibrary.services.BookCatalogService;
import com.smbc.schoollibrary.utils.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(BookCatalogController.class)
public class BookCatalogControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private BookCatalogService bookCatalogService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void getAllBookCatalog_StatusBook_Available_Test() throws Exception {

                List<ListBookPojo> books = new ArrayList<>();
                books.add(new ListBookPojo(1, "BC1001", "The Great Adventure", "Fiction", "John Doe", "HarperCollins",
                                Date.valueOf("2022-03-15"), "Available"));

                books.add(new ListBookPojo(2, "BC1002", "Science of Tomorrow update", "Science", "Alice Smith",
                                "Penguin Books",
                                Date.valueOf("2021-06-10"), "Available"));

                when(bookCatalogService.getAllBookCatalog(StatusBookEnum.AVAILABLE))
                                .thenReturn(ResponseUtil.success(books, ResponseMessage.SUCCESS_RETRIEVE_DATA));

                RequestBuilder request = MockMvcRequestBuilders
                                .get("/book-catalog")
                                .param("availability", "AVAILABLE")
                                .accept(MediaType.APPLICATION_JSON);

                MvcResult result = mockMvc.perform(request)
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andReturn();

                SMBCResponseDto<Object> response = ResponseUtil.success(books, ResponseMessage.SUCCESS_RETRIEVE_DATA);
                String expectedJson = objectMapper.writeValueAsString(response);

                JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
        }

        @Test
        public void getAllBookCatalog_StatusBook_Unavailable_Test() throws Exception {

                List<ListBookPojo> books = new ArrayList<>();
                books.add(new ListBookPojo(1, "BC1001", "The Great Adventure", "Fiction", "John Doe", "HarperCollins",
                                Date.valueOf("2022-03-15"), "Not_Available"));

                books.add(new ListBookPojo(2, "BC1002", "Science of Tomorrow update", "Science", "Alice Smith",
                                "Penguin Books",
                                Date.valueOf("2021-06-10"), "Not_Available"));

                when(bookCatalogService.getAllBookCatalog(StatusBookEnum.NOT_AVAILABLE))
                                .thenReturn(ResponseUtil.success(books, ResponseMessage.SUCCESS_RETRIEVE_DATA));

                RequestBuilder request = MockMvcRequestBuilders
                                .get("/book-catalog")
                                .param("availability", "NOT_AVAILABLE")
                                .accept(MediaType.APPLICATION_JSON);

                MvcResult result = mockMvc.perform(request)
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andReturn();

                SMBCResponseDto<Object> response = ResponseUtil.success(books,
                                ResponseMessage.SUCCESS_RETRIEVE_DATA);
                String expectedJson = objectMapper.writeValueAsString(response);

                JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
        }

}
