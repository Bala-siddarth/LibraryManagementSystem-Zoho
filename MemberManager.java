package main;
import domains.Member;
import services.IdGenerator;
import services.MemberService;
import java.util.List;
import java.util.Scanner;

public class MemberManager {
    public void registerMemberMenu(Scanner scanner, MemberService memberService) {
        try {
            String memberId = IdGenerator.generateMemberId();
            System.out.print("Enter Member Name: ");
             String name = scanner.nextLine().trim();
            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine().trim();
            Member member = new Member(memberId, name, phoneNumber);
            memberService.registerMember(member);
            System.out.println("Member registered successfully!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchMembersByNameMenu(Scanner scanner, MemberService memberService) {
        System.out.print("Enter member name to search: ");
        String name = scanner.nextLine().trim();
        try {
            List<Member> members = memberService.searchMembersByName(name);
              if (members.isEmpty()) {
                System.out.println("No members found with that name.");
                return;
            }
              System.out.println(">>> NAME SEARCH RESULTS <<<");
            for (Member member : members) {
                member.displayDetails();
            }
            System.out.println("<---------------------------->");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getMemberByIdMenu(Scanner scanner, MemberService memberService) {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine().trim();
        try {
                Member member = memberService.getMemberById(memberId);
                System.out.println(">>> MEMBER DETAILS <<<");
            member.displayDetails();
            System.out.println("<------------------------------->");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateMemberDetailsMenu(Scanner scanner, MemberService memberService) {
        System.out.print("Enter Member ID: ");
          String memberId = scanner.nextLine().trim();
        System.out.print("Enter new Member Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter new Phone Number: ");
          String phoneNumber = scanner.nextLine().trim();
        try {
            memberService.updateMemberDetails(memberId.toUpperCase(), name, phoneNumber);
            System.out.println("Member details updated successfully!");
           } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getAllMembersMenu(Scanner scanner,MemberService memberService){
        List<Member> members = memberService.getAllMembers();
        if(members.isEmpty()){
            System.out.println("No members found!!!");
        }
        System.out.println(">>>ALL MEMBERS DETAILS <<<");
        for(Member member : members){
            member.displayDetails();
            System.out.println();
        }
        System.out.println("<--------------------------------->");
    }

}
