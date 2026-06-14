# Java GUI Chess Game

Java Swing과 AWT를 활용하여 객체지향 프로그래밍(OOP) 기반으로 제작한 2인용 데스크톱 체스 게임입니다. 
시각적인 체스판(UI)과 백그라운드의 게임 규칙(Logic)을 분리하여 안정적인 체스 플레이 환경을 구현했습니다.

## 주요 기능 (Features)

* **직관적인 그래픽 인터페이스 (GUI):**
  * `GridLayout(8, 8)`을 활용한 정통 체스판 구현
  * 고화질 체스 기물 이미지 부드러운 스케일링(`SCALE_SMOOTH`) 적용
* **체스 룰 및 이동 논리 (Game Logic):**
  * **턴 제어 시스템:** 백(White)과 흑(Black)이 번갈아가며 플레이 (창 제목에 현재 턴 표시)
  * **아군 보호 로직:** 이동하려는 위치에 아군 기물이 있을 경우 이동 불가 처리
  * **기물별 이동 규칙 검사:** `isValidMove` 메서드를 통한 수학적 이동 경로 유효성 판별
* **승패 판정 시스템:**
  * 상대방의 킹(King) 기물 포획 시 승리 팝업창(`JOptionPane`) 출력 및 게임 즉시 종료 처리

## 사용 기술 (Tech Stack)

* **Language:** Java (JDK 8+)
* **GUI Framework:** Java Swing, Java AWT
* **Architecture:** UI(View)와 Logic(Model)의 분리 설계

## 파일 구조 (Project Structure)

프로젝트는 크게 화면을 제어하는 클래스와 논리를 제어하는 클래스로 나뉘어 있습니다.

* `Chess.java`: 메인 실행 클래스. 화면 UI 구성, 마우스 클릭 이벤트(ActionListener) 처리, 턴 및 게임 종료 상태 관리
* `ChessButton.java`: 체스판의 각 칸을 담당하며, 자신의 2차원 배열 좌표(row, col)를 기억하는 커스텀 버튼 객체
* `ChessPiece.java` & 기물 클래스들: 기물의 색상 정보와 고유의 이동 규칙(`isValidMove`)을 정의하는 추상 클래스 및 자식 클래스(Pawn, Rook, Knight, Bishop, Queen, King)

## 향후 개선 목표 (Future Improvements)

### 체스 특수 룰 및 게임 로직 고도화
 *특수 룰 완벽 구현:** 캐슬링(Castling), 앙파상(En Passant), 폰 승급(Promotion), 폰이 처음 움직일 때 ２칸 전진 기능 추가
 *체크/체크메이트 시스템:** 킹이 공격받는 상황(Check)과 피할 수 없는 상태(Checkmate)를 판별하는 정밀 알고리즘 도입
 *경로 막힘 검사(Collision Detection):** 모든 기물 이동 시 중간에 다른 기물이 있는지 판별하는 로직 완비
