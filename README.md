# Someverse

## 커밋 컨벤션

### 커밋 메시지 형식

```
<type>: <description>
```

### 작성 규칙

- **영어로 작성**: 커밋 메시지는 영어로 작성
- **동사로 시작**: description은 동사 원형으로 시작 (add, fix, update, remove, implement 등)

### Type

| Type | 설명 |
|------|------|
| `feat` | 새로운 기능 추가 |
| `refactor` | 코드 리팩토링 (기능 변경 없음) |
| `add` | 리소스, 컴포넌트, 에셋 추가 |
| `remove` | 파일, 코드 삭제 |
| `fix` | 버그 수정 |
| `docs` | 문서 작성 및 수정 |
| `chore` | 빌드 설정, 패키지 관리 등 유지보수 |

### 예시

```
feat: add feed usecase
feat: implement chat repository
feat: create matching screen
refactor: use Strings for text resources
add: add neutral20, neutral80, gradation colors
remove: delete firebender module
docs: update PR template
chore: update app logo
```
