//package com.meli.xmen.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//@AutoConfigureJsonTesters
//public class AnalizeDNAControllerIntegrationTest {
//    @Autowired
//    private MockMvc mvc;
//
//    List<String> adn = new ArrayList<>();
//
//
//    @Test
//    public void testOnlyHorizontal() throws Exception {
//        adn.add("CTGCGA");
//        adn.add("CTGTAC");
//        adn.add("TTATCT");
//        adn.add("AGAAGG");
//        adn.add("TAGCTA");
//        adn.add("AAAATG");
//
//        String json ="{\"dna\":"+ asJsonString(adn)+"}";
//        System.out.println("JSON: "+json);
//        mvc.perform( MockMvcRequestBuilders
//                        .post("/mutant/")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden());
//    }
//    @Test
//    public void testHorizontalAndVertical() throws Exception {
//        adn.add("ATGCGA");
//        adn.add("CTGTGC");
//        adn.add("TTATGT");
//        adn.add("AGAAGG");
//        adn.add("CCCCTA");
//        adn.add("TCACTG");
//
//        String json ="{\"dna\":"+ asJsonString(adn)+"}";
//        System.out.println("JSON: "+json);
//        mvc.perform( MockMvcRequestBuilders
//                        .post("/mutant/")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}