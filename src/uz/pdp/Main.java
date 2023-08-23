package src.uz.pdp;

import src.uz.pdp.config.MailConfig;
import src.uz.pdp.util.MailUtils;

import javax.mail.Session;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int id=0;
    private static List<User> dataBase=new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("1.register\n2.get db\nsend file");
        int r=sc().nextInt();
        switch (r){
            case 1->{
                Session session= MailConfig.initAuthSession();
                System.out.println("Ismingizni kiriting: ");
                String ism=sc().next();
                System.out.println("Emailingizni kiritng: ");
                String email=sc().next();
                Random random=new Random();
                int n=random.nextInt(1000,9999);
                String num=String.valueOf(n);
                String toMsg="Dear "+ism+", it's your code \uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47 \n\n\n\n\n"+num;
                MailUtils.sendMessage(session,
                        "no auth mailing"
                        ,"bsobirov8027@gmail.com",email
                        ,
                        toMsg);
                System.out.println("Kodni kiriting: ");
                String pass= sc().next();
                if (num.equals(pass)){System.out.println(
                        "You passed check account\n" +
                                "You need get new password: ");
                    String password=sc().next();

                    User user=new User(id,ism,email,password);
                    id++;
                    dataBase.add(user);
                }
            }case 2->{
                for (int i = 0; i < id; i++) {
                    System.out.println(dataBase.get(i));
                }
            }case 3->{
                Session session= MailConfig.initAuthSession();
                System.out.println("Ismingizni kiriting: ");
                String ism=sc().next();
                System.out.println("Emailingizni kiritng: ");
                String email=sc().next();
                Random random=new Random();
                int n=random.nextInt(1000,9999);
                String num=String.valueOf(n);
                String toMsg="Dear "+ism+", it's your code \uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47 \n\n\n\n\n"+num;
                MailUtils.sendFile(session,
                        "no auth mailing"
                        ,"bsobirov8027@gmail.com",email
                        ,
                        toMsg,new File("src/source/text.txt"));
                System.out.println("sucessfully");
            }
        }


        main(args);
    }
    static Scanner sc(){
        return new Scanner(System.in);
    }
}
class User{
    private int id;
    private String ism,
            email,password;

    public User(int id, String ism, String email, String password) {
        this.id = id;
        this.ism = ism;
        this.email = email;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ism='" + ism + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}