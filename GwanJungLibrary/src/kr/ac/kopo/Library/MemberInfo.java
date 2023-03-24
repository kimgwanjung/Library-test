package kr.ac.kopo.Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberInfo implements Member {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner sc = new Scanner(System.in);
    private List<MemberData>list = new ArrayList<MemberData>();
    private MemberData userdb;
    String MemberScv = "Member";
    MemberData db = new MemberData();
    String filename = "Members.csv";
    
    //생성자
    public MemberInfo() {
        MemberData db = new MemberData();
        db.setId("123");
        db.setName("관리자(김관중)");
        db.setAddress("경기도 부천시 범박동");
        db.setPhone("010-9217-5176");
        db.setPublishedDate("1998-08-15");
        list.add(db);
    }
    // 로그인
    public void logIn() {
        System.out.println("로그인");
        String id;
        
        try {
            System.out.print("로그인 할 ID를 입력하요. : ");
            id=br.readLine();
            
            MemberData db = readUser(id);
            if(db == null) {
                System.out.println("그런 ID는 존재하지 않아요");
                return;
            } else {
                userdb = db;
                System.out.println("로그인이 되었습니다.");
                return;
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        
    }
    // 회원등록
    public void join() {
        System.out.println("회원등록");
        try {
            System.out.print("아이디를 입력해주세요. : ");
            db.setId(br.readLine());
            
            if(readUser(db.getId()) != null) {
                System.out.println("이미 등록된 아이디 입니다.");
                return;
            } else {
                System.out.println("사용가능한 아이디 입니다.");
            }
            System.out.print("이름을 입력해주세요. : ");
            db.setName(br.readLine());
            System.out.print("주소를 입력하세요. : ");
            db.setAddress(br.readLine());
            System.out.print("생년월일을 입력하세요 : ");
            db.setPublishedDate(br.readLine());
            System.out.print("전화번호를 입력해주세요. (010-9217-5176) : ");
            db.setPhone(br.readLine());
            String[] phoneNum = db.getPhone().split("-");
            String Num1 = phoneNum[0];
            String Num2 = phoneNum[1];
            String Num3 = phoneNum[2];
            if(!(Num1.equals("010"))) {
                System.out.println("핸드폰 번호는 010으로 시작합니다.");
                return;
            }
            if(!(Num2.length() == 4 && Num3.length() == 4)) {
                System.out.println("핸드폰 번호를 잘못 입력하셨습니다.");
                return;
            }
                
            System.out.println("이름\t생년월일\tID\t핸드폰번호");
            System.out.println(db.getName() + "\t" + db.getAddress() + "\t" + db.getId() + "\t" + db.getPhone()); 
            System.out.print("입력하신 정보가 맞나요?(y/n) : ");
            String ans = sc.nextLine();
            
            if(ans.equals("y")||ans.equals("Y")) {
                list.add(db);               
                FileWriter fw = new FileWriter(filename, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(db.getName() + "," + db.getAddress() + "," + db.getId() + "," + db.getPhone());
                pw.close();

                // 회원 가입 축하 메시지 출력
                System.out.println("회원가입을 축하합니다.");
                } else if(ans.equals("n")||ans.equals("N")) {
                    System.out.println("회원가입이 취소되었습니다.");
                    } else {
                        System.out.println("y또는n만 입력해주세요.");
                        System.out.println("회원정보를 다시 입력해주세요.");
                        join();
                    }
        }catch(Exception e) {
           
        }
 
    }
    //회원 삭제
    public void userDelete() {
        System.out.println("회원 삭제");
        try {
            System.out.print("삭제할 회원의 아이디를 입력해주세요. : ");
            String deleteId = br.readLine();

            boolean isExist = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(deleteId)) {
                    isExist = true;
                    list.remove(i);
                    break;
                }
            }

            if (!isExist) {
                System.out.println("삭제할 회원이 존재하지 않습니다.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (!fields[0].equals(deleteId)) {  
                    sb.append(line).append("\n");
                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(sb.toString());
            writer.close();

            System.out.println("회원 삭제가 완료되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //회원 수정
    @Override
    public void userUpdate() {
        // TODO Auto-generated method stub
        
    }
    

    // 회원조회
    private MemberData readUser(String id) {
        MemberData db = null;
        for(MemberData data : list) {
            if(data.getId().equals(id)) {
                db = data;
                break;
            }
        }
        return db;
    }
    //회원 전체 출력
    public void userPrint() {
        String line = "";
        String csvsplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                // CSV 파일의 각 라인을 그대로 출력
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //로그아웃
    @Override
    public void logOut() {
        // TODO Auto-generated method stub
        
    }
    //회원 검색
    @Override
    public void userSearch() {
        // TODO Auto-generated method stub
        
    }
    // MemberData 정보 읽어오기
    public ArrayList<String[]> readCsv(String fileName) {
        ArrayList<String[]> values = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                values.add(fields);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }
    
    public ArrayList<MemberData> readCSV() {
        ArrayList<MemberData> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Members.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                String name = values[1];
                String address = values[2];
                String phone = values[3];
                members.add(new MemberData(id, name, address, phone));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }
    
    //쓰기
    public void writeCSV(List<MemberData> members) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Members.csv"))) {
            for (MemberData member : members) {
                writer.println(member.getId() + "," + member.getName() + "," + member.getPhone());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //  열기
    @Override
    public boolean open() {
        // TODO Auto-generated method stub
        return false;
    }
    //  닫기
    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }
}






