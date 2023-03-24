package kr.ac.kopo.Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Loan {
    BookData b = new BookData();
    MemberData m = new MemberData();
    Scanner sc = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Map<String, BookData> map = new HashMap<>();
    DecimalFormat df = new DecimalFormat("000");
    private Calendar now = Calendar.getInstance();
    public void borrow() { 
        System.out.println("1.도서대출 2.도서대출목록");
        int num = sc.nextInt();
        sc.nextLine();
       
        switch(num) {
            case 1:
                try {
                    System.out.println("대출하기 원하는 코드번호: ");
                    String code = br.readLine();
                    BookData db = map.get(code);

                    if (map.get(code) == null) {
                        System.out.println("없어요~");
                    }

                    if (db.isRental() && db.getStock() == 0) {
                        System.out.println("이미 대여중입니다.");
                        return;
                    }

                    System.out.println("몇 권 빌리시나요?");
                    int bookNum = sc.nextInt();
                    sc.nextLine();

                    if (bookNum > db.getStock()) {
                        System.out.println("재고보다 많이 빌릴 수 없습니다.");
                        System.out.println("다시 시도해주세요...");
                        borrow();
                    } else {
                        db.setStock(db.getStock() - bookNum);
                        System.out.println("번호\t책제목\t저자명\t재고");
                        System.out.println("========================================================================");
                        System.out.print(code + "\t");
                        System.out.print(db.getName() + "\t");
                        System.out.print(db.getAuthor() + "\t");
                        System.out.print(db.getStock() + "\n");
                        System.out.println("대여완료!");

                        Calendar now = Calendar.getInstance();
                        int lastday = now.getActualMaximum(Calendar.DAY_OF_MONTH);

                        // 대여시간 포맷팅
                        String rentalPattern = "yyyy년 M월 d일";
                        SimpleDateFormat rentalSdf = new SimpleDateFormat(rentalPattern);
                        String rentalDate = rentalSdf.format(now.getTime());
                        String returnDate;
                        System.out.println("대여시간 : " + rentalDate);

                        // 반납시간 계산 및 포맷팅
                        now.add(Calendar.DAY_OF_MONTH, 14);
                        if(now.get(Calendar.DAY_OF_MONTH) <= lastday) {
                            String returnPattern = "yyyy년 M월 d일";
                            SimpleDateFormat returnSdf = new SimpleDateFormat(returnPattern);
                            returnDate = returnSdf.format(now.getTime());
                            System.out.println("반납시간 : " + returnDate);
                        } else {
                            now.add(Calendar.MONTH, 1);
                            now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - lastday);
                            String returnPattern = "yyyy년 M월 d일";
                            SimpleDateFormat returnSdf = new SimpleDateFormat(returnPattern);
                            returnDate = returnSdf.format(now.getTime());
                            System.out.println("반납시간 : " + returnDate);
                        }

                        // 파일 입출력 기능 추가
                        String fileName = "Book.csv";
                        File file = new File(fileName);
                        FileWriter fw = new FileWriter(file);
                        BufferedWriter bw = new BufferedWriter(fw);

                        for (String key : map.keySet()) {
                            BookData book = map.get(key);
                            String line = book.getId() + "," + book.getName() + "," + book.getAuthor() + "," + book.getStock() + "," + book.isRental();
                            if (key.equals(code)) {
                                line += "," + rentalDate + "," + returnDate; // 대여 날짜와 반납 날짜 추가
                            }
                            bw.write(line);
                            bw.newLine();
                        }

                        bw.flush();
                        bw.close();
                        fw.close();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                break;
        case 2 :
            try {
                BufferedReader br = new BufferedReader(new FileReader("Book.csv"));
                String line = "";
                System.out.println("번호\t책제목\t저자명\t재고\t대여상태\t빌린날짜\t\t\t반납예정날짜");
                System.out.println("======================================================");
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    String code = data[0];
                    String name = data[1];
                    String author = data[2];
                    int stock = Integer.parseInt(data[3]);
                    boolean rental = Boolean.parseBoolean(data[4]);
                    String rentalDate = data[5];
                    String returnDate = data[6];
                    System.out.print(code + "   ");
                    System.out.print(name + "   ");
                    System.out.print(author + "   ");
                    System.out.print(stock + "   ");
                    System.out.print(rental + " ");
                    System.out.print(rentalDate + "  ");
                    System.out.println(returnDate);
                }
                br.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            break;
        case 3:
            try {
                System.out.println("연장 원하는 코드번호 : ");
                String code = br.readLine();
                BookData db = map.get(code);
                
                if(db.isRental()==false) {
                    System.out.println("대출중이 아닙니다.");
                    return;
                }
                
                System.out.println("번호\t책제목\t책장르\t저자명\t재고");
                System.out.println("===================================");
                System.out.print(code + "\t");
                System.out.print(db.getName() + "\t");
                System.out.print(db.getAuthor() + "\t");
                System.out.print(db.getStock() + "\n");
                
                System.out.print("입력하신 정보가 맞나요?(y/n) : ");
                String ans = sc.nextLine();
                if(ans.equals("y")||ans.equals("Y")) {
                    String pattern = "yyyy년 M월 d일";
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    String data = sdf.format(new Date(num));
                    System.out.println("대여시간 : " + data);
                    int lastday = now.getActualMaximum(Calendar.DAY_OF_MONTH);
                    if(now.get(Calendar.DAY_OF_MONTH)+7 <= lastday) {
                        System.out.println("연장 후 반납시간 : " + now.get(Calendar.YEAR) + "년 " + (now.get(Calendar.MONTH)+1) + "월 " + (now.get(Calendar.DAY_OF_MONTH)+7) + "일");
                    } else {
                        System.out.println("연장 후 반납시간 : " + now.get(Calendar.YEAR) + "년 " + (now.get(Calendar.MONTH)+2) + "월 " + (now.get(Calendar.DAY_OF_MONTH)+7-lastday) + "일");
                    }
                } else if(ans.equals("n")||ans.equals("N")) {
                    System.out.println("취소되었습니다.");
                } else {
                    System.out.println("y 또는 n만 입력해주세요.");
                    System.out.println("다시 시도해주세요.");
                    borrow();
                }
                
            } catch(Exception e) {
                System.out.println(e.toString());
            }
            break;
        default :
            return;
        }
    }

}
