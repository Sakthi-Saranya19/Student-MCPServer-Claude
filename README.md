# 🎓 Student Management MCP Server

> A Spring Boot application that implements the **Model Context Protocol (MCP)**, allowing AI models like Claude to perform CRUD operations on a student database through tool calling.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![MCP](https://img.shields.io/badge/Protocol-MCP-blue)
![Claude](https://img.shields.io/badge/AI-Claude%20Desktop-purple)

## 🌟 Overview

This project transforms a standard Student Management System into an AI-ready server. By implementing the Model Context Protocol, the application exposes its logic as "tools" that an LLM can invoke dynamically.

**Example interaction:**
> *User:* "Claude, find the student with ID 101 and change their department to Robotics."
> *Claude:* (Calls `update_student` tool via MCP) "I've successfully updated the record for you."

## 🛠️ MCP Tools Exposed

The server provides the following tools to the MCP client (Claude):

* `list_students`: Returns all student records.
* `get_student`: Fetches a single student by ID.
* `add_student`: Creates a new record (requires name, email, dept).
* `update_student`: Modifies existing student data.
* `delete_student`: Removes a student from the database.

## 🏗️ Architecture



1.  **Claude Desktop (Client):** The user interface where prompts are entered.
2.  **MCP Server (This App):** The Spring Boot bridge that translates MCP requests into Java logic.
3.  **Database:** Persistent storage for student records.

## 🚀 Configuration for Claude Desktop

To use this with Claude, you must add the server to your `claude_desktop_config.json`:

1.  Build the project: `./mvnw clean install`
2.  Locate your config file:
    * **macOS:** `~/Library/Application Support/AnthropicCloud/claude_desktop_config.json`
    * **Windows:** `%APPDATA%\AnthropicCloud\claude_desktop_config.json`
3.  Add the configuration:

```json
{
  "mcpServers": {
    "student-manager": {
      "command": "java",
      "args": [
        "-jar",
        "/absolute/path/to/your/project/target/student-mcp-server.jar"
      ]
    }
  }
}
