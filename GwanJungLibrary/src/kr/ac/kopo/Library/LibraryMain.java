package kr.ac.kopo.Library;

import java.io.IOException;
import java.util.Scanner;

public class LibraryMain {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        MemberInfo user = new MemberInfo();
        BookInfo book = new BookInfo();
        BookInfo info = new BookInfo();
        int num;
        while(true) {
            do {
                System.out.println("1.회원관리 2.도서관리 3.종료");
                num = sc.nextInt();
                sc.nextLine();
            }while(num < 1 || num > 3);
            if(num == 3) {
                System.out.println("종료할게요");
                break;
            }
            
            switch(num) {
                // 1.회원관리
                case 1:
                    System.out.println("0.로그인 1.회원등록 2.회원수정 3.회원삭제 4.회원전체조회 ");
                    num = sc.nextInt();
                    switch(num) {
                        case 0:
                            // 로그인
                            user.logIn();
                            break;
                        case 1:
                            // 회원등록
                            user.join();
                            break;
                        case 2:
                            // 회원수정
                            //user.userUpdate();
                            break;
                            
                        case 3:
                            // 회원삭제
                            user.userDelete();
                            break;
                        case 4:
                            // 회원전체조회
                            user.userPrint();
                            break;                   
                    }
                    break;
                    
                case 2:
                    // 2.도서관리
                    do {
                        System.out.println("1.도서 대출 관련 2.도서추가 3.도서삭제 4.모든 도서 출력");
                        num = sc.nextInt();
                        sc.nextLine();
                    }while(num < 1 || num > 7);                   
                    switch(num){
                        case 1:
                            // 도서 대출 관련 --> 1.도서 대출 2.도서 전체 출력 3.
                            info.borrow();
                            break;
                        case 2:
                            // 도서 추가
                            book.bookAdd();
                            break;
                        case 3:
                            // 도서 삭제
                            book.bookDelete();
                            break;
                        case 4:
                            //도서 모두 출력
                            book.printBookList();
                        default:
                            break;
                    }
                    
                    
                    
            }
        }
        
    }

}
