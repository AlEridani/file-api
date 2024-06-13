# 클래스 명세서

## 모델 패키지 (`model`)

### User 클래스

#### 설명
시스템의 사용자를 나타냅니다.

#### 필드
- `Long id`: 사용자의 고유 식별자.
- `String username`: 사용자의 이름.
- `String password`: 사용자의 암호화된 비밀번호.

#### 메서드
- `Long getId()`: 사용자의 ID를 반환합니다.
- `void setId(Long id)`: 사용자의 ID를 설정합니다.
- `String getUsername()`: 사용자의 이름을 반환합니다.
- `void setUsername(String username)`: 사용자의 이름을 설정합니다.
- `String getPassword()`: 사용자의 비밀번호를 반환합니다.
- `void setPassword(String password)`: 사용자의 비밀번호를 설정합니다.

## 리포지토리 패키지 (`repository`)

### UserRepository 인터페이스

#### 설명
User 엔티티에 접근하고 조작하는 메서드를 제공합니다.

#### 메서드
- `User findByUsername(String username)`: 사용자 이름으로 사용자를 찾습니다.

## 서비스 패키지 (`service`)

### CustomUserDetailsService 클래스

#### 설명
Spring Security의 UserDetailsService를 구현하여 사용자 세부 정보를 제공합니다.

#### 메서드
- `UserDetails loadUserByUsername(String username)`: 사용자 이름으로 사용자를 로드합니다.

### FileService 인터페이스

#### 설명
파일 관련 비즈니스 로직을 정의합니다.

#### 메서드
- `void saveFile(MultipartFile file)`: 파일을 로컬 파일 시스템에 저장합니다.
- `List<String> listFiles()`: 로컬 파일 시스템의 파일 목록을 반환합니다.
- `Resource loadFile(String filename)`: 로컬 파일 시스템에서 파일을 로드합니다.
- `void deleteFile(String filename)`: 로컬 파일 시스템에서 파일을 삭제합니다.

### FileServiceImpl 클래스

#### 설명
FileService 인터페이스를 구현합니다.

#### 메서드
- `void saveFile(MultipartFile file)`: 파일을 로컬 파일 시스템에 저장합니다.
- `List<String> listFiles()`: 로컬 파일 시스템의 파일 목록을 반환합니다.
- `Resource loadFile(String filename)`: 로컬 파일 시스템에서 파일을 로드합니다.
- `void deleteFile(String filename)`: 로컬 파일 시스템에서 파일을 삭제합니다.

## 보안 패키지 (`security`)

### SecurityConfig 클래스

#### 설명
Spring Security 설정을 정의합니다.

#### 메서드
- `void configure(AuthenticationManagerBuilder auth)`: 사용자 인증을 설정합니다.
- `void configure(HttpSecurity http)`: HTTP 보안을 설정합니다.
- `PasswordEncoder passwordEncoder()`: 비밀번호 인코더를 설정합니다.
- `AuthenticationManager authenticationManagerBean()`: 인증 매니저를 설정합니다.

## 유틸리티 패키지 (`util`)

### JwtTokenUtil 클래스

#### 설명
JWT 토큰을 생성하고 유효성을 검사하는 유틸리티 클래스입니다.

#### 메서드
- `String extractUsername(String token)`: 토큰에서 사용자 이름을 추출합니다.
- `Date extractExpiration(String token)`: 토큰의 만료 날짜를 추출합니다.
- `String generateToken(String username)`: 주어진 사용자 이름으로 JWT 토큰을 생성합니다.
- `Boolean validateToken(String token, String username)`: 토큰의 유효성을 검사합니다.

### JwtRequestFilter 클래스

#### 설명
JWT 토큰을 필터링하고 유효성을 검사하는 필터 클래스입니다.

#### 메서드
- `void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)`: 요청을 필터링하고 JWT 토큰의 유효성을 검사합니다.

## 컨트롤러 패키지 (`controller`)

### AuthController 클래스

#### 설명
사용자 인증을 처리하는 컨트롤러 클래스입니다.

#### 메서드
- `String createAuthenticationToken(AuthenticationRequest authenticationRequest)`: 사용자 인증 토큰을 생성합니다.

### FileController 클래스

#### 설명
파일 업로드, 다운로드 및 삭제를 처리하는 컨트롤러 클래스입니다.

#### 메서드
- `String uploadFile(MultipartFile file, Model model)`: 파일을 업로드합니다.
- `String listFiles(Model model)`: 파일 목록을 보여줍니다.
- `ResponseEntity<Resource> downloadFile(String filename)`: 파일을 다운로드합니다.
- `ResponseEntity<?> deleteFile(String filename)`: 파일을 삭제합니다.

### UserController 클래스

#### 설명
사용자 등록 및 로그인 처리를 위한 컨트롤러 클래스입니다.

#### 메서드
- `String showRegistrationForm(Model model)`: 회원가입 폼을 보여줍니다.
- `String register(User user)`: 사용자를 등록합니다.
- `String showLoginForm()`: 로그인 폼을 보여줍니다.

## 기타 클래스

### AuthenticationRequest 클래스

#### 설명
사용자 인증 요청을 나타내는 클래스입니다.

#### 필드
- `String username`: 사용자 이름.
- `String password`: 비밀번호.

#### 메서드
- `String getUsername()`: 사용자 이름을 반환합니다.
- `void setUsername(String username)`: 사용자 이름을 설정합니다.
- `String getPassword()`: 비밀번호를 반환합니다.
- `void setPassword(String password)`: 비밀번호를 설정합니다.