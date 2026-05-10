# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Craftly** is a Spring Boot 4.0.0 backend application for a project collaboration and code management platform. The system manages user projects, team collaboration, billing/subscriptions, and integrates with Stripe for payments.

## Build & Development Commands

### Prerequisites
- Java 21
- Maven (via included Maven wrapper: `mvnw` or `mvnw.cmd` on Windows)
- PostgreSQL 9000 (configured in application.yaml)

### Build Commands
``
# Build the project
./mvnw clean package

# Build without running tests
./mvnw clean package -DskipTests

# Run the application
./mvnw spring-boot:run

# Run tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=CraftlyApplicationTests

# Check dependencies
./mvnw dependency:tree
``

## Architecture Overview

### Technology Stack
- **Framework**: Spring Boot 4.0.0
- **Language**: Java 21
- **ORM**: JPA/Hibernate with PostgreSQL
- **Authentication**: JWT (JJWT 0.12.6)
- **Security**: Spring Security with method-level authorization
- **Object Mapping**: MapStruct 1.6.3
- **Password Encoding**: BCrypt
- **Payments**: Stripe (v32.1.0)
- **Utilities**: Lombok, Jakarta Validation

### Core Layers

#### 1. Controllers (src/main/java/com/avish/Craftly/controller/)
HTTP entry points organized by domain:
- AuthController: /api/auth - signup, login, profile retrieval
- ProjectController: /api/projects - CRUD operations for projects
- ProjectMemberController: /api/projects/{projectId}/members - team collaboration
- FileController: /api/projects/{projectId}/files - project file structure and content
- BillingController: /api/plans, /api/payments, /api/me/subscription - billing and Stripe integration
- usageController: /api/usage - usage tracking and plan limits

#### 2. Services (src/main/java/com/avish/Craftly/service/)
Business logic layer with both interfaces and implementations:

**Core Services:**
- AuthService: User signup/login with JWT token generation
- ProjectService: Project lifecycle (create, read, update, soft delete)
- ProjectMemberService: Team member management with role-based access
- FileService: Project file tree traversal and content retrieval
- UserService: User profile operations

**Billing Services:**
- SubscriptionService: User subscription management
- PlanService: Billing plan information (max projects, tokens per day, AI preview limits)
- PaymentProcessor (interface): Payment processing abstraction
- StripePaymentProcessor: Stripe-specific implementation for checkout and customer portal

**Tracking Services:**
- UsageService: Track daily usage against plan limits

#### 3. Entities (src/main/java/com/avish/Craftly/entity/)
Domain models with JPA annotations:

**User and Auth:**
- User: Implements UserDetails for Spring Security integration; supports soft delete via deletedAt

**Projects and Files:**
- Project: Core project entity with soft-delete and performance indexes
- ProjectFile: File metadata for files stored in external storage (references Minio object keys)
- ProjectMember: Join table with @EmbeddedId (composite key); tracks user roles and invitation status
- ProjectMemberId: Composite primary key (projectId + userId)

**Billing:**
- Plan: Subscription plan with Stripe price ID; defines limits (maxProjects, maxTokenPerDay, maxPreviews, unlimitedAi)
- Subscription: User subscription tracking with Stripe customer/subscription IDs and renewal period

**AI Features (Partial):**
- Preview: AI-generated code preview (entity exists but implementation incomplete)
- ChatSession and ChatMessage: Conversation history (infrastructure in place)
- UsageLog: Action and token tracking for usage analytics

#### 4. Security (src/main/java/com/avish/Craftly/security/)

**Authentication:**
- JwtAuthFilter: OncePerRequestFilter extracting Bearer tokens from Authorization header
- AuthUtil: JWT generation/verification using JJWT; extracts userId and username from tokens; provides getCurrentUserId() helper
- JwtUserPrincipal: DTO holding userId, username, and authorities for security context

**Configuration:**
- WebSecurityConfig: Stateless session management; allows /api/auth/** unauthenticated; requires JWT for all other endpoints; BCrypt password encoding

#### 5. Repositories (src/main/java/com/avish/Craftly/repository/)
JPA repositories for data access:
- UserRepository: Lookup by username for authentication
- ProjectRepository: Custom queries for user-accessible projects and soft-delete filtering
- ProjectMemberRepository: Project team member queries
- PlanRepository: Billing plan lookups

#### 6. DTOs (src/main/java/com/avish/Craftly/dto/)
Request/response objects organized by domain:
- auth: SignupRequest, LoginRequest, AuthResponse, UserProfileResponse
- project: ProjectRequest, ProjectResponse, ProjectSummaryResponse, FileNode, FileContentResponse
- subscription: PlanResponse, SubscriptionResponse, CheckoutRequest, CheckoutResponse, PortalResponse, PlanLimitsResponse, UsageTodayResponse
- member: InviteMemberRequest, MemberResponse, UpdateMemberRoleRequest

#### 7. Mappers (src/main/java/com/avish/Craftly/mapper/)
MapStruct-based DTO converters:
- UserMapper: User to UserProfileResponse
- ProjectMapper: Project to ProjectResponse/ProjectSummaryResponse
- ProjectMemberMapper: ProjectMember to MemberResponse

#### 8. Enums (src/main/java/com/avish/Craftly/enums/)
- ProjectRole: OWNER, EDITOR, VIEWER (member access levels)
- ProjectPermission: Fine-grained permission definitions for authorization
- SubscriptionStatus: ACTIVE, PAUSED, CANCELLED, EXPIRED
- PreviewStatus: Status tracking for AI-generated code previews
- MessageRole: USER, ASSISTANT (for chat messages)

#### 9. Error Handling (src/main/java/com/avish/Craftly/error/)
- GlobalExceptionHandler: Centralized exception mapping to REST responses
- ResourceNotFoundException: 404 responses
- BadRequestException: Validation errors
- ApiError: Standard error response structure

## Database Configuration

**Connection**: PostgreSQL at localhost:9000/testDB

**Credentials**:
- Username: testUser
- Password: password

**Settings**:
- Schema: public
- DDL Strategy: update (auto-migrate schema)
- Timezone: UTC (set both in Hibernate config and JVM startup)

**Key Indexes** (on projects table):
- idx_project_updated_at_dexc: Optimizes filtering by update time
- idx_project_deleted_at_dexc: Soft-delete queries
- idx_project_deleted_at_updated_at_desc: Combined soft-delete and ordering

## Authentication and Authorization

### JWT Flow
1. User calls /api/auth/signup or /api/auth/login which calls AuthService to generate JWT
2. JWT token includes userId and username; expires in 10 minutes
3. Client includes token in Authorization: Bearer <token> header
4. JwtAuthFilter validates token on each request
5. SecurityContextHolder stores JwtUserPrincipal for request context

### Method-Level Security
- @EnableMethodSecurity configured in WebSecurityConfig
- Use @PreAuthorize or @PostAuthorize on controller/service methods for role-based access
- ProjectRole enum defines OWNER/EDITOR/VIEWER levels

## Key Architectural Patterns

### Service-Oriented Architecture
- Controllers delegate to service interfaces (not implementations)
- Services encapsulate business logic and repository interactions
- Allows easy testing and future implementation swaps (e.g., alternate payment processors)

### Soft Deletes
- All entities with temporal needs include deletedAt timestamp
- Repository queries filter by deletedAt IS NULL to exclude deleted records
- Supports data retention and auditing

### Role-Based Access Control (RBAC)
- ProjectMember ties users to projects with specific roles
- ProjectRole enum (OWNER/EDITOR/VIEWER) defines access levels
- Fine-grained permissions defined in ProjectPermission enum

### External Integrations
- **Stripe**: Payment processing via StripePaymentProcessor (incomplete implementation)
- **Minio** (implied): File storage references ProjectFile.minioObjectKey

## Important Implementation Notes

### Incomplete Features
- StripePaymentProcessor.createCheckoutSessionUrl() and openCustomerPortal() are stub implementations
- Preview entity exists but AI code generation flow is not implemented
- ChatSession and ChatMessage infrastructure exists but conversation flow incomplete
- FileServiceImpl implementation should fetch files from Minio based on projectId/path

### Hardcoded Values
- AuthController.getProfile() and usageController hardcode userId = 1L (should extract from JWT)
- BillingController hardcodes userId = 1L (should use AuthUtil.getCurrentUserId())
- JWT secret key in application.yaml should be moved to environment variables for production

### Configuration Warnings
- Stripe API keys are in application.yaml (security risk; use environment variables or secrets management)
- show-sql: true and format_sql: true in Hibernate config will impact performance in production

## Testing

**Test Framework**: JUnit 5 (via spring-boot-starter-webmvc-test and spring-boot-starter-data-jpa-test)

**Test Location**: src/test/java/com/avish/Craftly/

**Current Test**: CraftlyApplicationTests (basic context loading)

### Test Coverage Gaps
- No service layer tests
- No controller integration tests
- No repository custom query tests
- No JWT/security tests

## Recent Development History

The codebase evolved through these phases:
1. Initial setup with entities and controller scaffolding
2. API endpoints and ProjectService construction
3. ProjectMember APIs for team collaboration
4. Validation and exception handling
5. JWT Authentication implementation
6. Authorization with ProjectRole (OWNER, EDITOR, VIEWER) and method-level security

## Additional Notes

- **Timezone**: Application explicitly sets JVM timezone to UTC in CraftlyApplication.main() to avoid PostgreSQL timezone mismatches
- **Request Validation**: Jakarta Validation annotations used on DTOs with @Valid in controllers
- **Lombok Usage**: Extensive use of @Getter, @Setter, @RequiredArgsConstructor, and field defaults to reduce boilerplate
- **Session Management**: Stateless (no HTTP sessions); relies entirely on JWT in Authorization header
