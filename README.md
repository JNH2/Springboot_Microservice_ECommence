🛒 E-Commerce Microservices Ecosystem | 微服務電商系統

![Java](https://img.shields.io/badge/Java-11%2F17-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.3-brightgreen?style=flat-square&logo=spring)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2021.0.x-blue?style=flat-square&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)
![Stars](https://img.shields.io/github/stars/JNH2/Springboot_Microservice_ECommence?style=flat-square)
![Forks](https://img.shields.io/github/forks/JNH2/Springboot_Microservice_ECommence?style=flat-square)


📜 Attribution | 引用聲明
If you use or redistribute this project, please provide proper attribution and link back to the original repository.
使用或轉載本項目時，請務必標明出處並引用原作者連結。

🏗️ System Architecture | 整體系統架構
This project is built on a standardized Spring Cloud distributed architecture. It adopts a distributed microservices architecture where each service is an independent process, handling specific business logic, and horizontally scaling through REST communication.
本項目基於標準的 Spring Cloud 分佈式架構構建。採用微服務架構，每個服務都是獨立的進程，處理特定業務邏輯，並透過 REST 通訊實現水平擴展。
 * Service Registry (Eureka): The system's central hub, responsible for service discovery, governance, and monitoring.
   服務註冊中心 (Eureka)： 系統中心樞紐，負責服務發現、治理與監控。
 * API Gateway: Unified entry point (Port 9090) for authentication, dynamic routing, and request forwarding.
   API 網關： 統一入口 (端口 9090)，負責安全鑒權、動態路由與請求轉發。
 * Product Service: Independent process handling product management and CRUD operations with MySQL.
   產品服務： 獨立進程，負責產品管理與 MySQL 的 CRUD 操作。
 * Order Service: Order processing module demonstrating load balancing and Remote Procedure Call (RPC) via Feign.
   訂單服務： 訂單處理模塊，演示負載均衡與透過 Feign 實現的遠程過程調用 (RPC)。
 * Payment Service: Core component responsible for processing payment requests and persisting transaction details.
   支付服務： 核心組件，負責處理支付請求並持久化交易明細。

🛠️ Tech Stack | 技術棧
 * Framework: Spring Boot 2.7.3 (Enterprise stability) & Spring Cloud 2021.0.x.
   框架： Spring Boot 2.7.3（確保企業級穩定性）與 Spring Cloud 2021.0.x。
 * Language: Java 11 & Java 17 compatibility.
   語言： 兼容 Java 11 與 Java 17。
 * Persistence: Spring Data JPA / Hibernate with MySQL 8.0.
   持久化： Spring Data JPA / Hibernate 與 MySQL 8.0。
 * Patterns: DTO Pattern, Builder Pattern, and Global Exception Handling.
   設計模式： DTO 模式、Builder 模式以及全域異常處理。
 * Communication: OpenFeign for declarative inter-service calls.
   通信： 使用 OpenFeign 進行聲明式服務間調用。
 * Monitoring: Zipkin & Sleuth for distributed tracing and latency monitoring.
   監控： 使用 Zipkin 與 Sleuth 進行鏈路追踪與延遲監控。

⚠️ Challenges & Solutions | 挑戰與解決方案
1. Architectural Decoupling | 架構解耦
 * Challenge: Preventing internal DB schemas (Entities) from leaking to the API layer, causing high coupling.
   挑戰： 防止資料庫實體 (Entity) 結構直接暴露於 API 層，導致架構高度耦合。
 * Solution: Implemented ProductRequest and ProductResponse DTOs to abstract the data layer.
   解決方案： 實作 ProductRequest 與 ProductResponse 作為數據傳輸物件 (DTO) 以抽象化數據層。
2. Dependency & Environment | 依賴與環境
 * Challenge: Runtime incompatibility between Java 11 and Spring Boot 3.x; 401 Unauthorized errors during testing.
   挑戰： Java 11 與 Spring Boot 3.x 之間的運行時不相容；測試期間遇到 401 未授權錯誤。
 * Solution: Strategic downgrade to Spring Boot 2.7.3 and excluded SecurityAutoConfiguration during development.
   解決方案： 策略性降級至 Spring Boot 2.7.3，並在開發階段排除安全自動配置以加速測試。
3. Order Service Specific Issues | 訂單服務特定問題
 * Errors: 400 Bad Request (wrong JSON keys), Port 8082 occupied, and Git push rejections.
   錯誤： 400 錯誤（JSON 鍵名錯誤）、8082 端口被佔用、以及 Git 推送被拒絕。
 * Solutions: Corrected key to productId, used fuser -k to kill processes, and applied git pull --rebase.
   解決方案： 修正鍵名為 productId，使用 fuser -k 關閉進程，並使用 rebase 解決 Git 衝突。
💻 Technical Operations | 技術操作
Infrastructure (Docker & SQL) | 基礎設施
-- Create Payment Database
CREATE DATABASE payment_db;
-- View Transactions
USE payment_db;
SELECT * FROM TRANSACTION_DETAILS;

# Provision MySQL Instance
docker run --name mysql-product-service -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=springbootdb -p 3306:3306 -d mysql:8.0

# Shell Access
docker exec -it mysql-product-service mysql -u root -p

Build & Run | 構建與運行
# Clean and Run application skipping tests
mvn clean install -DskipTests
mvn spring-boot:run

# Kill Process on 8082
fuser -k 8082/tcp

Git Workflow | Git 工作流
git init
git add .
git commit -m "feat: complete microservice integration"
git branch -M main
# Sync and Push
git stash -> git pull --rebase -> git stash pop -> git push -u origin main

🧪 Testing Guide | 測試指南
Full-Link Test | 全鏈路測試
 * Start Services: Eureka Server → Business Services (Product/Order/Payment) → API Gateway.
   啟動服務： 依次運行 註冊中心 → 各業務微服務 → API 網關。
 * Postman Call: Send a POST request to http://localhost:9090/order/placeOrder.
   接口調用： 向網關 9090 端口發送下單請求。
 * Trace: Observe request flow from Gateway to Order to Payment in Zipkin.
   追蹤： 在 Zipkin 面板觀察請求如何從網關流向訂單，最後抵達支付服務。

📐 Architecture Diagrams | 架構設計圖
System Flow Diagram
      [ User / Postman ]
             │
      ▼ [ API Gateway: 9090 ] ───► (Unified Entry / Auth / Dynamic Routing)
             │                      (統一入口 / 安全鑒權 / 內容路由)
      ┌──────┴──────┬──────────────┐
      ▼             ▼              ▼
[Order Service] [Payment Service] [Product Service]  (Business Logic)
 (Port: 8082)    (Port: 8083)      (Port: 8081)       (核心業務邏輯)
      │             │              │
      └──────┬──────┴──────┬───────┘
             │             │
      ▼ [ Eureka Server ]  ▼ [ Zipkin/Sleuth ]
        (Service Discovery)   (Tracing & Latency Monitoring)

Component Interaction
       [ User Request ]
              |
      [ Service Registry ] <---- (Heartbeat) ---- [ Product Service ]
         (Eureka: 8761)                          (Port: 8081)
              ^                                       |
              |                                 [ MySQL DB ]
      [ Order Service ] <-----------------------------/
         (Port: 8082)

下一步建議：
記得將徽章代碼中的 YOUR_USERNAME/REPO_NAME 替換成你真實的 GitHub 用戶名和倉庫名。
你需要我幫你寫一份 OpenFeign 的 Java 接口配置代碼，讓 Order Service 能正式「求人辦事」去調用 Product Service 嗎？
