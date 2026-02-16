package com.example.MCPServer;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpServerSession;

@SpringBootApplication
public class McpServerApplication {

	 

    public static void main(String[] args) {
    	 SpringApplication.run(McpServerApplication.class, args);
      
    }

    @Bean
    public List<ToolCallback> tools(McpServer mcpServer) {
        //System.err.println("=== TOOLS BEAN CALLED ===");
        List<ToolCallback> callbacks = List.of(ToolCallbacks.from(mcpServer));
        //System.err.println("Number of tools registered: " + callbacks.size());
        return callbacks;
    }
    
}
