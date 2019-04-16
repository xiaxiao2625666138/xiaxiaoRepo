package com.zxz.wordladder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class ActuatorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accessActuatorOk() throws Exception {
        mockMvc.perform(get("/actuator"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessHealthReturnJson() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"UP\"}"));
    }
    @Test
    public void accessBeansReturnJson() throws Exception {
        mockMvc.perform(get("/actuator/beans"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessMappingsReturnJson() throws Exception {
        mockMvc.perform(get("/actuator/mappings"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessInfoReturnJson() throws Exception {
        mockMvc.perform(get("/actuator/info"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessEnvReturnJson() throws Exception {
        mockMvc.perform(get("/actuator/env"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessAuditEventsReturnJson() throws Exception {
        mockMvc.perform(get("/actuator/auditevents"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessCachesReturnJson() throws Exception {
        mockMvc.perform(get("/actuator/caches"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessHttpTraceReturnJson() throws Exception {
        mockMvc.perform(get("/actuator/httptrace"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessMetricsReturnJson() throws Exception {
        mockMvc.perform(get("/actuator/metrics"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessLoggersReturnJson() throws Exception {
        mockMvc.perform(get("/actuator/loggers"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessScheduledTasks() throws Exception {
        mockMvc.perform(get("/actuator/scheduledtasks"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessThreadDump() throws Exception {
        mockMvc.perform(get("/actuator/threaddump"))
                .andExpect(status().isOk());
    }
    @Test
    public void accessConfigProps() throws Exception {
        mockMvc.perform(get("/actuator/configprops"))
                .andExpect(status().isOk());
    }
}