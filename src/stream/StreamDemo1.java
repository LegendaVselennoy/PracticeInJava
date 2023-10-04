package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo1 {
    public static void main(String[] args) {
        ArrayList<NamePhoneEmail>myList=new ArrayList<>();
        myList.add(new NamePhoneEmail("Larry","555-5555","Larry@h.com"));
        myList.add(new NamePhoneEmail("James","555-4444","James@h.com"));
        myList.add(new NamePhoneEmail("Mary","555-3333","Mary@h.com"));

        Stream<NamePhone>nameAndPhone=myList.stream().map(
                (a)->new NamePhone(a.name,a.phonenum)
        );

        List<NamePhone>npList=nameAndPhone.collect(Collectors.toList());
        System.out.println("Names and phone list ");
        for (NamePhone e : npList) {
            System.out.println(e.name+": "+e.phonenum);
        }

        nameAndPhone=myList.stream().map(
                (a)->new NamePhone(a.name,a.phonenum)
        );

        Set<NamePhone>npSet=nameAndPhone.collect(Collectors.toSet());
        System.out.println("\nNames and phone set ");
        for (NamePhone e : npSet) {
            System.out.println(e.name+": "+e.phonenum);
        }
    }
}

class NamePhoneEmail{
    String name;
    String phonenum;
    String email;

    NamePhoneEmail(String n,String p,String e){
        name=n;
        phonenum=p;
        email=e;
    }
}

class NamePhone{
    String name;
    String phonenum;

    NamePhone(String n,String p){
        name=n;
        phonenum=p;
    }
}
