SELECT TABLE_NAME FROM USER_TABLES;

SELECT * FROM ROOMMEMBER;
SELECT * FROM PAYMENT;
SELECT * FROM WORKSPACE;
SELECT * FROM CHANNEL;
SELECT * FROM WORKSPACEMEMBER;
SELECT * FROM CHAT;
SELECT * FROM CHANNELMEMBER;
SELECT * FROM CHANNELROOM;
SELECT * FROM MEMBER;
SELECT * FROM ALARM;


// CHANNELMEMEBER -> 로그인한 사람 보이기.
// WORKSPACEMEMBER -> 로그인한 사람 보이기.
// CHAT -> 채팅 검색.

MY를 클릭하면 SEARCH_MEMBER.JSP로 가기.
그리고 그곳에서 일단 자신의 멤버넘과 같은 곳에 속해있는 워스크페이스 넘에따라
값을 쭉 뿌리기.(워크스페이스 넘에따라 )

일단 member_num에 따라 값을 가져오기 -> 워크스페이스 번호 찾기/그에따른 이름 가져오기.
워크스페이스 넘에 따른 접속된 사람 표시하기.(중복값 없애기..?)
일단 사람들 리스트 가져오고 사람들 멤버넘에 따른 이름 멤버테이블에서 가져오기.







