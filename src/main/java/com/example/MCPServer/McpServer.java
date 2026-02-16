
package com.example.MCPServer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ai.tool.annotation.Tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;


@Service
public class McpServer {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${mcp.crud-base-url}")
    private String crudBaseUrl;

    // --- Tool 1: Get all students ---
    @Tool(name="get_all_students",description = "Fetch all students from CRUD API")
    public Object getAllStudents() {
        String url = crudBaseUrl + "/getAllStudents";
        return restTemplate.getForObject(url, Object.class);
    }

    // --- Tool 2: Get student by ID ---
    @Tool(name="get_a_student",description = "Get student details by the given id")
    public Object getStudentById(int id) {
        String url = crudBaseUrl + "/get/" + id;
        return restTemplate.getForObject(url, Object.class);
    }

    // --- Tool 3: Create new student ---
    @Tool(name="create_a_student",description = "Create a new student record with given student name and id")
    public Object createStudent(String name,int id) {
        String url = crudBaseUrl + "/create";
        Map<String, Object> payload=new HashMap<String,Object>();
        payload.put("id", id);
        payload.put("name", name);
		return restTemplate.postForObject(url, payload, Object.class);
    }

    // --- Tool 4: Delete student ---
    @Tool(name="delete_a_student",description = "Delete a student by ID")
    public Object deleteStudent(int id) {
        String url = crudBaseUrl + "/delete/" + id;
        restTemplate.delete(url);
        return Map.of("status", "deleted", "id", id);
    }
    
    // --- Tool 4: Update student ---
    @Tool(name="update_a_student",description = "Update a student by ID")
    public Object updateStudent(int id,String name) {
    	  Map<String, Object> payload=new HashMap<String,Object>();
          payload.put("id", id);
          payload.put("name", name);
        String url = crudBaseUrl + "/update/" + id;
        restTemplate.put(url,payload);
        return Map.of("status", "updated", "id", id);
    }
}
