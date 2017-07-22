package com.github.bassaer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.lang.reflect.Method

import static org.hamcrest.CoreMatchers.containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Test of MainController
 * Created by nakayama on 2017/07/22.
 */
@WebMvcTest
class MainControllerTest extends Specification {

    @Autowired
    MockMvc mvc

    def "get Main"() {
        expect:
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().string(containsString("Main View")))

    }

    def "load setting file"() {
        setup:
        def mainController = new MainController()
        Method method = MainController.getDeclaredMethod("loadConf")
        method.setAccessible(true)
        String setting = (String)method.invoke(mainController)

        expect:
            setting.is(MainController.LOADING_ERROR)

    }

}
