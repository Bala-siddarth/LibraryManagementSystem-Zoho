package services;

import domains.Member;
import memory.InMemoryDataStore;
import java.util.*;

public class MemberService {

    private final InMemoryDataStore dataStore;

    public MemberService(InMemoryDataStore dataStore) {

        this.dataStore = dataStore;
    }

    public void registerMember(Member member){
        if(member == null){
            throw new IllegalArgumentException("Member cannot be NULL !!!");
        }
        for (Member existingMember : dataStore.getMembers().values()) {
            if (existingMember.getPhoneNumber().equals(member.getPhoneNumber())) {
                throw new IllegalArgumentException("Phone number already registered.");
            }
        }
        dataStore.getMembers().put(member.getMemberId(), member);
        System.out.println(">>> ADDED MEMBER DETAILS <<<");
        member.displayDetails();
        System.out.println("<---------------------------->");
    }

    public Member getMemberById(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }
        Member member = dataStore.getMembers().get(memberId.toUpperCase().trim());
        if (member == null) {
            throw new IllegalArgumentException("Memeber not found.");
        }
        return member;
    }

    public List<Member>  getAllMembers(){
        return new ArrayList<>(dataStore.getMembers().values());
    }

    public void updateMemberDetails(String memberId, String name, String phoneNumber) {
        Member member = getMemberById(memberId.toUpperCase());
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty.");
        }
        if (phoneNumber == null || phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Phone number must contain exactly 10 digits.");
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            char digit = phoneNumber.charAt(i);
            if (digit < '0' || digit > '9') {
                throw new IllegalArgumentException("Phone number must contain only digits.");
            }
        }
        for (Member existingMember : dataStore.getMembers().values()) {
            if (!existingMember.getMemberId().equals(memberId.toUpperCase().trim()) && existingMember.getPhoneNumber().equals(phoneNumber)) {
                throw new IllegalArgumentException("Phone number already registered.");
            }
        }
        member.setName(name);
        member.setPhoneNumber(phoneNumber);
    }

    public List<Member> searchMembersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Search name cannot be empty."
            );
        }
        List<Member> results = new ArrayList<>();
        String searchName = name.trim().toLowerCase();
        for (Member member : dataStore.getMembers().values()) {
            if (member.getName().toLowerCase().contains(searchName)) {
                results.add(member);
            }
        }
        return results;
    }

}
